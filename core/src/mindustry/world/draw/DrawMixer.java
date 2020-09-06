package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.production.GenericCrafter.*;

public class DrawMixer extends DrawRotator{
    public TextureRegion top;

    @Override
    public void draw(GenericCrafterBuild entity){
        super.draw(entity);

        Draw.rect(top, entity.x, entity.y);
    }

    @Override
    public void load(Block block){
        super.load(block);
        
        top = Core.atlas.find(block.name + "-top");
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{block.region, rotator, top};
    }
}
