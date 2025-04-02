package daplumer.more_bugs.ModRegistries;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

import static net.minecraft.stat.Stats.CUSTOM;

public class ModStatTypeRegisterer implements ModDataRegisterer<Stat<Identifier>, StatFormatter>{
    private final String namespace;
    @Override
    public String getNameSpace() {
        return this.namespace;
    }
    ModStatTypeRegisterer(String namespace){
        this.namespace = namespace;
    }
    /**
     * generate an instance of data with a custom {@code name}, {@code settings}, and {@code dataFactory}.
     * for more implementation information, see {@link GeneralModDataRegisterer#register(String, Object, Function) register}
     * in {@link GeneralModDataRegisterer}.
     *
     * @param name
     * @param instanceSettings
     * @param instanceFactory
     * @see GeneralModDataRegisterer
     * @see GeneralModDataRegisterer#register(String, Object, Function) register
     * @see Registries
     */
    @Override
    public Stat<Identifier> register(@NotNull String name, @Nullable StatFormatter instanceSettings, @Nullable Function<StatFormatter, Stat<Identifier>> instanceFactory) {
        Identifier identifier = getIdentifier(name);
        Registry.register(Registries.CUSTOM_STAT, name, identifier);
        return CUSTOM.getOrCreateStat(identifier, Objects.requireNonNullElse(instanceSettings,StatFormatter.DEFAULT));
    }

    /**
     * @param identifier
     * @return an instance of the data identified by the Identifier given
     * @see ModRegistries
     */
    @Override
    public Stat<Identifier> getInstance(Identifier identifier) {
        return CUSTOM.getOrCreateStat(identifier);
    }

    @Override
    public RegistryKey<Identifier> getRegistryKey(Identifier identifier) {
        return RegistryKey.of(RegistryKeys.CUSTOM_STAT,identifier);
    }
}
