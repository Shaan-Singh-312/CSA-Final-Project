import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class QuizPanel extends JPanel implements ActionListener {
    private int score;
    private int questionNum;

    private final AppFrame frame;
    private final JTextArea input;
    private final Label textTerm;
    private  VocabFile data;
    private final JButton submitButton;


    public QuizPanel(AppFrame f, File file){
        super();
        frame = f;
        setLayout(new GridLayout(3,1));
        try {
            data = FileManger.load(file);
        } catch (FileNotFoundException e) {
            data = new VocabFile();
        }

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
        StartQuiz();
    }

    public void StartQuiz(){
        data.randomize();
        questionNum = 0;
        showQuestion();
    }

    public void showQuestion(){
        if(questionNum < data.size()) textTerm.setText(data.getTerm(questionNum));
        else textTerm.setText("You have Completed the quiz. Final score: " + score + "/" + data.size());
        repaint();
    }

    public void checkAnswer(){
        String answer = input.getText();
        if (answer.equals(data.getDef(questionNum))){
            textTerm.setText("Correct");
            score++;
        }
        else textTerm.setText("Sorry the answer was: " + data.getDef(questionNum));
        input.setText("");
        repaint();
        questionNum++;
        showQuestion();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            checkAnswer();
        }
    }
}
