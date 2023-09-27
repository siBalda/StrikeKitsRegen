package me.balda.strikekitsregen;

import me.balda.strikekitsregen.events.DamageEvent;
import me.balda.strikekitsregen.events.DeathEvent;
import me.balda.strikekitsregen.events.QuitEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class StrikeKitsRegen extends JavaPlugin {
    public Map<Player, Player> incombat= new HashMap<>();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathEvent(this),this);
        getServer().getPluginManager().registerEvents(new DamageEvent(this),this);
        getServer().getPluginManager().registerEvents(new QuitEvent(this),this);
        incombat.clear();
    }
}
