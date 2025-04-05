package daplumer.more_bugs.block;

import com.mojang.serialization.MapCodec;
import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ShroomFarm extends Block {

    public static final IntProperty FILL_LEVEL = IntProperty.of("fill_level", 0,2);
    public static final BooleanProperty LEAVES = BooleanProperty.of("leaves");
    public static final MapCodec<ShroomFarm> CODEC = createCodec(ShroomFarm::new);
    public ShroomFarm(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FILL_LEVEL,0).with(LEAVES,false));
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int fill_level = state.get(FILL_LEVEL);
        if(stack.isOf(Items.SHEARS) & fill_level > 0){
            if (player instanceof ServerPlayerEntity serverPlayerEntity) Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayerEntity, pos, stack);
            world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_BOGGED_SHEAR, SoundCategory.BLOCKS, 1,1,true);
            stack.damage(1,player, LivingEntity.getSlotForHand(hand));
            world.setBlockState(pos,state.with(FILL_LEVEL, 0), NOTIFY_ALL_AND_REDRAW);
            world.emitGameEvent(player,GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            player.incrementStat(BugsoPlenty.SHEAR_SHROOM_FARM_STAT);
            Direction direction = hit.getSide();
            Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5 + direction2.getOffsetX() * 0.65,
                    pos.getY() + 0.1,
                    pos.getZ() + 0.5 + direction2.getOffsetZ() * 0.65,
                    new ItemStack(BugsoPlenty.LEAFCUTTER_SHROOM_ITEM, (state.get(FILL_LEVEL) == 1)? 1:3)
            );
            itemEntity.setVelocity(
                    0.05 * direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * direction2.getOffsetZ() + world.random.nextDouble() * 0.02
            );
            world.spawnEntity(itemEntity);
        }

        return ActionResult.FAIL;
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.updateFillLevel(state,world,pos);
        super.randomTick(state, world, pos, random);
    }
    private void updateFillLevel(BlockState state, ServerWorld world, BlockPos pos){
        int fill = state.get(FILL_LEVEL);
        boolean leaves = state.get(LEAVES);
        if(leaves && fill != 2){
            world.setBlockState(pos,state.with(FILL_LEVEL,fill + 1).with(LEAVES,false),NOTIFY_ALL_AND_REDRAW);
        }
        world.setBlockState(pos,state.with(LEAVES,true),NOTIFY_ALL_AND_REDRAW);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILL_LEVEL);
        builder.add(LEAVES);
        super.appendProperties(builder);
    }
}
