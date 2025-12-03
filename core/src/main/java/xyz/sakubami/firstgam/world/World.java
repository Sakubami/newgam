package xyz.sakubami.firstgam.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import xyz.sakubami.firstgam.chunks.ChunkManager;
import xyz.sakubami.firstgam.chunks.WorldGenerator;
import xyz.sakubami.firstgam.entities.EntityManager;
import xyz.sakubami.firstgam.entities.livingentity.Player;
import xyz.sakubami.firstgam.rendering.ChunkRenderer;
import xyz.sakubami.firstgam.rendering.EntityRenderer;

import java.util.*;

public class World {
    private final List<Player> onlinePlayers;
    private final List<Player> offlinePlayers;
    private final long seed;
    private final UUID uuid;
    private final String name;

    private final OrthographicCamera camera;
    private final ScreenViewport viewport;
    private ChunkManager chunkManager;
    private EntityManager entityManager;
    private WorldGenerator generator;
    private ChunkRenderer chunkRenderer;
    private EntityRenderer entityRenderer;
    private final SpriteBatch batch;

    private final int TILE_SIZE = 32;
    private final int MAP_HEIGHT = 20;
    private final int MAP_WIDTH = 20;

    private final float CAMERA_SPEED = 200f;

    public World(String name) {
        System.out.println("WORLDINIT: " + System.currentTimeMillis());
        seed = System.currentTimeMillis();
        uuid = UUID.randomUUID();

        this.name = name;

        onlinePlayers = new ArrayList<>();
        offlinePlayers = new ArrayList<>();



        camera = new OrthographicCamera();
        batch = new SpriteBatch();


        viewport = new ScreenViewport(camera); // 1 world unit = 1 pixel
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void init() {
        generator = new WorldGenerator(seed);
        chunkManager = new ChunkManager(generator);
        entityManager = new EntityManager();
        chunkRenderer = new ChunkRenderer(batch, chunkManager, TILE_SIZE);
        entityRenderer = new EntityRenderer(batch, chunkManager, TILE_SIZE);
    }

    public void tick() {
        chunkManager.update();
    }

    public void render() {
        ScreenUtils.clear(0, 0, 0, 1); // clear to black
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        chunkRenderer.render();
        batch.end();
    }

    public void resize(int width, int height, boolean centerCamera) {
        viewport.update(width, height, centerCamera);
    }

    public UUID getUuid() { return this.uuid; }

    public List<Player> getOnlinePlayers() {
        return this.onlinePlayers;
    }

    public List<Player> getOfflinePlayers() {
        return this.offlinePlayers;
    }

    public Player getOnlinePlayer(UUID uuid) {
        return this.onlinePlayers.stream().filter(player -> player.getUuid().equals(uuid)).findFirst().get();
    }

    public Player getOfflinePlayer(UUID uuid) {
        return this.offlinePlayers.stream().filter(player -> player.getUuid().equals(uuid)).findFirst().get();
    }

    public int getTileSize() { return TILE_SIZE; }

    public void connectPlayer(Player player) { this.onlinePlayers.put(player.getUuid(), player); }

    public WorldGenerator getGenerator() { return this.generator; }
}
