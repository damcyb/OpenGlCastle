package entities;

import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Door extends Entity {

//    private static boolean openDoor = false;
//
    public Door(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }
//
//    public static void openTheDoor(int key) {
//        int counter;
//        if (key == GLFW_KEY_SPACE) {
//            openDoor = true;
//            counter = 0;
//        }
//
//        if(counter >= 50) {
//            openDoor = false
//        }
//    }
//
//    public static boolean isOpenDoor() {
//        return openDoor;
//    }
}
