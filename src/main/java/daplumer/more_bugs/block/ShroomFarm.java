package daplumer.more_bugs.block;

import com.mojang.serialization.MapCodec;
import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ShroomFarm extends Block {
    public static final IntProperty FILL = IntProperty.of("fill_level", 0,2);
    public static final BooleanProperty LEAVES = BooleanProperty.of("leaves");
    public static final MapCodec<ShroomFarm> CODEC = createCodec(ShroomFarm::new);
    public ShroomFarm(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int fill_level = state.get(FILL);
        if(stack.isOf(Items.SHEARS) & fill_level > 0){
            if (player instanceof ServerPlayerEntity serverPlayerEntity) Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayerEntity, pos, stack);
            world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_BOGGED_SHEAR, SoundCategory.BLOCKS, 1,1,true);
            stack.damage(1,player, LivingEntity.getSlotForHand(hand));
            world.setBlockState(pos,state.with(FILL, 0));
            world.emitGameEvent(player,GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            player.incrementStat(Stats.CUSTOM.getOrCreateStat(Identifier.of(BugsoPlenty.MOD_ID,"")));

        }
        return ActionResult.FAIL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FILL, LEAVES);
        super.appendProperties(builder);
    }
}
