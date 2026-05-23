import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    private final JComboBox<String> list;
    private final JButton button;
    private final AppFrame frame;


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
