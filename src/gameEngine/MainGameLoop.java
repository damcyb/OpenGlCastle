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

import static java.lang.Thread.sleep;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_SAMPLE_ALPHA_TO_COVERAGE;
import static org.lwjgl.opengl.GL14.glBlendFuncSeparate;

public class MainGameLoop {

    public static boolean gateAction = false;

    public static void main(String[] args) {

        Window window = new Window(1920, 1000, "Test");
        Loader loader = new Loader();

        TerrainCoordinatesLottery lottery = new TerrainCoordinatesLottery();

        float angle = 0.0f;

        window.init();
        window.createCapabilities();

        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.5f);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_SAMPLE_ALPHA_TO_COVERAGE);
        glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);

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
        RawModel house = OBJLoader.loadOBJModel("twierdza6Converted", loader);
        ModelTexture textureHouse = loader.loadTexture("wallBricks3");
        TexturedModel texturedHouse = new TexturedModel(house, textureHouse);
        texturedHouse.getTexture().setShineDamper(2);
        texturedHouse.getTexture().setReflectivity(1);

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

        Entity houseX = new Entity(texturedHouse,
                new Vector3f(50,4.5f,-130),
                0f, 0f, 0f, 0.8f);

        Entity gateEntity = new Entity(texturedGate,
                new Vector3f(100,1.2f,-133),
                0f, 0f, 0f, 1f);

        Entity towerEntity = new Entity(texturedTower,
                new Vector3f(74,4.5f,-133),
                0f, 0f, 0f, 1f);

        Entity pavementEntity = new Entity(texturedPavement,
                new Vector3f(74, 0.05f, -133),
                0f, 90f, 0f, 1f);

        Entity pennantEntity = new Entity(texturedPennant,
                new Vector3f(53.0f, 15f, -130),
                0f, 0f, 0f, 1f);

        //gateEntity.translate(0.0f, 0.0f, 2f);
        Entity horseX = new Entity(texturedHorse,
                new Vector3f(0,2,-130),
                0f,0f,0f,0.01f);

        Entity lanternX = new Entity(texturedLantern,
                new Vector3f(-5,0,-130),
                0f,0f,0f,1f);

        //animation
        Entity ch1X = new Entity(texturedCh1, new Vector3f(130,0,-130), 0f,0f,0f,0.5f);

        Entity ch2X = new Entity(texturedCh2, new Vector3f(130,0,-129), 0f,0f,0f,0.5f);

        Entity ch3X = new Entity(texturedCh3, new Vector3f(130,0,-128), 0f,0f,0f,0.5f);
        Entity ch4X = new Entity(texturedCh4, new Vector3f(130,0,-127), 0f,0f,0f,0.5f);
        Entity ch5X = new Entity(texturedCh5, new Vector3f(130,0,-126), 0f,0f,0f,0.5f);
        Entity ch6X = new Entity(texturedCh6, new Vector3f(130,0,-125), 0f,0f,0f,0.5f);
        Entity ch7X = new Entity(texturedCh7, new Vector3f(130,0,-124), 0f,0f,0f,0.5f);
        Entity ch8X = new Entity(texturedCh8, new Vector3f(130,0,-123), 0f,0f,0f,0.5f);
        Entity ch9X = new Entity(texturedCh9, new Vector3f(130,0,-122), 0f,0f,0f,0.5f);
        Entity ch10X = new Entity(texturedCh10, new Vector3f(130,0,-121), 0f,0f,0f,0.5f);
        Entity ch11X = new Entity(texturedCh11, new Vector3f(130,0,-120), 0f,0f,0f,0.5f);


        int number = 1;
        int frame=0;

        while(!window.isClosed()) {

            //cameraLight.setPosition(camera.getPosition());
            //window.clearFrameBuffer();

            if(gateAction) {
                gateEntity.increasePosition(0, (float)Math.sin(Math.toRadians(angle)) /50,0f);
                if (angle % 360 == 179 || angle % 360 == 359) {
                    gateAction = false;
                }
                angle++;
            }

            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            //entity.increaseRotation(0,1,0);
            for (Entity object : allObjects) {
                renderer.processEntity(object);
            }

            renderer.processEntity(horseX);
            renderer.processEntity(lanternX);

            renderer.processEntity(pavementEntity);
            renderer.processEntity(pennantEntity);
            renderer.processEntity(houseX);
            renderer.processEntity(gateEntity);
            renderer.processEntity(towerEntity);

            if(frame%10==0){

                number=number+1;
            }

            if(number==1){
                renderer.processEntity(ch1X);
            }else if (number==2){
                renderer.processEntity(ch2X);
            }else if (number==3){
                renderer.processEntity(ch3X);
            }else if (number==4){
                renderer.processEntity(ch4X);
            }else if (number==5){
                renderer.processEntity(ch5X);
            }else if (number==6){
                renderer.processEntity(ch6X);
            }else if (number==7){
                renderer.processEntity(ch7X);
            }else if (number==8){
                renderer.processEntity(ch8X);
            }else if (number==9){
                renderer.processEntity(ch9X);
            }else if (number==10){
                renderer.processEntity(ch10X);
            }else if (number==11){
                renderer.processEntity(ch11X);
            }

            frame=frame+1;
            if(number==11) number = 1;
//            renderer.processEntity(ch1X);
//            //sleep(0.1);
//            renderer.processEntity(ch2X);
//            renderer.processEntity(ch3X);
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
