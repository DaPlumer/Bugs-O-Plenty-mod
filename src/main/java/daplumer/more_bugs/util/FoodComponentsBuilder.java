package daplumer.more_bugs.util;

import net.minecraft.component.type.FoodComponent;

public class FoodComponentsBuilder {
    /**
     * This is just a small class for building a food component from the available parameters
     */
    public static FoodComponent buildFoodComponent(int nutrition, float saturation, boolean alwaysEdible){
        FoodComponent.Builder builder = new FoodComponent.Builder();
        builder.nutrition(nutrition);
        builder.saturationModifier(saturation);
        if(alwaysEdible){
            builder.alwaysEdible();
        }
        return builder.build();
    }
    public static FoodComponent buildFoodComponent(int nutrition, float saturation){
        return buildFoodComponent(nutrition, saturation, false);
    }
}
