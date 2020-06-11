package gameEngine;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TestModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class MainGameLoop {

    public static void main(String[] args) {

        Window window = new Window(800, 800, "Test");
        Loader loader = new Loader();
        TestModel testModel = new TestModel();

        window.init();
        window.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        //tree
        RawModel tree = OBJLoader.loadOBJModel("tree", loader);
        ModelTexture textureTree = loader.loadTexture("tree");
        TexturedModel texturedTree = new TexturedModel(tree, textureTree);
//        ModelTexture modelTexture = texturedModel.getTexture();
//        modelTexture.setShineDamper(2);
//        modelTexture.setReflectivity(1);

        //grass
//        RawModel grass = OBJLoader.loadOBJModel("grassModel", loader);
//        ModelTexture textureGrass = loader.loadTexture("grassTexture");
//        TexturedModel texturedGrass = new TexturedModel(tree, textureTree);
        TexturedModel grass = new TexturedModel(OBJLoader.loadOBJModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("grassTexture").getId()));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLightning(true);

        //fern
//        RawModel fern = OBJLoader.loadOBJModel("fern", loader);
//        ModelTexture textureFern = loader.loadTexture("fern");
//        TexturedModel texturedFern = new TexturedModel(tree, textureTree);
        TexturedModel fern = new TexturedModel(OBJLoader.loadOBJModel("fern", loader),
                new ModelTexture(loader.loadTexture("fern2").getId()));
        fern.getTexture().setHasTransparency(true);
        fern.getTexture().setUseFakeLightning(true);

        //terrain
        Terrain terrain = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("grass").getId()));
        Terrain terrain2 = new Terrain(1,0, loader, new ModelTexture(loader.loadTexture("grass").getId()));

        //Entity entity = new Entity(texturedTree, new Vector3f(0,0,-25),0,0,0,1);
        Light light = new Light(new Vector3f(200,200,200), new Vector3f(1,1,1));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(0, 2, 0));

        MasterRenderer renderer = new MasterRenderer(window);
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
                    0f, 0f, 0f, 0.3f));
            allObjects.add(new Entity(fern,
                    new Vector3f(random.nextFloat() * 500 - 400,0,random.nextFloat() * -100),
                    0f, 0f, 0f, 0.2f));
        }

        while(!window.isClosed()) {
            //window.clearFrameBuffer();
            //entity.increasePosition(0, 0,-0.01f);
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            //entity.increaseRotation(0,1,0);
            for (Entity object : allObjects) {
                renderer.processEntity(object);
            }
            renderer.render(light, camera);
            window.swapBuffers();
            window.update();
        }
        renderer.cleanUp();
        loader.cleanUp();
        window.stop();
    }
}
