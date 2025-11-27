package xyz.sakubami.firstgam.textures.objects;

public enum TallObjectID {
    LANTERN(0),
    TREE_0(1),
    TREE_1(2),
    PAINTING_0(3),
    CHAIR(4);

    private final int id;

    TallObjectID(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }
}
