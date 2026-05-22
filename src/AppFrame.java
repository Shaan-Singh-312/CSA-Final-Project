import javax.swing.*;
import java.io.File;

public class AppFrame extends JFrame {
    private final StartPanel start;
    private UpdatePanel updatePanel;
    private QuizPanel quizPanel;

    public AppFrame() {
        setTitle("Flashcard App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        start = new StartPanel(this);
        updatePanel = null;
        quizPanel = null;
        add(start);
        setContentPane(start);
        setVisible(true);
    }

    public void setEditor(){
        remove(start);
        updatePanel = new UpdatePanel(this);
        add(updatePanel);
        setContentPane(updatePanel);
        revalidate();
        repaint();
    }

    public  void setQuiz(String filename){
        //remove(start);
        if (updatePanel != null) remove(updatePanel);
        quizPanel = new QuizPanel(this, new File("DataFiles/" + filename + ".txt"));
        add(quizPanel);
        setContentPane(quizPanel);
        revalidate();
        repaint();
    }
}
