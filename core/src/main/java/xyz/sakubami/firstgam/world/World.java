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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class World {
    private final Map<UUID, Player> onlinePlayers;
    private final Map<UUID, Player> offlinePlayers;
    private final long seed;
    private final UUID uuid;

    private final OrthographicCamera camera;
    private final ScreenViewport viewport;
    private final ChunkManager chunkManager;
    private final EntityManager entityManager;
    private final WorldGenerator generator;
    private final ChunkRenderer chunkRenderer;
    private final EntityRenderer entityRenderer;
    private final SpriteBatch batch;

    private final int TILE_SIZE = 32;
    private final int MAP_HEIGHT = 20;
    private final int MAP_WIDTH = 20;

    private final float CAMERA_SPEED = 200f;

    public World() {
        seed = System.currentTimeMillis();
        uuid = UUID.randomUUID();

        onlinePlayers = new HashMap<>();
        offlinePlayers = new HashMap<>();

        generator = new WorldGenerator(seed);

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        chunkManager = new ChunkManager(generator);
        entityManager = new EntityManager();
        chunkRenderer = new ChunkRenderer(batch, chunkManager, TILE_SIZE);
        entityRenderer = new EntityRenderer(batch, chunkManager, TILE_SIZE);

        viewport = new ScreenViewport(camera); // 1 world unit = 1 pixel
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void tick() {
        System.out.println("test" + System.currentTimeMillis());
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

    public Map<UUID, Player> getOnlinePlayers() {
        return this.onlinePlayers;
    }

    public Map<UUID, Player> getOfflinePlayers() {
        return this.offlinePlayers;
    }

    public Player getOnlinePlayer(UUID uuid) {
        return this.onlinePlayers.get(uuid);
    }

    public Player getOfflinePlayer(UUID uuid) {
        return this.offlinePlayers.get(uuid);
    }
}
