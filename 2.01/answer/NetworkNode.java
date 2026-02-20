// 1 & 2. Define the class and fields
class ChatServer {
    String serverName;
    int onlineMembers;

    // 3. Define the state-mutating behavior
    void userJoined() {
        onlineMembers++; // Increment the member count
        System.out.println("A user joined " + serverName + ". Total online: " + onlineMembers);
    }
}

public class NetworkNode {
    public static void main(String[] args) {
        // 4. Create the server object and set initial state
        ChatServer server = new ChatServer();
        server.serverName = "Global-Chat-1";
        server.onlineMembers = 5;

        // Simulate 3 users joining sequentially
        server.userJoined();
        server.userJoined();
        server.userJoined();
    }
}