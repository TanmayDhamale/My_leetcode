// Interface definition
interface Switchable {
    void turnOn();
    void turnOff();
}

// Class implementing the interface
class Bulb implements Switchable {
    private boolean isOn = false;

    @Override
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("💡 Bulb is now ON.");
        } else {
            System.out.println("💡 Bulb is already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("💡 Bulb is now OFF.");
        } else {
            System.out.println("💡 Bulb is already OFF.");
        }
    }
}

// Main class
public class BulbSwitch {
    public static void main(String[] args) {
        Bulb myBulb = new Bulb();

        myBulb.turnOn();
        myBulb.turnOff();
        myBulb.turnOff(); // Trying to turn OFF again
        myBulb.turnOn();  // Turning ON again
    }
}