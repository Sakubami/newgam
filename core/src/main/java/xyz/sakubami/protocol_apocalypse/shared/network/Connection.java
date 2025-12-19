package xyz.sakubami.protocol_apocalypse.shared.network;

import xyz.sakubami.protocol_apocalypse.shared.network.packets.PacketType;
import xyz.sakubami.protocol_apocalypse.shared.network.packets.handlers.PacketHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final List<Packet> incoming = new ArrayList<>();

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public synchronized void send(Packet packet) {
        try {
            out.writeInt(packet.getId());  // ID comes directly from the packet
            packet.write(out);             // Write packet data
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
    }

    public void readPackets() {
        try {
            while (in.available() > 0) {
                int id = in.readInt();
                Packet packet = PacketType.getById(id).getPacket(); // Simple factory method
                if (packet == null) continue;

                packet.read(in);
                incoming.add(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }
    }

    public void tick(PacketHandler handler) {
        readPackets();
        for (Packet packet : incoming) {
            handler.handle(packet);
        }
        incoming.clear();
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException ignored) {}
    }

    /** Getters for input/output streams if needed */
    public DataInputStream getInputStream() { return in; }
    public DataOutputStream getOutputStream() { return out; }
}
