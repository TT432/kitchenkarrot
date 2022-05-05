package io.github.tt432.kitchenkarrot.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.util.json.JsonUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CocktailModelRegister {
    private static final Map<ResourceLocation, BakedModel> MODEL_MAP = new HashMap<>();

    public static BakedModel get(ResourceLocation resourceLocation) {
        return MODEL_MAP.get(resourceLocation);
    }

    public static void render(BakedModel bakedModel, MultiBufferSource bufferSource, BlockEntity blockEntity,
                              PoseStack poseStack, int packedLight, int packedOverlay) {

        VertexConsumer buffer = bufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(blockEntity.getBlockState(), false));

        poseStack.pushPose();
        Minecraft.getInstance().getBlockRenderer().getModelRenderer()
                .renderModel(poseStack.last(), buffer, blockEntity.getBlockState(),
                        bakedModel, 1, 1, 1, packedLight, packedOverlay);
        poseStack.popPose();
    }

    static ResourceLocation from(ModelResourceLocation modelResourceLocation) {
        return new ResourceLocation(modelResourceLocation.getNamespace(), modelResourceLocation.getPath().split("cocktail/")[1]);
    }

    static ModelResourceLocation to(ResourceLocation resourceLocation) {
        return new ModelResourceLocation(resourceLocation.getNamespace(), "cocktail/" + resourceLocation.getPath(), "inventory");
    }

    @SubscribeEvent
    public static void registerModelUnBake(ModelRegistryEvent e) {
        ResourceManager manager = Minecraft.getInstance().getResourceManager();

        for (String namespace : manager.getNamespaces()) {
            try {
                var resourceName = new ResourceLocation(namespace, "cocktail/list.json");

                if (manager.hasResource(resourceName)) {
                    var resources = manager.getResources(resourceName);

                    for (Resource resource : resources) {
                        var reader = new InputStreamReader(resource.getInputStream());
                        var list = JsonUtils.INSTANCE.noExpose.fromJson(reader, CocktailList.class);

                        CocktailList.INSTANCE.cocktails.addAll(list.cocktails);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        for (String cocktailName : CocktailList.INSTANCE.cocktails) {
            var name = new ResourceLocation(cocktailName);
            var namespace = name.getNamespace();
            var path = name.getPath();

            var json = "{\"parent\": \"minecraft:item/generated\", \"textures\": {\"layer0\":\"" + namespace + ":item/cocktail/" + path + "\"}}";
            ForgeModelBakery.addSpecialModel(to(new ResourceLocation(cocktailName)));

            var instance = ForgeModelBakery.instance();
            var model = BlockModel.fromString(json);

            instance.unbakedCache.put(to(name), model);
            instance.topLevelModels.put(to(name), model);
        }
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent evt) {
        MODEL_MAP.clear();

        for (String cocktailName : CocktailList.INSTANCE.cocktails) {
            var modelName = to(new ResourceLocation(cocktailName));
            MODEL_MAP.put(from(modelName), evt.getModelManager().getModel(modelName));
        }

        evt.getModelRegistry().put(new ModelResourceLocation(
                Kitchenkarrot.MOD_ID,
                "cocktail",
                "inventory"
        ), new CocktailBakedModel());
    }

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(Kitchenkarrot.MOD_ID, path);
    }
}
