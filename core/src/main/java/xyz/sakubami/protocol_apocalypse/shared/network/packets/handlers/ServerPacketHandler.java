package xyz.sakubami.protocol_apocalypse.shared.network.packets.handlers;

import xyz.sakubami.protocol_apocalypse.server.logic.worlds.World;
import xyz.sakubami.protocol_apocalypse.shared.network.Packet;

public record ServerPacketHandler(World world) implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        packet.execute(this);
    }
}
