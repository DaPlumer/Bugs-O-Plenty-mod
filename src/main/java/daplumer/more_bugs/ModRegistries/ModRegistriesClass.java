package daplumer.more_bugs.ModRegistries;

import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
/**
 * This is a class made to merge all registry methods of different
 * data types to make updating data registration methods easier and centralize data registration.
 * @see ModRegistries
 * @see #register(String, Object, Function)
 * @see Registry
 */

public class ModRegistriesClass<T, S>{
    public final Registry<T> registry;
    public final BiFunction<S, RegistryKey<T>, S> settingsFactory;
    public final Function<S,T> defaultDataFactory;
    public final Supplier<S> defaultSettingsFactory;

    /**
     * @param registry this Is the registry identifier used by the class instance and
     * {@code settingsFactory} and {@code defaultDataFactory} are lambda functions manipulating data settings.
     * This function registers lambda functions associated with data {@link Registry} and the {@link Registry} type
     * @see Registry
     * @see ModRegistries
     * @see #register(String, Object, Function)
     */
    ModRegistriesClass(Registry<T> registry, BiFunction<S, RegistryKey<T>, S> settingsFactory, Function<S,T> defaultDataFactory, Supplier<S> defaultSettingsFactory){

        this.registry = registry;
        this.settingsFactory = settingsFactory;//This lambda function applies registry keys to settings
        this.defaultDataFactory = defaultDataFactory;//This lambda function creates an instance of the data using settings provided
        this.defaultSettingsFactory = defaultSettingsFactory;//This lambda function supplies the default settings when a parameter is ignored
    }

    /**
     * @param name The name of the object being registered
     * @param instanceSettings The settings of the data being registered
     * @param dataFactory Lambda function that turns the data settings into instances. This is so subclasses of objects can be registered too.
     * @summary This function registers a data instance given instance settings
     * @see ModRegistries
     * @see Registry
     */
    public T register(@NotNull String name, @Nullable S instanceSettings, @Nullable Function<S,T> dataFactory){
        RegistryKey<T> key = RegistryKey.of(this.registry.getKey(), Identifier.of(BugsoPlenty.MOD_ID, name)); //create registry key for item

        T dataInstance = Objects.requireNonNullElse(dataFactory, this.defaultDataFactory).apply( this.settingsFactory.apply(Objects.requireNonNullElse(instanceSettings, this.defaultSettingsFactory.get()), key));// apply settings key and turn into data instance

        return Registry.register(this.registry, key, dataInstance);
    }

}
