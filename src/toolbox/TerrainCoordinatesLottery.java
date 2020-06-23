package toolbox;

import org.lwjgl.util.vector.Vector3f;

import java.util.Random;

public class TerrainCoordinatesLottery {

    public float randomZCoordinate(float x, int iteration) {
        Random random = new Random();
        if (x < 30.0f || x > 110.0f) {
            return random.nextFloat() * - 200;
        } else if(iteration % 2 == 0){
            return -(float)random.nextInt(110);
        } else {
            return -(float)random.nextInt(250) - 160;
        }
    }
}
