package mindustry.world.meta.values;

import arc.graphics.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.meta.*;

public class ItemListValue implements StatValue{
    private final ItemStack[] stacks;
    private final boolean displayName;
    private final float time;

    public ItemListValue(ItemStack... stacks){
        this(true, stacks);
    }

    public ItemListValue(boolean displayName, ItemStack... stacks){
        this(displayName, -1, stacks);
    }

    public ItemListValue(boolean displayName, float time, ItemStack... stacks){
        this.displayName = displayName;
        this.stacks = stacks;
        this.time = time;
    }

    @Override
    public void display(Table table){
        for(ItemStack stack : stacks){
            table.add(new ItemDisplay(stack.item, stack.amount, displayName)).padRight(5);

            if(time > 0){
                table.add("(" + Strings.fixed(stack.amount / time * 60f, 1) + StatUnit.perSecond.localized() + ") ").color(Color.lightGray).style(Styles.outlineLabel);
            }
        }
    }
}
