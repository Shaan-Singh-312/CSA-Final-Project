import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel {
    private final String name;
    private final AppFrame frame;
    private final JTextField termEntry;
    private final JTextArea defEntry;
    private final Label textTerm;
    private final Label textDef;
    private final JButton submit;
    private final VocabFile data;

    public EditorPanel(AppFrame f, String n){
        super();
        name =n;
        frame = f;
        setLayout(new GridLayout(3,2));
        textTerm = new Label("Term: ");
        textTerm.setFont(new Font("Arial", Font.BOLD, 20));
        add(textTerm);
        termEntry = new JTextField();
        termEntry.setFont(new Font("Arial", Font.BOLD, 20));
        add(termEntry);
        textDef = new Label("Definition: " );
        textDef.setFont(new Font("Arial", Font.BOLD, 20));
        add(textDef);
        defEntry = new JTextArea();
        defEntry.setFont(new Font("Arial", Font.BOLD, 20));
        add(defEntry);
        submit = new JButton("Continue");
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        submit.setLayout(new GridLayout(1,2));
        add(submit);
        data = new VocabFile();
    }

}
