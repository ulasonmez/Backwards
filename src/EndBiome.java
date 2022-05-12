import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class EndBiome implements Listener{

	private Main plugin;
	public EndBiome(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	List<Material> ironMats = List.of(Material.IRON_SWORD,Material.IRON_PICKAXE,Material.IRON_AXE,Material.IRON_SHOVEL,
			Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,Material.IRON_BOOTS);

	List<Material> diaMats = List.of(Material.DIAMOND_SWORD,Material.DIAMOND_PICKAXE,Material.DIAMOND_AXE,Material.DIAMOND_SHOVEL,
			Material.DIAMOND_HELMET,Material.DIAMOND_CHESTPLATE,Material.DIAMOND_LEGGINGS,Material.DIAMOND_BOOTS);

	List<Material> leatherMats = List.of(Material.WOODEN_SWORD,Material.WOODEN_PICKAXE,Material.WOODEN_AXE,Material.WOODEN_SHOVEL,
			Material.LEATHER_HELMET,Material.LEATHER_CHESTPLATE,Material.LEATHER_LEGGINGS,Material.LEATHER_BOOTS);

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(!p.getWorld().equals(Bukkit.getWorlds().get(2)))return;
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getClickedBlock().getType().equals(Material.CHEST)){
				new BukkitRunnable() {
					@Override
					public void run() {
						Chest c = (Chest) e.getClickedBlock().getState();
						for(int x = 0; x<27;x++){
							ItemStack i = c.getInventory().getItem(x);
							if(i!=null) {
								if(ironMats.contains(i.getType())) {
									ItemMeta meta = i.getItemMeta();
									ItemStack a = new ItemStack(leatherMats.get(ironMats.indexOf(i.getType())));
									a.setItemMeta(meta);
									c.getBlockInventory().setItem(x, a);
								}
								if(diaMats.contains(i.getType())) {
									ItemMeta meta = i.getItemMeta();
									ItemStack a = new ItemStack(leatherMats.get(diaMats.indexOf(i.getType())));
									a.setItemMeta(meta);
									c.getBlockInventory().setItem(x, a);
								}
							}
						}
					}
				}.runTask(plugin);
			}
		}
	}
	@EventHandler
	public void tptest(PlayerChangedWorldEvent event){
		Player p = event.getPlayer();
		if(deaths.contains(p))return;
		if(event.getFrom().equals(Bukkit.getWorlds().get(2))) {
			if(p.getWorld().equals(Bukkit.getWorlds().get(0))) {
				new BukkitRunnable() {
					@Override
					public void run() {
						p.teleport(plugin.netherStrongholdLocation);
						p.sendMessage(p.getName()+" has made the advencement "+ChatColor.GREEN+"[Back home... Almost]");
					}
				}.runTaskLater(plugin,1);
			}
		}
	}
	ArrayList<Player> deaths = new ArrayList<>();
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		deaths.add(p);
	}
	@EventHandler
	public void onSpawn(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		if(deaths.contains(p)) {
			new BukkitRunnable() {
				@Override
				public void run() {
					p.teleport(plugin.endSpawnLocation);
					if(deaths.contains(p)) {
						deaths.remove(p);
					}
				}
			}.runTaskLater(plugin, 1);

		}
	}
}
