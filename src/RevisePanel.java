import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/// Controls Removing items from files
public class RevisePanel extends JPanel implements ActionListener {
    /// Refence to the main frame for changing current frame
    private final AppFrame frame;
    /// Dropdown list of terms and definitions in a file
    private JComboBox<String> list;
    /// Vocab file containing data from file
    private VocabFile data;
    /// Button for detecting user wishes to remove selected item
    private final JButton removeButton;
    /// Button for detecting user wishes to confirm choices
    private final JButton finishButton;
    /// Name of file
    private final String name;

    /// Creates a new RevisePanel object
    /// @param f A refence to the frame object that RevisePanel is currently added to.
    /// @param str Name of file being revised
    public RevisePanel(AppFrame f, String str){
        frame = f;
        name = str;
        try{
            data = FileManger.load(new File("DataFiles/" + name + ".txt"));
        } catch (FileNotFoundException e) {
            data = new VocabFile();
        }
        list = new JComboBox<>(data.toStringArray());
        add(list);
        removeButton = new JButton("Remove");
        finishButton = new JButton("Confirm changes");
        removeButton.addActionListener(this);
        finishButton.addActionListener(this);
        add(removeButton);
        add(finishButton);
    }


    /// Detects button presses and acts accordingly\
    /// Can destroy current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton && list.getSelectedItem() != null){
            data.remove(list.getSelectedItem().toString());
            remove(list);
            remove(removeButton);
            remove(finishButton);
            list = new JComboBox<>(data.toStringArray());
            add(list);
            add(removeButton);
            add(finishButton);
            if(data.size() == 0) remove(removeButton);
            revalidate();
            repaint();
        }
        if (e.getSource() == finishButton){
            FileManger.save(data, new File("DataFiles/" + name + ".txt"));
            frame.setStart();
        }
    }
}
