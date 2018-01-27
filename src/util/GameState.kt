package util

import com.google.gson.Gson
import gameObjects.BoeseKugel
import gameObjects.GuteKugel
import gameObjects.Score
import java.util.*

data class GameState(var kugeln: Array<GuteKugel>, var players: Array<BoeseKugel>, var score: Array<Score>) {
    fun update(frame:Int){
        ServerNetworkManager.sendToAll(Packet<GameState>(type = ActionEnum.GAMESTATE, content = this))
    }
}

class Movement(var direction:Vector2D)

class Packet<T>(val type: ActionEnum, val time:Long = System.currentTimeMillis(), sender: UUID = User.uuid, val content:T) {
    fun serialize(): String {
        return Gson().toJson(this)
    }
}

enum class ActionEnum{
    MOVEMENT, CONNECT, GAMESTATE
}