import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePanel extends JPanel implements ActionListener {
    private final JTextField nameOfFileInput;
    private String name;
    private final Label text;
    private final AppFrame frame;

    public UpdatePanel(AppFrame f){
        super();
        name = null;
        frame = f;



        text = new Label("Enter the name of new data set:");
        text.setFont(new Font("Arial", Font.BOLD, 20));
        add(text);

        nameOfFileInput = new JTextField(20);
        nameOfFileInput.setFont(new Font("Arial", Font.BOLD, 20));
        nameOfFileInput.addActionListener(this);
        add(nameOfFileInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        name = nameOfFileInput.getText();
        boolean unique = true;
        String[] existingFiles = FileManger.findFiles();
        for (String existingFile : existingFiles) {
            if (name.equals(existingFile)) {
                unique = false;
                break;
            }
        }
        if(unique){
            EditorPanel editorPanel = new EditorPanel(frame, name);
            frame.add(editorPanel);
            frame.setContentPane(editorPanel);
            frame.revalidate();
            frame.repaint();
        }else{
            text.setText("That name already exists, please enter a new name:");
            nameOfFileInput.setText("");
        }

    }
}
