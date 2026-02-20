// 1. Abstract Class (The Blueprint)
abstract class BaseMessage {
    // 2. Concrete method (Shared logic)
    void encrypt() {
        System.out.println("Data encrypted using AES.");
    }

    // 3. Abstract method (Forced implementation)
    abstract void send();
}

// 4. Child Class
class EmailMessage extends BaseMessage {
    @Override
    void send() {
        System.out.println("Sending Email via SMTP server...");
    }
}

public class MessagingSystem {
    public static void main(String[] args) {
        // 5. Polymorphism: Reference is Base, Object is Email
        BaseMessage msg = new EmailMessage();
        msg.encrypt(); // Inherited behavior
        msg.send();    // Overridden behavior
    }
}