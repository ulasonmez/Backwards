import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class GoldenBucket implements Listener{

	private Main plugin;
	public GoldenBucket(Main plugin) {
		this.plugin=plugin;
	}

	Items item = new Items();
	@EventHandler
	public void onBreak(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
		if(!event.getHand().equals(EquipmentSlot.HAND))return;
		Material mat = event.getClickedBlock().getType();
		if(plugin.hasItem(p, "main", item.goldenBucket())) {
			if(mat.equals(Material.BASALT)) {
				event.getClickedBlock().setType(Material.AIR);
				playerCollectedBlock(p, item.basaltBucket());
			}
			else if(mat.equals(Material.SOUL_SAND)) {
				event.getClickedBlock().setType(Material.AIR);
				playerCollectedBlock(p, item.soulBucket());
			}
			if(mat.equals(Material.NETHER_BRICKS)) {
				event.getClickedBlock().setType(Material.AIR);
				playerCollectedBlock(p, item.netherBricksBucket());
			}
		}
	}
	public void playerCollectedBlock(Player p, ItemStack addItem) {
		p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
		for(ItemStack invItem : p.getInventory().getContents()) {
			if(invItem != null) {
				if(invItem.isSimilar(addItem)) {
					invItem.setAmount(invItem.getAmount() + 1);
					return;
				}
			}
		}
		p.getInventory().addItem(addItem);
	}
}
