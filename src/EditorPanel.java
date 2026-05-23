import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditorPanel extends JPanel implements ActionListener {
    private final String name;
    private final AppFrame frame;
    private final JTextField termEntry;
    private final JTextPane defEntry;
    private final JButton submit;
    private final JButton complete;
    private final VocabFile data;
    private final Timer timer;

    public EditorPanel(AppFrame f, String n) {
        super();
        name =n;
        frame = f;
        data = new VocabFile();
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
