package daplumer.more_bugs.ModRegistries;

import com.google.common.base.Supplier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;
/**
 * This is a wrapper class for the {@link ModRegistriesClass} to merge all data registration into two files. Find more information there.
 * @see ModRegistriesClass
 * @see Registry
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public enum ModRegistries {

    ITEM(Registries.ITEM, Item.Settings::registryKey, Item::new, Item.Settings::new),
    BLOCK(Registries.BLOCK, Block.Settings::registryKey, Block::new, Block.Settings::create),
    ITEM_GROUP(Registries.ITEM_GROUP, (builder, key) -> builder, ItemGroup.Builder::build, FabricItemGroup::builder);

    /**
     * the initializer for an instance of a data registration tool
     */
    private final ModRegistriesClass classInstance;
    <T,S>ModRegistries(Registry<T> registry, BiFunction<S, RegistryKey<T>, S> settingsFactory, Function<S,T> defaultDataFactory, Supplier<S> defaultSettingsFactory){
        this.classInstance = new ModRegistriesClass(registry,settingsFactory,defaultDataFactory,defaultSettingsFactory);
    }

    /**
     * generate an instance of data with a custom {@code name}, {@code settings}, and {@code dataFactory}.
     * for more implementation information, see {@link ModRegistriesClass#register(String, Object, Function) register}
     * in {@link ModRegistriesClass}.
     * @see ModRegistriesClass
     * @see ModRegistriesClass#register(String, Object, Function) register
     * @see Registries
     */
    public <T,S> T register(@NotNull String name, @Nullable S instanceSettings, @Nullable Function<S,T> dataFactory){
        return (T) this.classInstance.register(name,instanceSettings,dataFactory);
    }

    /**
     * generate an instance of data with a custom {@code name} and {@code settings} using the default {@code dataFactory}.
     * for more implementation information, see {@link ModRegistriesClass#register(String, Object, Function) register}
     * in {@link ModRegistriesClass}.
     * @see ModRegistriesClass
     * @see ModRegistriesClass#register(String, Object, Function) register
     * @see Registries
     */
    public <T,S> T register(String name, @Nullable S instanceSettings){
        return register(name,instanceSettings, null);
    }
    /**
     * generate an instance of data with a custom {@code name} and the default {@code settings} and {@code dataFactory}.
     * for more implementation information, see {@link ModRegistriesClass#register(String, Object, Function) register}
     * in {@link ModRegistriesClass}.
     * @see ModRegistriesClass
     * @see ModRegistriesClass#register(String, Object, Function) register
     * @see Registries
     */
    public <T> T register(String name){
        return register(name, null, null);
    }
}
