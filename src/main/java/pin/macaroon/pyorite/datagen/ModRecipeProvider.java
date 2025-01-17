package pin.macaroon.pyorite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import pin.macaroon.pyorite.items.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }



    //private static final List<ItemConvertible> STEEL_SMELTABLES = List.of(ModItems.RAW_STEEL, ModBlocks.RAW_STEEL_BLOCK, ModBlocks.DEEPSLATE_STEEL_ORE);

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        //offerSmelting(exporter, STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT,
        //        0.7f, 200, "steel");

        //offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT, RecipeCategory.DECORATIONS,
        //        ModBlocks.STEEL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CREEPER_COOKIE, 1)
                .pattern(" MM")
                .pattern("GCM")
                .pattern("GG ")
                .input('C', Items.COOKIE)
                .input('M', Items.MOSS_BLOCK)
                .input('G', Items.GUNPOWDER)
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .criterion(hasItem(Items.MOSS_BLOCK), conditionsFromItem(Items.MOSS_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CREEPER_COOKIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ELEMENTITE, 1)
                .pattern("SA") // ayo 🧐
                .pattern("AS") // lmao again 💀
                .input('A', Items.AMETHYST_SHARD)
                .input('S', Items.NETHERITE_SCRAP)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ELEMENTITE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ELEMENTITE_TEMPLATE, 1)
                .pattern("SGS")
                .pattern("STS")
                .pattern("DDD")
                .input('S', Items.AMETHYST_SHARD)
                .input('G', Blocks.GOLD_BLOCK)
                .input('D', Blocks.COBBLED_DEEPSLATE)
                .input('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK))
                .criterion(hasItem(Blocks.DEEPSLATE), conditionsFromItem(Blocks.DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ELEMENTITE_TEMPLATE)));


        //TODO add normal crafting recipe for aeroblade
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.AEROBLADE, 1)
                .pattern(" I ")
                .input('I', Items.IRON_SWORD)
                .criterion(hasItem(Items.IRON_SWORD), conditionsFromItem(Items.IRON_SWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.AEROBLADE)));

        /*
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CRYSTAL_METH, 1)
                .input(ModItems.STEEL_INGOT)
                .input(Items.QUARTZ)
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CRYSTAL_METH)));

         */


        offerCustomUpgradeRecipe(exporter, Items.NETHERITE_HOE, RecipeCategory.COMBAT, ModItems.SYTHE, ModItems.ELEMENTITE_TEMPLATE, ModItems.ELEMENTITE);
    }

    public static void offerCustomUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item input, RecipeCategory category, Item result, Item template, Item material) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template), Ingredient.ofItems(input), Ingredient.ofItems(material), category, result
                )
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }
}
