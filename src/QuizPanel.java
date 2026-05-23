import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/// Creates UI for the user to take the quiz and gives the user a score paced on their answers
public class QuizPanel extends JPanel implements ActionListener {
    /// Stores current user score
    private int score;
    /// Stores number of question the user is on minus one
    private int questionNum;
    /// Refence to the main frame for changing current frame
    private final AppFrame frame;
    /// Location for user to input their answer
    private final JTextArea input;
    /// Gives question to the user
    private final Label textTerm;
    /// Stores question data, including question and answer
    private VocabFile data;
    /// Button for user to submit their answer to be scored
    private final JButton submitButton;
    /// Button for the user to return to the start frame
    private final JButton homeButton;
    /// Timer for creating delay/show user feedback
    private final Timer timer;

    /// Creates a new QuizPanel object that gives a quiz based on data in given file. Then gives user the quiz
    /// @param f A refence to the frame object that QuizPanel is currently added to.
    /// @param file File from witch quiz is to be generated
    public QuizPanel(AppFrame f, File file){
        super();
        frame = f;
        timer = new Timer(300, this);

        try {
            data = FileManger.load(file);
        } catch (FileNotFoundException e) {
            data = new VocabFile();
        }
        setLayout(new GridLayout(3,1));

        score = 0;
        textTerm = new Label("Quiz");
        textTerm.setFont(new Font("Arial",Font.BOLD, 20));
        add(textTerm);

        input = new JTextArea();
        input.setFont(new Font("Arial", Font.PLAIN, 20));
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        add(input);

        submitButton = new JButton("Submit Answer");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.addActionListener(this);
        add(submitButton);

        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 20));
        homeButton.addActionListener(this);

        StartQuiz();
    }

    /// Prepares data for quiz and starts the quiz
    private void StartQuiz(){
        data.randomize();
        questionNum = 0;
        showQuestion();
    }

    /// Shows the question at questionNum if it exists otherwise displays end screen.
    private void showQuestion(){
        if(questionNum < data.size()) {
            textTerm.setText(data.getTerm(questionNum));
        }
        else {
            textTerm.setText("You have Completed the quiz. Final score: " + score + "/" + data.size());
            remove(submitButton);
            remove(input);
            add(homeButton);
        }
        revalidate();
        repaint();
    }

    /// Compares user answer with correct answer and updates score accordingly
    /// Gives user feedback on their answer
    private void checkAnswer(){
        String answer = input.getText().toUpperCase();
        if (answer.equals(data.getDef(questionNum).toUpperCase())){
            textTerm.setText("Correct");
            score++;
        }
        else textTerm.setText("Sorry the answer was: " + data.getDef(questionNum));
        input.setText("");
        repaint();
    }

    /// Checks if button was clicked or timer pulsed and acts accordingly
    /// If submitButton is clicked, checks answer
    /// If home button is clicked, returns to start destroying current object
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            checkAnswer();
            timer.start();
        }
        if (e.getSource() == timer){
            timer.stop();
            questionNum++;
            showQuestion();
        }
        if(e.getSource() == homeButton){
            frame.setStart();
        }
    }
}
