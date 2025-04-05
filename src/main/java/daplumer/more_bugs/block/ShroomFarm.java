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
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;

public class ShroomFarm extends Block {

    public static final IntProperty FILL_LEVEL = IntProperty.of("fill_level", 0,2);
    public static final BooleanProperty LEAVES = BooleanProperty.of("leaves");
    public static final MapCodec<ShroomFarm> CODEC = createCodec(ShroomFarm::new);
    public ShroomFarm(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FILL_LEVEL,0).with(LEAVES,false));
    }
    private static final TagKey<Item> SHEARS_TAG = TagKey.of(RegistryKeys.ITEM, Identifier.of("c:tools/shears"));
    private static final TagKey<Item> LEAVES_TAG = TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft:leaves"));

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int fill_level = state.get(FILL_LEVEL);
        if(stack.isIn(LEAVES_TAG) &! state.get(LEAVES)){
            // get the sound associated with the item held or default it to oak leaves breaking
            SoundEvent sound = Blocks.OAK_LEAVES.getDefaultState().getSoundGroup().getBreakSound();
            if (stack.getItem() instanceof BlockItem _blockItem) sound = _blockItem.getBlock().getDefaultState().getSoundGroup().getBreakSound();

            world.playSoundAtBlockCenter(pos,sound, SoundCategory.BLOCKS,1,1,true);
            world.setBlockState(pos, state.with(LEAVES, true), NOTIFY_ALL_AND_REDRAW);
            player.incrementStat(BugsoPlenty.FILL_SHROOM_FARM_STAT);
            stack.decrementUnlessCreative(1, player);
            return ActionResult.SUCCESS;
        }
        if(stack.isIn(SHEARS_TAG) & fill_level > 0){
            //copy of the pumpkin shearing script with some modifications
            if (player instanceof ServerPlayerEntity serverPlayerEntity) Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayerEntity, pos, stack);
            world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_BOGGED_SHEAR, SoundCategory.BLOCKS, 1,1,true);
            stack.damage(1,player, LivingEntity.getSlotForHand(hand));
            world.setBlockState(pos,state.with(FILL_LEVEL, 0), NOTIFY_ALL_AND_REDRAW);
            world.emitGameEvent(player,GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
            player.incrementStat(BugsoPlenty.SHEAR_SHROOM_FARM_STAT);
            Direction direction = player.getFacing().getOpposite();
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    pos.getX() + 0.5 + direction.getOffsetX() * 0.65,
                    pos.getY() + 0.1,
                    pos.getZ() + 0.5 + direction.getOffsetZ() * 0.65,
                    new ItemStack(BugsoPlenty.LEAFCUTTER_SHROOM_ITEM, (state.get(FILL_LEVEL) == 1)? 1:3)
            );
            itemEntity.setVelocity(
                    0.05 * direction.getOffsetX() + world.random.nextDouble() * 0.02,
                    0.05 * direction.getOffsetY() + world.random.nextDouble() * 0.02,
                    0.05 * direction.getOffsetZ() + world.random.nextDouble() * 0.02
            );
            world.spawnEntity(itemEntity);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
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
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILL_LEVEL);
        builder.add(LEAVES);
        super.appendProperties(builder);
    }
}
