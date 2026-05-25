import javax.swing.*;
import java.io.File;
/// Creates and manages a new JFrame to display the application

public class AppFrame extends JFrame {
    /// Holds the current StartPanel object on the screen, if there is not one, is null
    private StartPanel start;
    /// Holds the current UpdatePanel object on the screen, if there is not one, is null
    private UpdatePanel updatePanel;
    /// Holds the current QuizPanel object on the screen, if there is not one, is null
    private QuizPanel quizPanel;
    private ChoseRevisePanel revisePanel;

    /// Creates a new AppFrame object with a title of "Flashcard app"
    /// Adds a Start Panel object to the frame
    public AppFrame() {
        setTitle("Flashcard App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        start = new StartPanel(this);
        updatePanel = null;
        quizPanel = null;
        revisePanel = null;
        add(start);
        setContentPane(start);
        setVisible(true);
    }

    /// Removes all other Panel objects and creates a new StartPanel object
    /// Places the StartPanel object on the screen
    public void setStart(){
        if(updatePanel != null) {
            remove(updatePanel);
            updatePanel = null;
        }
        if(quizPanel != null) {
            remove(quizPanel);
            quizPanel = null;
        }
        if(revisePanel != null) {
            remove(revisePanel);
            revisePanel = null;
        }
        start = new StartPanel(this);
        add(start);
        setContentPane(start);
        revalidate();
        repaint();
    }

    /// Removes all other Panel objects and creates an UpdatePanel object
    /// Places the UpdatePanel object on the screen
    public void setEditor(){
        if (start != null){
            remove(start);
            start = null;
        }
        if (quizPanel != null){
            remove(quizPanel);
            quizPanel = null;
        }
        if(revisePanel != null) {
            remove(revisePanel);
            revisePanel = null;
        }
        updatePanel = new UpdatePanel(this);
        add(updatePanel);
        setContentPane(updatePanel);
        revalidate();
        repaint();
    }
    /// Removes all other Panel objects and creates a new QuizPanel object
    /// Places the QuizPanel object on the screen
    /// @param filename Name of file containing quiz data without file extension and directory name (DataFiles/Hello.txt is simply Hello)
    public  void setQuiz(String filename){
        if (start != null){
            remove(start);
            start = null;
        }
        if(updatePanel != null) {
            remove(updatePanel);
            updatePanel = null;
        }
        if(revisePanel != null) {
            remove(revisePanel);
            revisePanel = null;
        }
        quizPanel = new QuizPanel(this, new File("DataFiles/" + filename + ".txt"));
        add(quizPanel);
        setContentPane(quizPanel);
        revalidate();
        repaint();
    }

    /// Removes all other Panel objects and creates a new ChoseRevisePanel object
    /// Places the ChoseRevisePanel object on the screen
    public void setRevise(){
        if (start != null){
            remove(start);
            start = null;
        }
        if(updatePanel != null) {
            remove(updatePanel);
            updatePanel = null;
        }
        if(quizPanel != null) {
            remove(quizPanel);
            quizPanel = null;
        }
        revisePanel = new ChoseRevisePanel(this);
        add(revisePanel);
        setContentPane(revisePanel);
        revalidate();
        repaint();
    }
}
