import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	Items item = new Items();
	Location netherStrongholdLocation = null, netherPortalLocation = null;
	@Override
	public void onEnable() {
		netherStrongholdLocation = new Location(Bukkit.getWorlds().get(1),0,0,0);
		netherPortalLocation = new Location(Bukkit.getWorlds().get(1),0,0,0);
		
		
		this.chorusCraftingTable();this.chorusPickaxe();this.chorusSword();this.obsidianChestplate();
		this.endStonePickaxe();this.endStoneSword();this.obsidianBoots();this.obsidianCrossbow();
		this.obsidianHelmet();this.obsidianLeggings();this.phantomCrystal();this.purpurRocket();
		this.shulkerPickaxe();this.silverfishSword();this.goldenBucket();obsidianSword();
		obsidianPickaxe();
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new ChorusItems(this), this);
		getServer().getPluginManager().registerEvents(new Craftings(this), this);
		getServer().getPluginManager().registerEvents(new PhantomCrystal(this), this);
		getServer().getPluginManager().registerEvents(new SilverfishSword(this), this);
		getServer().getPluginManager().registerEvents(new Nether(this), this);
		getServer().getPluginManager().registerEvents(new WitherPearl(this), this);
		getServer().getPluginManager().registerEvents(new ObsidianCrossbow(this), this);
		getServer().getPluginManager().registerEvents(new EndermiteMob(this), this);
		getServer().getPluginManager().registerEvents(new Piglins(this), this);
		getServer().getPluginManager().registerEvents(new ImmortalSpellBook(this), this);
		getServer().getPluginManager().registerEvents(new GoldenBucket(this), this);
		getServer().getPluginManager().registerEvents(new SoulArmor(this), this);
		getCommand("spawn").setExecutor(new Commands(this));
		getCommand("giveitem").setExecutor(new Commands(this));
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		for(NamespacedKey key : keys) {
			if(key!=null) {
				p.discoverRecipe(key);
			}
		}
	}
	
	public Location addToLoc(Location loc, double x, double y, double z) {
		return new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y, loc.getZ()+z);
	}
	public boolean hasItem(Player p,String hand,ItemStack item1) {
		if(hand.equals("main")) {
			if(p.getInventory().getItemInMainHand()!=null) {
				if(p.getInventory().getItemInMainHand().getItemMeta()!=null) {
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(item1.getItemMeta().getDisplayName())) {
						return true;
					}
				}
			}
		}
		else if(hand.equals("off")) {
			if(p.getInventory().getItemInOffHand()!=null) {
				if(p.getInventory().getItemInOffHand().getItemMeta()!=null) {
					if(p.getInventory().getItemInOffHand().getItemMeta().getDisplayName().equals(item1.getItemMeta().getDisplayName())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean hasArmor(Player p, ItemStack h, ItemStack c, ItemStack l, ItemStack b) {
		int count = 0;
		for(ItemStack armor : p.getInventory().getArmorContents()) {
			if(armor!=null) {
				if(armor.getItemMeta()!=null) {
					if(armor.getItemMeta().getDisplayName().equals(h.getItemMeta().getDisplayName()) ||
							armor.getItemMeta().getDisplayName().equals(c.getItemMeta().getDisplayName()) ||
							armor.getItemMeta().getDisplayName().equals(l.getItemMeta().getDisplayName()) ||
							armor.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName())) {
						count += 1;
					}
				}
			}
		}
		if(count == 4) return true;
		return false;
	}
	
	////////////////////////////////////////////////////////
	NamespacedKey a,b,c,d,e,f,g,h,j,k,l,m,n,o,w,x,y,z,q,aa,bb,cc,dd;
	//List<NamespacedKey> keys = List.of(a,b,c,d,e,f,g,h,j,k,l,m,n,o,w,x,y,z);
	NamespacedKey[] keys = {a,b,c,d,e,f,g,h,j,k,l,m,n,o,w};
	public void chorusPickaxe() {
		ItemStack result = item.chorusPickaxe();
		this.a = new NamespacedKey(this, "a");
		ShapedRecipe recipe = new ShapedRecipe(a, result);
		recipe.shape("NNN"," A "," A ");
		recipe.setIngredient('A',Material.CHORUS_FRUIT);
		recipe.setIngredient('N',Material.CHORUS_FLOWER);
		Bukkit.addRecipe(recipe);
	}
	public void chorusSword() {
		ItemStack result = item.chorusSword();
		this.b = new NamespacedKey(this, "b");
		ShapedRecipe recipe = new ShapedRecipe(b, result);
		recipe.shape(" N "," N "," A ");
		recipe.setIngredient('A',Material.CHORUS_FRUIT);
		recipe.setIngredient('N',Material.CHORUS_FLOWER);
		Bukkit.addRecipe(recipe);
	}
	public void chorusCraftingTable() {
		ItemStack result = item.chorusSword();
		this.c = new NamespacedKey(this, "c");
		ShapelessRecipe recipe = new ShapelessRecipe(c, result);
		recipe.addIngredient(Material.CHORUS_FRUIT);
		recipe.addIngredient(Material.CHORUS_FRUIT);
		recipe.addIngredient(Material.CHORUS_FRUIT);
		recipe.addIngredient(Material.CHORUS_FRUIT);
		Bukkit.addRecipe(recipe);
	}
	public void endStonePickaxe() {
		ItemStack result = item.endStonePickaxe();
		this.d = new NamespacedKey(this, "d");
		ShapedRecipe recipe = new ShapedRecipe(d, result);
		recipe.shape("NNN"," A "," A ");
		recipe.setIngredient('A',Material.CHORUS_FRUIT);
		recipe.setIngredient('N',Material.END_STONE);
		Bukkit.addRecipe(recipe);
	}
	public void endStoneSword() {
		ItemStack result = item.endStoneSword();
		this.e = new NamespacedKey(this, "e");
		ShapedRecipe recipe = new ShapedRecipe(e, result);
		recipe.shape(" N "," N "," A ");
		recipe.setIngredient('A',Material.CHORUS_FRUIT);
		recipe.setIngredient('N',Material.END_STONE);
		Bukkit.addRecipe(recipe);
	}
	public void immortalSpellBook() {
		ItemStack result = item.immortalSpellbook();
		this.f = new NamespacedKey(this, "f");
		ShapedRecipe recipe = new ShapedRecipe(f, result);
		recipe.shape("BBB","COC","BBB");
		recipe.setIngredient('B',Material.BOOK);
		recipe.setIngredient('C',Material.COAL);
		recipe.setIngredient('O',Material.BONE);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianHelmet() {
		ItemStack result = item.obsidianHelmet();
		this.h = new NamespacedKey(this, "h");
		ShapedRecipe recipe = new ShapedRecipe(h, result);
		recipe.shape("OOO","O O","   ");
		recipe.setIngredient('O',Material.OBSIDIAN);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianChestplate() {
		ItemStack result = item.obsidianChestplate();
		this.g = new NamespacedKey(this, "g");
		ShapedRecipe recipe = new ShapedRecipe(g, result);
		recipe.shape("O O","OOO","OOO");
		recipe.setIngredient('O',Material.OBSIDIAN);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianLeggings() {
		ItemStack result = item.obsidianLeggings();
		this.j = new NamespacedKey(this, "j");
		ShapedRecipe recipe = new ShapedRecipe(j, result);
		recipe.shape("OOO","O O","O O");
		recipe.setIngredient('O',Material.OBSIDIAN);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianBoots() {
		ItemStack result = item.obsidianBoots();
		this.k = new NamespacedKey(this, "k");
		ShapedRecipe recipe = new ShapedRecipe(k, result);
		recipe.shape("   ","O O","O O");
		recipe.setIngredient('O',Material.OBSIDIAN);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianCrossbow() {
		ItemStack result = item.obsidianCrossbow();
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.enderCarrot());
		this.l = new NamespacedKey(this, "l");
		ShapedRecipe recipe = new ShapedRecipe(l, result);
		recipe.shape("OEO","POP"," O ");
		recipe.setIngredient('O',Material.OBSIDIAN);
		recipe.setIngredient('P',Material.PHANTOM_MEMBRANE);
		recipe.setIngredient('E',usedItem);
		Bukkit.addRecipe(recipe);
	}
	public void phantomCrystal() {
		ItemStack result = item.phantomCrystal();
		this.m = new NamespacedKey(this, "m");
		ShapedRecipe recipe = new ShapedRecipe(m, result);
		recipe.shape("EEE","EPE","OOO");
		recipe.setIngredient('O',Material.OBSIDIAN);
		recipe.setIngredient('P',Material.PHANTOM_MEMBRANE);
		recipe.setIngredient('E',Material.END_STONE);
		Bukkit.addRecipe(recipe);
	}
	public void purpurRocket() {
		ItemStack result = item.purpurRocket(6);
		this.n = new NamespacedKey(this, "n");
		ShapelessRecipe recipe = new ShapelessRecipe(n, result);
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.enderCarrot());
		recipe.addIngredient(usedItem);
		recipe.addIngredient(Material.CHORUS_FRUIT);
		recipe.addIngredient(Material.SHULKER_SHELL);
		Bukkit.addRecipe(recipe);
	}
	public void shulkerPickaxe() {
		ItemStack result = item.shulkerPickaxe();
		this.o = new NamespacedKey(this, "o");
		ShapedRecipe recipe = new ShapedRecipe(o, result);
		recipe.shape("PSP"," E "," E ");
		recipe.setIngredient('P',Material.PURPUR_BLOCK);
		recipe.setIngredient('S',Material.SHULKER_SHELL);
		recipe.setIngredient('E',Material.END_ROD);
		Bukkit.addRecipe(recipe);
	}
	public void silverfishSword() {
		ItemStack result = item.silverfishSword();
		this.w = new NamespacedKey(this, "w");
		ShapedRecipe recipe = new ShapedRecipe(w, result);
		recipe.shape(" G "," G "," S ");
		recipe.setIngredient('G',Material.GRAVEL);
		recipe.setIngredient('S',Material.GOLDEN_SWORD);
		Bukkit.addRecipe(recipe);
	}
	public void goldenBucket() {
		ItemStack result = item.goldenBucket();
		this.x = new NamespacedKey(this, "x");
		ShapedRecipe recipe = new ShapedRecipe(x, result);
		recipe.shape("G G"," G ","   ");
		recipe.setIngredient('G',Material.GOLD_INGOT);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianPickaxe() {
		ItemStack result = item.obsidianPickaxe();
		this.y = new NamespacedKey(this, "y");
		ShapedRecipe recipe = new ShapedRecipe(y, result);
		recipe.shape("NNN"," A "," A ");
		recipe.setIngredient('A',Material.STICK);
		recipe.setIngredient('N',Material.OBSIDIAN);
		Bukkit.addRecipe(recipe);
	}
	public void obsidianSword() {
		ItemStack result = item.obsidianSword();
		this.z = new NamespacedKey(this, "z");
		ShapedRecipe recipe = new ShapedRecipe(z, result);
		recipe.shape(" N "," N "," A ");
		recipe.setIngredient('A',Material.STICK);
		recipe.setIngredient('N',Material.OBSIDIAN);
		Bukkit.addRecipe(recipe);
	}
	
	public void soulHelmet() {
		ItemStack result = item.soulHelmet();
		this.aa = new NamespacedKey(this, "aa");
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.soulBucket());
		ShapedRecipe recipe = new ShapedRecipe(aa, result);
		recipe.shape("OSO","O O","   ");
		recipe.setIngredient('O',Material.OBSIDIAN);
		recipe.setIngredient('S',usedItem);
		Bukkit.addRecipe(recipe);
	}
	public void soulChestplate() {
		ItemStack result = item.soulChestplate();
		this.bb = new NamespacedKey(this, "bb");
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.soulBucket());
		ShapedRecipe recipe = new ShapedRecipe(bb, result);
		recipe.shape("O O","OSO","OOO");
		recipe.setIngredient('O',Material.OBSIDIAN);
		recipe.setIngredient('S',usedItem);
		Bukkit.addRecipe(recipe);
	}
	public void soulLeggings() {
		ItemStack result = item.soulLeggings();
		this.cc = new NamespacedKey(this, "cc");
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.soulBucket());
		ShapedRecipe recipe = new ShapedRecipe(cc, result);
		recipe.shape("OOO","O O","S S");
		recipe.setIngredient('O',Material.OBSIDIAN);
		recipe.setIngredient('S',usedItem);
		Bukkit.addRecipe(recipe);
	}
	public void soulBoots() {
		ItemStack result = item.soulBoots();
		this.dd = new NamespacedKey(this, "dd");
		RecipeChoice usedItem = new RecipeChoice.ExactChoice(item.soulBucket());
		ShapedRecipe recipe = new ShapedRecipe(dd, result);
		recipe.shape("   ","O O","S S");
		recipe.setIngredient('O',Material.OBSIDIAN);
		recipe.setIngredient('S',usedItem);
		Bukkit.addRecipe(recipe);
	}
}
