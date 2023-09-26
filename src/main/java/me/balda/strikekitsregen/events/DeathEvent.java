package me.balda.strikekitsregen.events;

import ga.strikepractice.StrikePractice;
import ga.strikepractice.api.StrikePracticeAPI;
import ga.strikepractice.battlekit.BattleKit;
import me.balda.strikekitsregen.StrikeKitsRegen;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    StrikeKitsRegen plugin;
    public DeathEvent(StrikeKitsRegen plugin) {
        this.plugin = plugin;
    }
    StrikePracticeAPI api = StrikePractice.getAPI();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player victim = e.getEntity();
        Player killer = victim.getKiller();
        Player damager = plugin.incombat.get(victim);
        if (killer != null && api.getFight(killer) != null) {
            givekit(killer);
        }else if(api.getFight(damager) != null){
            givekit(damager);
        }
        plugin.incombat.remove(victim);
    }

    public void givekit(Player aggressor){
        if(api.getFight(aggressor).getArena().isFFA()){
            BattleKit kit = api.getFight(aggressor).getKit();
            if (kit != null) {
                if(plugin.getConfig().getBoolean("feed_on_ffa_kill")){
                    aggressor.setFoodLevel(20);
                }
                if(plugin.getConfig().getBoolean("heal_on_ffa_kill")){
                    aggressor.setHealth(20);
                }
                if(plugin.getConfig().getBoolean("restore_kit_on_ffa_kill")){
                    BattleKit lastkit = api.getLastSelectedEditedKit(aggressor);
                    if (lastkit != null) {
                        kit.giveKitStuff(aggressor, lastkit);
                    } else {
                        String kitname = kit.getName();
                        BattleKit kitdefault = api.getKit(kitname);
                        kit.giveKitStuff(aggressor, kitdefault);
                    }
                }
            }
        }
    }
}
