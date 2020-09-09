package mindustry.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.ctype.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.experimental.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;

public class Blocks implements ContentList{
    public static Block

    //environment
    air, spawn, cliff, deepwater, water, taintedWater, tar, slag, stone, craters, charr, sand, darksand, ice, snow, darksandTaintedWater,
    holostone, rocks, sporerocks, icerocks, cliffs, sporePine, snowPine, pine, shrubs, whiteTree, whiteTreeDead, sporeCluster,
    iceSnow, sandWater, darksandWater, duneRocks, sandRocks, moss, sporeMoss, shale, shaleRocks, shaleBoulder, sandBoulder, grass, salt,
    metalFloor, metalFloorDamaged, metalFloor2, metalFloor3, metalFloor5, ignarock, magmarock, hotrock, snowrocks, rock, snowrock, saltRocks,
    darkPanel1, darkPanel2, darkPanel3, darkPanel4, darkPanel5, darkPanel6, darkMetal,
    pebbles, tendrils,

    //ores
    oreCopper, oreLead, oreScrap, oreCoal, oreTitanium, oreThorium,

    //crafting
    siliconSmelter, siliconCrucible, kiln, graphitePress, plastaniumCompressor, multiPress, phaseWeaver, surgeSmelter, pyratiteMixer, blastMixer, cryofluidMixer,
    melter, separator, disassembler, sporePress, pulverizer, incinerator, coalCentrifuge,

    //sandbox
    powerSource, powerVoid, itemSource, itemVoid, liquidSource, liquidVoid, illuminator,

    //defense
    copperWall, copperWallLarge, titaniumWall, titaniumWallLarge, plastaniumWall, plastaniumWallLarge, thoriumWall, thoriumWallLarge, door, doorLarge,
    phaseWall, phaseWallLarge, surgeWall, surgeWallLarge, mender, mendProjector, overdriveProjector, overdriveDome, forceProjector, shockMine,
    scrapWall, scrapWallLarge, scrapWallHuge, scrapWallGigantic, thruster, //ok, these names are getting ridiculous, but at least I don't have humongous walls yet

    //transport
    conveyor, titaniumConveyor, plastaniumConveyor, armoredConveyor, distributor, junction, itemBridge, phaseConveyor, sorter, invertedSorter, router,
    overflowGate, underflowGate, massDriver, payloadConveyor, payloadRouter,

    //liquid
    mechanicalPump, rotaryPump, thermalPump, conduit, pulseConduit, platedConduit, liquidRouter, liquidTank, liquidJunction, bridgeConduit, phaseConduit,

    //power
    combustionGenerator, thermalGenerator, turbineGenerator, differentialGenerator, rtgGenerator, solarPanel, largeSolarPanel, thoriumReactor,
    impactReactor, battery, batteryLarge, powerNode, powerNodeLarge, surgeTower, diode,

    //production
    mechanicalDrill, pneumaticDrill, laserDrill, blastDrill, waterExtractor, oilExtractor, cultivator,

    //storage
    coreShard, coreFoundation, coreNucleus, vault, container, unloader,

    //turrets
    duo, scatter, scorch, hail, arc, wave, lancer, swarmer, salvo, fuse, ripple, cyclone, spectre, meltdown, segment, parallax,

    //units
    commandCenter,
    groundFactory, airFactory, navalFactory,
    additiveReconstructor, multiplicativeReconstructor, exponentialReconstructor, tetrativeReconstructor,
    repairPoint, resupplyPoint,

    //logic
    message, switchBlock, microProcessor, logicProcessor, hyperProcessor, logicDisplay, memoryCell,

    //campaign
    launchPad, launchPadLarge,

    //misc experimental
    blockForge, blockLoader, blockUnloader;

