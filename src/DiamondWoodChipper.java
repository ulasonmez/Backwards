import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class DiamondWoodChipper implements Listener{

	private Main plugin;
	Location bigTreeLocation = null;
	public DiamondWoodChipper(Main plugin) {
		this.plugin=plugin;
		bigTreeLocation = new Location(Bukkit.getWorlds().get(0),0,70,0);
	}
	Items item = new Items();
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.hasItem(p, "main", item.woodChipper())) {
					Location blockLoc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
					Location loc = new Location(blockLoc.getWorld(),blockLoc.getX(),blockLoc.getY(),blockLoc.getZ(),p.getLocation().getYaw(),0);
					ArmorStand stand = spawnStand(loc,item.woodChipper());
					getNearbyTrees(stand);
				}
			}
		}
	}
	public void getNearbyTrees(ArmorStand stand) {
		Location loc = stand.getLocation();
		Location treeLoc = getFrontBlocks(loc);
		Location chipperLoc = getFrontChipper(loc);
		for(int x = -4; x<=4;x++) {
			for(int z = -4; z<=4;z++) {
				for(int y = -3; y<=7;y++) {
					Location tempLoc = plugin.addToLoc(treeLoc, x, y, z);
					if(tempLoc.getBlock().getType().toString().endsWith("_LOG") || tempLoc.getBlock().getType().toString().endsWith("_LEAVES")) {
						sendTreeToChipper(tempLoc, chipperLoc);
					}
				}
			}
		}
	}
	public void sendTreeToChipper(Location block,Location chipperLoc) {
		Material mat = block.getBlock().getType();
		block.getBlock().setType(Material.AIR);
		FallingBlock fb = (FallingBlock)block.getWorld().spawnFallingBlock(plugin.addToLoc(block, 0.5, 0, 0.5), mat.createBlockData());
		fb.setGravity(false);
		fb.setInvulnerable(true);
		new BukkitRunnable() {
			double t = 0.5;
			Location destination = chipperLoc;
			Vector dir = (destination.toVector()).clone().subtract((block.toVector())).normalize();
			Location pLoc = block;
			@Override
			public void run() {
				if(destination.distance(fb.getLocation())<=1) {
					fb.remove();
					block.getWorld().dropItemNaturally(fb.getLocation(), new ItemStack(mat));
					fb.getWorld().playSound(fb.getLocation(), Sound.BLOCK_AZALEA_LEAVES_BREAK, Integer.MAX_VALUE, 10);
					this.cancel();
				}
				t+=0.1;
				double a = dir.getX()*t;
				double b = dir.getY()*t;
				double c = dir.getZ()*t;
				pLoc.add(a,b,c);
				fb.teleport(pLoc);
				pLoc.subtract(a,b,c);
			}
		}.runTaskTimer(plugin, 0, 1);
	}

	public Location getFrontBlocks(Location loc){
		Vector dir = loc.getDirection().normalize();
		Location startLoc = loc.clone();
		Location standLoc = loc.clone();
		for(double t = 1; t<= 10; t+=0.35) {
			double a = dir.getX()*t;
			double b = dir.getY()*t;
			double c = dir.getZ()*t;
			standLoc.add(a,b,c);
			if(standLoc.distance(startLoc)>=8) {
				return plugin.addToLoc(standLoc, 0, 1, 0);
			}
			standLoc.subtract(a,b,c);
		}
		return null;
	}
	public Location getFrontChipper(Location loc){
		Vector dir = loc.getDirection().normalize();
		Location startLoc = loc.clone();
		Location standLoc = loc.clone();
		for(double t = 1; t<= 10; t+=0.35) {
			double a = dir.getX()*t;
			double b = dir.getY()*t;
			double c = dir.getZ()*t;
			standLoc.add(a,b,c);
			if(standLoc.distance(startLoc)>=2) {
				return plugin.addToLoc(standLoc, 0, 1, 0);
			}
			standLoc.subtract(a,b,c);
		}
		return null;
	}
	public ArmorStand spawnStand(Location loc, ItemStack helmet) {
		ArmorStand stand = (ArmorStand)loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		stand.setInvulnerable(true);
		stand.setInvisible(true);
		stand.getEquipment().setHelmet(helmet);
		return stand;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.getWorld().equals(Bukkit.getWorlds().get(0))) {
			if(event.getBlock().toString().endsWith("_LOG")) {
				event.getBlock().setType(Material.AIR);
			}
		}
	}
}
