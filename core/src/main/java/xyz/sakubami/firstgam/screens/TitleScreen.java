package xyz.sakubami.firstgam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import xyz.sakubami.firstgam.Loop;
import xyz.sakubami.firstgam.Main;
import xyz.sakubami.firstgam.world.World;
import xyz.sakubami.firstgam.world.WorldManager;

public class TitleScreen implements Screen {
    private final Main game;
    private final SpriteBatch batch;
    private final BitmapFont font;

    public TitleScreen(Main game) {
        Gdx.graphics.setForegroundFPS(60);
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "MY AWESOME GAME",
            0, Gdx.graphics.getHeight() / 2f + 50,
            Gdx.graphics.getWidth(), Align.center, false);

        font.draw(batch, "Press ENTER to Start",
            0, Gdx.graphics.getHeight() / 2f - 50,
            Gdx.graphics.getWidth(), Align.center, false);

        batch.end();
        // test lol

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            WorldManager.get().loadWorld();
            game.setScreen(new GameScreen(game)); // Change to your actual game screen
        }
    }

    @Override public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void show() {}
}
