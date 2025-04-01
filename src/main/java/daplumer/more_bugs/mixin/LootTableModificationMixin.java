package daplumer.more_bugs.mixin;

import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LootTables.class)
public interface LootTableModificationMixin {
    @Invoker("registerLootTable")
    static RegistryKey<LootTable> registerLootTable(RegistryKey<LootTable> key) {
        throw new AssertionError();
    }
}
