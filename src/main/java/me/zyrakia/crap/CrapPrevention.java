package me.zyrakia.crap;

import java.io.InputStream;

import org.bukkit.plugin.java.JavaPlugin;

import de.leonhard.storage.Yaml;
import me.zyrakia.crap.commands.Crap;

public class CrapPrevention extends JavaPlugin {

    private static CrapPrevention instance;
    private static Yaml config;

    @Override
    public void onEnable() {
        CrapPrevention.instance = this;

        if (!setupConfig()) {
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

    public static CrapPrevention getInstance() {
        return instance;
    }

    public Yaml getConf() {
        return config;
    }

}
