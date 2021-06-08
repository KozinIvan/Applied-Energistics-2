package appeng.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.item.ItemStack;

import appeng.hooks.ItemRendererHooks;

/**
 * This mixin specifically targets rendering of items in the user interface to allow us to customize _only_ the UI
 * representation of an item, and none of the others (held items, in-world, etc.)
 */
@Mixin(ItemRenderer.class)
public abstract class RenderEncodedPatternMixin {

    @Inject(method = "renderItemModelIntoGUI", at = @At("HEAD"), cancellable = true)
    protected void renderGuiItemModel(ItemStack stack, int x, int y, IBakedModel model, CallbackInfo ci) {
        if (ItemRendererHooks.onRenderGuiItemModel((ItemRenderer) (Object) this, stack, x, y, model)) {
            ci.cancel();
        }
    }

}