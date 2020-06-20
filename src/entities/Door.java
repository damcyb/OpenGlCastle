package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Door extends Entity {

    public Door(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

//    public static void openDoor(int key) {
//        if(key == GLFW_KEY_SPACE) {
//            increaseRotation(0,0.5, 0);
//        }
//    }
}
