package xyz.sakubami.protocol_apocalypse.shared.network.packets;

import xyz.sakubami.protocol_apocalypse.shared.network.Packet;
import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.GameState;
import xyz.sakubami.protocol_apocalypse.shared.network.packets.handlers.ClientPacketHandler;
import xyz.sakubami.protocol_apocalypse.shared.network.packets.handlers.PacketHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class S2CGameStatePacket implements Packet {
    private GameState state;

    public S2CGameStatePacket() {}

    public S2CGameStatePacket(GameState state) {
        this.state = state;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {

    }

    @Override
    public void read(DataInputStream in) throws IOException {

    }

    @Override
    public void execute(PacketHandler handler) {
        if (!(handler instanceof ClientPacketHandler))
            return;
        ((ClientPacketHandler) handler).client().applyState(state);
    }

    @Override
    public int getId() {
        return 3;
    }
}
