package entities;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Window;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private static Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera() {}

    public static void move(int key) {
       if(key == GLFW_KEY_W) {
           position.z -= 0.2f;
       }
        if(key == GLFW_KEY_D) {
            position.x -= 0.2f;
        }
        if(key == GLFW_KEY_A) {
            position.x += 0.2f;
        }
        if(key == GLFW_KEY_S) {
            position.z += 0.2f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
