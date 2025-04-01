package daplumer.more_bugs.ModRegistries;

import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

/**
 * Tool that can register objects
 */
public interface ModDataRegisterer<T,S> {
    String getNameSpace();

    /**
     * generate an instance of data with a custom {@code name}, {@code settings}, and {@code dataFactory}.
     * for more implementation information, see {@link GeneralModDataRegisterer#register(String, Object, Function) register}
     * in {@link GeneralModDataRegisterer}.
     *
     * @see GeneralModDataRegisterer
     * @see GeneralModDataRegisterer#register(String, Object, Function) register
     * @see Registries
     */
    T register(@NotNull String name,@Nullable S instanceSettings,@Nullable Function<S,T> instanceFactory);

    /**
     * Get an Identifier of the object given a string
     * @see ModRegistries
     */
    default Identifier getIdentifier(@NotNull String name) {
        return Identifier.of(getNameSpace(), name);
    }

    /**
     * @return an instance of the data identified by the name given
     * @see ModRegistries
     */
    default T getInstance(String name){
        return getInstance(getIdentifier(name));
    }

    /**
     * @return an instance of the data identified by the Identifier given
     * @see ModRegistries
     */
    T getInstance(Identifier identifier);
    default RegistryKey getRegistryKey(String name){
        return getRegistryKey(getIdentifier(name));
    }
    RegistryKey getRegistryKey(Identifier identifier);

    /**
     * generate an instance of data with a custom {@code name} and {@code settings} using the default {@code dataFactory}.
     * for more implementation information, see {@link GeneralModDataRegisterer#register(String, Object, Function) register}
     * in {@link GeneralModDataRegisterer}.
     *
     * @see GeneralModDataRegisterer
     * @see GeneralModDataRegisterer#register(String, Object, Function) register
     * @see Registries
     */
    default T register(String name, @Nullable S instanceSettings) {
        return this.register(name, instanceSettings, null);
    }

    /**
     * generate an instance of data with a custom {@code name} and the default {@code settings} and {@code dataFactory}.
     * for more implementation information, see {@link GeneralModDataRegisterer#register(String, Object, Function) register}
     * in {@link GeneralModDataRegisterer}.
     *
     * @see GeneralModDataRegisterer
     * @see GeneralModDataRegisterer#register(String, Object, Function) register
     * @see Registries
     */
    default T register(String name) {
        return this.register(name, null, null);
    }
}
