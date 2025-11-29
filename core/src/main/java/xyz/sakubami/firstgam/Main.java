package xyz.sakubami.firstgam;

import com.badlogic.gdx.Game;
import xyz.sakubami.firstgam.objects.ObjectRegistry;
import xyz.sakubami.firstgam.screens.TitleScreen;

public class Main extends Game {

    @Override
    public void create() {
        ObjectRegistry.init();
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
