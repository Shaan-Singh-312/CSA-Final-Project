import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/// Panel for UI for writing to files
public class EditorPanel extends JPanel implements ActionListener {
    /// Name of file being written to
    private final String name;
    /// Refence to the main frame for changing current frame
    private final AppFrame frame;
    /// Location for user to enter term
    private final JTextField termEntry;
    /// Location for user to enter definition
    private final JTextPane defEntry;
    /// Button for user to click after term and definition are both written to
    private final JButton submit;
    /// Button to stop adding new information
    private final JButton complete;
    /// VocabFile to store user input
    private VocabFile data;
    /// Timer for creating delay/show user feedback
    private final Timer timer;

    /// Creates a new EditorPanel object that writes to file with name n
    /// @param f A refence to the frame object that UpdatePanel is currently added to.
    /// @param n Name of file being written to
    /// @param isBeingAddedTo Is true if the Editor is adding information to a preexisting file
    public EditorPanel(AppFrame f, String n, boolean isBeingAddedTo) {
        super();
        name =n;
        frame = f;
        try {
            if(isBeingAddedTo) data = FileManger.load(new File("DataFiles/" + name + ".txt"));
            else data = new VocabFile();
        } catch (FileNotFoundException e) {
            data = new VocabFile();
        }

        timer = new Timer(600, this);

        JLabel textTerm = new JLabel("Term:        ");
        textTerm.setFont(f.APP_FONT);
        add(textTerm);

        termEntry = new JTextField();
        termEntry.setBorder(null);
        termEntry.setFont(f.APP_FONT);
        termEntry.setPreferredSize(new Dimension(getFontMetrics(termEntry.getFont()).charWidth('m') *20, 27 * 2));

        add(termEntry);

        JLabel textDef = new JLabel("Definition: ");
        textDef.setFont(f.APP_FONT);
        add(textDef);

        defEntry = new JTextPane();
        defEntry.setFont(f.APP_FONT);
        defEntry.setPreferredSize(new Dimension(getFontMetrics(termEntry.getFont()).charWidth('m') *20,27 * 5));

        add(defEntry);

        submit = new JButton("<html>ADD</html>");
        submit.setFont(f.APP_FONT);
        submit.setPreferredSize(new Dimension(getFontMetrics(submit.getFont()).charWidth('m') *25, 27));
        submit.addActionListener(this);
        add(submit);

        complete = new JButton("Finalize and return to start");
        complete.setFont(f.APP_FONT);
        complete.setPreferredSize(new Dimension(getFontMetrics(complete.getFont()).charWidth('m') *17, 27));
        complete.addActionListener(this);
        add(complete);
    }


    /// Used to validate user input, changes text shown on button and starts timer
    private void saveInput() {
        if (termEntry.getText().isEmpty() && defEntry.getText().isEmpty()) {
            submit.setText("<html>Invalid Please enter a term and def</html>");
        } else if (termEntry.getText().isEmpty()) {
            submit.setText("<html>Invalid Please enter a term</html>");
        } else if (defEntry.getText().isEmpty()) {
            submit.setText("<html>Invalid Please enter a def</html>");
        }else {
            if(data.containsOneOf(new String[]{termEntry.getText(), defEntry.getText()})) {
                submit.setText("<html>Invalid Please enter a new value</html>");
            }
            else{
                data.add(termEntry.getText(), defEntry.getText());
                submit.setText("<html>ADDED</html>");
            }
        }
        termEntry.setText("");
        defEntry.setText("");
        timer.start();
    }
    /// Detects button clicks and timer pulses to save data and return to homepage
    /// Can destroy current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
           saveInput();
        }
        if(e.getSource() == timer){
            timer.stop();
            submit.setText("<html>ADD<html>");
        }
        if(e.getSource() == complete){
            saveInput();
            try{
                FileManger.save(data, new File("DataFiles/" + name+ ".txt"));
                frame.setStart();
            } catch (RuntimeException ex) {
                complete.setText("<html>Error Occurred, Click to try again</html>");
            }
        }
    }
}
