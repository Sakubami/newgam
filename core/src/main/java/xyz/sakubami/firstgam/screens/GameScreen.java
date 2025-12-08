package xyz.sakubami.firstgam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import xyz.sakubami.firstgam.Loop;
import xyz.sakubami.firstgam.Main;
import xyz.sakubami.firstgam.world.World;
import xyz.sakubami.firstgam.world.WorldManager;

public class GameScreen implements Screen {
    private final Main game;
    private final Loop gameLoop;
    private final World loadedWorld;

    public GameScreen(Main game) {
        Gdx.graphics.setForegroundFPS(60);

        this.game = game;
        this.gameLoop = new Loop();
        this.loadedWorld = WorldManager.get().getCurrentWorld();
    }

    @Override
    public void resize(int width, int height) {
        loadedWorld.resize(width, height, true);
    }

    @Override
    public void render(float v) {
        gameLoop.update(Gdx.graphics.getDeltaTime());
        loadedWorld.render();
    }

    @Override public void dispose() {
        WorldManager.get().shutdown();
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void show() {}
}
