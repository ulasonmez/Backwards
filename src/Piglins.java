import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Piglins implements Listener{

	private Main plugin;
	public Piglins(Main plugin) {
		this.plugin=plugin;
	}
	ArrayList<Piglin> tradingPiglins = new ArrayList<>();
	Items item = new Items();
	@EventHandler
	public void onBreak(PlayerDropItemEvent event) {
		Player p = event.getPlayer();
		if(!p.getWorld().equals(Bukkit.getWorlds().get(1)))return;
		if(!event.getItemDrop().getItemStack().getType().isBlock())return;
		Piglin piglin = nearestPiglin(event.getItemDrop());
		if(piglin==null)return;
		if(tradingPiglins.contains(piglin))return;
		tradingPiglins.add(piglin);
		new BukkitRunnable() {
			@Override
			public void run() {
				piglin.getEquipment().setItemInMainHand(event.getItemDrop().getItemStack());
				event.getItemDrop().remove();
			}
		}.runTaskLater(plugin, 20);
		new BukkitRunnable() {
			@Override
			public void run() {
				if(piglin.getEquipment().getItemInMainHand()!=null) {
					if(piglin.getEquipment().getItemInMainHand().getType().isBlock()) {
						piglin.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
						piglin.getWorld().dropItemNaturally(piglin.getLocation(), new ItemStack(Material.GOLD_INGOT));
					}
				}
				if(tradingPiglins.contains(piglin)) {
					tradingPiglins.remove(piglin);
				}
			}
		}.runTaskLater(plugin, 40);
	}
	public Piglin nearestPiglin(Item droppedItem) {
		for(Entity ent : droppedItem.getNearbyEntities(1.5, 2, 1.5)) {
			if(ent.getType().equals(EntityType.PIGLIN)) {
				return (Piglin)ent;
			}
		}
		return null;
	}
}
