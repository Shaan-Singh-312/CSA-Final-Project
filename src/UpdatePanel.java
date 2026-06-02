import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/// Basic Panel to validate new file creation
public class UpdatePanel extends JPanel implements ActionListener {
    /// Location for use to write new file name
    private final JTextField nameOfFileInput;
    /// Stores the string the user enters within nameOfFileInput
    private String name;
    /// Displays instructions for user
    private final JLabel text;
    /// Refence to the main frame for changing current frame
    private final AppFrame frame;

    /// Creates a new UpdatePanel object
    /// @param f A refence to the frame object that UpdatePanel is currently added to.
    public UpdatePanel(AppFrame f){
        super();
        name = null;
        frame = f;

        text = new JLabel("<html>Enter a new file name: <html>");
        text.setFont(f.APP_FONT);
        text.setPreferredSize(new Dimension(getFontMetrics(text.getFont()).charWidth('m') *25, 27 * 2));
        add(text);

        nameOfFileInput = new JTextField(20);
        nameOfFileInput.setFont(f.APP_FONT);
        nameOfFileInput.addActionListener(this);
        add(nameOfFileInput);
    }

    /// Detects 'enter key' press and validates if the given file name is valid (not already existing file)
    /// Can destroy current object
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
            EditorPanel editorPanel = new EditorPanel(frame, name, false);
            frame.add(editorPanel);
            frame.setContentPane(editorPanel);
            frame.revalidate();
            frame.repaint();
        }else{
            text.setText("<html>That name already exists please enter a new name: </html>");
            nameOfFileInput.setText("");
            revalidate();
            repaint();
        }

    }
}
