package me.balda.strikekitsregen.events;

import me.balda.strikekitsregen.StrikeKitsRegen;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {
    StrikeKitsRegen plugin;
    public DamageEvent(StrikeKitsRegen plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void ondamage(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof  Player){
            Player victim = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            plugin.incombat.put(victim,damager);
        }
    }
}
