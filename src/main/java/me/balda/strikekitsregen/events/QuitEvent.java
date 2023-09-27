package me.balda.strikekitsregen.events;

import me.balda.strikekitsregen.StrikeKitsRegen;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    StrikeKitsRegen plugin;
    public QuitEvent(StrikeKitsRegen plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        plugin.incombat.remove(e.getPlayer());
    }
}
