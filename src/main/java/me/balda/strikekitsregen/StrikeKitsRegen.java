package me.balda.strikekitsregen;

import me.balda.strikekitsregen.events.DeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrikeKitsRegen extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathEvent(this),this);
    }
}
