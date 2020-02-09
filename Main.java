/**
 * Main
 */
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{

    public Main() {
        setSize(500, 600);
        setTitle("My Calculator");
        getContentPane().add(new Calculator());
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
    }
}