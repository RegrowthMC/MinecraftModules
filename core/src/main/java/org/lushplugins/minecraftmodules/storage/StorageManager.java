package org.lushplugins.minecraftmodules.storage;

import com.google.gson.JsonObject;
import org.bukkit.configuration.file.FileConfiguration;
import org.lushplugins.minecraftmodules.MinecraftModulesPlugin;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class StorageManager {
    private final ExecutorService threads = Executors.newFixedThreadPool(1);
    private Storage storage;

    public StorageManager() {
        reload();
    }

    public void reload() {
        disable();

        MinecraftModulesPlugin.getInstance().saveDefaultResource("storage.yml");
        FileConfiguration config = MinecraftModulesPlugin.getInstance().getConfigResource("storage.yml");

        String storageType = config.getString("type", "null");
        switch (storageType) {
            case "mysql", "mariadb" -> storage = new MySQLStorage();
            case "sqlite" -> storage = new SQLiteStorage();
            default -> {
                storage = new SQLiteStorage();
                MinecraftModulesPlugin.getInstance().getLogger().severe("'" + storageType + "' is not a valid storage type, defaulting to SQLite.");
            }
        }

        boolean outdated = config.contains("mysql");
        if (outdated) {
            MinecraftModulesPlugin.getInstance().getLogger().warning("Deprecated: The 'mysql' section in the storage.yml has been renamed to 'storage'");
        }

        MinecraftModulesPlugin.getInstance().getLogger().info("Setting up '" + storageType +"' database");
        storage.enable(config.getConfigurationSection("storage"));
    }

    public void disable() {
        if (storage != null) {
            storage.disable();
        }
    }

    public CompletableFuture<JsonObject> loadSMPUserJson(UUID uuid) {
        return runAsync(() -> storage.loadSMPUserJson(uuid));
    }

    public CompletableFuture<Void> saveSMPUserJson(UUID uuid, JsonObject json) {
        return runAsync(() -> storage.saveSMPUserJson(uuid, json));
    }

    private <T> CompletableFuture<T> runAsync(Callable<T> callable) {
        CompletableFuture<T> future = new CompletableFuture<>();

        threads.submit(() -> {
            try {
                future.complete(callable.call());
            } catch (Throwable e) {
                MinecraftModulesPlugin.getInstance().getLogger().log(Level.WARNING, "Caught error when running storage method: ", e);
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    private CompletableFuture<Void> runAsync(Runnable runnable) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        threads.submit(() -> {
            try {
                runnable.run();
                future.complete(null);
            } catch (Throwable e) {
                MinecraftModulesPlugin.getInstance().getLogger().log(Level.WARNING, "Caught error when running storage method: ", e);
                future.completeExceptionally(e);
            }
        });

        return future;
    }
}
