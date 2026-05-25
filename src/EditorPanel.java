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

        timer = new Timer(300, this);

        Label textTerm = new Label("Term:        ");
        textTerm.setFont(new Font("Arial", Font.BOLD, 20));
        add(textTerm);

        termEntry = new JTextField();
        termEntry.setBorder(null);
        termEntry.setFont(new Font("Arial", Font.BOLD, 20));
        termEntry.setPreferredSize(new Dimension(getFontMetrics(termEntry.getFont()).charWidth('m') *20, 27 * 2));

        add(termEntry);

        Label textDef = new Label("Definition: ");
        textDef.setFont(new Font("Arial", Font.BOLD, 20));
        add(textDef);

        defEntry = new JTextPane();
        defEntry.setFont(new Font("Arial", Font.BOLD, 20));
        defEntry.setPreferredSize(new Dimension(getFontMetrics(termEntry.getFont()).charWidth('m') *20,27 * 5));

        add(defEntry);

        submit = new JButton("ADD");
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setLayout(new GridLayout(1,2));
        submit.addActionListener(this);
        add(submit);

        complete = new JButton("Finalize and return to start");
        complete.setFont(new Font("Arial", Font.BOLD, 20));
        complete.setLayout(new GridLayout(1,2));
        complete.addActionListener(this);
        add(complete);
    }


    /// Detects button clicks and timer pulses to save data and return to homepage
    /// Can destroy current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit){
            data.add(termEntry.getText(), defEntry.getText());
            termEntry.setText("");
            defEntry.setText("");
            submit.setText("ADDED");
            timer.start();
        }
        if(e.getSource() == timer){
            timer.stop();
            submit.setText("ADD");
        }
        if(e.getSource() == complete){
            try{
                FileManger.save(data, new File("DataFiles/" + name+ ".txt"));
                frame.setStart();
            } catch (RuntimeException ex) {
                complete.setText("Error Occurred, Click to try again");
            }
        }
    }
}
