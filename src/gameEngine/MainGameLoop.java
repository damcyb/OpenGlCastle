package gameEngine;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Music;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.TerrainCoordinatesLottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_SAMPLE_ALPHA_TO_COVERAGE;
import static org.lwjgl.opengl.GL14.glBlendFuncSeparate;

public class MainGameLoop {

    public static boolean gateAction = false;
    public static boolean fortressGateAction = false;

    public static void main(String[] args) {

        Window window = new Window(1920, 1000, "Test");
        Loader loader = new Loader();

        TerrainCoordinatesLottery lottery = new TerrainCoordinatesLottery();

        float angle = 0.0f;
        float fortressGateAngle = 0.0f;
        float knightAngle = 0.0f;
        float knightAngle2 = 30.0f;

        window.init();
        window.createCapabilities();

        //animation
        //character
        RawModel ch1 = OBJLoader.loadOBJModel("character_animation/ch1", loader);
        ModelTexture textureCh1 = loader.loadTexture("character_animation/Character Texture");
        TexturedModel texturedCh1 = new TexturedModel(ch1, textureCh1);
        RawModel ch2 = OBJLoader.loadOBJModel("character_animation/ch2", loader);
        TexturedModel texturedCh2 = new TexturedModel(ch2, textureCh1);
        RawModel ch3 = OBJLoader.loadOBJModel("character_animation/ch3", loader);
        TexturedModel texturedCh3 = new TexturedModel(ch3, textureCh1);
        RawModel ch4 = OBJLoader.loadOBJModel("character_animation/ch4", loader);
        TexturedModel texturedCh4 = new TexturedModel(ch4, textureCh1);
        RawModel ch5 = OBJLoader.loadOBJModel("character_animation/ch5", loader);
        TexturedModel texturedCh5 = new TexturedModel(ch5, textureCh1);
        RawModel ch6 = OBJLoader.loadOBJModel("character_animation/ch6", loader);
        TexturedModel texturedCh6 = new TexturedModel(ch6, textureCh1);
        RawModel ch7 = OBJLoader.loadOBJModel("character_animation/ch7", loader);
        TexturedModel texturedCh7 = new TexturedModel(ch7, textureCh1);
        RawModel ch8 = OBJLoader.loadOBJModel("character_animation/ch8", loader);
        TexturedModel texturedCh8 = new TexturedModel(ch8, textureCh1);
        RawModel ch9 = OBJLoader.loadOBJModel("character_animation/ch9", loader);
        TexturedModel texturedCh9 = new TexturedModel(ch9, textureCh1);
        RawModel ch10 = OBJLoader.loadOBJModel("character_animation/ch10", loader);
        TexturedModel texturedCh10 = new TexturedModel(ch10, textureCh1);
        RawModel ch11 = OBJLoader.loadOBJModel("character_animation/ch11", loader);
        TexturedModel texturedCh11 = new TexturedModel(ch11, textureCh1);

        /////////////////
        //horse
        RawModel horse = OBJLoader.loadOBJModel("horse", loader);
        ModelTexture textureHorse = loader.loadTexture("horse_texture");
        TexturedModel texturedHorse = new TexturedModel(horse, textureHorse);

        //horn lantern
        RawModel lantern = OBJLoader.loadOBJModel("lantern", loader);
        ModelTexture textureLantern = loader.loadTexture("lantern_texture");
        TexturedModel texturedLantern = new TexturedModel(lantern, textureLantern);

        /////////////////

        //tree
        RawModel tree = OBJLoader.loadOBJModel("pine", loader);
        ModelTexture textureTree = loader.loadTexture("pine");
        TexturedModel texturedTree = new TexturedModel(tree, textureTree);
//        ModelTexture modelTexture = texturedTree.getTexture();
//        modelTexture.setShineDamper(2);
//        modelTexture.setReflectivity(1);

        //castle
        RawModel fortress = OBJLoader.loadOBJModel("twierdza6Converted", loader);
        ModelTexture textureFortress = loader.loadTexture("wallBricks3");
        TexturedModel texturedFortress = new TexturedModel(fortress, textureFortress);
        texturedFortress.getTexture().setShineDamper(2);
        texturedFortress.getTexture().setReflectivity(1);

        //gate
        RawModel gate = OBJLoader.loadOBJModel("gate2", loader);
        ModelTexture textureGate = loader.loadTexture("iron gate");
        TexturedModel texturedGate = new TexturedModel(gate, textureGate);

        //gate
        RawModel fortressGate = OBJLoader.loadOBJModel("gate2", loader);
        ModelTexture textureFortressGate = loader.loadTexture("iron gate");
        TexturedModel texturedFortressGate = new TexturedModel(fortressGate, textureFortressGate);

        RawModel tower = OBJLoader.loadOBJModel("castleD4converted", loader);
        ModelTexture textureTower = loader.loadTexture("wallBricks3");
        TexturedModel texturedTower = new TexturedModel(tower, textureTower);
        texturedTower.getTexture().setShineDamper(2);
        texturedTower.getTexture().setReflectivity(1);

        RawModel pavement = OBJLoader.loadOBJModel("posadzka zamkowa", loader);
        ModelTexture texturePavement = loader.loadTexture("castlefloor");
        TexturedModel texturedPavement = new TexturedModel(pavement, texturePavement);
        texturedPavement.getTexture().setShineDamper(2);
        texturedPavement.getTexture().setReflectivity(1);

        RawModel pennant = OBJLoader.loadOBJModel("emblem", loader);
        ModelTexture texturePennant = loader.loadTexture("emblem");
        TexturedModel texturedPennant = new TexturedModel(pennant, texturePennant);
//        texturedPennant.getTexture().setShineDamper(1);
//        texturedPennant.getTexture().setReflectivity(1);

        RawModel flag = OBJLoader.loadOBJModel("flagConverted", loader);
        ModelTexture textureFlag = loader.loadTexture("flag");
        TexturedModel texturedFlag = new TexturedModel(flag, textureFlag);
        texturedFlag.getTexture().setShineDamper(2);
        texturedFlag.getTexture().setReflectivity(1);

        RawModel medievalHouse = OBJLoader.loadOBJModel("medievalHouse", loader);
        ModelTexture textureMedievalHouse = loader.loadTexture("Medieval_House_Diff");
        TexturedModel texturedMedievalHouse = new TexturedModel(medievalHouse, textureMedievalHouse);
        texturedMedievalHouse.getTexture().setShineDamper(2);
        texturedMedievalHouse.getTexture().setReflectivity(1);

        RawModel carriage = OBJLoader.loadOBJModel("carriageConverted", loader);
        ModelTexture textureCarriage = loader.loadTexture("wood");
        TexturedModel texturedCarriage = new TexturedModel(carriage, textureCarriage);
        texturedCarriage.getTexture().setShineDamper(2);
        texturedCarriage.getTexture().setReflectivity(1);

        RawModel barrel = OBJLoader.loadOBJModel("barrelConverted", loader);
        ModelTexture textureBarrel = loader.loadTexture("wood");
        TexturedModel texturedBarrel = new TexturedModel(barrel, textureBarrel);
        texturedBarrel.getTexture().setShineDamper(2);
        texturedBarrel.getTexture().setReflectivity(1);

        RawModel stall = OBJLoader.loadOBJModel("stall", loader);
        ModelTexture textureStall = loader.loadTexture("stall");
        TexturedModel texturedStall = new TexturedModel(stall, textureStall);
        texturedStall.getTexture().setShineDamper(2);
        texturedStall.getTexture().setReflectivity(1);

        RawModel rainProtection = OBJLoader.loadOBJModel("rainProtectionConverted", loader);
        ModelTexture textureRainProtection = loader.loadTexture("woodwithcolor");
        TexturedModel texturedRainProtection = new TexturedModel(rainProtection, textureRainProtection);
//        texturedRainProtection.getTexture().setShineDamper(2);
//        texturedRainProtection.getTexture().setReflectivity(1);

        RawModel stable = OBJLoader.loadOBJModel("stable", loader);
        ModelTexture textureStable = loader.loadTexture("wood2");
        TexturedModel texturedStable = new TexturedModel(stable, textureStable);
        texturedStable.getTexture().setShineDamper(2);
        texturedStable.getTexture().setReflectivity(1);

        RawModel knight = OBJLoader.loadOBJModel("knight", loader);
        ModelTexture textureKnight = loader.loadTexture("knight");
        TexturedModel texturedKnight = new TexturedModel(knight, textureKnight);
        texturedKnight.getTexture().setShineDamper(2);
        texturedKnight.getTexture().setReflectivity(1);

        RawModel barrack = OBJLoader.loadOBJModel("barrack2Converted", loader);
        ModelTexture textureBarrack = loader.loadTexture("wallBricks3");
        TexturedModel texturedBarrack = new TexturedModel(barrack, textureBarrack);
        texturedBarrack.getTexture().setShineDamper(2);
        texturedBarrack.getTexture().setReflectivity(1);

        RawModel bed = OBJLoader.loadOBJModel("bedConverted", loader);
        ModelTexture textureBed = loader.loadTexture("bed");
        TexturedModel texturedBed = new TexturedModel(bed, textureBed);
        texturedBed.getTexture().setShineDamper(2);
        texturedBed.getTexture().setReflectivity(1);

        RawModel bench = OBJLoader.loadOBJModel("benchConverted", loader);
        ModelTexture textureBench = loader.loadTexture("wood");
        TexturedModel texturedBench = new TexturedModel(bench, textureBench);
        texturedBench.getTexture().setShineDamper(2);
        texturedBench.getTexture().setReflectivity(1);

        RawModel well = OBJLoader.loadOBJModel("well2", loader);
        ModelTexture textureWell = loader.loadTexture("well");
        TexturedModel texturedWell = new TexturedModel(well, textureWell);
//        texturedWell.getTexture().setShineDamper(2);
//        texturedWell.getTexture().setReflectivity(1);

        RawModel chest = OBJLoader.loadOBJModel("chest", loader);
        ModelTexture textureChest = loader.loadTexture("chest");
        TexturedModel texturedChest = new TexturedModel(chest, textureChest);
//        texturedChest.getTexture().setShineDamper(2);
//        texturedChest.getTexture().setReflectivity(1);

        //grass
        TexturedModel grass = new TexturedModel(OBJLoader.loadOBJModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("grassTexture2").getId()));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLightning(true);

