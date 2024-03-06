package Puzel;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Puzel extends JFrame implements ActionListener, WindowListener {
    private JLabel imageLabel;
    private Label l;
  private Connection connection;
    Puzel() {
        super("Puzzle Game");
         
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Personal\\Downloads\\WhatsApp Image 2023-10-11 at 21.04.44_64d78f9d.jpg");
        Image image = imageIcon.getImage().getScaledInstance(1600, 1600, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        imageLabel = new JLabel(imageIcon);
        l = new Label("Mind Twister\u2665");
        l.setFont(new Font("Arial", Font.BOLD, 56));
        Button playButton = new Button("Play");
        playButton.setForeground(Color.RED);
        playButton.setBounds(322, 199, 80, 90);
        playButton.setBackground(Color.BLACK);
        playButton.setFont(new Font("Arial", Font.BOLD, 26));
        playButton.addActionListener(this);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(imageLabel, BorderLayout.CENTER);
        contentPanel.add(l, BorderLayout.NORTH);
        contentPanel.add(playButton, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        addWindowListener(this);
        setSize(1600, 1600);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
        private void saveScore(int score) {
        String url = "jdbc:mysql://localhost:3306/puzzle_game";
        String username = "your_username"; 
        String password = "your_password"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String insertQuery = "INSERT INTO scores (score) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, score);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error saving score: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void retrieveScores() {
        String url = "jdbc:mysql://localhost:3306/puzzle_game";
        String username = "your_username"; 
        String password = "your_password"; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String selectQuery = "SELECT score FROM scores";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int score = resultSet.getInt("score");
                System.out.println("Score: " + score);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error retrieving scores: " + e.getMessage());
            e.printStackTrace();
        }
    }

     
    public static void main(String[] args) {
        new Puzel();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(this, "Exit?", "Are you sure", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PuzelFrame puzelFrame = new PuzelFrame();
        puzelFrame.setSize(1600, 1600);
        puzelFrame.setBackground(Color.BLACK);
        puzelFrame.setVisible(true);
         new SoundClipTest();
         int score = 100;
         saveScore(score);
        retrieveScores();
    }
}

class PuzelFrame extends Frame implements ActionListener, WindowListener {
   
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9,b10,b11,b12,tutorial,pause,resume;
    Label L,l1;
    Timer timer;
    int remainingTime = 60;
    Menu file, sub;
    MenuItem New,  close, closeall;
    CheckboxMenuItem auto;
    TextField tf,tf2;
   
  
        
    PuzelFrame() {
         super("Puzzle Game");
         New = new MenuItem("New");
        close = new MenuItem("Close");
        closeall = new MenuItem("Close All");

       auto = new CheckboxMenuItem("Auto Save");
       file = new Menu("File");
        sub = new Menu("Close");

        file.add(New);
        file.add(sub);
        file.add(auto);

        sub.add(close);
         sub.add(closeall);
        l1=new Label("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- KANISHKA PUZZEL GAME-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        l1.setBounds(00, 63,1500, 30);
        add(l1);
        L = new Label("Remaining Time: " + remainingTime + " s");
        L.setBounds(513, 103, 250, 40);
        add(L);
       tutorial = new Button("?");
       tutorial.setBounds(539, 639, 20, 20);
       
       add(tutorial);
       pause = new Button("||");
       pause.setBounds(639, 639, 20, 20);
      add(pause);
       
    resume = new Button("^"); 
    resume.setBounds(739, 639, 20, 20);
    add(resume);

        b1 = new Button("8");
        b1.setBounds(322, 199, 90, 90);
        b1.setBackground(Color.yellow);
      
        b2 = new Button("5");
        b2.setBounds(420, 199, 90, 90);
        b2.setBackground(Color.red);
       
        b3 = new Button("7");
        b3.setBounds(518, 199, 90, 90);
        b3.setBackground(Color.blue);
        
        b4 = new Button("4");
        b4.setBounds(324, 293, 90, 90);
        b4.setBackground(Color.green);
        
        b5 = new Button("1");
        b5.setBounds(422, 293, 90, 90);

        b6 = new Button("6");
        b6.setBounds(520, 293, 90, 90);
        b6.setBackground(Color.pink);
        
        b7 = new Button("3");
        b7.setBounds(325, 387, 90, 90);
        b7.setBackground(Color.magenta);
        
        b8 = new Button("");
        b8.setBackground(Color.orange);
        b8.setBounds(423, 387, 90, 90);
       
        b9 = new Button("2");
        b9.setBounds(522, 387, 90, 90);
        b9.setBackground(Color.cyan);
       
        b10 = new Button("11");
        b10.setBounds(327, 485, 90, 90);
        b10.setBackground(Color.blue);
        
        b11 = new Button("9");
        b11.setBackground(Color.RED);
        b11.setBounds(423, 485, 90, 90);
       
        b12 = new Button("10");
        b12.setBounds(522, 485, 90, 90);
        b12.setBackground(Color.DARK_GRAY);
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--; 
                L.setText(" Remaning Time : " + remainingTime + " sec"); 

                if (remainingTime == 0) {
                    timer.stop(); 
                    JOptionPane.showMessageDialog(
    PuzelFrame.this, "Time's up! Better Luck Next Time.");
                }
            }
        });

        timer.start();
     
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);
        b12.addActionListener(this);
        
        addWindowListener(this);
       
        MenuBar mb = new MenuBar();
        mb.add(file);
        setMenuBar(mb);
        tf = new TextField(20);
        
        add(tf);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(b10);
        add(b11);
        add(b12);
        setSize(400, 400);
        setLayout(null);
        setVisible(true);
        
        New.addActionListener((ActionEvent ae) ->
        {
            resetGame();
        });

        close.addActionListener((ActionEvent ae) -> 
       {
            System.exit(0);
        });

        
        closeall.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        tutorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTutorial();
            }
        });
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayPause();
                
            }

            private void displayPause() {
             timer.stop();
            }
        });
    resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayResume();
            }

            private void displayResume() {
             timer.start();
            }
        });
    }
    public void resetGame() {
        remainingTime = 60;
        timer.restart();
        L.setText("Remaining Time: " + remainingTime + " s");
        shufflePuzzlePieces();
        tf.setText("");
        

      
    }
    public void shufflePuzzlePieces() {
     b1.setLabel("4");
     b2.setLabel("9");
     b3.setLabel("8");
     b4.setLabel("11");
     b5.setLabel("2");
     b6.setLabel("7");
     b7.setLabel("5");
     b8.setLabel("3");
     b9.setLabel("");
     b10.setLabel("6");
     b11.setLabel("1");
     b12.setLabel("10");
    }
   
    public void actionPerformed(ActionEvent e) {
   
        if (e.getSource() == b1) {
            String label = b1.getLabel();
            if (b2.getLabel().equals("")) {
                b2.setLabel(label);
                b1.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b1.setLabel("");

            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b1.setLabel("");

            }
            if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b1.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b1.setLabel("");

            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b1.setLabel("");

            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b1.setLabel("");

            }
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b1.setLabel("");

            }
             if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b1.setLabel("");
            }
            if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b1.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b1.setLabel("");
            }
        }
        if (e.getSource() == b2) {
            String label = b2.getLabel();
            if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b2.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b2.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b2.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b2.setLabel("");
            }
            if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b2.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b2.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b2.setLabel("");
            }
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b2.setLabel("");
            }
            if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b2.setLabel("");
            }
            if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b2.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b2.setLabel("");
            }
        }
        if (e.getSource() == b3) {
            String label = b3.getLabel();
            if (b2.getLabel().equals("")) {
                b2.setLabel(label);
                b3.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b3.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b3.setLabel("");
            }
            if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b3.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b3.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b3.setLabel("");
            }
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b3.setLabel("");
            }
            if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b3.setLabel("");
            }
            if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b3.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b3.setLabel("");
            }
        }
        if (e.getSource() == b4) {
            String label = b4.getLabel();
            if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b4.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b4.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b4.setLabel("");
            }
            if (b2.getLabel().equals("")) {
                b2.setLabel(label);
                b4.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b4.setLabel("");
            }
            if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b4.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b4.setLabel("");
            }
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b4.setLabel("");
            }
              if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b4.setLabel("");
            }
            if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b4.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b4.setLabel("");
            }

        }
        if (e.getSource() == b5) {
            String label = b5.getLabel();
            if (b2.getLabel().equals("")) {
                b2.setLabel(label);
                b5.setLabel("");
            }
            if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b5.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b5.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b5.setLabel("");
            }
            if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b5.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b5.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b5.setLabel("");
            }
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b5.setLabel("");
            }
              if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b5.setLabel("");
            }
            if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b5.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b5.setLabel("");
            }
        }
        if (e.getSource() == b6) {
            String label = b6.getLabel();
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b6.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b6.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b6.setLabel("");
            }
             if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b1.setLabel("");
            }
            if (b2.getLabel().equals("")) {
                b2.setLabel(label);
                b6.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b6.setLabel("");
            }
             if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b6.setLabel("");
            }
           
        }
        if (e.getSource() == b7) {
            String label = b7.getLabel();
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b7.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b7.setLabel("");
            }
             if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b7.setLabel("");
            }
            if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b7.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b7.setLabel("");
            }
        }
        if (e.getSource() == b8) {
            String label = b8.getLabel();
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b8.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b8.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b8.setLabel("");
            }
             if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b8.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b8.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b8.setLabel("");
            }
        }
        if (e.getSource() == b9) {
            String label = b9.getLabel();
            if (b6.getLabel().equals("")) {
                b6.setLabel(label);
                b9.setLabel("");
            }
            if (b8.getLabel().equals("")) {
                b8.setLabel(label);
                b9.setLabel("");
            }
        }
        if (e.getSource() == b10) {
            String label = b10.getLabel();
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b10.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b10.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b10.setLabel("");
            }
               if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b10.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b10.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b10.setLabel("");
            }
               if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b10.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b10.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b10.setLabel("");
            }
        }
        if (e.getSource() == b11) {
            String label = b11.getLabel();
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b11.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b11.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b11.setLabel("");
            }
               if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b11.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b11.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b11.setLabel("");
            }
               if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b11.setLabel("");
            }
            if (b12.getLabel().equals("")) {
                b12.setLabel(label);
                b11.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b11.setLabel("");
            }
            
        }
          if (e.getSource() == b12) {
            String label = b12.getLabel();
            if (b9.getLabel().equals("")) {
                b9.setLabel(label);
                b12.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b12.setLabel("");
            }
            if (b5.getLabel().equals("")) {
                b5.setLabel(label);
                b12.setLabel("");
            }
               if (b1.getLabel().equals("")) {
                b1.setLabel(label);
                b12.setLabel("");
            }
            if (b3.getLabel().equals("")) {
                b3.setLabel(label);
                b12.setLabel("");
            }
            if (b4.getLabel().equals("")) {
                b4.setLabel(label);
                b12.setLabel("");
            }
               if (b11.getLabel().equals("")) {
                b11.setLabel(label);
                b12.setLabel("");
            }
            if (b10.getLabel().equals("")) {
                b10.setLabel(label);
                b12.setLabel("");
            }
            if (b7.getLabel().equals("")) {
                b7.setLabel(label);
                b12.setLabel("");
            }
           
          }
         
    if(b1.getLabel().equals("1")&&b2.getLabel().equals("2")&&b3.getLabel()    
            .equals("3")&&b4.getLabel().equals("4")&&b5.getLabel().equals("5")    
            &&b6.getLabel().equals("6")&&b7.getLabel().equals("7")&&b8.getLabel()    
            .equals("8")&&b9.getLabel().equals("9")&&b10.getLabel()    
            .equals("10")&&b11.getLabel().equals("11")&&b12.getLabel().equals("")){     
            JOptionPane.showMessageDialog(this,"Congratulations! You won."); 
           
    }
}
    public void displayTutorial() {
        String tutorialText ="Welcome to the Puzzle Game Tutorial!\n\n" +
                "Arrange the puzzle  from 1 to 8.\n\n" +
                "How to Play:\n" +
                "- Click on a puzzle piece to move it to the empty slot.\n" +
                "- You have 60 seconds to complete the puzzle.\n" ;

        JOptionPane.showMessageDialog(this, tutorialText, "How to play", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        
        
    PuzelFrame p = new 
    PuzelFrame();
        p.setSize(1600, 1600);
        p.setBackground(Color.black);
        p.setVisible(true);
       
        
  
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(this, " Exit?", "Are you Sure",JOptionPane.YES_NO_CANCEL_OPTION);
        if (result ==JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
     
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
class SoundClipTest extends JFrame {

    public SoundClipTest() {

        try {

            File audioFile = new File("C:\\Users\\Personal\\Downloads\\mmm-1.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();

            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}