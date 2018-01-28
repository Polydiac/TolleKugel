package util

import com.google.gson.Gson
import kotlinx.coroutines.experimental.*
import java.lang.reflect.Type
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

val GLOBALSOCKETOUT = 4568
val GLOBALSOCKETIN = 4569

object User {
    val uuid = UUID.randomUUID()
}

object ServerNetworkManager {
    val sockets: HashMap<Int, Connection> = HashMap<Int, Connection>()
    val gson = Gson()
    val socket = DatagramSocket(GLOBALSOCKETIN)
    val pooledActions = Stack<Packet<Movement>>()
    var accepting = true
    var currentMoves = HashMap<UUID, ArrayList<Movement>>()

    fun sendToAll(state: Packet<GameState>) {
        val msg = state.serialize()
        for ((_, con) in sockets){
            con.sendDatagram(msg)
        }
    }

    fun getMovementsForUser(uuid: UUID): ArrayList<Movement>? {
        return currentMoves[uuid]
    }

    fun dispatchActions() {
        val returnList = ArrayList<Packet<Movement>>()
        for (action in pooledActions) {
            returnList.add(action)
        }
        pooledActions.clear()
        currentMoves.clear()
        for(pak in returnList) {
            currentMoves.putIfAbsent(pak.sender, ArrayList())
            currentMoves[pak.sender]?.add(pak.content)
        }
    }

    fun registerReceivingSocket(con: Connection) : Int {
        sockets.put(con.hashCode(), con)
        return con.hashCode()
    }

    fun receiveUpdates(){
        async {
            while(accepting) {
                pooledActions.add(receivePacket(TypeToken<Packet<Movement>>.type()::class.java))
            }
        }
    }

    fun waitForConnections(){

    }

    fun receivePacket(expectType: Type): Packet<Movement>{
        val packet = DatagramPacket(ByteArray(32768), 32768)

        socket.receive(packet)
        val action = gson.fromJson<Packet<Movement>>(packet.data.toString(), expectType)

        return action
    }
}

object ClientNetworkManager{
    var serverCon = Connection()
    val gson = Gson()
    val states = Stack<GameState>()
    var running = true
    val socket = DatagramSocket(GLOBALSOCKETOUT)

    fun registerConnection(ipAdress: String){
        serverCon = Connection(ipAdress)
    }

    fun sendMovement(mov: Movement){
        val msg = gson.toJson(mov)
        serverCon.sendDatagram(msg)
    }

    fun receiveStates() {
        launch {
            while(running){
                states.add(receivePacket(TypeToken<Packet<GameState>>.type()::class.java))
            }
        }
    }

    fun receivePacket(expectType: Type): GameState{
        val socket = DatagramSocket(GLOBALSOCKETOUT)
        val packet = DatagramPacket(ByteArray(32768), 32768)

        socket.receive(packet)
        val state = gson.fromJson<Packet<GameState>>(packet.data.toString(), expectType)

        return state.content
    }

    fun getGameState(): GameState {
        val lastState = states.first()
        states.clear()
        return lastState
    }

}


class Connection constructor(val ipAdress: String = "0.0.0.0"){
    fun sendDatagram(msg: String) {
        val sock = DatagramSocket()
        sock.send(preparePackage(msg))
    }

    fun preparePackage(msg: String): DatagramPacket {
        val data = msg.toByteArray()
        val pkg = DatagramPacket(data, data.size, InetAddress.getByName(ipAdress), GLOBALSOCKETOUT)
        return pkg
    }

}
