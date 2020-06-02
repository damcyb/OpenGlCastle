package renderEngine;

import de.matthiasmann.twl.utils.PNGDecoder;
import models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import textures.ModelTexture;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Loader {

//    private List<Integer> vaos = new ArrayList<>();
//    private List<Integer> vbos = new ArrayList<>();
//    private List<Integer> textures = new ArrayList<>();
//
//    public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices) {
//        int vaoID = createVAO();
//        bindIndicesBuffer(indices);
//        storeDataInAttributeList(0, 3, positions);
//        storeDataInAttributeList(1, 3, textureCoords);
//        unbindVAO();
//        return new RawModel(vaoID, indices.length);
//    }
//
//    public int loadTexture(String fileName) {
//        int texture;
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(Loader.class.getResourceAsStream(fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return 0;
//        }
//        int width = img.getWidth();
//        int height = img.getHeight();
//        int[] pixels = img.getRGB(0,0, width, height, null, 0, width);
//        ByteBuffer byteBuffer = BufferUtils.createByteBuffer((width * height * 3));
//        texture = GL11.glGenTextures();
//
//        for (int i = 0; i < pixels.length; i++) {
//            byte rr = (byte)((pixels[i] >> 16) & 0xFF);
//            byte gg = (byte)((pixels[i] >> 8) & 0xFF);
//            byte bb = (byte)((pixels[i]) & 0xFF);
//
//            byteBuffer.put(rr);
//            byteBuffer.put(gg);
//            byteBuffer.put(bb);
//        }
//
//        byteBuffer.flip();
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0,
//                GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, byteBuffer);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//
//        textures.add(texture);
//        return texture;
//    }
//
//    public void cleanUp() {
//        for (int vao : vaos) {
//            GL30.glDeleteVertexArrays(vao);
//        }
//        for (int vbo : vbos) {
//            GL15.glDeleteBuffers(vbo);
//        }
//        for (int texture : textures) {
//            GL11.glDeleteTextures(texture);
//        }
//    }
//
//    private int createVAO() {
//        int vaoID = GL30.glGenVertexArrays();
//        vaos.add(vaoID);
//        GL30.glBindVertexArray(vaoID);
//        return vaoID;
//    }
//
//    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data) {
//        int vboID = GL15.glGenBuffers();
//        vbos.add(vboID);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
//        FloatBuffer buffer = storeDataInFloatBuffer(data);
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
//        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//    }
//
//    private void unbindVAO() {
//        GL30.glBindVertexArray(0);
//    }
//
//    private FloatBuffer storeDataInFloatBuffer(float[] data) {
//        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
//        buffer.put(data);
//        buffer.flip();
//        return buffer;
//    }
//
//    private IntBuffer storeDataInIntBuffer(int[] data) {
//        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
//        buffer.put(data);
//        buffer.flip();
//        return buffer;
//    }
//
//    private void bindIndicesBuffer(int[] indices) {
//        int vboId = GL15.glGenBuffers();
//        vbos.add(vboId);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
//        IntBuffer buffer = storeDataInIntBuffer(indices);
//        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
//    }
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<>();

    public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3, positions);
        storeDataInAttributeList(1,2, textureCoords);
        storeDataInAttributeList(2,3, normals);
        unbindVAO();
        return new RawModel(vaoID,indices.length);
    }

    public ModelTexture loadTexture(String fileName) {
        //load png file
        PNGDecoder decoder = null;
        try {
            //decoder = new PNGDecoder(Loader.class.getResourceAsStream("/res/"+ fileName + ".png"));
            decoder = new PNGDecoder(new FileInputStream("res/obj/"+ fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create a byte buffer big enough to store RGBA values
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
        //decode
        try {
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //flip the buffer so its ready to read
        buffer.flip();
        //create a texture
        int textureId = glGenTextures();
        //bind the texture
        glBindTexture(GL_TEXTURE_2D, textureId);
        //tell opengl how to unpack bytes
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        //set the texture parameters, can be GL_LINEAR or GL_NEAREST
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        //upload texture
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D);
        textures.add(textureId);

//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        return new ModelTexture(textureId);
    }

    public void cleanUp(){
        for(int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo : vbos){
            GL15.glDeleteBuffers(vbo);
        }
        for(int texture : textures){
            GL15.glDeleteBuffers(texture);
        }
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,coordinateSize,GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }


}
