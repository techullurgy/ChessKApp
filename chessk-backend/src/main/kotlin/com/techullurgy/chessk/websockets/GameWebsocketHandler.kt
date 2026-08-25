package com.techullurgy.chessk.websockets

import com.techullurgy.chessk.domain.GameServer
import com.techullurgy.chessk.shared.events.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.json.Json
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors

@Configuration
@EnableWebSocket
class GameWebsocketConfiguration(
    private val handler: GameWebsocketHandler,
): WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(handler, "/game").setAllowedOrigins("*")
    }

    @Bean
    fun baseEventJson(): Json {
        return baseEventJson
    }
}

@Component
class GameWebsocketHandler(
    private val baseEventJson: Json,
    private val gameServer: GameServer
) : TextWebSocketHandler() {

    private val connections = ConcurrentHashMap<String, WebSocketSession>()

    val broadcaster = Channel<String>()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            broadcaster.receiveAsFlow()
                .collect { msg ->
                    connections.forEach {(_, v) ->
                        v.sendMessage(TextMessage(msg))
                    }
                }
        }
    }

    override fun handleTextMessage(
        session: WebSocketSession,
        message: TextMessage
    ) {
        when(val event = baseEventJson.decodeFromString<ClientToServerBaseEvent>(message.payload)) {
            is CellSelection -> {
                val room = gameServer.getRoomById(event.roomId) ?: return
                room.cellSelectedForMove(event)
            }
            is Disconnected -> TODO()
            is EnterRoomHandshake -> TODO()
            is PieceMove -> {
                val room = gameServer.getRoomById(event.roomId) ?: return
                room.movePiece(event)
            }
            is ResetSelection -> {
                val room = gameServer.getRoomById(roomId = event.roomId) ?: return
                room.resetSelection()
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)

        connections[session.id] = session
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)

        connections.remove(session.id)
    }
}

@Component
class TestWebsocketRunner(
    private val gameHandler: GameWebsocketHandler,
): ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        val executorDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

        CoroutineScope(executorDispatcher).launch {
            (1..1000).asFlow().onEach { delay(1000) }
                .onEach {
                    gameHandler.broadcaster.send("Item - $it")
                }
                .launchIn(this)
        }
    }
}