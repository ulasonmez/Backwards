import org.bukkit.Material;
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
		if(!item.allItems.contains(event.getRecipe().getResult())) {
			event.getInventory().setResult(new ItemStack(Material.AIR));
		}
	}
}
