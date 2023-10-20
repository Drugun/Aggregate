package net.drugunMC.aggregate.mixin;


import net.drugunMC.aggregate.AggregateMain;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin extends Block {


    @Shadow @Final protected FlowableFluid fluid;

    @Shadow public abstract FluidState getFluidState(BlockState state);

    public FluidBlockMixin(FlowableFluid fluid, AbstractBlock.Settings settings) {
        super(settings);
    }


    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if(context.getPlayer() == null && !AggregateMain.CONFIG.disallowReplaceFluidNonPlayer() ){
            return super.canReplace(state, context);
        }
        if(this.fluid == Fluids.WATER){
            if(this.getFluidState(state).isStill()){
                if(AggregateMain.CONFIG.disallowReplaceWaterSource()){
                    return false;
                }
                else{
                    return super.canReplace(state, context);
                }
            }
            else{
                if(AggregateMain.CONFIG.disallowReplaceWaterFlowing()){
                    return false;
                }
                else{
                    return super.canReplace(state, context);
                }
            }
        }
        else if(this.fluid == Fluids.LAVA){
            if(this.getFluidState(state).isStill()){
                if(AggregateMain.CONFIG.disallowReplaceLavaSource()){
                    return false;
                }
                else{
                    return super.canReplace(state, context);
                }
            }
            else{
                if(AggregateMain.CONFIG.disallowReplaceLavaFlowing()){
                    return false;
                }
                else{
                    return super.canReplace(state, context);
                }
            }
        }
        return super.canReplace(state, context);
    }

    @Override
    public boolean canBucketPlace(BlockState state, Fluid fluid) {
        if(AggregateMain.CONFIG.bucketExploitFix() && fluid == this.fluid && this.getFluidState(state).isStill()){
            return false;
        }
        else{
            return super.canBucketPlace(state, fluid);
        }

    }
}
