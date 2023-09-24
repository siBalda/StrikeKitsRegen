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
        Player killer = e.getEntity().getKiller();
        if (killer != null && api.getFight(killer) != null) {
            if(api.getFight(killer).getArena().isFFA()){
                BattleKit kit = api.getFight(killer).getKit();
                if (kit != null) {
                    if(plugin.getConfig().getBoolean("feed_on_ffa_kill")){
                        killer.setFoodLevel(20);
                    }
                    if(plugin.getConfig().getBoolean("heal_on_ffa_kill")){
                        killer.setHealth(20);
                    }
                    if(plugin.getConfig().getBoolean("restore_kit_on_ffa_kill")){
                        BattleKit lastkit = api.getLastSelectedEditedKit(killer);
                        if (lastkit != null) {
                            kit.giveKitStuff(killer, lastkit);
                        } else {
                            String kitname = kit.getName();
                            BattleKit kitdefault = api.getKit(kitname);
                            kit.giveKitStuff(killer, kitdefault);
                        }
                    }
                }
            }
        }
    }
}
