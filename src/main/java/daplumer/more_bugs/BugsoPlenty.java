package daplumer.more_bugs;

import daplumer.more_bugs.ModRegistries.ModRegistries;
import daplumer.more_bugs.block.ModBlocks;
import daplumer.more_bugs.util.FoodComponentsBuilder;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.stat.Stat;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static daplumer.more_bugs.Registries.*;

@SuppressWarnings({"StringTemplateMigration", "RedundantSuppression"})
public class BugsoPlenty extends ModRegistries implements ModInitializer{
	public static final String MOD_ID = "more_bugs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String NAME = "Bugs o' Plenty";

	public static final Item LEAFCUTTER_SHROOM_ITEM =
			ITEMS.register("leafcutter_shroom",
					new Item.Settings()
							.food(FoodComponentsBuilder.buildFoodComponent(3,0.4F))
							.maxCount(16)
			);

	public static final Item LEAFCUTTER_SHROOM_SOUP_ITEM =
			ITEMS.register("leafcutter_shroom_soup",
					new Item.Settings()
							.food(FoodComponentsBuilder.buildFoodComponent(7,0.6F))
							.useRemainder(Items.BOWL)
							.maxCount(1)
			);
	public static final Stat<Identifier> SHEAR_SHROOM_FARM_STAT =
			STATS.register("shear_shroom_farm");

	public static final ItemGroup BUGS_O_PLENTY_ITEM_GROUP =
			ITEM_GROUPS.register("bugs_o_plenty",
					FabricItemGroup.builder()
							.icon(() -> new ItemStack(LEAFCUTTER_SHROOM_ITEM))
							.displayName(Text.translatable("itemgroup.more_bugs.bugs_o_plenty"))
							.entries(((displayContext, entries) -> {
								entries.add(LEAFCUTTER_SHROOM_ITEM);
								entries.add(LEAFCUTTER_SHROOM_SOUP_ITEM);
								entries.add(ModBlocks.SHROOM_FARM_BLOCK);
							}))
			);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Registering Mod " + NAME);
		Registries.Initialize();
		ModBlocks.initialize();
	}


	public String getNamespace() {
		return MOD_ID;
	}

}