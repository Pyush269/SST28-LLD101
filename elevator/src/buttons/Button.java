package buttons;

public abstract class Button {

    private boolean isPressed;

    public Button() {
        this.isPressed = false;
    }

    public void press() {
        this.isPressed = true;
        onPress();
    }

    public void reset() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    protected abstract void onPress();
}
