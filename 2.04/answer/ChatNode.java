public class ChatNode {
    // 2. The global tracker (shared by all objects)
    static int totalConnections = 0;
    
    // 3. Instance variable (unique to each object)
    String nodeName;

    // 4. Constructor increments the shared static variable
    public ChatNode(String nodeName) {
        this.nodeName = nodeName;
        totalConnections++; 
    }

    public static void main(String[] args) {
        // 5. Create three separate node objects
        ChatNode lobby = new ChatNode("Lobby");
        ChatNode general = new ChatNode("General");
        ChatNode gaming = new ChatNode("Gaming");

        // 6. Access the static variable directly via the Class name
        System.out.println("Total active nodes connected: " + ChatNode.totalConnections); 
        // Output will be 3!
    }
}