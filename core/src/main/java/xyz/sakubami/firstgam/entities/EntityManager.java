package xyz.sakubami.firstgam.entities;

import xyz.sakubami.firstgam.entities.livingentity.Player;

import java.util.*;

public class EntityManager {
    private final Map<UUID, Entity> loadedEntities;

    public EntityManager() {
        this.loadedEntities = new HashMap<>();
    }

    public void update(float deltaT) {

    }

    public Map<UUID, Entity> getLoadedEntities() {
        return this.loadedEntities;
    }

    public void updateLoadedEntity(Entity entity) {
        this.loadedEntities.replace(entity.getUuid(), entity);
    }

    public void loadEntitiesAroundPlayers(Player player) {

    }
}
