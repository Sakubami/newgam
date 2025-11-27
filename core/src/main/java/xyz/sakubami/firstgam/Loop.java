package xyz.sakubami.firstgam;

import xyz.sakubami.firstgam.world.WorldManager;

public class Loop {
    private static final float TICK_RATE = 1f / 30f;
    private double accumulator = 0;

    public void update(float deltaTime) {
        accumulator += deltaTime;

        while (accumulator >= TICK_RATE) {
            tick();
            accumulator -= TICK_RATE;
        }
    }

    private void tick() {
        // Everything that should update every tick
        //updatePlayer();
        //updateEnemies();
        //updateWorld();
        WorldManager.get().getCurrentWorld().tick();
    }
}
