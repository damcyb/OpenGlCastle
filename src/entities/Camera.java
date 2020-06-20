package entities;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Window;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private static Vector3f position = new Vector3f(0,0,0);
    private static float pitch;
    private static float yaw;
    private static float roll;

    public Camera() {}

    public static void move(int key) {
       if(key == GLFW_KEY_W) {
           position.x += 0.8f * Math.sin(Math.toRadians(yaw));
           position.z -= 0.8f * Math.cos(Math.toRadians(yaw));
       }
        if(key == GLFW_KEY_D) {
            position.x += 0.8f * Math.cos(Math.toRadians(yaw));
            position.z += 0.8f * Math.sin(Math.toRadians(yaw));
        }
        if(key == GLFW_KEY_A) {
            position.x -= 0.8f * Math.cos(Math.toRadians(yaw));
            position.z -= 0.8f * Math.sin(Math.toRadians(yaw));
        }
        if(key == GLFW_KEY_S) {
            position.x -= 0.8f * Math.sin(Math.toRadians(yaw));
            position.z += 0.8f * Math.cos(Math.toRadians(yaw));
        }
        if(key == GLFW_KEY_Q) {
            yaw -= 0.8f;
        }
        if(key == GLFW_KEY_E) {
            yaw += 0.8f;
        }
        if(key == GLFW_KEY_Z) {
            pitch += 0.8f;
        }
        if(key == GLFW_KEY_X) {
            pitch -= 0.8f;
        }
        if(key == GLFW_KEY_1) {
            position.y += 0.2f;
        }
        if(key == GLFW_KEY_2) {
            position.y -= 0.2f;
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
