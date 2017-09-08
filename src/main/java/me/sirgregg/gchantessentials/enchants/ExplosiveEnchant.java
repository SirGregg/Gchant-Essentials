package me.sirgregg.gchantessentials.enchants;

import me.sirgregg.gchantbase.enchantsys.BaseEnchant;
import me.sirgregg.gchantbase.enchantsys.EnchantType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class ExplosiveEnchant extends BaseEnchant {
	private static List<Material> materials = new ArrayList<>();

	static {
		materials.add(Material.GOLD_PICKAXE);
		materials.add(Material.IRON_PICKAXE);
		materials.add(Material.DIAMOND_PICKAXE);
		materials.add(Material.WOOD_PICKAXE);
		materials.add(Material.STONE_PICKAXE);
	}

	public ExplosiveEnchant() {
		super("Explosive", 1, 1, materials, EnchantType.SWORD);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();

		if (hasEnchant(player.getItemInHand())) {
			Location loc = e.getBlock().getLocation();

			for (int xOff = -1; xOff <= 1; xOff++) {
				for (int yOff = -1; yOff <= 1; yOff++) {
					for (int zOff = -1; zOff <= 1; zOff++) {
						player.getWorld().getBlockAt(loc).getRelative(xOff, yOff, zOff).breakNaturally();
					}
				}
			}
		}
	}
}