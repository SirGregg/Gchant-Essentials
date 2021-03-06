package me.sirgregg.gchantessentials.enchants;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import com.codingforcookies.armorequip.ArmorType;
import me.sirgregg.gchantbase.GchantBase;
import me.sirgregg.gchantbase.enchantsys.BaseEnchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class JumpEnchant extends BaseEnchant {
	private static List<Material> materials = new ArrayList<>();

	static {
		materials.add(Material.LEATHER_BOOTS);
		materials.add(Material.GOLD_BOOTS);
		materials.add(Material.CHAINMAIL_BOOTS);
		materials.add(Material.IRON_BOOTS);
		materials.add(Material.DIAMOND_BOOTS);
	}

	public JumpEnchant() {
		super("Jump", 1, 3, ChatColor.RED, materials);
		GchantBase.getEnchantManager().registerEnchant(this);
	}

	@EventHandler
	public void onEquipUnEquip(ArmorEquipEvent e) {
		if (!e.getType().equals(ArmorType.BOOTS)) return;
		Player player = e.getPlayer();

		if (e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) { // Equipping
			ItemStack boots = e.getNewArmorPiece();
			if (getLevel(boots) == -1) return;

			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, getLevel(boots) - 1, true));
		} else if (e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR) { // Unequipping
			ItemStack boots = e.getOldArmorPiece();

			if (hasEnchant(boots)) {
				player.removePotionEffect(PotionEffectType.JUMP);
			}
		}
	}
}
