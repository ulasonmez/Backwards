import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EndBiome implements Listener{

	@SuppressWarnings("unused")
	private Main plugin;
	public EndBiome(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	List<Material> ironMats = List.of(Material.IRON_SWORD,Material.IRON_PICKAXE,Material.IRON_AXE,Material.IRON_SHOVEL,
			Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,Material.IRON_BOOTS);
	List<Material> leatherMats = List.of(Material.WOODEN_SWORD,Material.WOODEN_PICKAXE,Material.WOODEN_AXE,Material.WOODEN_SHOVEL,
			Material.LEATHER_HELMET,Material.LEATHER_CHESTPLATE,Material.LEATHER_LEGGINGS,Material.LEATHER_BOOTS);
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(!p.getWorld().equals(Bukkit.getWorlds().get(2)))return;
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock() instanceof Chest){
				Chest c = (Chest) e.getClickedBlock();
				for(ItemStack i : c.getInventory()){
					if(i!=null && i.getItemMeta()!=null) {
						//change diamond tools to end stone
						if(i.getType().toString().endsWith("DIAMOND_PICKAXE")) {
							ItemMeta meta = i.getItemMeta();
							i = item.endStonePickaxe();
							i.setItemMeta(meta);
						}
						else if(i.getType().toString().endsWith("DIAMOND_SWORD")) {
							ItemMeta meta = i.getItemMeta();
							i = item.endStoneSword();
							i.setItemMeta(meta);
						}
						else if(i.getType().toString().endsWith("DIAMOND_SHOVEL")) {
							i.setType(Material.AIR);
						}
						else if(i.getType().toString().endsWith("DIAMOND_AXE")) {
							i.setType(Material.AIR);
						}
						//change the iron to leather
						if(ironMats.contains(i.getType())) {
							ItemMeta meta = i.getItemMeta();
							i = new ItemStack(leatherMats.get(ironMats.indexOf(i.getType())));
							i.setItemMeta(meta);
						}
					}
				}
			}
		}
	}
}
