import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/// Panel to control update actions (adding or removing data from files)
public class ChoseRevisePanel extends JPanel implements ActionListener {
    /// Refence to the main frame for changing current frame
    private final AppFrame frame;
    /// Dropdown list of file names in the system without file extension
    private final JComboBox<String> dropdown;
    /// Button for user to confirm choice of removing items from list
    private final JButton removeItems;
    /// Button for user to confirm choice of adding items to list
    private final JButton addItems;

    /// Creates a new ChoseRevisePanel object
    /// @param f A refence to the frame object that ChoseRevisePanel is currently added to.
    public ChoseRevisePanel(AppFrame f){
        frame = f;
        String[] temp = FileManger.findFiles();
        String[] fileList = new String[temp.length - 2];
        for (int i = 0; i < fileList.length; i++) {
            fileList[i] = temp[i];
        }

        dropdown = new JComboBox<>(fileList);
        addItems = new JButton("Add Items to list");
        removeItems = new JButton("Remove Items from list");
        removeItems.addActionListener(this);
        addItems.addActionListener(this);
        add(dropdown);
        add(addItems);
        add(removeItems);
    }

    /// Detects button press and checks user input
    /// Can destroy current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeItems && dropdown.getSelectedItem() != null){
            RevisePanel revisePanel = new RevisePanel(frame, dropdown.getSelectedItem().toString());
            frame.add(revisePanel);
            frame.setContentPane(revisePanel);
            frame.revalidate();
            frame.repaint();
        }
        if(e.getSource() == addItems && dropdown.getSelectedItem() != null){
            EditorPanel addPanel = new EditorPanel(frame, dropdown.getSelectedItem().toString(), true);
            frame.add(addPanel);
            frame.setContentPane(addPanel);
            frame.revalidate();
            frame.repaint();
        }
    }
}
