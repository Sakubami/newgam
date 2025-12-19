package xyz.sakubami.protocol_apocalypse.server;

import xyz.sakubami.protocol_apocalypse.server.logic.worlds.World;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity.Player;
import xyz.sakubami.protocol_apocalypse.server.saving.Saviour;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.WorldManager;
import xyz.sakubami.protocol_apocalypse.shared.network.Connection;
import xyz.sakubami.protocol_apocalypse.shared.network.Packet;
import xyz.sakubami.protocol_apocalypse.shared.network.packets.handlers.ServerPacketHandler;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2f;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private final List<Connection> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    private volatile boolean running = true;
    private World world;

    private final Map<Connection, Player> connectedPlayers = new HashMap<>();
    private final Map<UUID, Player> onlinePlayers = new HashMap<>();
    private final Map<UUID, Player> offlinePlayers = new HashMap<>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        init();

        new Thread(() -> {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    Connection connection = new Connection(clientSocket);
                    clients.add(connection);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (running) {
                tick();
                try {
                    Thread.sleep(33); // ~30 ticks per second
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
    }

    private void init() {
        Player player = new Player();
        this.onlinePlayers.put(player.getUuid(), player);
    }

    private void tick() {
        for (Connection c : clients) {
            c.tick(new ServerPacketHandler(world)); // read & execute packets
        }

        world.tick();
        Saviour.get().tick();

        Player player = this.onlinePlayers.get(UUID.fromString("sakubami"));
        Vector2f move = new Vector2f(20, 0);
        player.setPos(player.getPos().add(move));
        System.out.println("x: " + this.onlinePlayers.get(UUID.fromString("sakubami")).getTilePos().x() + " y: " + this.onlinePlayers.get(UUID.fromString("sakubami")).getTilePos().y());
    }

    public void setupWorld(String name) {
        this.world = WorldManager.get().selectWorld(name);
    }

    public void broadcast(Packet packet) {
        for (Connection c : clients) {
            c.send(packet);
        }
    }

    public void stop() throws IOException {
        this.running = false;
        for (Connection c : clients) {
            c.disconnect();
        }
        serverSocket.close();
    }

    public World getWorld() { return world; }
    public Map<UUID, Player> getOnlinePlayers() { return this.onlinePlayers; }
    public Map<UUID, Player> getOfflinePlayers() { return this.offlinePlayers; }
    public void updatePlayer(Player player) { this.onlinePlayers.replace(player.getUuid(), player); }
    public void connectPlayer(Player player) { this.onlinePlayers.put(player.getUuid(), player); }
    public void disconnectPlayer(UUID uuid) { this.onlinePlayers.remove(uuid); }
    public Player getOnlinePlayer(UUID uuid) { return this.onlinePlayers.get(uuid); }
    public Player getOfflinePlayer(UUID uuid) { return this.offlinePlayers.get(uuid); }
}
