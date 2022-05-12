import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class Craftings implements Listener{

	@SuppressWarnings("unused")
	private Main plugin;
	public Craftings(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if(!isInEnd())return;
		if(event.getInventory().getResult()==null)return;
		if(!item.allItems.contains(event.getInventory().getResult())) {
			event.getInventory().setResult(new ItemStack(Material.AIR));
		}
	}
	public boolean isInEnd() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getWorld().equals(Bukkit.getWorlds().get(2))) {
				return true;
			}
		}
		return false;
	}
}
