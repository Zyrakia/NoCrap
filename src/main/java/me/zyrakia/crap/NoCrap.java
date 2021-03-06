package me.zyrakia.crap;

import java.io.InputStream;
import java.nio.file.Paths;

import org.bukkit.plugin.java.JavaPlugin;

import de.leonhard.storage.Json;
import de.leonhard.storage.Yaml;
import me.zyrakia.crap.commands.Crap;

public class NoCrap extends JavaPlugin {

    private static NoCrap instance;
    private static Yaml config;
    private static Json crapData;

    @Override
    public void onEnable() {
        NoCrap.instance = this;

        if (!setupConfig()) {
            getLogger().severe("Configuration defaults resource missing, disabling plugin.");
            disable();
            return;
        }

        setupCommands();
    }

    @Override
    public void onDisable() {
    }

    private boolean setupConfig() {
        InputStream configDefaults = getResource("config.yml");
        if (configDefaults == null)
            return false;

        config = new Yaml("config", getDataFolder().getPath(), configDefaults);
        crapData = new Json("crap", Paths.get(getDataFolder().getPath(), "data").toString());

        return true;
    }

    private void setupCommands() {
        Crap crap = new Crap();
        getCommand("crap").setExecutor(crap);
        getCommand("crap").setTabCompleter(crap);
    }

    private void disable() {
        getServer().getPluginManager().disablePlugin(this);
    }

    public static NoCrap getInstance() {
        return instance;
    }

    public Yaml getConf() {
        return config;
    }

    public Json getCrapData() {
        return crapData;
    }

}
