package fi.dy.masa.tweakeroo.mixin;


import com.mojang.blaze3d.systems.RenderSystem;
import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.tweakeroo.config.Configs;
import fi.dy.masa.tweakeroo.config.FeatureToggle;
import fi.dy.masa.tweakeroo.items.ItemList;
import fi.dy.masa.tweakeroo.renderer.RenderUtils;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.gui.screen.ingame.HandledScreen.drawSlotHighlight;

@Mixin(HandledScreen.class)
public abstract class MixinHandledScreen extends DrawableHelper {



    @Inject(method = "drawSlot",at=@At("HEAD"))
    public void injectItemHighlight(MatrixStack matrices, Slot slot, CallbackInfo ci) {


        if (ItemList.INSTANCE.isItemSelected(slot.getStack().getItem()) && FeatureToggle.TWEAK_SELECTED_SCAN_ITEM_HIGHLIGHT.getBooleanValue()) {

            fi.dy.masa.malilib.render.RenderUtils.drawRect(slot.x , slot.y, 16, 16, Configs.Generic.CONTAINER_SCAN_ITEM_HIGHLIGHT_COLOR.getColor().intValue,32);
            // Draw the border
            //fi.dy.masa.malilib.render.RenderUtils.drawOutline(slot.x, slot.y, 16, 16, 1, 0xFF800080,32);
        }

    }

}
