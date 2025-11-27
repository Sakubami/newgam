package xyz.sakubami.firstgam.textures.entities;

public enum TallEntityID {
    PLAYER(0);

    private final int id;

    TallEntityID(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }
}
