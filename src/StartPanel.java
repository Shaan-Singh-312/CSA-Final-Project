import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(AppFrame f){
        super();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        JComboBox<String> list = new JComboBox<>(FileManger.FindFiles());

    }
}
