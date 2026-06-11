import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Basic Panel class that manges start screen
 * Has all the elements of the start screen and logic to change to next screen
 */

public class StartPanel extends JPanel implements ActionListener {
    /// Dropdown list of file names in the system without file extension
    private final JComboBox<String> list;
    /// Button for user to confirm choice
    private final JButton button;
    /// Refence to the main frame for changing current frame
    private final AppFrame frame;

    /// Creates a new StartPanel object
    /// @param f A refence to the frame object that StartPanel is currently added to.
    public StartPanel(AppFrame f){
        super();
        frame = f;
        String[] text = FileManger.findFiles();
        for (int i = 0; i < text.length; i++) {
            text[i] = "<html>" + text[i] + "</html>";
        }

        list = new JComboBox<>(text);
        list.setFont(f.APP_FONT);
        list.setPreferredSize(new Dimension(getFontMetrics(list.getFont()).charWidth('m') *27, 30));
        add(list);

        button = new JButton("Next");
        button.setFont(f.APP_FONT);
        button.addActionListener(this);
        add(button);
    }

    /// Detects button press and checks user input
    /// Can destroy current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button) && list.getSelectedItem() != null){
            if ((list.getSelectedItem().toString()).equals("<html>Add New ++</html>")){
                frame.setEditor();
            } else if (list.getSelectedItem().toString().equals("<html>Update Old</html>")) {
                frame.setRevise();

            } else {
                String s = list.getSelectedItem().toString();
                s = s.substring(6, s.length() - 7);
                frame.setQuiz(s);
            }
        }
    }
}
