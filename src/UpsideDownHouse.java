import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.scheduler.BukkitRunnable;

public class UpsideDownHouse implements Listener{
	private Main plugin;
	public UpsideDownHouse(Main plugin) {
		this.plugin=plugin;
	}
	Items item = new Items();
	ArrayList<Player> houseP = new ArrayList<>();
	@EventHandler
	public void onCLik(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				if(plugin.hasItem(p, "main", item.upsideDownHouse()) && !houseP.contains(p)) {
					p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
					Location loc = p.getLocation();
					loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_LAND, Integer.MAX_VALUE, 20);
					(new SchematicPaster()).pasteSchematics(plugin.schematics, loc, plugin,"UpsideDownHouse.schem");
					spawnVillager(plugin.addToLoc(loc, 0, 3, 0));
					clearItems(p);
					houseP.add(p);
					new BukkitRunnable() {
						@Override
						public void run() {
							if(houseP.contains(p)) {
								houseP.remove(p);
							}
						}
					}.runTaskLater(plugin, 2);
				}
			}
		}
	}
	public void clearItems(Player p) {
		new BukkitRunnable() {
			int timer = 0;
			@Override
			public void run() {
				timer++;
				if(timer >= 20) {
					this.cancel();
				}
				for(Entity ent : p.getNearbyEntities(5, 5, 5)) {
					if(ent.getType().equals(EntityType.DROPPED_ITEM)) {
						Item it = (Item)ent;
						if(it.getItemStack().getType().equals(Material.OAK_DOOR) || it.getItemStack().getType().equals(Material.WHITE_BED)) {
							it.remove();
						}
					}
				}
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	@EventHandler
	public void onCLik(PlayerInteractAtEntityEvent event) {
		Player p = event.getPlayer();
		if(event.getHand().equals(EquipmentSlot.HAND)) {
			if(event.getRightClicked().getCustomName()!=null) {
				if(event.getRightClicked().getCustomName().equals("Dinnerbone")) {
					event.setCancelled(true);
					openMerchant(p);
				}
			}
		}
	}
	public void spawnVillager(Location loc) {
		Villager v = (Villager)loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		v.setCustomName("Dinnerbone");
		v.setCustomNameVisible(true);
		v.setInvulnerable(true);
	}
	public void openMerchant(Player player) {
		Merchant merchant = Bukkit.createMerchant("Dinnerbone");
		merchant.setRecipes(recipes());
		player.openMerchant(merchant, true);
	}
	private List<MerchantRecipe> recipes() {
		MerchantRecipe recipe = new MerchantRecipe(item.emeraldIngot(), 20);
		recipe.addIngredient(new ItemStack(Material.BLAZE_ROD,7));

		MerchantRecipe recipe2 = new MerchantRecipe(item.emeraldIngot(), 20);
		recipe2.addIngredient(new ItemStack(Material.CHORUS_FRUIT,12));

		MerchantRecipe recipe3 = new MerchantRecipe(item.emeraldIngot(), 20);
		recipe3.addIngredient(new ItemStack(Material.GOLD_INGOT,8));

		MerchantRecipe recipe4 = new MerchantRecipe(item.emeraldIngot(), 20);
		recipe4.addIngredient(item.obsidianPickaxe());

		List<MerchantRecipe> recipeArray = List.of(recipe,recipe2,recipe3,recipe4);
		return recipeArray;
	}
}
