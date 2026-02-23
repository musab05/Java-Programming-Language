class MessageChannel {
    private String content;
    private boolean empty = true;

    public synchronized String take() {
        // 1. Wait while there is no message to read
        while (empty) {
            try { wait(); } catch (InterruptedException e) { }
        }
        
        // 2. Clear the state and alert the sender
        empty = true;
        String message = content;
        System.out.println("Receiver: Got [" + message + "]");
        notifyAll(); 
        return message;
    }

    public synchronized void put(String message) {
        // 1. Wait while the previous message hasn't been read yet
        while (!empty) {
            try { wait(); } catch (InterruptedException e) { }
        }
        
        // 2. Set the message and alert the receiver
        empty = false;
        this.content = message;
        System.out.println("Sender: Putting [" + message + "]");
        notifyAll();
    }
}

public class MessagingApp {
    public static void main(String[] args) {
        MessageChannel channel = new MessageChannel();

        Thread sender = new Thread(() -> {
            String[] messages = {"Hello", "How are you?", "Goodbye"};
            for (String m : messages) {
                channel.put(m);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        Thread receiver = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                channel.take();
            }
        });

        sender.start();
        receiver.start();
    }
}