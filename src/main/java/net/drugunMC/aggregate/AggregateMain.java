package net.drugunMC.aggregate;

import net.drugunMC.aggregate.entity.projectile.JavelinStoneEntity;
import net.drugunMC.aggregate.item.JavelinStone;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AggregateMain implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("aggregate");

	public static final String ModID = "aggregate";

	public static final net.drugunMC.aggregate.AggregateConfig CONFIG = net.drugunMC.aggregate.AggregateConfig.createAndLoad();



	static {
		AggregateJsonCondProvider.init();
		//PatchedTestConditions.register( new Identifier(ModID, "aggregate-cfg"), new AggregateUtils.AggregatePatchedConfig() );
	}


	public static final Identifier AGGREGATE_BIOME_FEATURES = new Identifier(ModID, "aggregate-biome-features");


	public static final EntityType<JavelinStoneEntity> JavelinStoneEntityType = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(ModID, "javelin_stone_entity"),
			FabricEntityTypeBuilder.<JavelinStoneEntity>create(SpawnGroup.MISC, JavelinStoneEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
					.trackRangeBlocks(28).trackedUpdateRate(5)
					.build()
	);





	private static final Identifier COW_LOOT_TABLE_ID = new Identifier("minecraft", "entities/cow");
	private static final Identifier PIG_LOOT_TABLE_ID = new Identifier("minecraft", "entities/pig");
	private static final Identifier SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep");
	private static final Identifier DONKEY_LOOT_TABLE_ID = new Identifier("minecraft", "entities/donkey");
	private static final Identifier HORSE_LOOT_TABLE_ID = new Identifier("minecraft", "entities/horse");
	private static final Identifier GOAT_LOOT_TABLE_ID = new Identifier("minecraft", "entities/goat");



	public static final Item JAVELIN_STONE = new JavelinStone(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(4));




	@Override
	public void onInitialize() {

		LOGGER.info("Aggregate loaded!");



		Registry.register(Registry.ITEM, new Identifier(ModID, "javelin_stone"), JAVELIN_STONE);


		FabricLoader.getInstance().getModContainer(ModID).ifPresent((modContainer) -> {
			ResourceManagerHelper.registerBuiltinResourcePack(AGGREGATE_BIOME_FEATURES, modContainer, "Aggregate - biomes", ResourcePackActivationType.NORMAL);
		});



		if( AggregateMain.CONFIG.animalBones()){
			LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
				if (source.isBuiltin() && (COW_LOOT_TABLE_ID.equals(id) || HORSE_LOOT_TABLE_ID.equals(id) || DONKEY_LOOT_TABLE_ID.equals(id))) {
					LootPool.Builder poolBuilder = LootPool.builder()
							.with(ItemEntry.builder(Items.BONE))
							.rolls(ConstantLootNumberProvider.create(1));

					tableBuilder.pool(poolBuilder);
				}
				else if (source.isBuiltin() && (GOAT_LOOT_TABLE_ID.equals(id) || PIG_LOOT_TABLE_ID.equals(id) || SHEEP_LOOT_TABLE_ID.equals(id))) {
					LootPool.Builder poolBuilder = LootPool.builder()
							.with(ItemEntry.builder(Items.BONE))
							.rolls(ConstantLootNumberProvider.create(1));

					tableBuilder.pool(poolBuilder);
				}
			});
		}




	}



}
