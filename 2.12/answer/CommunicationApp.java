interface Messenger {
    void sendText(String msg);

    // 2. Default method (Backward compatibility)
    default void startVideoCall() {
        System.out.println("Video call not supported on this device.");
    }
}

// 3. Class that ignores the default method
class OldPhone implements Messenger {
    public void sendText(String msg) {
        System.out.println("OldPhone SMS: " + msg);
    }
}

// 4. Class that utilizes/overrides the default method
class SmartPhone implements Messenger {
    public void sendText(String msg) {
        System.out.println("SmartPhone iMessage: " + msg);
    }

    @Override
    public void startVideoCall() {
        System.out.println("Connecting video call via 5G...");
    }
}

public class CommunicationApp {
    public static void main(String[] args) {
        Messenger nokia = new OldPhone();
        Messenger iphone = new SmartPhone();

        nokia.startVideoCall();  // Uses default interface logic
        iphone.startVideoCall(); // Uses its own overridden logic
    }
}