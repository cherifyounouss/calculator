/**
 * Screen
 */
import java.awt.*;
import javax.swing.*;

public class Screen extends JTextField{

    public Screen() {
        setPreferredSize(new Dimension(300, 75));
        setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 30));
        setForeground(new Color(187, 183, 189));
        setBackground(Color.WHITE);
        setEditable(false);
        setHorizontalAlignment(JTextField.RIGHT);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //f.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

    }

    @Override
    public Insets getInsets() {
        return new Insets(20, 10, 0, 10);
    }
}