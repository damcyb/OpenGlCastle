package gameEngine;

import entities.Camera;
import entities.Door;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.TestModel;
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

        window.init();
        window.createCapabilities();

        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.5f);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_SAMPLE_ALPHA_TO_COVERAGE);
        glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);

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

        //walls and rooks
//        RawModel wall = OBJLoader.loadOBJModel("zamek1Converted", loader);
//        ModelTexture textureWall = loader.loadTexture("wallBricks2");
//        TexturedModel texturedWall = new TexturedModel(wall, textureWall);
//        texturedWall.getTexture().setShineDamper(2);
//        texturedWall.getTexture().setReflectivity(2);

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

        RawModel hay = OBJLoader.loadOBJModel("hayConverted", loader);
        ModelTexture textureHay = loader.loadTexture("hay");
        TexturedModel texturedHay = new TexturedModel(hay, textureHay);
//        texturedHay.getTexture().setShineDamper(2);
//        texturedHay.getTexture().setReflectivity(1);

        RawModel stable = OBJLoader.loadOBJModel("stable", loader);
        ModelTexture textureStable = loader.loadTexture("wood2");
        TexturedModel texturedStable = new TexturedModel(stable, textureStable);
        texturedStable.getTexture().setShineDamper(2);
        texturedStable.getTexture().setReflectivity(1);

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
        Light light = new Light(new Vector3f(1000,1000,1000), new Vector3f(0.8f,0.8f,0.8f));


        //Light light = new Light(new Vector3f(0,1000,100), new Vector3f(1f,1f,1f));
        List<Light> lights = new ArrayList<Light>();
        lights.add(light);
        lights.add(new Light(new Vector3f(-5, 0f, -130), new Vector3f(0.5f,0.5f,0), new Vector3f(0.01f, 0.01f, 0.002f)));
        //lights.add(new Light(new Vector3f(-200, 10,-200), new Vector3f(10,0,0)));
        //lights.add(new Light(new Vector3f(200, 10,200), new Vector3f(0,0,10)));
        //Light light2 = new Light(new Vector3f(0,1000,-100), new Vector3f(0.8f,0.8f,0.8f));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(132, 2, -140));

        Light cameraLight = new Light(camera.getPosition(), new Vector3f(1,1,1));

        MasterRenderer renderer = new MasterRenderer(window, loader);
        Random random = new Random();

        List<Entity> allObjects = new ArrayList<>();
        List<Entity> flagObjects = new ArrayList<>();
        List<Entity> barrelObjects = new ArrayList<>();
        List<Entity> stallObjects = new ArrayList<>();

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

        Entity hayEntity = new Entity(texturedHay,
                new Vector3f(99.5f, 0f, -140),
                0f, 0f, -0f, 1f);

        Entity stableEntity = new Entity(texturedStable,
                new Vector3f(70f, 0.1f, -118.5f),
                0f, 180f, -0f, 0.4f);

        Entity stableHorse = new Entity(texturedHorse,
                new Vector3f(71f, 1f, -118.1f),
                0f, -90f, -0f, 0.004f);

        Entity stableHorse2 = new Entity(texturedHorse,
                new Vector3f(67f, 1f, -118.1f),
                0f, -90f, -0f, 0.004f);

        //gateEntity.translate(0.0f, 0.0f, 2f);
        Entity horseX = new Entity(texturedHorse,
                new Vector3f(0,2,-130),
                0f,0f,0f,0.01f);

        Entity lanternX = new Entity(texturedLantern,
                new Vector3f(-5,0,-130),
                0f,0f,0f,1f);

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

            renderer.processEntity(horseX);
            renderer.processEntity(lanternX);
            renderer.processEntity(medievalHouseEntity);
            renderer.processEntity(medievalHouseEntity2);
            renderer.processEntity(medievalHouseEntity3);
            renderer.processEntity(medievalHouseEntity4);
            renderer.processEntity(medievalHouseEntity5);
            renderer.processEntity(carriageEntity);
            renderer.processEntity(pavementEntity);
            renderer.processEntity(pennantEntity);
            renderer.processEntity(fortressEntity);
            renderer.processEntity(gateEntity);
            renderer.processEntity(fortressGateEntity);
            renderer.processEntity(towerEntity);
            renderer.processEntity(rainProtectionEntity);
            renderer.processEntity(stableEntity);
            renderer.processEntity(stableHorse);
            renderer.processEntity(stableHorse2);
            //renderer.processEntity(hayEntity);
            //gateEntity.increaseRotation(0f, 0.5f, 0f);

            //renderer.processEntity(wallA);
            renderer.render(lights, camera);
            window.swapBuffers();
            window.update();
        }
        renderer.cleanUp();
        loader.cleanUp();
        window.stop();
    }
}
