import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePanel extends JPanel implements ActionListener {
    private final JTextField nameOfFileInput;
    private String name;
    private final AppFrame frame;

    public UpdatePanel(AppFrame f){
        super();
        name = null;
        frame = f;
        Label text = new Label("Enter the name of new data set:");
        text.setFont(new Font("Arial", Font.BOLD, 20));
        add(text);
        setLayout(new GridLayout(3,1));
        nameOfFileInput = new JTextField();
        nameOfFileInput.setFont(new Font("Arial", Font.BOLD, 20));
        nameOfFileInput.addActionListener(this);
        add(nameOfFileInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        name = nameOfFileInput.getText();
        EditorPanel editorPanel = new EditorPanel(frame, name);
        frame.add(editorPanel);
        frame.setContentPane(editorPanel);
        frame.revalidate();
        frame.repaint();
    }
}
