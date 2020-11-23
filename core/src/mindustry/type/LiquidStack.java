package mindustry.type;

import mindustry.content.*;

public class LiquidStack{
    public static final LiquidStack[] empty = {};

    public Liquid liquid;
    public float amount;

    public LiquidStack(Liquid liquid, float amount){
        this.liquid = liquid;
        this.amount = amount;
    }

    /** serialization only*/
    protected LiquidStack(){
        liquid = Liquids.water;
    }

    //dammit java
    public static LiquidStack[] with(Liquid l, Object... liquids){
        LiquidStack[] stacks = new LiquidStack[(liquids.length + 1) / 2];
        stacks[0] = new LiquidStack(l, ((Number)liquids[0]).floatValue());
        for(int i = 1; i < liquids.length; i += 2){
            stacks[(i + 1) / 2] = new LiquidStack((Liquid)liquids[i], ((Number)liquids[i + 1]).floatValue());
        }
        return stacks;
    }

    @Override
    public String toString(){
        return "LiquidStack{" +
        "liquid=" + liquid +
        ", amount=" + amount +
        '}';
    }
}
