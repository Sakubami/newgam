package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.world.WorldManager;

public class Saviour {
    private String path;

    public Saviour() {

    }

    // do some saving logic here, also loading ofc

    public void init() {
        this.path = WorldManager.get().getCurrentWorld().getName();
    }
}