    @Override
    public void load(){
        //region environment

        air = new Floor("air"){
            {
                alwaysReplace = true;
                hasShadow = false;
            }

            @Override public void drawBase(Tile tile){}
            @Override public void load(){}
            @Override public void init(){}
            @Override public boolean isHidden(){ return true; }

            @Override
            public TextureRegion[] variantRegions(){
                if(variantRegions == null){
                    variantRegions = new TextureRegion[]{Core.atlas.find("clear")};
                }
                return variantRegions;
            }
        };

        spawn = new OverlayFloor("spawn"){
            {
                variants = 0;
            }
            @Override
            public void drawBase(Tile tile){}
        };

        cliff = new Cliff("cliff"){{
            inEditor = false;
            saveData = true;
        }};

        //Registers build blocks
        //no reference is needed here since they can be looked up by name later
        for(int i = 1; i <= ConstructBlock.maxSize; i++){
            new ConstructBlock(i);
        }

        deepwater = new Floor("deepwater"){{
            albedo = 0.5f;
            cacheLayer = CacheLayer.water;
            drownTime = 140f;
            isLiquid = true;
            liquidDrop = Liquids.water;
            liquidMultiplier = 1.5f;
            speedMultiplier = 0.2f;
            status = StatusEffects.wet;
            statusDuration = 120f;
            variants = 0;
        }};

        water = new Floor("water"){{
            albedo = 0.5f;
            cacheLayer = CacheLayer.water;
            isLiquid = true;
            liquidDrop = Liquids.water;
            speedMultiplier = 0.5f;
            status = StatusEffects.wet;
            statusDuration = 90f;
            variants = 0;
        }};

        taintedWater = new Floor("tainted-water"){{
            albedo = 0.5f;
            cacheLayer = CacheLayer.water;
            drownTime = 120f;
            isLiquid = true;
            liquidDrop = Liquids.water;
            speedMultiplier = 0.17f;
            status = StatusEffects.wet;
            statusDuration = 140f;
            variants = 0;

        }};

        darksandTaintedWater = new ShallowLiquid("darksand-tainted-water"){{
            albedo = 0.5f;
            speedMultiplier = 0.75f;
            statusDuration = 60f;
        }};

        sandWater = new ShallowLiquid("sand-water"){{
            albedo = 0.5f;
            speedMultiplier = 0.8f;
            statusDuration = 50f;
        }};

        darksandWater = new ShallowLiquid("darksand-water"){{
            albedo = 0.5f;
            speedMultiplier = 0.8f;
            statusDuration = 50f;
        }};

        tar = new Floor("tar"){{
            cacheLayer = CacheLayer.tar;
            drownTime = 150f;
            isLiquid = true;
            liquidDrop = Liquids.oil;
            speedMultiplier = 0.19f;
            status = StatusEffects.tarred;
            statusDuration = 240f;
            variants = 0;
        }};

        slag = new Floor("slag"){{
            cacheLayer = CacheLayer.slag;
            drownTime = 150f;
            isLiquid = true;
            liquidDrop = Liquids.slag;
            speedMultiplier = 0.19f;
            status = StatusEffects.melting;
            statusDuration = 240f;
            variants = 0;
        }};

        stone = new Floor("stone");

        craters = new Floor("craters"){{
            blendGroup = stone;
            variants = 3;
        }};

        charr = new Floor("char"){{
            blendGroup = stone;
        }};

        ignarock = new Floor("ignarock"){{
            attributes.set(Attribute.water, -0.1f);
        }};

        hotrock = new Floor("hotrock"){{
            attributes.set(Attribute.heat, 0.5f);
            attributes.set(Attribute.water, -0.2f);
            blendGroup = ignarock;
            emitLight = true;
            lightColor = Color.orange.cpy().a(0.15f);
            lightRadius = 30f;
        }};

        magmarock = new Floor("magmarock"){{
            attributes.set(Attribute.heat, 0.75f);
            attributes.set(Attribute.water, -0.5f);
            blendGroup = ignarock;
            emitLight = true;
            lightColor = Color.orange.cpy().a(0.3f);
            lightRadius = 60f;
            updateEffect = Fx.magmasmoke;
        }};

        sand = new Floor("sand"){{
            attributes.set(Attribute.oil, 0.7f);
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};

        darksand = new Floor("darksand"){{
            attributes.set(Attribute.oil, 1.5f);
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};

        ((ShallowLiquid)darksandTaintedWater).set(Blocks.taintedWater, Blocks.darksand);
        ((ShallowLiquid)sandWater).set(Blocks.water, Blocks.sand);
        ((ShallowLiquid)darksandWater).set(Blocks.water, Blocks.darksand);

        holostone = new Floor("holostone");

        grass = new Floor("grass"){{
            attributes.set(Attribute.water, 0.1f);
        }};

        salt = new Floor("salt"){{
            attributes.set(Attribute.oil, 0.3f);
            attributes.set(Attribute.water, -0.25f);
            variants = 0;
        }};

        snow = new Floor("snow"){{
            attributes.set(Attribute.water, 0.2f);
        }};

        ice = new Floor("ice"){{
            attributes.set(Attribute.water, 0.4f);
            dragMultiplier = 0.35f;
            speedMultiplier = 0.9f;
        }};

        iceSnow = new Floor("ice-snow"){{
            attributes.set(Attribute.water, 0.3f);
            dragMultiplier = 0.6f;
            variants = 3;
        }};

        cliffs = new StaticWall("cliffs"){{
            fillsTile = false;
            variants = 1;
        }};

        rocks = new StaticWall("rocks"){{
            variants = 2;
        }};

        sporerocks = new StaticWall("sporerocks"){{
            variants = 2;
        }};

        rock = new Rock("rock"){{
            variants = 2;
        }};

        snowrock = new Rock("snowrock"){{
            variants = 2;
        }};

        icerocks = new StaticWall("icerocks"){{
            iceSnow.asFloor().wall = this;
            variants = 2;
        }};

        snowrocks = new StaticWall("snowrocks"){{
            variants = 2;
        }};

        duneRocks = new StaticWall("dunerocks"){{
            ignarock.asFloor().wall = this;
            variants = 2;
        }};

        sandRocks = new StaticWall("sandrocks"){{
            variants = 2;
        }};

        saltRocks = new StaticWall("saltrocks");

        sporePine = new StaticTree("spore-pine"){{
            variants = 0;
        }};

        snowPine = new StaticTree("snow-pine"){{
            variants = 0;
        }};

        pine = new StaticTree("pine"){{
            variants = 0;
        }};

        shrubs = new StaticWall("shrubs");

        whiteTreeDead = new TreeBlock("white-tree-dead");

        whiteTree = new TreeBlock("white-tree");

        sporeCluster = new Rock("spore-cluster"){{
            variants = 3;
        }};

        shale = new Floor("shale"){{
            attributes.set(Attribute.oil, 1f);
            variants = 3;
        }};

        shaleRocks = new StaticWall("shalerocks"){{
            variants = 2;
        }};

        shaleBoulder = new Rock("shale-boulder"){{
            variants = 2;
        }};

        sandBoulder = new Rock("sand-boulder"){{
            variants = 2;
        }};

        moss = new Floor("moss"){{
            attributes.set(Attribute.spores, 0.15f);
            variants = 3;
            wall = sporePine;
        }};

        sporeMoss = new Floor("spore-moss"){{
            attributes.set(Attribute.spores, 0.3f);
            variants = 3;
            wall = sporerocks;
        }};

        metalFloor = new Floor("metal-floor"){{
            variants = 0;
        }};

        metalFloorDamaged = new Floor("metal-floor-damaged"){{
            variants = 3;
        }};

        metalFloor2 = new Floor("metal-floor-2"){{
            variants = 0;
        }};

        metalFloor3 = new Floor("metal-floor-3"){{
            variants = 0;
        }};

        metalFloor5 = new Floor("metal-floor-5"){{
            variants = 0;
        }};

        darkPanel1 = new Floor("dark-panel-1"){{
            variants = 0; 
        }};

        darkPanel2 = new Floor("dark-panel-2"){{ 
            variants = 0; 
        }};

        darkPanel3 = new Floor("dark-panel-3"){{ 
            variants = 0; 
        }};

        darkPanel4 = new Floor("dark-panel-4"){{ 
            variants = 0; 
        }};

        darkPanel5 = new Floor("dark-panel-5"){{ 
            variants = 0; 
        }};

        darkPanel6 = new Floor("dark-panel-6"){{ 
            variants = 0; 
        }};

        darkMetal = new StaticWall("dark-metal");

        pebbles = new DoubleOverlayFloor("pebbles");

        tendrils = new OverlayFloor("tendrils");

        //endregion
        //region ore

        oreCopper = new OreBlock(Items.copper){{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        oreLead = new OreBlock(Items.lead){{
            oreDefault = true;
            oreThreshold = 0.828f;
            oreScale = 23.952381f;
        }};

        oreScrap = new OreBlock(Items.scrap);

        oreCoal = new OreBlock(Items.coal){{
            oreDefault = true;
            oreThreshold = 0.846f;
            oreScale = 24.428572f;
        }};

        oreTitanium = new OreBlock(Items.titanium){{
            oreDefault = true;
            oreThreshold = 0.864f;
            oreScale = 24.904762f;
        }};

        oreThorium = new OreBlock(Items.thorium){{
            oreDefault = true;
            oreThreshold = 0.882f;
            oreScale = 25.380953f;
        }};

        //endregion
        //region crafting

        graphitePress = new GenericCrafter("graphite-press"){{
            requirements(Category.crafting, with(Items.copper, 75, Items.lead, 30));

            craftEffect = Fx.pulverizeMedium;
            craftTime = 90f;
            hasItems = true;
            size = 2;

            outputItem = new ItemStack(Items.graphite, 1);

            consumes.item(Items.coal, 2);
        }};

        multiPress = new GenericCrafter("multi-press"){{
            requirements(Category.crafting, with(Items.titanium, 100, Items.silicon, 25, Items.lead, 100, Items.graphite, 50));

            craftEffect = Fx.pulverizeMedium;
            craftTime = 30f;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            size = 3;

            outputItem = new ItemStack(Items.graphite, 2);

            consumes.item(Items.coal, 3);
            consumes.liquid(Liquids.water, 0.1f);
            consumes.power(1.8f);
        }};

        siliconSmelter = new GenericSmelter("silicon-smelter"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.lead, 25));

            craftEffect = Fx.smeltsmoke;
            craftTime = 40f;
            flameColor = Color.valueOf("ffef99");
            hasLiquids = false;
            hasPower = true;
            size = 2;

            outputItem = new ItemStack(Items.silicon, 1);

            consumes.items(new ItemStack(Items.coal, 1), new ItemStack(Items.sand, 2));
            consumes.power(0.50f);
        }};

        siliconCrucible = new AttributeSmelter("silicon-crucible"){{
            requirements(Category.crafting, with(Items.titanium, 120, Items.metaglass, 80, Items.plastanium, 35, Items.silicon, 60));

            boostScale = 0.15f;
            craftEffect = Fx.smeltsmoke;
            craftTime = 90f;
            flameColor = Color.valueOf("ffef99");
            hasLiquids = false;
            hasPower = true;
            itemCapacity = 30;
            size = 3;

            outputItem = new ItemStack(Items.silicon, 8);

            consumes.items(new ItemStack(Items.coal, 3), new ItemStack(Items.sand, 6), new ItemStack(Items.pyratite, 1));
            consumes.power(4f);
        }};

        kiln = new GenericSmelter("kiln"){{
            requirements(Category.crafting, with(Items.copper, 60, Items.graphite, 30, Items.lead, 30));

            craftEffect = Fx.smeltsmoke;
            craftTime = 30f;
            flameColor = Color.valueOf("ffc099");
            hasPower = hasItems = true;
            size = 2;

            outputItem = new ItemStack(Items.metaglass, 1);

            consumes.items(new ItemStack(Items.lead, 1), new ItemStack(Items.sand, 1));
            consumes.power(0.60f);
        }};

        plastaniumCompressor = new GenericCrafter("plastanium-compressor"){{
            requirements(Category.crafting, with(Items.silicon, 80, Items.lead, 115, Items.graphite, 60, Items.titanium, 80));

            craftEffect = Fx.formsmoke;
            craftTime = 60f;
            drawer = new DrawGlow();
            hasItems = true;
            hasPower = hasLiquids = true;
            health = 320;
            liquidCapacity = 60f;
            size = 2;
            updateEffect = Fx.plasticburn;

            outputItem = new ItemStack(Items.plastanium, 1);

            consumes.item(Items.titanium, 2);
            consumes.liquid(Liquids.oil, 0.25f);
            consumes.power(3f);
        }};

        phaseWeaver = new GenericCrafter("phase-weaver"){{
            requirements(Category.crafting, with(Items.silicon, 130, Items.lead, 120, Items.thorium, 75));

            craftEffect = Fx.smeltsmoke;
            craftTime = 120f;
            drawer = new DrawWeave();
            hasPower = true;
            itemCapacity = 20;
            size = 2;

            outputItem = new ItemStack(Items.phasefabric, 1);

            consumes.items(new ItemStack(Items.thorium, 4), new ItemStack(Items.sand, 10));
            consumes.power(5f);
        }};

        surgeSmelter = new GenericSmelter("alloy-smelter"){{
            requirements(Category.crafting, with(Items.silicon, 80, Items.lead, 80, Items.thorium, 70));

            craftEffect = Fx.smeltsmoke;
            craftTime = 75f;
            hasPower = true;
            size = 3;

            outputItem = new ItemStack(Items.surgealloy, 1);

            consumes.items(new ItemStack(Items.copper, 3), new ItemStack(Items.lead, 4), new ItemStack(Items.titanium, 2), new ItemStack(Items.silicon, 3));
            consumes.power(4f);
        }};

        cryofluidMixer = new LiquidConverter("cryofluidmixer"){{
            requirements(Category.crafting, with(Items.lead, 65, Items.silicon, 40, Items.titanium, 60));

            craftTime = 120f;
            drawer = new DrawMixer();
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            outputsLiquid = true;
            rotate = false;
            size = 2;
            solid = true;

            outputLiquid = new LiquidStack(Liquids.cryofluid, 0.2f);

            consumes.item(Items.titanium);
            consumes.liquid(Liquids.water, 0.2f);
            consumes.power(1f);
        }};

        blastMixer = new GenericCrafter("blast-mixer"){{
            requirements(Category.crafting, with(Items.lead, 30, Items.titanium, 20));

            hasItems = true;
            hasPower = true;
            size = 2;

            outputItem = new ItemStack(Items.blastCompound, 1);

            consumes.items(new ItemStack(Items.pyratite, 1), new ItemStack(Items.sporePod, 1));
            consumes.power(0.40f);
        }};

        pyratiteMixer = new GenericSmelter("pyratite-mixer"){{
            requirements(Category.crafting, with(Items.copper, 50, Items.lead, 25));

            flameColor = Color.clear;
            hasItems = true;
            hasPower = true;
            size = 2;

            outputItem = new ItemStack(Items.pyratite, 1);

            consumes.items(new ItemStack(Items.coal, 1), new ItemStack(Items.lead, 2), new ItemStack(Items.sand, 2));
            consumes.power(0.20f);
        }};

        melter = new GenericCrafter("melter"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.lead, 35, Items.graphite, 45));

            craftTime = 10f;
            hasLiquids = hasPower = true;
            health = 200;

            outputLiquid = new LiquidStack(Liquids.slag, 2f);

            consumes.item(Items.scrap, 1);
            consumes.power(1f);
        }};

        separator = new Separator("separator"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.titanium, 25));

            craftTime = 35f;
            hasPower = true;
            size = 2;

            results = with(
                Items.copper, 5,
                Items.lead, 3,
                Items.graphite, 2,
                Items.titanium, 2
            );

            consumes.power(1f);
            consumes.liquid(Liquids.slag, 0.07f);
        }};

        disassembler = new Separator("disassembler"){{
            requirements(Category.crafting, with(Items.graphite, 140, Items.titanium, 100, Items.silicon, 150, Items.surgealloy, 70));
            
            craftTime = 15f;
            hasPower = true;
            itemCapacity = 20;
            size = 3;

            results = with(
            Items.sand, 4,
            Items.graphite, 2,
            Items.titanium, 2,
            Items.thorium, 1
            );

            consumes.item(Items.scrap);
            consumes.liquid(Liquids.slag, 0.12f);
            consumes.power(4f);
        }};

        sporePress = new GenericCrafter("spore-press"){{
            requirements(Category.crafting, with(Items.lead, 35, Items.silicon, 30));

            craftEffect = Fx.none;
            craftTime = 20f;
            drawer = new DrawAnimation();
            hasLiquids = true;
            hasPower = true;
            health = 320;
            liquidCapacity = 60f;
            size = 2;

            outputLiquid = new LiquidStack(Liquids.oil, 6f);

            consumes.item(Items.sporePod, 1);
            consumes.power(0.60f);
        }};

        pulverizer = new GenericCrafter("pulverizer"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.lead, 25));

            craftEffect = Fx.pulverize;
            craftTime = 40f;
            drawer = new DrawRotator();
            hasItems = hasPower = true;
            updateEffect = Fx.pulverizeSmall;

            outputItem = new ItemStack(Items.sand, 1);

            consumes.item(Items.scrap, 1);
            consumes.power(0.50f);
        }};

        coalCentrifuge = new GenericCrafter("coal-centrifuge"){{
            requirements(Category.crafting, with(Items.titanium, 20, Items.graphite, 40, Items.lead, 30));

            craftEffect = Fx.smeltsmoke;
            craftTime = 30f;
            hasPower = hasItems = hasLiquids = true;
            size = 2;

            outputItem = new ItemStack(Items.coal, 1);

            consumes.liquid(Liquids.oil, 0.09f);
            consumes.power(0.5f);
        }};

        incinerator = new Incinerator("incinerator"){{
            requirements(Category.crafting, with(Items.graphite, 5, Items.lead, 15));

            health = 90;

            consumes.power(0.50f);
        }};

        //endregion
        //region defense

        int wallHealthMultiplier = 4;

        copperWall = new Wall("copper-wall"){{
            requirements(Category.defense, with(Items.copper, 6));

            health = 80 * wallHealthMultiplier;
        }};

        copperWallLarge = new Wall("copper-wall-large"){{
            requirements(Category.defense, ItemStack.mult(copperWall.requirements, 4));

            health = 80 * 4 * wallHealthMultiplier;
            size = 2;
        }};

        titaniumWall = new Wall("titanium-wall"){{
            requirements(Category.defense, with(Items.titanium, 6));

            health = 110 * wallHealthMultiplier;
        }};

        titaniumWallLarge = new Wall("titanium-wall-large"){{
            requirements(Category.defense, ItemStack.mult(titaniumWall.requirements, 4));

            health = 110 * wallHealthMultiplier * 4;
            size = 2;
        }};

        plastaniumWall = new Wall("plastanium-wall"){{
            requirements(Category.defense, with(Items.plastanium, 5, Items.metaglass, 2));

            absorbLasers = true;
            health = 190 * wallHealthMultiplier;
            insulated = true;
        }};

        plastaniumWallLarge = new Wall("plastanium-wall-large"){{
            requirements(Category.defense, ItemStack.mult(plastaniumWall.requirements, 4));

            absorbLasers = true;
            health = 190 * wallHealthMultiplier * 4;
            insulated = true;
            size = 2;
        }};

        thoriumWall = new Wall("thorium-wall"){{
            requirements(Category.defense, with(Items.thorium, 6));

            health = 200 * wallHealthMultiplier;
        }};

        thoriumWallLarge = new Wall("thorium-wall-large"){{
            requirements(Category.defense, ItemStack.mult(thoriumWall.requirements, 4));

            health = 200 * wallHealthMultiplier * 4;
            size = 2;
        }};

        phaseWall = new Wall("phase-wall"){{
            requirements(Category.defense, with(Items.phasefabric, 6));

            flashHit = deflect = true;
            health = 150 * wallHealthMultiplier;
        }};

        phaseWallLarge = new Wall("phase-wall-large"){{
            requirements(Category.defense, ItemStack.mult(phaseWall.requirements, 4));

            flashHit = deflect = true;
            health = 150 * 4 * wallHealthMultiplier;
            size = 2;
        }};

        surgeWall = new Wall("surge-wall"){{
            requirements(Category.defense, with(Items.surgealloy, 6));

            health = 230 * wallHealthMultiplier;
            lightningChance = 0.05f;
        }};

        surgeWallLarge = new Wall("surge-wall-large"){{
            requirements(Category.defense, ItemStack.mult(surgeWall.requirements, 4));

            health = 230 * 4 * wallHealthMultiplier;
            lightningChance = 0.05f;
            size = 2;
        }};

        door = new Door("door"){{
            requirements(Category.defense, with(Items.graphite, 6, Items.silicon, 4));

            health = 100 * wallHealthMultiplier;
        }};

        doorLarge = new Door("door-large"){{
            requirements(Category.defense, ItemStack.mult(door.requirements, 4));

            closefx = Fx.doorcloselarge;
            health = 100 * 4 * wallHealthMultiplier;
            openfx = Fx.dooropenlarge;
            size = 2;
        }};

        scrapWall = new Wall("scrap-wall"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with());

            health = 60 * wallHealthMultiplier;
            variants = 5;
        }};

        scrapWallLarge = new Wall("scrap-wall-large"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with());

            health = 60 * 4 * wallHealthMultiplier;
            size = 2;
            variants = 4;
        }};

        scrapWallHuge = new Wall("scrap-wall-huge"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with());

            health = 60 * 9 * wallHealthMultiplier;
            size = 3;
            variants = 3;
        }};

        scrapWallGigantic = new Wall("scrap-wall-gigantic"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with());

            health = 60 * 16 * wallHealthMultiplier;
            size = 4;
        }};

        thruster = new Wall("thruster"){{
            health = 55 * 16 * wallHealthMultiplier;
            size = 4;
        }};

        //endregion
        //region effect

        mender = new MendProjector("mender"){{
            requirements(Category.effect, with(Items.lead, 30, Items.copper, 25));

            healPercent = 4f;
            health = 80;
            phaseBoost = 4f;
            phaseRangeBoost = 20f;
            range = 40f;
            reload = 200f;
            size = 1;

            consumes.item(Items.silicon).boost();
            consumes.power(0.3f);
        }};

        mendProjector = new MendProjector("mend-projector"){{
            requirements(Category.effect, with(Items.lead, 100, Items.titanium, 25, Items.silicon, 40));

            healPercent = 14f;
            health = 80 * 2 * 2;
            range = 85f;
            reload = 250f;
            size = 2;

            consumes.item(Items.phasefabric).boost();
            consumes.power(1.5f);
        }};

        overdriveProjector = new OverdriveProjector("overdrive-projector"){{
            requirements(Category.effect, with(Items.lead, 100, Items.titanium, 75, Items.silicon, 75, Items.plastanium, 30));

            size = 2;

            consumes.item(Items.phasefabric).boost();
            consumes.power(3.50f);
        }};

        overdriveDome = new OverdriveProjector("overdrive-dome"){{
            requirements(Category.effect, with(Items.lead, 200, Items.titanium, 130, Items.silicon, 130, Items.plastanium, 80, Items.surgealloy, 120));
            
            hasBoost = false;
            range = 200f;
            size = 3;
            speedBoost = 2.5f;
            useTime = 300f;

            consumes.items(with(Items.phasefabric, 1, Items.silicon, 1));
            consumes.power(10f);
        }};

        forceProjector = new ForceProjector("force-projector"){{
            requirements(Category.effect, with(Items.lead, 100, Items.titanium, 75, Items.silicon, 125));

            breakage = 750f;
            cooldownBrokenBase = 0.35f;
            cooldownLiquid = 1.2f;
            cooldownNormal = 1.5f;
            phaseRadiusBoost = 80f;
            radius = 101.7f;
            size = 3;

            consumes.item(Items.phasefabric).boost();
            consumes.power(4f);
        }};

        shockMine = new ShockMine("shock-mine"){{
            requirements(Category.effect, with(Items.lead, 25, Items.silicon, 12));

            damage = 23;
            hasShadow = false;
            health = 40;
            length = 10;
            tendrils = 4;
            tileDamage = 7f;
        }};

        //endregion
        //region distribution

        conveyor = new Conveyor("conveyor"){{
            requirements(Category.distribution, with(Items.copper, 1), true);

            buildCostMultiplier = 2f;
            displayedSpeed = 4.2f;
            health = 45;
            speed = 0.03f;
        }};

        titaniumConveyor = new Conveyor("titanium-conveyor"){{
            requirements(Category.distribution, with(Items.copper, 1, Items.lead, 1, Items.titanium, 1));

            displayedSpeed = 11f;
            health = 65;
            speed = 0.08f;
        }};

        plastaniumConveyor = new StackConveyor("plastanium-conveyor"){{
            requirements(Category.distribution, with(Items.plastanium, 1, Items.silicon, 1, Items.graphite, 1));

            itemCapacity = 10;
            health = 75;
            speed = 4f / 60f;
        }};

        armoredConveyor = new ArmoredConveyor("armored-conveyor"){{
            requirements(Category.distribution, with(Items.plastanium, 1, Items.thorium, 1, Items.metaglass, 1));

            displayedSpeed = 10f;
            health = 180;
            speed = 0.08f;
        }};

        junction = new Junction("junction"){{
            requirements(Category.distribution, with(Items.copper, 2), true);

            buildCostMultiplier = 6f;
            capacity = 12;
            health = 30;
            speed = 26;
        }};

        itemBridge = new BufferedItemBridge("bridge-conveyor"){{
            requirements(Category.distribution, with(Items.lead, 4, Items.copper, 4));

            bufferCapacity = 14;
            range = 4;
            speed = 70f;
        }};

        phaseConveyor = new ItemBridge("phase-conveyor"){{
            requirements(Category.distribution, with(Items.phasefabric, 5, Items.silicon, 7, Items.lead, 10, Items.graphite, 10));

            canOverdrive = false;
            consumes.power(0.30f);
            hasPower = true;
            range = 12;
        }};

        sorter = new Sorter("sorter"){{
            requirements(Category.distribution, with(Items.lead, 2, Items.copper, 2));

            buildCostMultiplier = 3f;
        }};

        invertedSorter = new Sorter("inverted-sorter"){{
            requirements(Category.distribution, with(Items.lead, 2, Items.copper, 2));

            buildCostMultiplier = 3f;
            invert = true;
        }};

        router = new Router("router"){{
            requirements(Category.distribution, with(Items.copper, 3));

            buildCostMultiplier = 2f;
        }};

        distributor = new Router("distributor"){{
            requirements(Category.distribution, with(Items.lead, 4, Items.copper, 4));

            size = 2;
        }};

        overflowGate = new OverflowGate("overflow-gate"){{
            requirements(Category.distribution, with(Items.lead, 2, Items.copper, 4));

            buildCostMultiplier = 3f;
        }};

        underflowGate = new OverflowGate("underflow-gate"){{
            requirements(Category.distribution, with(Items.lead, 2, Items.copper, 4));

            buildCostMultiplier = 3f;
            invert = true;
        }};

        massDriver = new MassDriver("mass-driver"){{
            requirements(Category.distribution, with(Items.titanium, 125, Items.silicon, 75, Items.lead, 125, Items.thorium, 50));

            itemCapacity = 120;
            range = 440f;
            reloadTime = 200f;
            size = 3;

            consumes.power(1.75f);
        }};

        payloadConveyor = new PayloadConveyor("payload-conveyor"){{
            requirements(Category.distribution, with(Items.graphite, 10, Items.copper, 20));

            canOverdrive = false;
        }};

        payloadRouter = new PayloadRouter("payload-router"){{
            requirements(Category.distribution, with(Items.graphite, 15, Items.copper, 20));

            canOverdrive = false;
        }};

        //endregion
        //region liquid

        mechanicalPump = new Pump("mechanical-pump"){{
            requirements(Category.liquid, with(Items.copper, 15, Items.metaglass, 10));

            pumpAmount = 0.1f;
        }};

        rotaryPump = new Pump("rotary-pump"){{
            requirements(Category.liquid, with(Items.copper, 70, Items.metaglass, 50, Items.silicon, 20, Items.titanium, 35));

            hasPower = true;
            liquidCapacity = 30f;
            pumpAmount = 0.2f;
            size = 2;

            consumes.power(0.3f);
        }};

        thermalPump = new Pump("thermal-pump"){{
            requirements(Category.liquid, with(Items.copper, 80, Items.metaglass, 90, Items.silicon, 30, Items.titanium, 40, Items.thorium, 35));

            hasPower = true;
            liquidCapacity = 40f;
            size = 3;
            pumpAmount = 0.22f;

            consumes.power(1.3f);
        }};

        conduit = new Conduit("conduit"){{
            requirements(Category.liquid, with(Items.metaglass, 1));

            health = 45;
        }};

        pulseConduit = new Conduit("pulse-conduit"){{
            requirements(Category.liquid, with(Items.titanium, 2, Items.metaglass, 1));

            health = 90;
            liquidCapacity = 16f;
            liquidPressure = 1.025f;
        }};

        platedConduit = new ArmoredConduit("plated-conduit"){{
            requirements(Category.liquid, with(Items.thorium, 2, Items.metaglass, 1, Items.plastanium, 1));

            health = 220;
            liquidCapacity = 16f;
            liquidPressure = 1.025f;
        }};

        liquidRouter = new LiquidRouter("liquid-router"){{
            requirements(Category.liquid, with(Items.graphite, 4, Items.metaglass, 2));

            liquidCapacity = 20f;
        }};

        liquidTank = new LiquidRouter("liquid-tank"){{
            requirements(Category.liquid, with(Items.titanium, 25, Items.metaglass, 25));

            health = 500;
            liquidCapacity = 1500f;
            size = 3;
        }};

        liquidJunction = new LiquidJunction("liquid-junction"){{
            requirements(Category.liquid, with(Items.graphite, 2, Items.metaglass, 2));
        }};

        bridgeConduit = new LiquidExtendingBridge("bridge-conduit"){{
            requirements(Category.liquid, with(Items.graphite, 4, Items.metaglass, 8));

            hasPower = false;
            range = 4;
        }};

        phaseConduit = new LiquidBridge("phase-conduit"){{
            requirements(Category.liquid, with(Items.phasefabric, 5, Items.silicon, 7, Items.metaglass, 20, Items.titanium, 10));

            canOverdrive = false;
            hasPower = true;
            range = 12;

            consumes.power(0.30f);
        }};

        //endregion
        //region power

        powerNode = new PowerNode("power-node"){{
            requirements(Category.power, with(Items.copper, 1, Items.lead, 3));

            laserRange = 6;
            maxNodes = 10;
        }};

        powerNodeLarge = new PowerNode("power-node-large"){{
            requirements(Category.power, with(Items.titanium, 5, Items.lead, 10, Items.silicon, 3));

            laserRange = 9.5f;
            maxNodes = 15;
            size = 2;
        }};

        surgeTower = new PowerNode("surge-tower"){{
            requirements(Category.power, with(Items.titanium, 7, Items.lead, 10, Items.silicon, 15, Items.surgealloy, 15));

            laserRange = 30f;
            maxNodes = 2;
            size = 2;
        }};

        diode = new PowerDiode("diode"){{
            requirements(Category.power, with(Items.silicon, 10, Items.plastanium, 5, Items.metaglass, 10));
        }};

        battery = new Battery("battery"){{
            requirements(Category.power, with(Items.copper, 4, Items.lead, 20));

            consumes.powerBuffered(4000f);
        }};

        batteryLarge = new Battery("battery-large"){{
            requirements(Category.power, with(Items.titanium, 20, Items.lead, 40, Items.silicon, 20));

            size = 3;

            consumes.powerBuffered(50000f);
        }};

        combustionGenerator = new BurnerGenerator("combustion-generator"){{
            requirements(Category.power, with(Items.copper, 25, Items.lead, 15));

            itemDuration = 120f;
            powerProduction = 1f;
        }};

        thermalGenerator = new ThermalGenerator("thermal-generator"){{
            requirements(Category.power, with(Items.copper, 40, Items.graphite, 35, Items.lead, 50, Items.silicon, 35, Items.metaglass, 40));

            generateEffect = Fx.redgeneratespark;
            powerProduction = 1.8f;
            size = 2;
        }};

        turbineGenerator = new BurnerGenerator("turbine-generator"){{
            requirements(Category.power, with(Items.copper, 35, Items.graphite, 25, Items.lead, 40, Items.silicon, 30));

            hasLiquids = true;
            itemDuration = 90f;
            powerProduction = 5.5f;
            size = 2;

            consumes.liquid(Liquids.water, 0.05f);
        }};

        differentialGenerator = new SingleTypeGenerator("differential-generator"){{
            requirements(Category.power, with(Items.copper, 70, Items.titanium, 50, Items.lead, 100, Items.silicon, 65, Items.metaglass, 50));

            hasItems = true;
            hasLiquids = true;
            itemDuration = 220f;
            powerProduction = 18f;
            size = 3;

            consumes.item(Items.pyratite).optional(true, false);
            consumes.liquid(Liquids.cryofluid, 0.1f);
        }};

        rtgGenerator = new DecayGenerator("rtg-generator"){{
            requirements(Category.power, with(Items.lead, 100, Items.silicon, 75, Items.phasefabric, 25, Items.plastanium, 75, Items.thorium, 50));

            itemDuration = 500f;
            powerProduction = 4.5f;
            size = 2;
        }};

        solarPanel = new SolarGenerator("solar-panel"){{
            requirements(Category.power, with(Items.lead, 10, Items.silicon, 15));

            powerProduction = 0.07f;
        }};

        largeSolarPanel = new SolarGenerator("solar-panel-large"){{
            requirements(Category.power, with(Items.lead, 100, Items.silicon, 145, Items.phasefabric, 15));

            powerProduction = 0.95f;
            size = 3;
        }};

        thoriumReactor = new NuclearReactor("thorium-reactor"){{
            requirements(Category.power, with(Items.lead, 300, Items.silicon, 200, Items.graphite, 150, Items.thorium, 150, Items.metaglass, 50));

            health = 700;
            heating = 0.02f;
            itemDuration = 360f;
            powerProduction = 15f;
            size = 3;

            consumes.item(Items.thorium);
            consumes.liquid(Liquids.cryofluid, heating / coolantPower).update(false);
        }};

        impactReactor = new ImpactReactor("impact-reactor"){{
            requirements(Category.power, with(Items.lead, 500, Items.silicon, 300, Items.graphite, 400, Items.thorium, 100, Items.surgealloy, 250, Items.metaglass, 250));
            
            health = 900;
            itemDuration = 140f;
            powerProduction = 130f;
            size = 4;
            
            consumes.item(Items.blastCompound);
            consumes.liquid(Liquids.cryofluid, 0.25f);
            consumes.power(25f);
        }};

        //endregion power
        //region production

        mechanicalDrill = new Drill("mechanical-drill"){{
            requirements(Category.production, with(Items.copper, 12), true);

            drawMineItem = true;
            drillTime = 600;
            size = 2;
            tier = 2;

            consumes.liquid(Liquids.water, 0.05f).boost();
        }};

        pneumaticDrill = new Drill("pneumatic-drill"){{
            requirements(Category.production, with(Items.copper, 18, Items.graphite, 10));

            drawMineItem = true;
            drillTime = 400;
            size = 2;
            tier = 3;

            consumes.liquid(Liquids.water, 0.06f).boost();
        }};

        laserDrill = new Drill("laser-drill"){{
            requirements(Category.production, with(Items.copper, 35, Items.graphite, 30, Items.silicon, 30, Items.titanium, 20));

            drillEffect = Fx.mineBig;
            drillTime = 280;
            hasPower = true;
            size = 3;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;

            consumes.liquid(Liquids.water, 0.08f).boost();
            consumes.power(1.10f);
        }};

        blastDrill = new Drill("blast-drill"){{
            requirements(Category.production, with(Items.copper, 65, Items.silicon, 60, Items.titanium, 50, Items.thorium, 75));

            drawRim = true;
            drillEffect = Fx.mineHuge;
            drillTime = 280;
            hasPower = true;
            //more than the laser drill
            liquidBoostIntensity = 1.8f;
            rotateSpeed = 6f;
            size = 4;
            tier = 5;
            updateEffect = Fx.pulverizeRed;
            updateEffectChance = 0.03f;
            warmupSpeed = 0.01f;
            
            consumes.liquid(Liquids.water, 0.1f).boost();
            consumes.power(3f);
        }};

        waterExtractor = new SolidPump("water-extractor"){{
            requirements(Category.production, with(Items.copper, 25, Items.graphite, 25, Items.lead, 20));

            attribute = Attribute.water;
            liquidCapacity = 30f;
            pumpAmount = 0.11f;
            rotateSpeed = 1.4f;
            size = 2;

            result = Liquids.water;

            consumes.power(1f);
        }};

        cultivator = new Cultivator("cultivator"){{
            requirements(Category.production, with(Items.copper, 10, Items.lead, 25, Items.silicon, 10));

            craftTime = 140;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            size = 2;

            outputItem = new ItemStack(Items.sporePod, 1);

            consumes.liquid(Liquids.water, 0.18f);
            consumes.power(0.80f);
        }};

        oilExtractor = new Fracker("oil-extractor"){{
            requirements(Category.production, with(Items.copper, 150, Items.graphite, 175, Items.lead, 115, Items.thorium, 115, Items.silicon, 75));
            
            attribute = Attribute.oil;
            baseEfficiency = 0f;
            itemUseTime = 60f;
            liquidCapacity = 30f;
            pumpAmount = 0.25f;
            size = 3;
            updateEffect = Fx.pulverize;
            updateEffectChance = 0.05f;

            result = Liquids.oil;

            consumes.item(Items.sand);
            consumes.liquid(Liquids.water, 0.15f);
            consumes.power(3f);
        }};

        //endregion
        //region storage

        coreShard = new CoreBlock("core-shard"){{
            requirements(Category.effect, BuildVisibility.hidden, with(Items.copper, 2000, Items.lead, 1000));
            
            alwaysUnlocked = true;
            health = 1100;
            itemCapacity = 4000;
            size = 3;
            unitCapModifier = 8;
            unitType = UnitTypes.alpha;
        }};

        coreFoundation = new CoreBlock("core-foundation"){{
            requirements(Category.effect, with(Items.copper, 3000, Items.lead, 3000, Items.silicon, 2000));

            health = 2000;
            itemCapacity = 9000;
            size = 4;
            unitCapModifier = 14;
            unitType = UnitTypes.beta;
        }};

        coreNucleus = new CoreBlock("core-nucleus"){{
            requirements(Category.effect, with(Items.copper, 8000, Items.lead, 8000, Items.silicon, 5000, Items.thorium, 4000));

            health = 4000;
            itemCapacity = 13000;
            size = 5;
            unitCapModifier = 20;
            unitType = UnitTypes.gamma;
        }};

        vault = new StorageBlock("vault"){{
            requirements(Category.effect, with(Items.titanium, 250, Items.thorium, 125));

            itemCapacity = 1000;
            size = 3;
        }};

        container = new StorageBlock("container"){{
            requirements(Category.effect, with(Items.titanium, 100));

            itemCapacity = 300;
            size = 2;
        }};

        unloader = new Unloader("unloader"){{
            requirements(Category.effect, with(Items.titanium, 25, Items.silicon, 30));

            speed = 6f;
        }};

        //endregion
        //region turrets

        duo = new ItemTurret("duo"){{
            requirements(Category.turret, with(Items.copper, 35), true);

            ammo(
            Items.copper, Bullets.standardCopper,
            Items.graphite, Bullets.standardDense,
            Items.pyratite, Bullets.standardIncendiary,
            Items.silicon, Bullets.standardHoming
            );

            alternate = true;
            ammoUseEffect = Fx.shellEjectSmall;
            health = 250;
            inaccuracy = 2f;
            range = 100;
            reloadTime = 20f;
            restitution = 0.03f;
            rotatespeed = 10f;
            shootCone = 15f;
            shots = 2;
            spread = 4f;
        }};

        scatter = new ItemTurret("scatter"){{
            requirements(Category.turret, with(Items.copper, 85, Items.lead, 45));

            ammo(
            Items.scrap, Bullets.flakScrap,
            Items.lead, Bullets.flakLead,
            Items.metaglass, Bullets.flakGlass
            );

            burstSpacing = 5f;
            health = 200 * 2 * 2;
            inaccuracy = 17f;
            range = 160f;
            recoilAmount = 2f;
            reloadTime = 18f;
            rotatespeed = 15f;
            shootCone = 35f;
            shootSound = Sounds.shootSnap;
            shots = 2;
            size = 2;
            targetGround = false;
        }};

        scorch = new ItemTurret("scorch"){{
            requirements(Category.turret, with(Items.copper, 25, Items.graphite, 22));

            ammo(
            Items.coal, Bullets.basicFlame,
            Items.pyratite, Bullets.pyraFlame
            );

            ammoUseEffect = Fx.none;
            coolantMultiplier = 1.5f;
            health = 400;
            range = 60f;
            recoilAmount = 0f;
            reloadTime = 6f;
            shootCone = 50f;
            shootSound = Sounds.flame;
            targetAir = false;
        }};

        hail = new ItemTurret("hail"){{
            requirements(Category.turret, with(Items.copper, 40, Items.graphite, 17));

            ammo(
            Items.graphite, Bullets.artilleryDense,
            Items.silicon, Bullets.artilleryHoming,
            Items.pyratite, Bullets.artilleryIncendiary
            );

            health = 260;
            inaccuracy = 1f;
            range = 230f;
            recoilAmount = 2f;
            reloadTime = 60f;
            shootCone = 10f;
            shootSound = Sounds.artillery;
            targetAir = false;
        }};

        wave = new LiquidTurret("wave"){{
            requirements(Category.turret, with(Items.metaglass, 45, Items.lead, 75));

            ammo(
            Liquids.water, Bullets.waterShot,
            Liquids.slag, Bullets.slagShot,
            Liquids.cryofluid, Bullets.cryoShot,
            Liquids.oil, Bullets.oilShot
            );

            health = 250 * 2 * 2;
            inaccuracy = 5f;
            liquidCapacity = 10f;
            range = 110f;
            recoilAmount = 0f;
            reloadTime = 2f;
            shootCone = 50f;
            shootEffect = Fx.shootLiquid;
            shootSound = Sounds.splash;
            size = 2;
        }};

        lancer = new ChargeTurret("lancer"){{
            requirements(Category.turret, with(Items.copper, 25, Items.lead, 50, Items.silicon, 45));

            shootType = new LaserBulletType(140){{
                colors = new Color[]{Pal.lancerLaser.cpy().mul(1f, 1f, 1f, 0.4f), Pal.lancerLaser, Color.white};
                hitEffect = Fx.hitLancer;
                despawnEffect = Fx.none;
                hitSize = 4;
                lifetime = 16f;
                drawSize = 400f;
                collidesAir = false;
            }};

            chargeBeginEffect = Fx.lancerLaserChargeBegin;
            chargeEffect = Fx.lancerLaserCharge;
            chargeEffects = 7;
            chargeMaxDelay = 30f;
            chargeTime = 50f;
            cooldown = 0.03f;
            health = 280 * 2 * 2;
            heatColor = Color.red;
            range = 155f;
            recoilAmount = 2f;
            reloadTime = 90f;
            shootEffect = Fx.lancerLaserShoot;
            shootShake = 2f;
            shootSound = Sounds.laser;
            size = 2;
            smokeEffect = Fx.none;
            targetAir = false;

            powerUse = 6f;
        }};

        arc = new PowerTurret("arc"){{
            requirements(Category.turret, with(Items.copper, 35, Items.lead, 50));

            shootType = new LightningBulletType(){{
                damage = 21;
                lightningLength = 25;
                collidesAir = false;
            }};

            health = 260;
            heatColor = Color.red;
            range = 90f;
            recoilAmount = 1f;
            reloadTime = 35f;
            rotatespeed = 8f;
            shootCone = 40f;
            shootEffect = Fx.lightningShoot;
            shootSound = Sounds.spark;
            size = 1;
            targetAir = false;

            powerUse = 4f;
        }};

        parallax = new TractorBeamTurret("parallax"){{
            requirements(Category.turret, with(Items.silicon, 120, Items.titanium, 90, Items.graphite, 30));

            damage = 0.1f;
            force = 4.5f;
            hasPower = true;
            health = 160 * 2 * 2;
            range = 170f;
            range = 85f;
            rotateSpeed = 10;
            scaledForce = 5.5f;
            size = 2;

            consumes.powerCond(3f, (TractorBeamBuild e) -> e.target != null);
        }};

        swarmer = new ItemTurret("swarmer"){{
            requirements(Category.turret, with(Items.graphite, 35, Items.titanium, 35, Items.plastanium, 45, Items.silicon, 30));

            ammo(
            Items.blastCompound, Bullets.missileExplosive,
            Items.pyratite, Bullets.missileIncendiary,
            Items.surgealloy, Bullets.missileSurge
            );

            burstSpacing = 5;
            health = 300 * 2 * 2;
            inaccuracy = 10f;
            range = 190f;
            reloadTime = 30f;
            shootSound = Sounds.missile;
            shots = 4;
            size = 2;
            xRand = 6f;
        }};

        salvo = new ItemTurret("salvo"){{
            requirements(Category.turret, with(Items.copper, 105, Items.graphite, 95, Items.titanium, 60));

            ammo(
            Items.copper, Bullets.standardCopper,
            Items.graphite, Bullets.standardDense,
            Items.pyratite, Bullets.standardIncendiary,
            Items.silicon, Bullets.standardHoming,
            Items.thorium, Bullets.standardThorium
            );

            ammoEjectBack = 3f;
            ammoUseEffect = Fx.shellEjectBig;
            burstSpacing = 3f;
            cooldown = 0.03f;
            health = 240 * 2 * 2;
            range = 150f;
            recoilAmount = 3f;
            reloadTime = 38f;
            restitution = 0.03f;
            shootShake = 1f;
            shootSound = Sounds.shootBig;
            shots = 4;
            size = 2;
        }};

        segment = new PointDefenseTurret("segment"){{
            requirements(Category.turret, with(Items.silicon, 130, Items.thorium, 80, Items.phasefabric, 25));

            bulletDamage = 18f;
            hasPower = true;
            health = 190 * 2 * 2;
            range = 125f;
            reloadTime = 11f;
            shootLength = 5f;
            size = 2;

            consumes.power(3f);
        }};

        fuse = new ItemTurret("fuse"){{
            requirements(Category.turret, with(Items.copper, 225, Items.graphite, 225, Items.thorium, 100));

            ammo(Items.thorium, new ShrapnelBulletType(){{
                length = range + 10f;
                damage = 105f;
                ammoMultiplier = 6f;
            }});

            health = 220 * 3 * 3;
            range = 90f;
            recoilAmount = 5f;
            reloadTime = 35f;
            restitution = 0.1f;
            shootCone = 30;
            shootShake = 4f;
            shootSound = Sounds.shotgun;
            shots = 3;
            size = 3;
            spread = 20f;
        }};

        ripple = new ItemTurret("ripple"){{
            requirements(Category.turret, with(Items.copper, 150, Items.graphite, 135, Items.titanium, 60));

            ammo(
            Items.graphite, Bullets.artilleryDense,
            Items.silicon, Bullets.artilleryHoming,
            Items.pyratite, Bullets.artilleryIncendiary,
            Items.blastCompound, Bullets.artilleryExplosive,
            Items.plastanium, Bullets.artilleryPlastic
            );

            ammoEjectBack = 5f;
            ammoPerShot = 2;
            ammoUseEffect = Fx.shellEjectBig;
            cooldown = 0.03f;
            health = 130 * 3 * 3;
            inaccuracy = 12f;
            minRange = 50f;
            range = 290f;
            recoilAmount = 6f;
            reloadTime = 60f;
            restitution = 0.02f;
            shootShake = 2f;
            shootSound = Sounds.artillery;
            shots = 4;
            size = 3;
            targetAir = false;
            velocityInaccuracy = 0.2f;
        }};

        cyclone = new ItemTurret("cyclone"){{
            requirements(Category.turret, with(Items.copper, 200, Items.titanium, 125, Items.plastanium, 80));

            ammo(
            Items.metaglass, Bullets.fragGlass,
            Items.blastCompound, Bullets.fragExplosive,
            Items.plastanium, Bullets.fragPlastic,
            Items.surgealloy, Bullets.fragSurge
            );

            health = 145 * 3 * 3;
            inaccuracy = 10f;
            range = 200f;
            recoilAmount = 3f;
            reloadTime = 8f;
            rotatespeed = 10f;
            shootCone = 30f;
            shootSound = Sounds.shootSnap;
            size = 3;
            xRand = 4f;
        }};

        spectre = new ItemTurret("spectre"){{
            requirements(Category.turret, with(Items.copper, 350, Items.graphite, 300, Items.surgealloy, 250, Items.plastanium, 175, Items.thorium, 250));

            ammo(
            Items.graphite, Bullets.standardDenseBig,
            Items.pyratite, Bullets.standardIncendiaryBig,
            Items.thorium, Bullets.standardThoriumBig
            );

            alternate = true;
            ammoUseEffect = Fx.shellEjectBig;
            coolantMultiplier = 0.5f;
            health = 155 * 4 * 4;
            inaccuracy = 3f;
            range = 200f;
            recoilAmount = 3f;
            reloadTime = 6f;
            restitution = 0.1f;
            shootCone = 24f;
            shootShake = 2f;
            shootSound = Sounds.shootBig;
            shots = 2;
            size = 4;
            spread = 8f;

            consumes.add(new ConsumeLiquidFilter(liquid -> liquid.temperature <= 0.5f && liquid.flammability < 0.1f, 2f)).update(false).optional(true, true);
        }};

        meltdown = new LaserTurret("meltdown"){{
            requirements(Category.turret, with(Items.copper, 250, Items.lead, 350, Items.graphite, 300, Items.surgealloy, 325, Items.silicon, 325));

            shootType = new ContinuousLaserBulletType(70){{
                length = 220f;
                hitEffect = Fx.hitMeltdown;
                drawSize = 420f;

                incendChance = 0.4f;
                incendSpread = 5f;
                incendAmount = 1;
            }};

            activeSound = Sounds.beam;
            activeSoundVolume = 2f;
            firingMoveFract = 0.5f;
            health = 200 * 4 * 4;
            range = 190f;
            recoilAmount = 4f;
            reloadTime = 90f;
            shootCone = 40f;
            shootDuration = 220f;
            shootEffect = Fx.shootBigSmoke2;
            shootShake = 2f;
            shootSound = Sounds.laserbig;
            size = 4;

            powerUse = 14f;
            consumes.add(new ConsumeLiquidFilter(liquid -> liquid.temperature <= 0.5f && liquid.flammability < 0.1f, 0.5f)).update(false);
        }};

        //endregion
        //region units

        commandCenter = new CommandCenter("command-center"){{
            requirements(Category.units, ItemStack.with(Items.copper, 200, Items.lead, 250, Items.silicon, 250, Items.graphite, 100));

            health = 2 * 2 * 55;
            size = 2;
        }};

        groundFactory = new UnitFactory("ground-factory"){{
            requirements(Category.units, with(Items.copper, 50, Items.lead, 120, Items.silicon, 80));

            plans = new UnitPlan[]{
                new UnitPlan(UnitTypes.dagger, 60f * 15, with(Items.silicon, 10, Items.lead, 10)),
                new UnitPlan(UnitTypes.crawler, 60f * 12, with(Items.silicon, 10, Items.coal, 20)),
                new UnitPlan(UnitTypes.nova, 60f * 40, with(Items.silicon, 30, Items.lead, 20, Items.titanium, 20)),
            };

            size = 3;

            consumes.power(1.2f);
        }};

        airFactory = new UnitFactory("air-factory"){{
            requirements(Category.units, with(Items.copper, 60, Items.lead, 70));

            plans = new UnitPlan[]{
                new UnitPlan(UnitTypes.flare, 60f * 15, with(Items.silicon, 15)),
                new UnitPlan(UnitTypes.mono, 60f * 35, with(Items.silicon, 30, Items.lead, 15)),
            };

            size = 3;

            consumes.power(1.2f);
        }};

        navalFactory = new UnitFactory("naval-factory"){{
            requirements(Category.units, with(Items.copper, 150, Items.lead, 130, Items.metaglass, 120));

            plans = new UnitPlan[]{
                new UnitPlan(UnitTypes.risso, 60f * 30f, with(Items.silicon, 20, Items.metaglass, 25)),
            };

            requiresWater = true;
            size = 3;

            consumes.power(1.2f);
        }};

        additiveReconstructor = new Reconstructor("additive-reconstructor"){{
            requirements(Category.units, with(Items.copper, 200, Items.lead, 120, Items.silicon, 90));

            upgrades = new UnitType[][]{
                {UnitTypes.nova, UnitTypes.pulsar},
                {UnitTypes.dagger, UnitTypes.mace},
                {UnitTypes.crawler, UnitTypes.atrax},
                {UnitTypes.flare, UnitTypes.horizon},
                {UnitTypes.mono, UnitTypes.poly},
                {UnitTypes.risso, UnitTypes.minke},
            };

            constructTime = 60f * 10f;
            size = 3;

            consumes.items(with(Items.silicon, 40, Items.graphite, 40));
            consumes.power(3f);
        }};

        multiplicativeReconstructor = new Reconstructor("multiplicative-reconstructor"){{
            requirements(Category.units, with(Items.lead, 650, Items.silicon, 350, Items.titanium, 350, Items.thorium, 650));

            upgrades = new UnitType[][]{
                {UnitTypes.horizon, UnitTypes.zenith},
                {UnitTypes.mace, UnitTypes.fortress},
                {UnitTypes.poly, UnitTypes.mega},
                {UnitTypes.minke, UnitTypes.bryde},
                {UnitTypes.pulsar, UnitTypes.quasar},
                {UnitTypes.atrax, UnitTypes.spiroct},
            };

            constructTime = 60f * 30f;
            size = 5;

            consumes.items(with(Items.silicon, 130, Items.titanium, 80, Items.metaglass, 30));
            consumes.power(6f);
        }};

        exponentialReconstructor = new Reconstructor("exponential-reconstructor"){{
            requirements(Category.units, with(Items.lead, 2000, Items.silicon, 750, Items.titanium, 950, Items.thorium, 450, Items.plastanium, 350, Items.phasefabric, 450));

            upgrades = new UnitType[][]{
                {UnitTypes.zenith, UnitTypes.antumbra},
                {UnitTypes.spiroct, UnitTypes.arkyid},
                {UnitTypes.fortress, UnitTypes.scepter},
            };

            constructTime = 60f * 60f * 1.5f;
            liquidCapacity = 60f;
            size = 7;

            consumes.items(with(Items.silicon, 450, Items.titanium, 550, Items.plastanium, 550));
            consumes.liquid(Liquids.cryofluid, 1f);
            consumes.power(13f);
        }};

        tetrativeReconstructor = new Reconstructor("tetrative-reconstructor"){{
            requirements(Category.units, with(Items.lead, 4000, Items.silicon, 1500, Items.thorium, 500, Items.plastanium, 450, Items.phasefabric, 600, Items.surgealloy, 500));

            upgrades = new UnitType[][]{
                {UnitTypes.antumbra, UnitTypes.eclipse},
                {UnitTypes.arkyid, UnitTypes.toxopid},
                {UnitTypes.scepter, UnitTypes.reign},
            };

            constructTime = 60f * 60f * 4;
            liquidCapacity = 180f;
            size = 9;

            consumes.items(with(Items.silicon, 350, Items.plastanium, 550, Items.surgealloy, 350, Items.phasefabric, 150));
            consumes.liquid(Liquids.cryofluid, 3f);
            consumes.power(25f);
        }};

        repairPoint = new RepairPoint("repair-point"){{
            requirements(Category.units, with(Items.lead, 15, Items.copper, 15, Items.silicon, 15));

            repairRadius = 65f;
            repairSpeed = 0.5f;

            powerUse = 1f;
        }};

        resupplyPoint = new ResupplyPoint("resupply-point"){{
            requirements(Category.units, BuildVisibility.ammoOnly, with(Items.lead, 20, Items.copper, 15, Items.silicon, 15));

            range = 80f;
            size = 2;

            consumes.item(Items.copper, 1);
        }};

        //endregion
        //region sandbox

        powerSource = new PowerSource("power-source"){{
            requirements(Category.power, BuildVisibility.sandboxOnly, with());

            alwaysUnlocked = true;
        }};

        powerVoid = new PowerVoid("power-void"){{
            requirements(Category.power, BuildVisibility.sandboxOnly, with());

            alwaysUnlocked = true;
        }};

        itemSource = new ItemSource("item-source"){{
            requirements(Category.distribution, BuildVisibility.sandboxOnly, with());

            alwaysUnlocked = true;
        }};

        itemVoid = new ItemVoid("item-void"){{
            requirements(Category.distribution, BuildVisibility.sandboxOnly, with());

            alwaysUnlocked = true;
        }};

        liquidSource = new LiquidSource("liquid-source"){{
            requirements(Category.liquid, BuildVisibility.sandboxOnly, with());

            alwaysUnlocked = true;
        }};

        liquidVoid = new LiquidVoid("liquid-void"){{
            requirements(Category.liquid, BuildVisibility.sandboxOnly, with());

            alwaysUnlocked = true;
        }};

        illuminator = new LightBlock("illuminator"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(Items.graphite, 12, Items.silicon, 8));

            brightness = 0.67f;
            radius = 140f;

            consumes.power(0.06f);
        }};

        //endregion
        //region legacy

        //looked up by name, no ref needed
        new LegacyMechPad("legacy-mech-pad");
        new LegacyUnitFactory("legacy-unit-factory");

        //endregion
        //region campaign

        launchPad = new LaunchPad("launch-pad"){{
            requirements(Category.effect, BuildVisibility.campaignOnly, with(Items.copper, 350, Items.silicon, 140, Items.lead, 200, Items.titanium, 150));

            hasPower = true;
            itemCapacity = 100;
            launchTime = 60f * 20;
            size = 3;

            consumes.power(4f);
        }};

        //TODO remove
        launchPadLarge = new LaunchPad("launch-pad-large"){{
            //requirements(Category.effect, BuildVisibility.campaignOnly, with(Items.titanium, 200, Items.silicon, 150, Items.lead, 250, Items.plastanium, 75));
            
            hasPower = true;
            itemCapacity = 300;
            launchTime = 60f * 35;
            size = 4;

            consumes.power(6f);
        }};

        //endregion campaign
        //region logic

        message = new MessageBlock("message"){{
            requirements(Category.logic, with(Items.graphite, 5));
        }};

        switchBlock = new SwitchBlock("switch"){{
            requirements(Category.logic, with(Items.graphite, 5));
        }};

        microProcessor = new LogicBlock("micro-processor"){{
            requirements(Category.logic, with(Items.copper, 80, Items.lead, 50, Items.silicon, 50));

            instructionsPerTick = 2;
            size = 1;
        }};

        logicProcessor = new LogicBlock("logic-processor"){{
            requirements(Category.logic, with(Items.lead, 320, Items.silicon, 100, Items.graphite, 60, Items.thorium, 50));

            instructionsPerTick = 5;
            range = 8 * 22;
            size = 2;
        }};

        hyperProcessor = new LogicBlock("hyper-processor"){{
            requirements(Category.logic, with(Items.lead, 450, Items.silicon, 150, Items.thorium, 75, Items.surgealloy, 50));

            hasLiquids = true;
            instructionsPerTick = 25;
            range = 8 * 42;
            size = 3;

            consumes.liquid(Liquids.cryofluid, 0.08f);
        }};

        logicDisplay = new LogicDisplay("logic-display"){{
            requirements(Category.logic, with(Items.copper, 200, Items.lead, 120, Items.silicon, 100, Items.metaglass, 50));

            displaySize = 80;
            size = 3;
        }};

        memoryCell = new MemoryBlock("memory-cell"){{
            requirements(Category.logic, with(Items.graphite, 40, Items.silicon, 40));

            memoryCapacity = 64;
        }};

        //endregion
        //region experimental

        blockForge = new BlockForge("block-forge"){{
            requirements(Category.production, BuildVisibility.debugOnly, with(Items.thorium, 100));

            hasPower = true;
            size = 3;

            consumes.power(2f);
            
        }};

        blockLoader = new BlockLoader("block-loader"){{
            requirements(Category.production, BuildVisibility.debugOnly, with(Items.thorium, 100));

            hasPower = true;
            size = 3;

            consumes.power(2f);
        }};

        blockUnloader = new BlockUnloader("block-unloader"){{
            requirements(Category.production, BuildVisibility.debugOnly, with(Items.thorium, 100));

            hasPower = true;
            size = 3;

            consumes.power(2f);
        }};

        //endregion
    }
}
