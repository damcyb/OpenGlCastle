package gameEngine;

import entities.Camera;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.TestModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

    public static void main(String[] args) {

        Window window = new Window(800, 800, "Test");
        Loader loader = new Loader();
        TestModel testModel = new TestModel();

        window.init();
        window.createCapabilities();

//        glEnable(GL_BLEND);
//        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
//        glEnable(GL_ALPHA_TEST);

        //tree
        RawModel tree = OBJLoader.loadOBJModel("tree", loader);
        ModelTexture textureTree = loader.loadTexture("tree");
        TexturedModel texturedTree = new TexturedModel(tree, textureTree);
//        ModelTexture modelTexture = texturedModel.getTexture();
//        modelTexture.setShineDamper(2);
//        modelTexture.setReflectivity(1);

        RawModel house = OBJLoader.loadOBJModel("cottage_blender2", loader);
        ModelTexture textureHouse = loader.loadTexture("cottage_diffuse");
        TexturedModel texturedHouse = new TexturedModel(house, textureHouse);

        //horse
        RawModel horse = OBJLoader.loadOBJModel("horse", loader);
        ModelTexture textureHorse = loader.loadTexture("horse_texture");
        TexturedModel texturedHorse = new TexturedModel(horse, textureHorse);

        //tower
        RawModel tower = OBJLoader.loadOBJModel("Medieval tower_mid", loader);
        ModelTexture textureTower = loader.loadTexture("Medieval tower_mid_Col");
        TexturedModel texturedTower = new TexturedModel(tower, textureTower);

        //outside house
        RawModel house2 = OBJLoader.loadOBJModel("medieval house2", loader);
        ModelTexture textureHouse2 = loader.loadTexture("medieval house2");
        TexturedModel texturedHouse2 = new TexturedModel(house2, textureHouse2);

        //wood
        RawModel wood = OBJLoader.loadOBJModel("wood", loader);
        ModelTexture textureWood = loader.loadTexture("WoodTexture");
        TexturedModel texturedWood = new TexturedModel(wood, textureWood);

        //market stand
        RawModel market = OBJLoader.loadOBJModel("market-stand", loader);
        ModelTexture textureMarket = loader.loadTexture("market-stand");
        TexturedModel texturedMarket = new TexturedModel(market, textureMarket);

        //fence
        RawModel fence = OBJLoader.loadOBJModel("fence", loader);
        ModelTexture textureFence = loader.loadTexture("fence");
        TexturedModel texturedFence = new TexturedModel(fence, textureFence);

        //chest
        RawModel chest = OBJLoader.loadOBJModel("chest", loader);
        ModelTexture textureChest = loader.loadTexture("chest");
        TexturedModel texturedChest = new TexturedModel(chest, textureChest);


        //grass
//        RawModel grass = OBJLoader.loadOBJModel("grassModel", loader);
//        ModelTexture textureGrass = loader.loadTexture("grassTexture");
//        TexturedModel texturedGrass = new TexturedModel(tree, textureTree);
        TexturedModel grass = new TexturedModel(OBJLoader.loadOBJModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("grassTexture2").getId()));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLightning(true);

        //fern
//        RawModel fern = OBJLoader.loadOBJModel("fern", loader);
//        ModelTexture textureFern = loader.loadTexture("fern");
//        TexturedModel texturedFern = new TexturedModel(tree, textureTree);
        TexturedModel fern = new TexturedModel(OBJLoader.loadOBJModel("fern", loader),
                new ModelTexture(loader.loadTexture("fern3").getId()));
        fern.getTexture().setHasTransparency(true);
        fern.getTexture().setUseFakeLightning(true);

        //terrain
        Terrain terrain = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("grass").getId()));
        Terrain terrain2 = new Terrain(1,0, loader, new ModelTexture(loader.loadTexture("grass").getId()));

        //Entity entity = new Entity(texturedTree, new Vector3f(0,0,-25),0,0,0,1);
        Light light = new Light(new Vector3f(200,200,200), new Vector3f(1,1,1));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(0, 2, 0));


        List<GuiTexture> guis = new ArrayList<>();
//        GuiTexture guis = new GuiTexture(loader.)

        GuiRenderer guiRenderer = new GuiRenderer(loader);

        MasterRenderer renderer = new MasterRenderer(window, loader);
        Random random = new Random();

        List<Entity> allObjects = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            float x = random.nextFloat() * 100 - 50;
            float y = random.nextFloat() * 100 - 50;
            float z = random.nextFloat() * 100 - 50;
            allObjects.add(new Entity(texturedTree,
                    new Vector3f(random.nextFloat() * 500 - 400,0,random.nextFloat() * -100),
                    0f, 0f, 0f, 1f));
            allObjects.add(new Entity(grass,
                    new Vector3f(random.nextFloat() * 500 - 400,0,random.nextFloat() * -100),
                    0f, 0f, 0f, 0.5f));
            allObjects.add(new Entity(fern,
                    new Vector3f(random.nextFloat() * 500 - 400,0,random.nextFloat() * -100),
                    0f, 0f, 0f, 0.2f));

        }

        Entity houseX = new Entity(texturedHouse,
                new Vector3f(0,0,-50),
                0f, 0f, 0f, 0.5f);

        Entity horseX = new Entity(texturedHorse,
                new Vector3f(0,2,-30),
                0f, 0f, 0f, 0.01f);

        Entity towerX = new Entity(texturedTower,
                new Vector3f(30,0,-50),
                0f, 0f, 0f, 0.5f);

        Entity house2X = new Entity(texturedHouse2,
                new Vector3f(0,0,-10),
                0f, 0f, 0f, 0.1f);

        Entity woodX = new Entity(texturedWood,
                new Vector3f(10,2,-10),
                0f, 0f, 0f, 0.2f);

        Entity marketX = new Entity(texturedMarket,
                new Vector3f(10,0,-10),
                0f, 0f, 0f, 1.5f);

        Entity fenceX = new Entity(texturedFence,
                new Vector3f(5,0,-10),
                0f, 0f, 0f, 0.5f);
        Entity fence2X = new Entity(texturedFence,
                new Vector3f(5,0,-7),
                0f, 0f, 0f, 0.5f);

        Entity chestX = new Entity(texturedChest,
                new Vector3f(3,0,-5),
                0f, 180f, 0f, 0.1f);

        while(!window.isClosed()) {
            //window.clearFrameBuffer();
            //entity.increasePosition(0, 0,-0.01f);
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            //entity.increaseRotation(0,1,0);
            for (Entity object : allObjects) {
                renderer.processEntity(object);
            }

            renderer.processEntity(houseX);
            renderer.processEntity(horseX);
            renderer.processEntity(towerX);
            renderer.processEntity(house2X);
            //renderer.processEntity(woodX);
            renderer.processEntity(marketX);
            renderer.processEntity(fenceX);
            renderer.processEntity(fence2X);
            renderer.processEntity(chestX);
//            guiRenderer.render(guis);
            renderer.render(light, camera);
            window.swapBuffers();
            window.update();
        }
//        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        window.stop();
    }
}
