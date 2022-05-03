import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ImmortalSpellBook implements Listener{

	private Main plugin;
	public ImmortalSpellBook(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	@EventHandler
	public void onBreak(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player p = (Player)event.getEntity();
			if(p.getHealth() <= event.getDamage()) {
				if(plugin.hasItem(p, "off", item.immortalSpellbook())) {
					giveBook(p,"off");
				}
				else if(plugin.hasItem(p, "main", item.immortalSpellbook())) {
					giveBook(p,"main");
				}
			}
		}
	}
	public void giveBook(Player p,String hand) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(hand.equals("off")) {
					p.getInventory().setItemInOffHand(item.immortalSpellbook());
				}
				else if(hand.equals("main")) {
					p.getInventory().setItemInMainHand(item.immortalSpellbook());
				}
			}
		}.runTask(plugin);
	}
}
