package com.github.alexnijjar.ad_astra.client.registry;

import com.github.alexnijjar.ad_astra.client.screens.*;
import com.github.alexnijjar.ad_astra.client.screens.utils.PlanetSelectionScreen;
import com.github.alexnijjar.ad_astra.registry.ModScreenHandlers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class ClientModScreens {

	public static void register() {
		HandledScreens.register(ModScreenHandlers.SOLAR_PANEL_SCREEN_HANDLER, SolarPanelScreen::new);
		HandledScreens.register(ModScreenHandlers.COAL_GENERATOR_SCREEN_HANDLER, CoalGeneratorScreen::new);
		HandledScreens.register(ModScreenHandlers.COMPRESSOR_SCREEN_HANDLER, CompressorScreen::new);
		HandledScreens.register(ModScreenHandlers.NASA_WORKBENCH_SCREEN_HANDLER, NasaWorkbenchScreen::new);
		HandledScreens.register(ModScreenHandlers.CONVERSION_SCREEN_HANDLER, ConversionScreen::new);
		HandledScreens.register(ModScreenHandlers.WATER_PUMP_SCREEN_HANDLER, WaterPumpScreen::new);
		HandledScreens.register(ModScreenHandlers.OXYGEN_DISTRIBUTOR_SCREEN_HANDLER, OxygenDistributorScreen::new);
		HandledScreens.register(ModScreenHandlers.CRYO_FREEZER_SCREEN_HANDLER, CryoFreezerScreen::new);

		HandledScreens.register(ModScreenHandlers.VEHICLE_SCREEN_HANDLER, VehicleScreen::new);
		HandledScreens.register(ModScreenHandlers.LARGE_VEHICLE_SCREEN_HANDLER, LargeVehicleScreen::new);
		HandledScreens.register(ModScreenHandlers.LANDER_SCREEN_HANDLER, LanderScreen::new);

		HandledScreens.register(ModScreenHandlers.PLANET_SELECTION_SCREEN_HANDLER, PlanetSelectionScreen::new);
	}
}