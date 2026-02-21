// 1. The Interface with multiple methods
interface MouseListener {
    void onClick();
    void onRightClick();
}

// 2. The Button class that consumes the interface
class Button {
    public void attachListener(MouseListener listener) {
        // Simulating user actions
        listener.onClick();
        listener.onRightClick();
    }
}

// 3. The Main class to tie it together
public class Main {
    public static void main(String[] args) {
        Button submitButton = new Button();

        // 4. Passing an Anonymous Inner Class directly into the method
        submitButton.attachListener(new MouseListener() {
            @Override
            public void onClick() {
                System.out.println("Left click detected! Submitting form...");
            }

            @Override
            public void onRightClick() {
                System.out.println("Right click detected! Opening context menu...");
            }
        });
    }
}