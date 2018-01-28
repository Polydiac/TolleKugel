package util

import com.google.gson.Gson
import gameObjects.BoeseKugel
import gameObjects.GuteKugelImpl
import gameObjects.ScoreImpl
import java.util.*

data class GameState(var kugeln: Array<GuteKugelImpl>, var players: Array<BoeseKugel>, var score: Array<ScoreImpl>) {
    fun update(frame:Int){
        ServerNetworkManager.sendToAll(Packet<GameState>(type = ActionEnum.GAMESTATE, content = this))
    }
}

class Movement(var direction:Vector2D)

data class Packet<T>(val type: ActionEnum, val time:Long = System.currentTimeMillis(), val sender: UUID = User.uuid, val content:T) {
    fun serialize(): String {
        return Gson().toJson(this)
    }
}

enum class ActionEnum{
    MOVEMENT, CONNECT, GAMESTATE
}