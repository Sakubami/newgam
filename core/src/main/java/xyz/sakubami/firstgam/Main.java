package xyz.sakubami.firstgam;

import com.badlogic.gdx.Game;
import xyz.sakubami.firstgam.screens.GameScreen;
import xyz.sakubami.firstgam.screens.TitleScreen;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.textures.items.ItemTexture;
import xyz.sakubami.firstgam.world.World;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new TitleScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
