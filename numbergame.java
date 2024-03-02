import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.Random;

public class numbergame extends JFrame implements ActionListener {
    private JButton checkbutton;
    private JTextField userinput;
    private JLabel image,title,heading,score,roundleft,attemptsleft;

    private int randomNumber;
    private int scored;
    private int rounds;
    private int attempts;
    private final int maxAttempts = 3;
    private final int maxRounds = 3;

    public numbergame() {
        
        // Generating random number
        generateRandomNumber();
        
        // setting the components
        getContentPane().setBackground(new Color(255,255,226));
        setLayout(null);

        ImageIcon icon = new ImageIcon("logo.png");

        title = new JLabel("GUESS MASTER");
        title.setBounds(90,25,500,45);
        title.setFont(new Font("Snap ITC",Font.BOLD,40));
        title.setForeground(Color.BLACK);
        add(title);

        ImageIcon img = new ImageIcon("guess1.jpg");
        Border border = BorderFactory.createLineBorder(Color.BLACK,3);
        image = new JLabel(img);
        image.setBounds(185,100,200,200);
        image.setBorder(border);
        add(image);

        heading = new JLabel("Guess a number between 1 to 100");
        heading.setBounds(115,330,500,25);
        heading.setFont(new Font("Times New Roman",Font.BOLD,25));
        heading.setForeground(Color.BLACK);
        add(heading);

        userinput = new JTextField();
        Border border1 = BorderFactory.createLineBorder(Color.BLACK,1);
        userinput.setBounds(187,380,200,25);
        userinput.setFont(new Font("Times New Roman",Font.BOLD,20));
        userinput.setBorder(border1);
        add(userinput);

        checkbutton = new JButton("CHECK");
        checkbutton.setBounds(237,430,100,25);
        checkbutton.setForeground(Color.BLACK);
        checkbutton.setBorder(border1);
        checkbutton.addActionListener(this);
        add(checkbutton);

        score = new JLabel("SCORE : "+scored);
        score.setBounds(240,485,500,25);
        score.setFont(new Font("Times New Roman",Font.BOLD,20));
        score.setForeground(Color.BLACK);
        add(score);

        roundleft = new JLabel("ROUNDS  : " + rounds + " of " + maxRounds);
        roundleft.setBounds(220,530,500,25);
        roundleft.setFont(new Font("Times New Roman",Font.BOLD,20));
        roundleft.setForeground(Color.BLACK);
        add(roundleft);

        attemptsleft = new JLabel("ATTEMPTS : " + attempts + " of " + maxAttempts);
        attemptsleft.setBounds(205,575,500,25);
        attemptsleft.setFont(new Font("Times New Roman",Font.BOLD,20));
        attemptsleft.setForeground(Color.BLACK);
        add(attemptsleft);


        setSize(600,700);
        setLocation(490,80);
        setTitle("NUMBER GUESSING GAME");
        setIconImage(icon.getImage());
        setVisible(true);

        scored = 0;
        rounds = 1;
        attempts=0;

    }

    private void generateRandomNumber() {
        randomNumber = new Random().nextInt(100) + 1;
    }

    private void updateUI(boolean newRound) {
        if(newRound) {
            heading.setText("Guess a number between 1 to 100");
            userinput.setText("");
            userinput.setEnabled(true);
            checkbutton.setEnabled(true);
        }
        score.setText("SCORE : " + scored);
        roundleft.setText("ROUNDS : " + rounds + " of " + maxRounds);
        attemptsleft.setText("ATTEMPTS : " + attempts + " of " + maxAttempts);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == checkbutton) {
            try {
                // if checkbutton is clicked
                int guess = Integer.parseInt(userinput.getText());
                
                if(attempts < maxAttempts){
                    if(guess == randomNumber) {          // guess is correct
                        scored++;
                        attempts=0;
                        rounds++;
                        ImageIcon img = new ImageIcon("win.jpg");
                        JOptionPane.showMessageDialog(null,"Correct !! The number was " + randomNumber +"  ", "RESULT", JOptionPane.INFORMATION_MESSAGE,img);
                        checkEndGame();
                        updateUI(true);
                    }
                    else if(guess < randomNumber){       // guess is lower
                        ++attempts;
                        JOptionPane.showMessageDialog(this, "Try again !! Your number is lower ", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                        checkEndGame();
                    }
                    else if(guess > randomNumber){       // guess is higher
                        ++attempts;
                        JOptionPane.showMessageDialog(this, "Try again !! Your number is higher ", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                        checkEndGame();
                    } 
                }
                else {                                   // attempts exceeded
                    attempts=0;
                    rounds++;
                    JOptionPane.showMessageDialog(this, "Attempts exceeded !!  The number was " + randomNumber+"  ", "MESSAGE", JOptionPane.INFORMATION_MESSAGE); 
                    checkEndGame();
                    updateUI(true);
                }
            } 
            catch(NumberFormatException nfe) {            // invalid input
                JOptionPane.showMessageDialog(this, "Please enter a valid number !! ", "MESSAGE", JOptionPane.WARNING_MESSAGE);
            }
        }
        updateUI(false);
    }

    private void checkEndGame() {
        if(rounds > maxRounds) {      // rounds exceeded
            JOptionPane.showMessageDialog(null, "Game Over !! Your final score is : " + scored+"  ", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            // Reset for a new game 
            rounds = 0; 
            scored = 0;
            attempts=0;
            updateUI(true);
        }
    }

    public static void main(String[] args){

        new numbergame().setVisible(true);
    }

}