        //fern
        TexturedModel fern = new TexturedModel(OBJLoader.loadOBJModel("fern", loader),
                new ModelTexture(loader.loadTexture("fern3").getId()));
        fern.getTexture().setHasTransparency(true);
        fern.getTexture().setUseFakeLightning(true);

        //terrain
        Terrain terrain = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("grass").getId()));
        Terrain terrain2 = new Terrain(1,0, loader, new ModelTexture(loader.loadTexture("grass").getId()));

        //Entity entity = new Entity(texturedTree, new Vector3f(0,0,-25),0,0,0,1);

        Light light = new Light(new Vector3f(1000,1000,1000), new Vector3f(0.5f,0.5f,0.5f));
        
        List<Light> lights = new ArrayList<Light>();
        lights.add(light);

        lights.add(new Light(new Vector3f(62, 0.3f, -118), new Vector3f(0.4f,0.2f,0), new Vector3f(2f, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(95, 0.3f, -118), new Vector3f(0.4f,0.2f,0), new Vector3f(2f, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(69, 0.3f, -145), new Vector3f(0.4f,0.2f,0), new Vector3f(2f, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(98, 0.3f, -145), new Vector3f(0.4f,0.2f,0), new Vector3f(2.5f, 0.01f, 0.002f)));
        lights.add(new Light(new Vector3f(49, 0.3f, -125), new Vector3f(0.4f,0.2f,0), new Vector3f(2f, 0.01f, 0.002f)));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(132, 2, -140));

        MasterRenderer renderer = new MasterRenderer(window, loader);
        Random random = new Random();

        List<Entity> allObjects = new ArrayList<>();
        List<Entity> flagObjects = new ArrayList<>();
        List<Entity> barrelObjects = new ArrayList<>();
        List<Entity> stallObjects = new ArrayList<>();
        List<Entity> bedObjects = new ArrayList<>();
        List<Entity> fortressPennantObjects = new ArrayList<>();

        for (int i = 0; i < 800; i++) {
            float x = random.nextFloat() * 500 - 50;
//            float y = random.nextFloat() * 100 - 50;
//            float z = random.nextFloat() * 100 - 50;
            allObjects.add(new Entity(texturedTree,
                    new Vector3f(x,0,lottery.randomZCoordinate(x, i)),
                    0f, 0f, 0f, 0.2f));

            x = random.nextFloat() * 500 - 50;
            allObjects.add(new Entity(grass,
                    new Vector3f(x,0,lottery.randomZCoordinate(x, i)),
                    0f, 0f, 0f, 0.5f));
            x = random.nextFloat() * 500 - 50;
            allObjects.add(new Entity(fern,
                    new Vector3f(x,0,lottery.randomZCoordinate(x, i)),
                    0f, 0f, 0f, 0.2f));
        }

        float[] flagXCoordinates = new float[] {44, 44, 101.5f, 101.5f};
        float[] flagZCoordinates = new float[] {-115.5f, -151, -115.5f, -151};

        for (int i = 0; i < 4; i++) {
            flagObjects.add(new Entity(texturedFlag,
                    new Vector3f(flagXCoordinates[i],8.5f,flagZCoordinates[i]),
                    0f, 0f, 0f, 4f));
        }

        float[] barrelXCoordinates = new float[] {77, 77, 77.5f, 50, 50.5f, 50, 50.1f, 50.8f};
        float[] barrelZCoordinates = new float[] {-137, -138, -137.5f, -120, -120.5f, -120.9f, -122, -122.5f};

        for (int i = 0; i < barrelXCoordinates.length; i++) {
            flagObjects.add(new Entity(texturedBarrel,
                    new Vector3f(barrelXCoordinates[i],0f,barrelZCoordinates[i]),
                    0f, 0.1f, 0f, 4f));
        }

        float[] stallXCoordinates = new float[] {50, 53, 56, 59};
        float[] stallZCoordinates = new float[] {-118, -118, -118, -118};

        for (int i = 0; i < stallXCoordinates.length; i++) {
            stallObjects.add(new Entity(texturedStall,
                    new Vector3f(stallXCoordinates[i],0f, stallZCoordinates[i]),
                    0f, 0.0f, 0f, 0.3f));
        }

        float[] bedXCoordinates = new float[] {78.5f, 80f, 81.5f, 83f, 84.5f, 86f};
        float[] bedZCoordinates = new float[] {-117.5f, -117.5f, -117.5f, -117.5f, -117.5f, -117.5f};

        for (int i = 0; i < bedXCoordinates.length; i++) {
            bedObjects.add(new Entity(texturedBed,
                    new Vector3f(bedXCoordinates[i],0f, bedZCoordinates[i]),
                    0f, 90f, 0f, 1f));
        }

        float[] fortressPennantXCoordinates = new float[] {47.5f, 52f, 56.5f};
        float[] fortressPennantZCoordinates = new float[] {-124.3f, -124.3f, -124.3f};

        for (int i = 0; i < fortressPennantXCoordinates.length; i++) {
            bedObjects.add(new Entity(texturedPennant,
                    new Vector3f(fortressPennantXCoordinates[i],4f, fortressPennantZCoordinates[i]),
                    0f, 90f, 0f, 1f));
        }

        Entity fortressEntity = new Entity(texturedFortress,
                new Vector3f(52,4.5f,-130),
                0f, 0f, 0f, 0.8f);

        Entity gateEntity = new Entity(texturedGate,
                new Vector3f(100,1.2f,-133),
                0f, 0f, 0f, 1f);

        Entity fortressGateEntity = new Entity(texturedFortressGate,
                new Vector3f(57,1.2f,-130),
                0f, 0f, 0f, 1f);

        Entity towerEntity = new Entity(texturedTower,
                new Vector3f(74,4.5f,-133),
                0f, 0f, 0f, 1f);

        Entity pavementEntity = new Entity(texturedPavement,
                new Vector3f(74, 0.05f, -133),
                0f, 90f, 0f, 1f);

        Entity pennantEntity = new Entity(texturedPennant,
                new Vector3f(54.2f, 15f, -130),
                0f, 0f, 0f, 1f);

        Entity pennantEntity2 = new Entity(texturedPennant,
                new Vector3f(85f, 2.5f, -120.5f),
                0f, 90f, 0f, 0.5f);

        Entity pennantEntity3 = new Entity(texturedPennant,
                new Vector3f(87f, 2.5f, -120.5f),
                0f, 90f, 0f, 0.5f);

        Entity medievalHouseEntity = new Entity(texturedMedievalHouse,
                new Vector3f(73f, 0f, -147),
                0f, 0f, 0f, 0.6f);

        Entity medievalHouseEntity2 = new Entity(texturedMedievalHouse,
                new Vector3f(80f, 0f, -147),
                0f, 0f, 0f, 0.6f);

        Entity medievalHouseEntity3 = new Entity(texturedMedievalHouse,
                new Vector3f(87f, 0f, -147),
                0f, 0f, 0f, 0.6f);
        Entity medievalHouseEntity4 = new Entity(texturedMedievalHouse,
                new Vector3f(94f, 0f, -147),
                0f, 0f, 0f, 0.6f);
        Entity medievalHouseEntity5 = new Entity(texturedMedievalHouse,
                new Vector3f(69f, 0f, -140),
                0f, 90f, 0f, 0.6f);

        Entity carriageEntity = new Entity(texturedCarriage,
                new Vector3f(74f, 0.1f, -143),
                -5f, 15f, -8f, 4f);

        Entity rainProtectionEntity = new Entity(texturedRainProtection,
                new Vector3f(95.5f, 0.1f, -140),
                0f, -180f, -0f, 4f);

        Entity stableEntity = new Entity(texturedStable,
                new Vector3f(70f, 0.1f, -118.5f),
                0f, 180f, -0f, 0.4f);

        Entity stableHorseEntity = new Entity(texturedHorse,
                new Vector3f(71f, 1f, -118.1f),
                0f, -90f, -0f, 0.004f);

        Entity stableHorseEntity2 = new Entity(texturedHorse,
                new Vector3f(67f, 1f, -118.1f),
                0f, -90f, -0f, 0.004f);

        Entity knightEntity = new Entity(texturedKnight,
                new Vector3f(102f, 6.5f, -145.1f),
                0f, 0f, -0f, 1f);

        Entity knightEntity2 = new Entity(texturedKnight,
                new Vector3f(102f, 6.5f, -130.1f),
                0f, 0f, -0f, 1f);

        Entity knightFortressEntity = new Entity(texturedKnight,
                new Vector3f(58f, 0f, -128.1f),
                0f, -90f, -0f, 1f);

        Entity knightFortressEntity2 = new Entity(texturedKnight,
                new Vector3f(58f, 0f, -132.1f),
                0f, -90f, -0f, 1f);

        Entity barrackEntity = new Entity(texturedBarrack,
                new Vector3f(85f, 2f, -118.5f),
                0f, 90f, -0f, 1f);

        Entity benchEntity = new Entity(texturedBench,
                new Vector3f(53.5f, 0f, -127.5f),
                0f, 90f, -0f, 6f);

        Entity wellEntity = new Entity(texturedWell,
                new Vector3f(88.5f, 0f, -138.5f),
                0f, 0f, -0f, 0.5f);

        Entity chestEntity = new Entity(texturedChest,
                new Vector3f(50f, 0f, -126.5f),
                0f, -30f, -0f, 0.1f);

        Entity lanternStable = new Entity(texturedLantern, new Vector3f(62,0,-118),0f,0f,0f,1f);
        Entity lanternBarrack = new Entity(texturedLantern, new Vector3f(95,0,-118),0f,0f,0f,1f);
        Entity lanternBackHouse = new Entity(texturedLantern, new Vector3f(69,0,-145),0f,0f,0f,1f);
        Entity lanternFrontHouse = new Entity(texturedLantern, new Vector3f(98,0,-145),0f,0f,0f,1f);
        Entity lanternInside = new Entity(texturedLantern, new Vector3f(49,0,-125),0f,0f,0f,1f);

        int number = 1;
        int frame=0;

        Music music = new Music("Stopping By the Inn");
        try {
            music.playSound();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(!window.isClosed()) {

            if(gateAction) {
                gateEntity.increasePosition(0, (float)Math.sin(Math.toRadians(angle)) /50,0f);
                if (angle % 360 == 179 || angle % 360 == 359) {
                    gateAction = false;
                }
                angle++;
            }
            if(fortressGateAction) {
                fortressGateEntity.increasePosition(0, (float)Math.sin(Math.toRadians(fortressGateAngle)) /60,0f);
                if (fortressGateAngle % 360 == 179 || fortressGateAngle % 360 == 359) {
                    fortressGateAction = false;
                }
                fortressGateAngle++;
            }

            knightEntity.increasePosition(0, 0,(float)Math.sin(Math.toRadians(knightAngle) * 0.5) / 30);
            if (knightAngle % 720 == 359 || knightAngle % 720 == 719) {
                knightEntity.setRotY(knightEntity.getRotY() + 180);
            }
            knightAngle++;
            knightEntity2.increasePosition(0, 0,(float)Math.sin(Math.toRadians(knightAngle) * 0.5) / 30);
            if (knightAngle2 % 720 == 359 || knightAngle2 % 720 == 719) {
                knightEntity2.setRotY(knightEntity2.getRotY() + 180);
            }
            knightAngle2++;

            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);

            for (Entity object : allObjects) {
                renderer.processEntity(object);
            }
            for (Entity object : flagObjects) {
                renderer.processEntity(object);
            }
            for (Entity object : barrelObjects) {
                renderer.processEntity(object);
            }
            for (Entity object : stallObjects) {
                renderer.processEntity(object);
            }
            for (Entity object : bedObjects) {
                renderer.processEntity(object);
            }
            for (Entity object : fortressPennantObjects) {
                renderer.processEntity(object);
            }

            renderer.processEntity(lanternStable);
            renderer.processEntity(lanternBarrack);
            renderer.processEntity(lanternBackHouse);
            renderer.processEntity(lanternFrontHouse);
            renderer.processEntity(lanternInside);
            renderer.processEntity(medievalHouseEntity);
            renderer.processEntity(medievalHouseEntity2);
            renderer.processEntity(medievalHouseEntity3);
            renderer.processEntity(medievalHouseEntity4);
            renderer.processEntity(medievalHouseEntity5);
            renderer.processEntity(carriageEntity);
            renderer.processEntity(pavementEntity);
            renderer.processEntity(pennantEntity);
            renderer.processEntity(pennantEntity2);
            renderer.processEntity(pennantEntity3);
            renderer.processEntity(fortressEntity);
            renderer.processEntity(gateEntity);
            renderer.processEntity(fortressGateEntity);
            renderer.processEntity(towerEntity);
            renderer.processEntity(rainProtectionEntity);
            renderer.processEntity(stableEntity);
            renderer.processEntity(stableHorseEntity);
            renderer.processEntity(stableHorseEntity2);
            renderer.processEntity(knightEntity);
            renderer.processEntity(knightEntity2);
            renderer.processEntity(barrackEntity);
            renderer.processEntity(benchEntity);
            renderer.processEntity(knightFortressEntity);
            renderer.processEntity(knightFortressEntity2);
            renderer.processEntity(wellEntity);
            renderer.processEntity(chestEntity);

            if(frame%7==0){
                number=number+1;
            }

            if(number==1) {
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(80,0,-140f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(75f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==2) {
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(80,0,-139.5f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(75.5f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==3) {
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(80,0,-139f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(76f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==4) {
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(80,0,-138.5f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(76.5f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==5) {
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(80,0,-138f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(77f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==6) {
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(80,0,-137.5f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(77.5f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==7) {
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(80,0,-137f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(78f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==8) {
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(80,0,-136.5f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(78.5f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==9) {
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(80,0,-136f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(79f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==10) {
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(80,0,-135.5f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(79.5f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==11) {
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(80,0,-135f), 0f,0f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(80f,0,-140), 0f,90f,0f,0.15f));
            }
            else if (number==12) {
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(80f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(80,0,-140f), 0f,0f,0f,0.15f));
            }
            else if (number==13) {
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(79.5f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(80,0,-139.5f), 0f,0f,0f,0.15f));
            }
            else if (number==14) {
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(79f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(80,0,-139f), 0f,0f,0f,0.15f));
            }
            else if (number==15) {
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(78.5f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(80,0,-138.5f), 0f,0f,0f,0.15f));
            }
            else if (number==16) {
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(78f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(80,0,-138f), 0f,0f,0f,0.15f));
            }
            else if (number==17) {
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(77.5f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(80,0,-137.5f), 0f,0f,0f,0.15f));
            }
            else if (number==18) {
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(77f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(80,0,-137f), 0f,0f,0f,0.15f));
            }
            else if (number==19) {
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(76.5f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(80,0,-136.5f), 0f,0f,0f,0.15f));
            }
            else if (number==20) {
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(76f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(80,0,-136f), 0f,0f,0f,0.15f));
            }
            else if (number==21) {
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(75.5f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(80,0,-135.5f), 0f,0f,0f,0.15f));
            }
            else if (number==22) {
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(75f,0,-135), 0f,-90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(80,0,-135f), 0f,0f,0f,0.15f));
            }
            else if (number==23) {
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(75,0,-135f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(80f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==24) {
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(75,0,-135.5f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(79.5f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==25) {
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(75,0,-136f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(79f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==26) {
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(75,0,-136.5f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(78.5f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==27) {
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(75,0,-137f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(78f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==28) {
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(75,0,-137.5f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(77.5f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==29) {
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(75,0,-138f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(77f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==30) {
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(75,0,-138.5f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(76.5f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==31) {
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(75,0,-139f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(76f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==32) {
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(75,0,-139.5f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(75.5f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==33) {
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(75,0,-140f), 0f,180f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(75f,0,-135), 0f,-90f,0f,0.15f));
            }
            else if (number==34) {
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(75f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh1, new Vector3f(75,0,-135f), 0f,180f,0f,0.15f));
            }
            else if (number==35) {
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(75.5f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh2, new Vector3f(75,0,-135.5f), 0f,180f,0f,0.15f));
            }
            else if (number==36) {
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(76f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh3, new Vector3f(75,0,-136f), 0f,180f,0f,0.15f));
            }
            else if (number==37) {
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(76.5f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh4, new Vector3f(75,0,-136.5f), 0f,180f,0f,0.15f));
            }
            else if (number==38) {
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(77f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh5, new Vector3f(75,0,-137f), 0f,180f,0f,0.15f));
            }
            else if (number==39) {
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(77.5f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh6, new Vector3f(75,0,-137.5f), 0f,180f,0f,0.15f));
            }
            else if (number==40) {
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(78f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh7, new Vector3f(75,0,-138f), 0f,180f,0f,0.15f));
            }
            else if (number==41) {
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(78.5f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh8, new Vector3f(75,0,-138.5f), 0f,180f,0f,0.15f));
            }
            else if (number==42) {
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(79f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh9, new Vector3f(75,0,-139f), 0f,180f,0f,0.15f));
            }
            else if (number==43) {
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(79.5f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh10, new Vector3f(75,0,-139.5f), 0f,180f,0f,0.15f));
            }
            else if (number==44) {
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(80f,0,-140), 0f,90f,0f,0.15f));
                renderer.processEntity(new Entity(texturedCh11, new Vector3f(75,0,-140f), 0f,180f,0f,0.15f));
            }
            frame=frame+1;
            if(number==44) number = 1;

            renderer.render(lights, camera);
            window.swapBuffers();
            window.update();
        }
        renderer.cleanUp();
        loader.cleanUp();
        music.stop();
        window.stop();
    }
}
