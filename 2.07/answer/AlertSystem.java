// 1. Parent Class
class Notifier {
    public void sendAlert(String message) {
        System.out.println("Generic alert: " + message);
    }
}

// 2. Child Classes
class EmailNotifier extends Notifier {
    @Override
    public void sendAlert(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class SMSNotifier extends Notifier {
    @Override
    public void sendAlert(String message) {
        System.out.println("Texting: " + message);
    }
}

public class AlertSystem {
    // 3. Method accepting the Parent type
    public static void triggerAlarm(Notifier n, String msg) {
        n.sendAlert(msg); // Dynamically calls the specific child implementation
    }

    public static void main(String[] args) {
        Notifier email = new EmailNotifier();
        Notifier sms = new SMSNotifier();
        
        // 4. Passing different child objects into the same method
        triggerAlarm(email, "Server 404 Error!");
        triggerAlarm(sms, "Server 404 Error!");
    }
}
