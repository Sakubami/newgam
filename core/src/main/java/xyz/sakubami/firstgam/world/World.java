package xyz.sakubami.firstgam.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import xyz.sakubami.firstgam.chunks.ChunkManager;
import xyz.sakubami.firstgam.chunks.WorldGenerator;
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
    private WorldGenerator generator;
    private ChunkRenderer chunkRenderer;
    private EntityRenderer entityRenderer;
    private final SpriteBatch batch;

    private final int TILE_SIZE = 32;
    private final int MAP_HEIGHT = 20;
    private final int MAP_WIDTH = 20;

    private final float CAMERA_SPEED = 200f;

    public World(String name) {
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
        chunkRenderer = new ChunkRenderer(batch, chunkManager, TILE_SIZE);
        entityRenderer = new EntityRenderer(batch, chunkManager, TILE_SIZE);
    }

    public void tick() {
        chunkManager.update();
        Player player = this.onlinePlayers.get(0);
        float x = player.getX();
        player.setX(x += 3);
        System.out.println("x: " + this.onlinePlayers.get(0).getTilePos().x() + " y: " + this.onlinePlayers.get(0).getTilePos().y());
    }

    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            shutdown();
        }

        float speed = 1000 * Gdx.graphics.getDeltaTime(); // pixels per second

        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.position.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.position.y -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.position.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.position.x += speed;

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) camera.zoom -= Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.E)) camera.zoom += Gdx.graphics.getDeltaTime();

        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 4f);

        camera.position.x = Math.round(camera.position.x);
        camera.position.y = Math.round(camera.position.y);

        camera.update();

        ScreenUtils.clear(0, 0, 0, 1); // clear to black
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        chunkRenderer.render();
        batch.end();
    }

    public void shutdown() {
        chunkManager.shutdown();
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

    public void connectPlayer(Player player) { this.onlinePlayers.add(player); }

    public WorldGenerator getGenerator() { return this.generator; }

    public String getName() { return this.name; }

}
