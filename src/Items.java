import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	
	public List<ItemStack> allItems = List.of(chorusCraftingTable(),chorusSword(),chorusPickaxe(),enderCarrot(),
			endStoneSword(),endStonePickaxe(),shulkerPickaxe(),obsidianBoots(),obsidianChestplate(),obsidianCrossbow(),obsidianHelmet(),
			obsidianLeggings(),purpurRocket(6),phantomCrystal(),silverfishSword(),witherPearl(),immortalSpellbook(),endermiteModel(),goldenBucket(),
			obsidianPickaxe(),obsidianSword(),soulHelmet(),soulChestplate(),soulLeggings(),soulBoots(),volcanoLauncher(),basaltPortal());
	public ItemStack chorusCraftingTable() {
		ItemStack item = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Chorus Crafting Table");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack chorusSword() {
		ItemStack item = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		meta.setDisplayName("Chorus Sword");
		lore.add("Teleports you randomly when used");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack chorusPickaxe() {
		ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		meta.setDisplayName("Chorus Pickaxe");
		lore.add("Teleports you randomly when used");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack enderCarrot() {
		ItemStack item = new ItemStack(Material.CARROT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Ender Carrot");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack endStoneSword() {
		ItemStack item = new ItemStack(Material.STONE_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("End Stone Sword");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack endStonePickaxe() {
		ItemStack item = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("End Stone Pickaxe");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack shulkerPickaxe() {
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Shulker Pickaxe");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Poofs nearby blocks");
		lore.add("when you mine with it.");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack obsidianHelmet() {
		ItemStack item = new ItemStack(Material.IRON_HELMET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Helmet");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack obsidianChestplate() {
		ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Chestplate");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack obsidianLeggings() {
		ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Leggings");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack obsidianBoots() {
		ItemStack item = new ItemStack(Material.IRON_BOOTS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Boots");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack purpurRocket(int amt) {
		ItemStack item = new ItemStack(Material.FIREWORK_ROCKET,amt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Purpur Rocket");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Flight Duration: 3");
		lore.add("Burst");
		lore.add("     Black, Purple, Cyan, Magenta, White");
		lore.add("     Fade to Purple, Cyan");
		lore.add("     Trail");
		lore.add("     Twinkle");
		lore.add("A special Firework capable of");
		lore.add("carrying Elytra across the End");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack phantomCrystal() {
		ItemStack item = new ItemStack(Material.END_CRYSTAL);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<>();
		meta.setDisplayName("Phantom Crystal");
		lore.add("Place on top of each Obsidian");
		lore.add("to summon an Enderdragon.");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack obsidianCrossbow() {
		ItemStack item = new ItemStack(Material.CROSSBOW);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Crossbow");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Shoots Ender Carrots.");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack endermiteModel() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack immortalSpellbook() {
		ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Immortal Spell Book");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Holds the secrets of the eternal life.");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack silverfishSword() {
		ItemStack item = new ItemStack(Material.STONE_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Silverfish Sword");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Turns mobs it hits");
		lore.add("into solid blocks.");
		meta.setLore(lore);
		meta.setCustomModelData(2);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack witherPearl() {
		ItemStack item = new ItemStack(Material.ENDER_PEARL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Wither Pearl");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("A projectile that breaks blocks where it lands,");
		lore.add("then teleports the thrower to that location.");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack goldenBucket() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Golden Bucket");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Collects solid blocks instead of liquid.");
		meta.setLore(lore);
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack basaltBucket() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Golden Bucket of Basalt");
		meta.setCustomModelData(2);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack soulBucket() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Golden Bucket of Soul Sand");
		meta.setCustomModelData(3);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack netherBricksBucket() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Golden Bucket of Nether Bricks");
		meta.setCustomModelData(4);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack obsidianSword() {
		ItemStack item = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Sword");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack obsidianPickaxe() {
		ItemStack item = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Obsidian Pickaxe");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack soulHelmet() {
		ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Soul Helmet");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack soulChestplate() {
		ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Soul Chestplate");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack soulLeggings() {
		ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Soul Leggings");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack soulBoots() {
		ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Soul Boots");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack volcanoLauncher() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Volcano Launcher");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Shoots projectiles that spawn a");
		lore.add("miniature volcano where they land.");
		meta.setLore(lore);
		meta.setCustomModelData(2);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack basaltPortal() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Basalt Portal");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("When placed and lit with a volcanic eruption,");
		lore.add("creates a portal to the Overworld.");
		meta.setLore(lore);
		meta.setCustomModelData(3);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack basaltPortalOpen() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Basalt Portal Open");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("When placed and lit with a volcanic eruption,");
		lore.add("creates a portal to the Overworld.");
		meta.setLore(lore);
		meta.setCustomModelData(4);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack miniatureVolcano() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Miniature Volcano");
		meta.setCustomModelData(5);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack mineshaftDrill() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Mineshaft Drill");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Creates a long tunnel with support beams");
		lore.add("Collects any discovered loot into chests.");
		meta.setLore(lore);
		meta.setCustomModelData(6);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack upsideDownHouse() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Upside-Down House");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("When placed, generates a house");
		lore.add("with an Upside-Down Village, who will");
		lore.add("trade you Emerald Ingots.");
		meta.setLore(lore);
		meta.setCustomModelData(7);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack emeraldIngot() {
		ItemStack item = new ItemStack(Material.DIAMOND);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Emerald Ingot");
		meta.setCustomModelData(2);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack diamondIngot() {
		ItemStack item = new ItemStack(Material.DIAMOND);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Diamond Ingot");
		meta.setCustomModelData(1);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack wardenEar() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Warden Ear");
		meta.setCustomModelData(5);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack villagerNose() {
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Villager Nose");
		meta.setCustomModelData(6);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack sculkSeeker() {
		ItemStack item = new ItemStack(Material.NAUTILUS_SHELL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Sculk Seeker");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Right lick to emit a sound that");
		lore.add("points towards the world origin.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack greatFertilizer() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Great Fertilizer");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("Used on the Great Oak Sapling at the world origin");
		lore.add("to grow a Great Oak Tree.");
		meta.setLore(lore);
		meta.setCustomModelData(9);
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack woodChipper() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Diamond Wood Chipper");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("The only way to collect");
		lore.add("wood from the surface.");
		meta.setLore(lore);
		meta.setCustomModelData(10);
		item.setItemMeta(meta);
		return item;
	}
}
