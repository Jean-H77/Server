package org.server.packet;

public abstract class Packet {

    private final int opcode;
    private final RSBuffer buffer;

    protected Packet(int opcode, RSBuffer buffer) {
        this.opcode = opcode;
        this.buffer = buffer;
    }
}
