package xyz.sakubami.firstgam.textures.objects;

public enum ObjectID {
    CHEST(0),
    WORKBENCH(1);

    private final int id;

    ObjectID(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }
}
