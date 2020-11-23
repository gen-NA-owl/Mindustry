package mindustry.world.meta.values;

//TODO: remove unused imports
import arc.scene.ui.layout.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.meta.*;

public class LiquidListValue implements StatValue{
    private final LiquidStack[] stacks;
    private final float timePeriod;

    public LiquidListValue(float timePeriod, LiquidStack... stacks){
        this.stacks = stacks;
        this.timePeriod = timePeriod;
    }

    @Override
    public void display(Table table){
        for(LiquidStack stack : stacks){
            table.add(new LiquidDisplay(stack.liquid, stack.amount / timePeriod * 60f, true)).padRight(5);
        }
    }
}
