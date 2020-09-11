package mindustry.world.blocks.storage;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.io.*;
import arc.struct.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;

import static mindustry.Vars.*;

public class Unloader extends Block{
    public float speed = 1f;
    public final int timerUnload = timers++;

    public Unloader(String name){
        super(name);
        update = true;
        solid = true;
        health = 70;
        hasItems = true;
        configurable = true;
        saveConfig = true;
        itemCapacity = 0;
        noUpdateDisabled = true;

        config(Item.class, (UnloaderBuild tile, Item item) -> tile.sortItems.add(item));
        configClear((UnloaderBuild tile) -> tile.sortItems.clear());
    }

    @Override
    public void drawRequestConfig(BuildPlan req, Eachable<BuildPlan> list){
        drawRequestConfigCenter(req, req.config, "unloader-center");
    }

    @Override
    public void setBars(){
        super.setBars();
        bars.remove("items");
    }

    public class UnloaderBuild extends Building{
        public Seq<Item> sortItems = new Seq<Item>();
        public Building dumpingTo;
        public int takeIndex = 0;

        @Override
        public void updateTile(){
            if(timer(timerUnload, speed / timeScale())){
                for(Building other : proximity){
                    if(other.interactable(team) && other.block.unloadable && other.block.hasItems
                        && ((sortItems.size == 0 && other.items.total() > 0) || (sortItems.size != 0 && other.items.has(sortItems.get(takeIndex))))){
                        //make sure the item can't be dumped back into this block
                        dumpingTo = other;

                        //get item to be taken
                        Item item = sortItems.size == 0 ? other.items.beginTake() : sortItems.get(takeIndex);

                        //remove item if it's dumped correctly
                        if(put(item)){
                            if(sortItems.size == 0){
                                other.items.endTake(item);
                            }else{
                                other.items.remove(item, 1);
                            }
                        }
                    }
                }
                //update takeIndex
                takeIndex = takeIndex >= sortItems.size - 1 ? 0 : takeIndex + 1;
            }
        }

        @Override
        public void draw(){
            super.draw();

            Draw.color(sortItems.size == 0 ? Color.clear : sortItems.get(0).color);
            Draw.rect("unloader-center", x, y);
            Draw.color();
        }

        @Override
        public void buildConfiguration(Table table){
            ItemSelection.buildTable(table, content.items(), sortItems, this::configure);
        }

        @Override
        public boolean onConfigureTileTapped(Building other){
            if(this == other){
                deselect();
                configure(null);
                return false;
            }

            return true;
        }

        @Override
        public boolean canDump(Building to, Item item){
            return !(to.block instanceof StorageBlock) && to != dumpingTo;
        }

        @Override
        public Item config(){
            return sortItems.size == 0 ? null : sortItems.get(0);
        }

        @Override
        public byte version(){
            return 1;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.s(sortItems.size == 0 ? -1 : sortItems.get(0).id);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            int id = revision == 1 ? read.s() : read.b();
            if(sortItems.contains(content.items().get(id))) {
                sortItems.remove(content.items().get(id));
            } else {
                sortItems.add(content.items().get(id));
            }
        }
    }
}
