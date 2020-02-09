/**
 * Calculator
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

public class Calculator extends JPanel{

    Screen screen;

    public Calculator() {
        screen = new Screen();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 5, 5, 5)); //top, left, bottom, right
        add(screen, BorderLayout.NORTH);
        add(new Clavier());
    }

    class Clavier extends JPanel{
        //private static final int NUMBEROFBUTTONS = 16;
        public int[] gridX = {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3};
        public int[] gridY = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        //public static int[] width = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        //public static int[] height = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        private String[] buttonValues = "7 8 9 / 4 5 6 * 1 2 3 - . 0 = +".split(" ");
        private JButton[] buttons = new JButton[buttonValues.length];
        public Clavier() {
            setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.ipadx = 20;
            constraints.ipady = 20;
            for (int i = 0; i < buttons.length; i++) {
                constraints.gridx = gridX[i];
                constraints.gridy = gridY[i];
                constraints.gridwidth = 1;
                constraints.gridheight = 1;
                buttons[i] = new MyButton(Color.WHITE, new Color(187, 183, 189), buttonValues[i]);
                buttons[i].setMargin(new Insets(15, 15, 15, 15));
                add(buttons[i], constraints);
            }
        }
    }

    class MyButton extends JButton {
        
        public MyButton(Color foreground, Color background, String value) {
            super(value);
            setForeground(foreground);
            setBackground(background);
            setPreferredSize(new Dimension(80, 60));
            setFont(getMyFont());
        }

        public Font getMyFont() {
            Font font = null;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("new_font.TTF")).deriveFont(50f);
                //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //ge.registerFont(font);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return font;
        }
    }
}