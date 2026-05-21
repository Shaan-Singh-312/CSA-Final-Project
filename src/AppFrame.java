import javax.swing.*;

public class AppFrame extends JFrame {
    private StartPanel start;
    private UpdatePanel updatePanel;
    private QuizPanel quizPanel;

    public AppFrame() {
        setTitle("Flashcard App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        start = new StartPanel(this);
        updatePanel = null;
        quizPanel =null;

        setContentPane(start);

        setVisible(true);
    }

    public void setEditor(){
        //TODO: Implement This method

    }

    public void setQuiz(){
        //TODO: Implement This method

    }
}
