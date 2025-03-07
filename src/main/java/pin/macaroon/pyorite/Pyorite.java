package pin.macaroon.pyorite;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pin.macaroon.pyorite.block.ModBlocks;
import pin.macaroon.pyorite.entity.ModEntities;
import pin.macaroon.pyorite.entity.effect.ModStatusEffects;
import pin.macaroon.pyorite.etc.ModParticles;
import pin.macaroon.pyorite.etc.ModSoundEvents;
import pin.macaroon.pyorite.etc.ModTrades;
import pin.macaroon.pyorite.items.ModItemTags;
import pin.macaroon.pyorite.items.ModItems;
import pin.macaroon.pyorite.items.ModLootTableModifiers;

public class Pyorite implements ModInitializer {
	public static final String MOD_ID = "pyorite";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("I am alive and ready to take over your world :)");
		ModItems.regModItems();
		ModItemTags.regTags();
		ModEntities.registerEntities();
		ModStatusEffects.registerModEffects();
		ModLootTableModifiers.modifyLootTables();
		ModParticles.regParticles();
		ModBlocks.registerModBlocks();
		ModSoundEvents.regSounds();
		ModTrades.regTrades();
	}
}