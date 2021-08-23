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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractBlock.class)
public class ExampleMixin {
	@Inject(method = "calcBlockBreakingDelta", at = @At("HEAD"), cancellable = true)
	public void calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos,
			CallbackInfoReturnable<Float> ci) {

		if (state.getBlock() == Blocks.DEEPSLATE) {
				ci.setReturnValue(player.getBlockBreakingSpeed(state) / 1.5f / 30.0f);
			}
		}
	}