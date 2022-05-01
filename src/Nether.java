import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Nether implements Listener{

	private Main plugin;
	public Nether(Main plugin) {
		this.plugin=plugin;
	}

	Items item = new Items();
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(plugin.netherStrongholdLocation.distance(p.getLocation())<=100) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onDearth(EntityDeathEvent event) {
		if(event.getEntity().getKiller() instanceof Player) {
			Player p = (Player)event.getEntity().getKiller();
			if(event.getEntity().getType().equals(EntityType.WITHER_SKELETON)) {
				p.getWorld().dropItemNaturally(event.getEntity().getLocation(), item.witherPearl());
			}
		}
	}
}
