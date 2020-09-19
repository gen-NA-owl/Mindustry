package mindustry.world.blocks.power;

import arc.*;
import mindustry.type.Item;
import mindustry.world.meta.*;

public class DecayGenerator extends ItemLiquidGenerator{
	public Attribute attribute = Attribute.heat;

    public DecayGenerator(String name){
        super(true, false, name);
        hasItems = true;
        hasLiquids = false;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(BlockStat.tiles, attribute, -0.5f);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        drawPlaceText(Core.bundle.formatFloat("bar.efficiency", 100f + (sumAttribute(attribute, x, y) * -12.5f), 1), x, y, valid);
    }

    @Override
    protected float getItemEfficiency(Item item){
        if(item == null) return 0.0f;
        return item.radioactivity;
    }

    public class DecayGeneratorBuild extends ItemLiquidGeneratorBuild{
        public float sum;

        @Override
        public void updateProductionEfficiency(float baseEfficiency) {
            productionEfficiency = (sum + attribute.env()) * baseEfficiency;
        }

        @Override
        public void onProximityAdded(){
            super.onProximityAdded();

            sum = 1f + (sumAttribute(attribute, tile.x, tile.y) / -8f);
        }
    }
}
