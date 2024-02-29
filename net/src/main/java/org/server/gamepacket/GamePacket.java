package org.server.gamepacket;

public abstract class GamePacket {

    private final int opcode;
    private final GamePacketBuffer buffer;

    protected GamePacket(int opcode, GamePacketBuffer buffer) {
        this.opcode = opcode;
        this.buffer = buffer;
    }
}
