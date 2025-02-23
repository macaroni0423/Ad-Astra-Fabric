package com.github.alexnijjar.ad_astra;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.alexnijjar.ad_astra.config.AdAstraConfig;
import com.github.alexnijjar.ad_astra.data.Planet;
import com.github.alexnijjar.ad_astra.data.PlanetData;
import com.github.alexnijjar.ad_astra.networking.ModC2SPackets;
import com.github.alexnijjar.ad_astra.registry.ModArmour;
import com.github.alexnijjar.ad_astra.registry.ModBlockEntities;
import com.github.alexnijjar.ad_astra.registry.ModCommands;
import com.github.alexnijjar.ad_astra.registry.ModCriteria;
import com.github.alexnijjar.ad_astra.registry.ModEntityTypes;
import com.github.alexnijjar.ad_astra.registry.ModFeatures;
import com.github.alexnijjar.ad_astra.registry.ModFluids;
import com.github.alexnijjar.ad_astra.registry.ModItems;
import com.github.alexnijjar.ad_astra.registry.ModPaintings;
import com.github.alexnijjar.ad_astra.registry.ModParticleTypes;
import com.github.alexnijjar.ad_astra.registry.ModRecipes;
import com.github.alexnijjar.ad_astra.registry.ModScreenHandlers;
import com.github.alexnijjar.ad_astra.registry.ModSoundEvents;
import com.github.alexnijjar.ad_astra.registry.ModStructures;
import com.github.alexnijjar.ad_astra.util.ModIdentifier;
import com.github.alexnijjar.ad_astra.world.chunk.PlanetChunkGenerator;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class AdAstra implements ModInitializer {

	public static final String MOD_ID = "ad_astra";
	public static final Logger LOGGER = LoggerFactory.getLogger("Ad Astra");
	public static AdAstraConfig CONFIG;

	public static Set<Planet> planets = new HashSet<>();
	public static Set<RegistryKey<World>> adAstraWorlds = new HashSet<>();
	public static Set<RegistryKey<World>> orbitWorlds = new HashSet<>();
	public static Set<RegistryKey<World>> planetWorlds = new HashSet<>();
	public static Set<RegistryKey<World>> worldsWithOxygen = new HashSet<>();

	@Override
	public void onInitialize() {

		// Register config
		AutoConfig.register(AdAstraConfig.class, Toml4jConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(AdAstraConfig.class).getConfig();

		// Registry
		ModFluids.register();
		ModItems.register();
		ModArmour.register();
		ModBlockEntities.register();
		ModRecipes.register();
		ModEntityTypes.register();
		ModScreenHandlers.register();
		ModCommands.register();
		ModSoundEvents.register();
		ModParticleTypes.register();
		ModPaintings.register();

		// Data
		PlanetData.register();

		// Worldgen
		ModFeatures.register();
		ModStructures.register();
		Registry.register(Registry.CHUNK_GENERATOR, new ModIdentifier("planet_noise"), PlanetChunkGenerator.CODEC);

		// Packets
		ModC2SPackets.register();

		ModCriteria.register();

		AdAstra.LOGGER.info("Ad Astra Initialized! 🚀");
	}
}