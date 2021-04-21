package mindustry.world.blocks.power;

import arc.*;
import arc.func.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.annotations.Annotations.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class WindGenerator extends PowerGenerator{
    /** Range of area around this that are checked. */
    public float clearanceNeeded = 40;
    /** Amount of efficiency lost per tile occupied. */
    public float efficiencyLost = 0.1f;
    public float turbineRotateSpeed = 15f;
    public @Load("@-turbine") TextureRegion turbineRegion;

    public WindGenerator(String name){
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();
        
        //TODO: yummy stats
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, turbineRegion};
    }

    /** Iterate through tiles around this and return amount of tiles occupied. */
    public int tilesAround(float x, float y, Cons<Tile> cons){
        Seq<Tile> tiles = new Seq<>();
        indexer.eachTile(x, y, clearanceNeeded, other -> 
            other.block().solid && other != world.tileWorld(x, y) &&
            (other.build == null || other == other.build.tile()), other -> {
            tiles.add(other);
            cons.get(other);
        });

        int lost = 0;
        for(Tile t : tiles){
            lost += t.build == null ? 1 : t.block().size * t.block().size;
        }

        return lost;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, clearanceNeeded, Pal.lightishGray);

        int lost = tilesAround(x * tilesize + offset, y * tilesize + offset, other -> Drawf.selected(other, Tmp.c1.set(Pal.lightishGray).a(Mathf.absin(4f, 1f))));
        drawPlaceText(Core.bundle.formatFloat("bar.efficiency", Mathf.maxZero(1 - lost * efficiencyLost) * 100, 1), x, y, valid);
    }

    public class WindGeneratorBuild extends GeneratorBuild{
        public float turbineRot = 0;

        @Override
        public void updateTile(){
            productionEfficiency = enabled ? Mathf.maxZero(Attribute.wind.env()) : 0f;

            int lost = tilesAround(x, y, other -> {});
            productionEfficiency *= Mathf.maxZero(1 - lost * efficiencyLost);

            turbineRot += productionEfficiency * turbineRotateSpeed * edelta();
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, clearanceNeeded, Pal.lightishGray);

            tilesAround(x, y, other -> Drawf.selected(other, Tmp.c1.set(Pal.lightishGray).a(Mathf.absin(4f, 1f))));
        }

        @Override
        public void draw(){
            super.draw();

            Draw.rect(turbineRegion, x, y, turbineRot);
        }
    }
}
