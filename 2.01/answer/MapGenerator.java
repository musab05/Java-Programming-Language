// 1 & 2. Define the blueprint and its fields
class TerrainChunk {
    int xPosition;
    int yPosition;
    String biomeType;

    // 3. Define the behavior
    void renderChunk() {
        System.out.println("Rendering " + biomeType + " chunk at " + xPosition + ", " + yPosition);
    }
}

public class MapGenerator {
    public static void main(String[] args) {
        // 4. Create the first object
        TerrainChunk chunk1 = new TerrainChunk();
        chunk1.xPosition = 0;
        chunk1.yPosition = 0;
        chunk1.biomeType = "Forest";

        // 4. Create the second object
        TerrainChunk chunk2 = new TerrainChunk();
        chunk2.xPosition = 16;
        chunk2.yPosition = 0;
        chunk2.biomeType = "Desert";

        // 5. Execute the methods
        chunk1.renderChunk();
        chunk2.renderChunk();
    }
}