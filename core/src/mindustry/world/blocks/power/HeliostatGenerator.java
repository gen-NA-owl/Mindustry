package mindustry.world.blocks.power;

import arc.math.*;
import arc.util.*;
import arc.struct.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.power.SolarGenerator.*;

import static mindustry.Vars.*;

public class HeliostatGenerator extends ItemLiquidGenerator{
    public float range = 150f;

    public HeliostatGenerator(String name){
        super(false, true, name);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.accent);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(generationType);
        stats.add(generationType, 0f, StatUnit.powerSecond);
    }

    @Override
    protected float getLiquidEfficiency(Liquid liquid){
        return 1f;
    }

    public class HeliostatGeneratorBuild extends ItemLiquidGeneratorBuild{
        /** Power production in this block means the multiplier of power, and there is a different variable keeping track of power */
        public float currentPowerProduction = 0f;

        @Override
        public float getPowerProduction(){
            return currentPowerProduction * productionEfficiency;
        }

        @Override
        public void updateTile(){
            currentPowerProduction = 0f;
            if(enabled && productionEfficiency > 0.001f) {
                indexer.eachBlock(this, range, other -> other.block instanceof SolarGenerator, other -> {
                    currentPowerProduction += ((SolarGenerator)other.block).powerProduction * ((SolarGeneratorBuild)other).productionEfficiency * powerProduction;
                    ((SolarGeneratorBuild)other).productionEfficiency = 0f;
                });
            }

            super.updateTile();
        }

        @Override
        public void drawSelect(){
            indexer.eachBlock(this, range, other -> other.block instanceof SolarGenerator, other -> Drawf.selected(other, Tmp.c1.set(Pal.accent).a(Mathf.absin(4f, 1f))));

            Drawf.dashCircle(x, y, range, Pal.accent);
        }
    }
}
