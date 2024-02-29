package org.server.gamepacket;

public abstract class GamePacket {

    private final int opcode;
    private final RSBuffer buffer;

    protected GamePacket(int opcode, RSBuffer buffer) {
        this.opcode = opcode;
        this.buffer = buffer;
    }
}
