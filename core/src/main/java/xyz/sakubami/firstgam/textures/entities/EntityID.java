package xyz.sakubami.firstgam.textures.entities;

public enum EntityID {
    SLIME(0);

    private final int id;

    EntityID(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }
}
