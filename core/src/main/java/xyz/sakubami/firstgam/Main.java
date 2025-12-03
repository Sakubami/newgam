package xyz.sakubami.firstgam;

import com.badlogic.gdx.Game;
import com.google.gson.Gson;
import xyz.sakubami.firstgam.chunks.Chunk;
import xyz.sakubami.firstgam.chunks.WorldGenerator;
import xyz.sakubami.firstgam.entities.livingentity.Player;
import xyz.sakubami.firstgam.objects.ObjectRegistry;
import xyz.sakubami.firstgam.objects.container.interfaces.Chest;
import xyz.sakubami.firstgam.screens.TitleScreen;
import xyz.sakubami.firstgam.utils.Vector2i;
import xyz.sakubami.firstgam.world.WorldManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.UUID;

public class Main extends Game {

    @Override
    public void create() {
        ObjectRegistry.init();
        setScreen(new TitleScreen(this));
        WorldManager.init();
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
