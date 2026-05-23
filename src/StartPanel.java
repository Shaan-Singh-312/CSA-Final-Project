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

        list = new JComboBox<>(FileManger.findFiles());
        list.setFont(new Font("Arial", Font.BOLD, 20));
        add(list);

        button = new JButton("Next");
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(this);
        add(button);
    }

    /// Detects button press and checks user input
    /// Can destroy current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button) && list.getSelectedItem() != null){
            if ((list.getSelectedItem().toString()).equals("Add New ++")){
                frame.setEditor();
            }
            else {
                frame.setQuiz(list.getSelectedItem().toString());
            }
        }
    }
}
