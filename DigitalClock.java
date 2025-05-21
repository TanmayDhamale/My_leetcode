import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DigitalClock extends JFrame implements Runnable {
    JLabel timeLabel;

    public DigitalClock() {
        setTitle("Digital Clock");
        setSize(300, 100);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(timeLabel);

        setVisible(true);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeLabel.setText(currentTime.format(formatter));
            try {
                Thread.sleep(1000); // Update every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to run the clock
    public static void main(String[] args) {
        new DigitalClock();
    }
}