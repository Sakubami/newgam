package xyz.sakubami.protocol_apocalypse.server.saving;

import com.google.gson.Gson;
import xyz.sakubami.protocol_apocalypse.server.saving.data.Serialized;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.WorldManager;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Saviour {
    private static Saviour instance;
    public static void init() { instance = new Saviour(); }
    public static Saviour get() { return instance; }

    private final Gson gson;
    private final Queue<Serialized<?>> saveQueue;

    public Saviour() {
        this.gson = new Gson();
        this.saveQueue = new LinkedList<>();
    }

    public <T, S extends Serialized<T>> T loadObject(String path, Class<S> dataClass) {
        try(Reader reader = new FileReader(path)) {
            S object = gson.fromJson(reader, dataClass);
            return object.createObject();
        } catch (IOException ignored) {}
        return null;
    }

    public <T extends Serialized<?>> T loadData(String path, Class<T> dataClass) {
        try(Reader reader = new FileReader(path)) {
            return gson.fromJson(reader, dataClass);
        } catch (IOException ignored) {}
        return null;
    }


    public void saveWorldData(Serialized<?> data) {
        this.saveQueue.add(data);
    }

    public void saveData(String path, Serialized<?> data) {
        try(Writer writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        String worldPath = WorldManager.get().getCurrentWorld().getDirectory();

        while (!saveQueue.isEmpty()) {
            Serialized<?> data = saveQueue.poll();
            try(Writer writer = new FileWriter(worldPath + "/" + data.getPath())) {
                gson.toJson(data, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
