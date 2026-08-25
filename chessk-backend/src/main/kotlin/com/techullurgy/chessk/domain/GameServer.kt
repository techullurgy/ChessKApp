package com.techullurgy.chessk.domain

import com.techullurgy.chessk.shared.models.GameRoomShared
import org.springframework.web.socket.WebSocketSession
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class GameServer {
    private val userSessions = ConcurrentHashMap<String, WebSocketSession>()

    private val rooms = ConcurrentHashMap<String, Room>()

    fun createRoom(model: GameRoomShared): GameRoomShared {
        val roomId = UUID.randomUUID().toString()
        val room = Room(roomId, model.roomName, model.roomDescription, model.createdBy)
        rooms[roomId] = room
        return room.toGameRoom()
    }

    fun deleteRoom(roomId: String) {
        rooms.remove(roomId)?.invalidateRoom()
    }

    fun getCreatedRoomsForUserId(userId: String): List<Room> {
        return rooms.values.filter { it.createdBy == userId }
    }

    fun createSessionForClientId(clientId: String, session: WebSocketSession) {
        userSessions[clientId] = session
    }

    fun disconnect(clientId: String) {
        userSessions.remove(clientId)
    }

    fun getJoinedRoomsForClientId(clientId: String): List<Room> {
        return rooms.values.filter { room -> room.getAssignedPlayers().firstOrNull { it.user.clientId == clientId } != null }
    }

    internal fun getRoomById(roomId: String): Room? = rooms[roomId]
    internal fun getSessionForClientId(clientId: String): WebSocketSession? = userSessions[clientId]
}