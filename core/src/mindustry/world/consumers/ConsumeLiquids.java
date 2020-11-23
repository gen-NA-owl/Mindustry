package mindustry.world.consumers;

//TODO: remove unused imports
import arc.scene.ui.layout.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.meta.*;
import mindustry.world.meta.values.*;

public class ConsumeLiquids extends Consume{
    public final LiquidStack[] liquids;
    /** Used to calculate perSec values for buildings with craftime != 60 */
    public float timePeriod = 60f;

    public ConsumeLiquids(float timePeriod, LiquidStack[] liquids){
        this.liquids = liquids;
        this.timePeriod = timePeriod;
    }

    /** Mods.*/
    protected ConsumeLiquids(){
        this(60f, LiquidStack.empty);
    }

    @Override
    public void applyLiquidFilter(Bits filter){
        for(LiquidStack stack : liquids){
            filter.set(stack.liquid.id);
        }
    }

    @Override
    public ConsumeType type(){
        return ConsumeType.liquid;
    }

    @Override
    public void build(Building tile, Table table){
        for(LiquidStack stack : liquids){
            //Although it is named ItemImage, it (kinda) supports liquid displays too
            table.add(new ReqImage(new ItemImage(stack.liquid.icon(Cicon.medium), stack.amount),
                () -> tile.liquids != null && tile.liquids.has(stack.liquid, stack.amount))).padRight(8);
        }
    }

    @Override
    public String getIcon(){
        return "icon-liquid-consume";
    }

    @Override
    public void update(Building entity){
        
    }

    @Override
    public void trigger(Building entity){
        for(LiquidStack stack : liquids){
            entity.liquids.remove(stack);
        }
    }

    @Override
    public boolean valid(Building entity){
        return entity.liquids != null && entity.liquids.has(liquids);
    }

    @Override
    public void display(Stats stats){
        stats.add(booster ? Stat.booster : Stat.input, new LiquidListValue(timePeriod, liquids));
    }
}
