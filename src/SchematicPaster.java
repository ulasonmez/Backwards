
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

public class SchematicPaster {

	public void pasteSchematics(File[] schematics, Location location, Plugin plugin, String worldName) {
		if (schematics.length == 0)
			return; 
		//File schematic = schematics[random.nextInt(schematics.length)];
		File schematic = getName(schematics,worldName);
		if(schematic!=null) {
			pasteSchematic(schematic, location, plugin);
		}
	}
	public File getName(File[] schematics, String worldName) {
		for(File a : schematics) {
			if(a.getName().equals(worldName)) {
				return a;
			}
		}
		return null;
	}
	public void pasteSchematic(File schematic, final Location location, Plugin plugin) {
		final Clipboard clipboard;
		ClipboardFormat format = ClipboardFormats.findByFile(schematic);
		if (format == null) {
			System.out.println("[SchematicPaster] No File Found. Did you Delete a Schematic?");
			return;
		} 
		try (ClipboardReader reader = format.getReader(new FileInputStream(schematic))) {
			clipboard = reader.read();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} 
		(new BukkitRunnable() {
			int y = clipboard.getRegion().getMinimumPoint().getY() - 1;

			public void run() {
				this.y++;
				if (this.y > clipboard.getRegion().getMaximumPoint().getY())
					cancel(); 
				if (location.getWorld() == null) {
					System.out.println("[SchematicPaster] World Not Found");
					return;
				} 
				try (@SuppressWarnings("deprecation")
				EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(location.getWorld()), -1)) {
					Operation operation = new ClipboardHolder(clipboard)
							.createPaste(editSession)
							.to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
							.ignoreAirBlocks(true)
							.build();
					Operations.complete(operation);
				} catch (WorldEditException e) {
					e.printStackTrace();
				} 
			}
		}).runTaskTimer(plugin, 0L, 2L);
	}
}
