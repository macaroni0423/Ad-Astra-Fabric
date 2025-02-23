package com.github.alexnijjar.ad_astra.mixin.client;

import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.alexnijjar.ad_astra.client.screens.PlayerOverlayScreen;
import com.github.alexnijjar.ad_astra.client.sound.PlanetSoundPlayer;
import com.github.alexnijjar.ad_astra.client.sound.PlanetWeatherSoundPlayer;
import com.github.alexnijjar.ad_astra.entities.vehicles.LanderEntity;
import com.github.alexnijjar.ad_astra.entities.vehicles.RocketEntity;
import com.github.alexnijjar.ad_astra.entities.vehicles.VehicleEntity;
import com.github.alexnijjar.ad_astra.items.armour.JetSuit;
import com.github.alexnijjar.ad_astra.items.armour.SpaceSuit;
import com.github.alexnijjar.ad_astra.util.entity.OxygenUtils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.StatHandler;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

	@Shadow
	@Final
	private List<ClientPlayerTickable> tickables;

	@Inject(at = @At(value = "TAIL"), method = "<init>")
	public void adastra_ClientPlayerEntity(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler, StatHandler stats, ClientRecipeBook recipeBook, boolean lastSneaking, boolean lastSprinting, CallbackInfo ci) {
		this.tickables.add(new PlanetWeatherSoundPlayer((ClientPlayerEntity) (Object) (this), client.getSoundManager()));
		this.tickables.add(new PlanetSoundPlayer((ClientPlayerEntity) (Object) (this), client.getSoundManager()));

	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void adastra_tick(CallbackInfo ci) {

		ClientPlayerEntity player = ((ClientPlayerEntity) (Object) this);
		ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);

		if (SpaceSuit.hasFullSet(player)) {
			PlayerOverlayScreen.shouldRenderOxygen = true;
			if (chest.getItem() instanceof SpaceSuit suit) {
				long oxygen = suit.getAmount(chest);

				// Render oxygen info
				double ratio = oxygen / (double) suit.getTankSize();
				PlayerOverlayScreen.oxygenRatio = ratio;
				PlayerOverlayScreen.doesNotNeedOxygen = OxygenUtils.entityHasOxygen(player.world, player) && !player.isSubmergedInWater();
			}
		} else {
			PlayerOverlayScreen.shouldRenderOxygen = false;
		}

		if (JetSuit.hasFullSet(player)) {
			PlayerOverlayScreen.shouldRenderBattery = true;
			if (chest.getItem() instanceof JetSuit suit) {

				// Render battery info
				double ratio = (double) suit.getStoredEnergy(chest) / (double) suit.getEnergyCapacity();
				PlayerOverlayScreen.batteryRatio = ratio;
			}
		} else {
			PlayerOverlayScreen.shouldRenderBattery = false;
		}

		if (player.getVehicle() instanceof VehicleEntity vehicle) {
			// Rocket
			if (vehicle.renderPlanetBar()) {
				PlayerOverlayScreen.shouldRenderBar = true;
				if (vehicle instanceof RocketEntity rocket) {
					if (rocket.isFlying()) {
						PlayerOverlayScreen.countdownSeconds = rocket.getCountdownSeconds();
					}
				}
			}

			// Show the warning screen when falling in a lander
			if (vehicle instanceof LanderEntity lander) {

				double speed = lander.getVelocity().getY();
				if (speed != 0.0) {
					PlayerOverlayScreen.shouldRenderWarning = true;
					PlayerOverlayScreen.speed = speed * 55;
				} else {
					PlayerOverlayScreen.disableAllVehicleOverlays();
				}
			}

		} else {
			PlayerOverlayScreen.disableAllVehicleOverlays();
		}
	}
}
