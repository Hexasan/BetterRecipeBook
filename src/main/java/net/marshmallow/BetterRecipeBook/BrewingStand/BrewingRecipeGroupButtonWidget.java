package net.marshmallow.BetterRecipeBook.BrewingStand;

import com.mojang.blaze3d.systems.RenderSystem;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.recipebook.BrewingRecipeBookGroup;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class BrewingRecipeGroupButtonWidget extends ToggleButtonWidget {
    private final BrewingRecipeBookGroup group;
    private static final float field_32412 = 15.0F;
    private ClientBrewingStandRecipeBook recipeBook;

    public BrewingRecipeGroupButtonWidget(BrewingRecipeBookGroup category, ClientBrewingStandRecipeBook recipeBook) {
        super(0, 0, 35, 27, false);
        this.group = category;
        this.recipeBook = recipeBook;
        this.setTextureUV(153, 2, 35, 0, BrewingStandRecipeBookWidget.TEXTURE);
    }

    public void checkForNewRecipes(MinecraftClient client) {
        ClientBrewingStandRecipeBook clientRecipeBook = recipeBook;
        // List<RecipeResultCollection> list = clientRecipeBook.getResultsForGroup(this.category);
        // if (client.player.currentScreenHandler instanceof AbstractRecipeScreenHandler) {
        //     Iterator var4 = list.iterator();
//
        //     while(var4.hasNext()) {
        //         RecipeResultCollection recipeResultCollection = (RecipeResultCollection)var4.next();
        //         Iterator var6 = recipeResultCollection.getResults(clientRecipeBook.isFilteringCraftable((AbstractRecipeScreenHandler)client.player.currentScreenHandler)).iterator();
//
        //         while(var6.hasNext()) {
        //             Recipe<?> recipe = (Recipe)var6.next();
        //             if (clientRecipeBook.shouldDisplay(recipe)) {
        //                 this.bounce = 15.0F;
        //                 return;
        //             }
        //         }
        //     }
//
        // }
    }

    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, this.texture);
        RenderSystem.disableDepthTest();
        int i = this.u;
        int j = this.v;
        if (this.toggled) {
            i += this.pressedUOffset;
        }

        if (this.isHovered()) {
            j += this.hoverVOffset;
        }

        int k = this.x;
        if (this.toggled) {
            k -= 2;
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexture(matrices, k, this.y, i, j, this.width, this.height);
        RenderSystem.enableDepthTest();
        this.renderIcons(minecraftClient.getItemRenderer());
    }

    private void renderIcons(ItemRenderer itemRenderer) {
        List<ItemStack> list = this.group.getIcons();
        int i = this.toggled ? -2 : 0;
        if (list.size() == 1) {
            itemRenderer.renderInGui((ItemStack)list.get(0), this.x + 9 + i, this.y + 5);
        } else if (list.size() == 2) {
            itemRenderer.renderInGui((ItemStack)list.get(0), this.x + 3 + i, this.y + 5);
            itemRenderer.renderInGui((ItemStack)list.get(1), this.x + 14 + i, this.y + 5);
        }

    }

    public BrewingRecipeBookGroup getGroup() {
        return this.group;
    }
}