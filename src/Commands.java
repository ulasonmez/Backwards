import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPhantom;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Commands implements CommandExecutor{

	
	EndermiteMob mob;
	private Main plugin;
	public Commands(Main plugin) {
		this.plugin=plugin;
		mob = new EndermiteMob(plugin);
	}
	Items item = new Items();
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(label.equals("spawn")) {
				if(args.length==2) {
					if(args[0].equals("phantom")) {
						int num = Integer.parseInt(args[1]);
						for(int i = 0; i<=num;i++) {
							CraftPhantom cp = (CraftPhantom)p.getWorld().spawnEntity(p.getLocation(), EntityType.PHANTOM);
							new BukkitRunnable() {
								int count = 0;
								@Override
								public void run() {
									count += 1;
									if(count >= 20 * 3) {
										cp.setTarget(p);
										this.cancel();
									}
									cp.setTarget(null);
								}
							}.runTaskTimer(plugin, 0, 1);
						}
					}
				}
				else if(args.length==1) {
					if(args[0].equals("villager")) {
						Villager v = (Villager)p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
						v.setCustomName("Dinnerbone");
						v.setProfession(null);
						//v.setCustomNameVisible(true);
					}
					if(args[0].equals("endermite")) {
						mob.spawnEndermite(p);
					}
				}
			}
			else if(label.equals("giveitem")) {
				if(args[0].equals("crystal")) {
					if(args.length==2) {
						int amt = Integer.parseInt(args[1]);
						for(int i = 0; i<amt;i++) {
							p.getInventory().addItem(item.phantomCrystal());
						}
					}
					else {
						p.getInventory().addItem(item.phantomCrystal());
					}
				}
				else if(args[0].equals("silverfishsword")) {
					p.getInventory().addItem(item.silverfishSword());
				}
				else if(args[0].equals("witherpearl")) {
					p.getInventory().addItem(item.witherPearl());
				}
				else if(args[0].equals("obsidiancrossbow")) {
					p.getInventory().addItem(item.obsidianCrossbow());
				}
				else if(args[0].equals("endercarrot")) {
					p.getInventory().addItem(item.enderCarrot());
				}
				else if(args[0].equals("all")) {
					for(ItemStack allItems : item.allItems) {
						p.getInventory().addItem(allItems);
					}
				}
			}
		}
		return false;
	}
}
