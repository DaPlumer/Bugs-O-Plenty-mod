package daplumer.more_bugs;

import daplumer.more_bugs.ModRegistries.ModRegistries;
import daplumer.more_bugs.util.FoodComponentsBuilder;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("StringTemplateMigration")
public class BugsoPlenty implements ModInitializer {
	public static final String MOD_ID = "more_bugs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String NAME = "Bugs o' Plenty";
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Registering Mod " + NAME);
		ModRegistries.ITEM.register("leafcutter_shroom_soup", new Item.Settings().food(FoodComponentsBuilder.buildFoodComponent(5,0.6F)));
	}
}