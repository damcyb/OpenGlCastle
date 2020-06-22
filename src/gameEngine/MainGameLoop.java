package gameEngine;

import entities.Camera;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_SAMPLE_ALPHA_TO_COVERAGE;
import static org.lwjgl.opengl.GL14.glBlendFuncSeparate;

public class MainGameLoop {

    public static void main(String[] args) {

        Window window = new Window(1920, 1000, "Test");
        Loader loader = new Loader();

        float angle = 0.0f;

        window.init();
        window.createCapabilities();

        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.5f);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_SAMPLE_ALPHA_TO_COVERAGE);
        glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE);

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
        RawModel gate = OBJLoader.loadOBJModel("gate", loader);
        ModelTexture textureGate = loader.loadTexture("gate4");
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
        //Light light2 = new Light(new Vector3f(0,1000,-100), new Vector3f(0.8f,0.8f,0.8f));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(132, 2, -140));

        Light cameraLight = new Light(camera.getPosition(), new Vector3f(1,1,1));

        MasterRenderer renderer = new MasterRenderer(window, loader);
        Random random = new Random();

        List<Entity> allObjects = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            float x = random.nextFloat() * 100 - 50;
            float y = random.nextFloat() * 100 - 50;
            float z = random.nextFloat() * 100 - 50;
            allObjects.add(new Entity(texturedTree,
                    new Vector3f(random.nextFloat() * 500 - 100,0,random.nextFloat() * -200),
                    0f, 0f, 0f, 0.2f));
            allObjects.add(new Entity(grass,
                    new Vector3f(random.nextFloat() * 500 - 100,0,random.nextFloat() * -200),
                    0f, 0f, 0f, 0.5f));
            allObjects.add(new Entity(fern,
                    new Vector3f(random.nextFloat() * 500 - 100,0,random.nextFloat() * -200),
                    0f, 0f, 0f, 0.2f));
        }

        Entity houseX = new Entity(texturedHouse,
                new Vector3f(50,4.5f,-130),
                0f, 0f, 0f, 0.8f);

        Entity gateEntity = new Entity(texturedGate,
                new Vector3f(64,0,-133),
                0f, 0f, 0f, 5f);

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

        while(!window.isClosed()) {

            cameraLight.setPosition(camera.getPosition());
            //window.clearFrameBuffer();
            //entity.increasePosition(0, 0,-0.01f);
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            //entity.increaseRotation(0,1,0);
            for (Entity object : allObjects) {
                renderer.processEntity(object);
            }


            renderer.processEntity(pavementEntity);
            renderer.processEntity(pennantEntity);
            renderer.processEntity(houseX);
            //renderer.processEntity(gateEntity);
            renderer.processEntity(towerEntity);
            //gateEntity.increaseRotation(0f, 0.5f, 0f);

            //renderer.processEntity(wallA);
            renderer.render(light, camera);
            window.swapBuffers();
            window.update();
        }
        renderer.cleanUp();
        loader.cleanUp();
        window.stop();
    }
}
