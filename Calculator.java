/**
 * Calculator
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

import javax.script.*;

public class Calculator extends JPanel{

    private Screen screen;
    private StringBuilder stringCalcul = new StringBuilder();

    public Calculator() {
        screen = new Screen();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 5, 5, 5)); //top, left, bottom, right
        add(screen, BorderLayout.NORTH);
        add(new Clavier());
    }

    class Clavier extends JPanel implements ActionListener{
        //private static final int NUMBEROFBUTTONS = 16;
        public int[] gridX = {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3};
        public int[] gridY = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        boolean newCalcul = true;

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
                buttons[i].addActionListener(this);
                add(buttons[i], constraints);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonValue = e.getActionCommand();
            String result = null;
            // If it is a new calcul blank the screen
            if (newCalcul)
                screen.setText("");
            if (buttonValue.equals("=")) {
                if (stringCalcul.toString().length() > 0) {
                    try {
                        result = (engine.eval(stringCalcul.toString())).toString();
                        screen.setText(result);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    finally {
                        stringCalcul.delete(0, stringCalcul.toString().length());
                        newCalcul = true;
                    }
                }
            }
            else {
                stringCalcul.append(e.getActionCommand());
                screen.setText(stringCalcul.toString());
                newCalcul = false;
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