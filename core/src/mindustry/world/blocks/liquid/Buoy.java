package mindustry.world.blocks.liquid;

import mindustry.world.*;

public class Buoy extends Block{

    public Buoy(String name){
        super(name);
        requiresWater = true;
        solid = false;
        destructible = true;
        canOverdrive = false;
        noUpdateDisabled = true;
    }
}
