package net.fabricmc.example.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class ExampleMixin {
	@Inject(method = "calcBlockBreakingDelta", at = @At("HEAD"), cancellable = true)
	public void calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos,
			CallbackInfoReturnable<Float> ci) {

		float f = state.getHardness(world, pos);
		if (state.getBlock() == Blocks.DEEPSLATE) {
            int i = player.canHarvest(state) ? 30 : 100;
            ci.setReturnValue(player.getBlockBreakingSpeed(state) / 1.5f / (float)i);
		}
        else if(f == -1.0F) {
			ci.setReturnValue(0.0F);
        } else {
            int i = player.canHarvest(state) ? 30 : 100;
            ci.setReturnValue(player.getBlockBreakingSpeed(state) / f / (float)i);
        }

	}
}