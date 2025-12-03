package xyz.sakubami.firstgam.utils;

public record Vector2i(int x, int y) {

    public static Vector2i fromString(String v) {
        String[] split = v.split("%");
        return new Vector2i(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public static String toString(Vector2i v) {
        return v.x + "%" + v.y;
    }
}

