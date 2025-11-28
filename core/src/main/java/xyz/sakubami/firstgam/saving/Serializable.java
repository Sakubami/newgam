package xyz.sakubami.firstgam.saving;

public interface Serializable <T> {
    T toData();
    void fromData(T data);
}
