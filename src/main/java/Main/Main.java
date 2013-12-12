package Main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
    FileConfiguration config;
    File cfile;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		 config = getConfig();
		
		this.getConfig().addDefault("AutoUpdater", true); 
		this.getConfig().addDefault("Delay", 10); 
		this.getConfig().addDefault("Messages", new String[] {
				"§7§l[§6DragonCast§7§l] §3This is the Default Config",
				"§7§l[§6DragonCast§7§l] §3If you see this, Please contact the Serveradmin",
		});
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		cfile = new File(getDataFolder(), "config.yml");
		Player[] ap = Bukkit.getOnlinePlayers();
		for(Player p : ap){
			int Timer = config.getInt("Delay");
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Messager(this ,p, config.getStringList("Messages")), 0L, Timer*20);
		}
		if(config.getBoolean("AutoUpdater") == true){
			try {
				new AutoUpdater(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void onDisable() {
		
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		int Timer = config.getInt("Delay");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Messager(this, e.getPlayer(), config.getStringList("Messages")), 0L, Timer*20);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Bukkit.getScheduler().cancelTasks(this);
		int Timer = config.getInt("Delay");
		Player[] ap = Bukkit.getOnlinePlayers();
		for(Player p : ap){
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Messager(this ,p, config.getStringList("Messages")), 0L, Timer*20);
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("dc")){
			if(args[0].equalsIgnoreCase("reload")){
				if(sender.hasPermission("dc.reload")){
				Bukkit.getScheduler().cancelTasks(this);
                config = YamlConfiguration.loadConfiguration(cfile);
                sender.sendMessage("§7§l[§6DragonCast§7§l] §aConfig Reloaded");
        		Player[] ap = Bukkit.getOnlinePlayers();
        		for(Player p : ap){
        			int Timer = config.getInt("Delay");
        			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Messager(this ,p, config.getStringList("Messages")), 0L, Timer*20);
        		}
        		return true;
			}
			}
			if(args[0].equalsIgnoreCase("off")){
				if(sender.hasPermission("dc.off")){
				Bukkit.getScheduler().cancelTasks(this);
                sender.sendMessage("§7§l[§6DragonCast§7§l] §aDeactivated");
        		return true;
			}
			}
			if(args[0].equalsIgnoreCase("on")){
				if(sender.hasPermission("dc.on")){
                config = YamlConfiguration.loadConfiguration(cfile);
                sender.sendMessage("§7§l[§6DragonCast§7§l] §aActivated");
        		Player[] ap = Bukkit.getOnlinePlayers();
        		for(Player p : ap){
        			int Timer = config.getInt("Delay");
        			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Messager(this ,p, config.getStringList("Messages")), 0L, Timer*20);
        		}
        		return true;
			}
			}
		}
		return false;
		
	}
}
