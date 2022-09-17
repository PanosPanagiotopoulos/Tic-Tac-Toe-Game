package package3;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacPageSetUp implements ActionListener {
    JFrame GamePage;    // BASE FRAME OF THE GAME //
    JPanel GamePlayPage;    // GAMEPLAY PANEL //
    JLabel LogoPhoto , LinePhotoLabel1 , LinePhotoLabel2;
    ArrayList<JButton> ButtonArray; // TIC BUTTONS //
    String Winner = "Player ", ReplayQuestion = "";
    // COSMETIC STRINGS FOR USER FIRENDLY ENVIRONMENT //
    final byte ArrayDimension = 3, Square = 2, GameBorderThickness = 7 , GameButtonThickness = 5;
    // 3x3 Game (Can be Changed Any Time) , SQUARE FOR THE 2D ARRAY AND THE THICKNESS OF THE COLOR OF THE PANEL //

    boolean PlayFlag = false; // false = X turn , true = O turn //

    TicTacPageSetUp() {
        GamePage = new JFrame();
        GamePlayPage = new JPanel();
        LogoPhoto = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package3_Files\\TIC - TAC - TOE - GAME.png"));
        LinePhotoLabel1 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package3_Files\\Lines.png"));
        LinePhotoLabel2 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package3_Files\\Lines.png"));
        ButtonArray = new ArrayList<JButton>();

        GamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePage.setResizable(false);
        GamePage.setTitle("TicTacToe Game");
        GamePage.setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package3_Files\\Tic-Tac-Toe Photo.png").getImage());
        GamePage.getContentPane().setBackground(Color.DARK_GRAY);
        GamePage.setSize(720, 720);
        GamePage.setLayout(null);

        LogoPhoto.setBounds(55,35,600,90);
        LinePhotoLabel1.setBounds(26,175,42,420);
        LinePhotoLabel2.setBounds(626,175,42,420);

        GamePlayPage.setBackground(Color.LIGHT_GRAY);
        GamePlayPage.setLayout(new GridLayout(ArrayDimension, ArrayDimension, 3, 3));
        GamePlayPage.setBorder(TicTacPageSetUp.MakeBorder(Color.WHITE , GameBorderThickness));
        GamePlayPage.setBounds(101, 151, 480, 480);

        for (int i = 0; (i < Math.pow((double) ArrayDimension, (double) Square)); i++) {
            ButtonArray.add(new JButton(" "));  // ' ' IS THE DEFAULT TEXT //
            ButtonArray.get(i).setSize(40, 40);
            ButtonArray.get(i).setBorder(TicTacPageSetUp.MakeBorder(Color.DARK_GRAY , GameButtonThickness));
            ButtonArray.get(i).setFont(TicTacPageSetUp.SetFont());
            ButtonArray.get(i).setFocusable(false);
            ButtonArray.get(i).setBackground(Color.LIGHT_GRAY);
            ButtonArray.get(i).addActionListener(this);

            GamePlayPage.add(ButtonArray.get(i));
        }


        GamePage.add(LogoPhoto);
        GamePage.add(LinePhotoLabel1);
        GamePage.add(LinePhotoLabel2);
        GamePage.add(GamePlayPage);

        SetVisible();
    }

    public void SetVisible() {
        GamePage.setVisible(true);
    }
    // GAME START //

    private static Border MakeBorder() {
        return BorderFactory.createLineBorder(Color.BLACK, 2);
    }

    // CREATING BORDERS WITH OR WITHOUT USER PREFERENCE //
    private static Border MakeBorder(Color BorderColor) {
        return BorderFactory.createLineBorder(BorderColor, 2);
    }

    private static Border MakeBorder(byte Thickness) {
        return BorderFactory.createLineBorder(Color.BLACK, Thickness);
    }

    private static Border MakeBorder(Color BorderColor , byte Thickness) {
        return BorderFactory.createLineBorder(BorderColor , Thickness);
    }

    private static Font SetFont() {
        return new Font("Serif Bold", Font.PLAIN, 50);
    }
    // CREATING FONT WITHOUT USER PREFERENCE //
    private boolean ArrayIsFull() { // CHECKS IF THE ARRAY IS FULL WITHOUT A WINNER //
        for (int i = 0; (i < ButtonArray.size()); i++) {
            if (ButtonArray.get(i).getText() == " ")
                return false;
        }

        return true;
    }

    public String GameWon() {
        // CHECKS IF THE GAME WAS WON FROM EITHER PLAYER //
        // RETURNS "X" OR "O" FOR PLAYER X OR O OR NULL FOR NONE //
        char[][] GamePageTics = new char[ArrayDimension][ArrayDimension];   // GAME ARRAY //
        final byte Zero = 0;    // TO GET THE FIRST (AND ONLY CHAR FROM THE BUTTON TEXT //
        int i = 0, j = 0, k = 0, X_Counter = 0, O_Counter = 0;   // LOOP AND X,O TIC COUNTERS //

        for (i = 0; (i < ArrayDimension); i++) {  // CREATING A 2D CHAR ARRAY THROUGH AN 1D BUTTON TEXT ARRAY //
            for (j = 0; (j < ArrayDimension); j++, k++) {
                GamePageTics[i][j] = ButtonArray.get(k).getText().charAt(Zero);
            }
        }

        for (i = 0, X_Counter = 0, O_Counter = 0; (i < ArrayDimension); i++) {
            for (j = 0; (j < ArrayDimension); j++) {  // CHECKS FOR A WINNER IN THE PRIMARY DIAGONAL //

                if (i == j) {
                    if (GamePageTics[i][j] == 'X')
                        X_Counter++;

                    else if (GamePageTics[i][j] == 'O')
                        O_Counter++;
                }
            }
        }
        if (X_Counter == ArrayDimension)
            return "X";

        if (O_Counter == ArrayDimension)
            return "O";


        for (i = 0, X_Counter = 0, O_Counter = 0; (i < ArrayDimension); i++) {
            for (j = 0; (j < ArrayDimension); j++) {
                if ((i + j) == (ArrayDimension - 1)) {    // CHECKS SECONDARY DIAGONAL FOR A WINNER //
                    if (GamePageTics[i][j] == 'X')
                        X_Counter++;

                    else if (GamePageTics[i][j] == 'O')
                        O_Counter++;
                }
            }
        }
        if (X_Counter == ArrayDimension)
            return "X";

        else if (O_Counter == ArrayDimension)
            return "O";


        for (i = 0; (i < ArrayDimension); i++) { // CHECKS EVERY ROW FOR A WINNER //
            for (j = 0, X_Counter = 0, O_Counter = 0; (j < ArrayDimension); j++) {
                if (GamePageTics[i][j] == 'X')
                    X_Counter++;

                else if (GamePageTics[i][j] == 'O')
                    O_Counter++;
            }
            if (X_Counter == ArrayDimension)
                return "X";

            else if (O_Counter == ArrayDimension)
                return "O";
        }

        for (i = 0; (i < ArrayDimension); i++) {
            for (j = 0, X_Counter = 0, O_Counter = 0; (j < ArrayDimension); j++) {
                if (GamePageTics[j][i] == 'X')  // CHECKS EVERY COLUMN FOR A WINNER //
                    X_Counter++;

                else if (GamePageTics[j][i] == 'O')
                    O_Counter++;
            }

            if (X_Counter == ArrayDimension)
                return "X";
            else if (O_Counter == ArrayDimension)
                return "O";
        }


        return null;
    }

    private void RestartGame() {    // RESTARTING THE GAME //
        GamePage.dispose();
        GamePlayPage.removeAll();
        // DELETING PREVIOUS DATA AND RESTARTING PROCESS //

        ButtonArray.clear();
        for (int j = 0; (j < Math.pow((double) ArrayDimension, (double) Square)); j++) {
            ButtonArray.add(new JButton(" "));
            ButtonArray.get(j).setSize(40, 40);
            ButtonArray.get(j).setBorder(TicTacPageSetUp.MakeBorder(Color.DARK_GRAY));
            ButtonArray.get(j).setFont(TicTacPageSetUp.SetFont());
            ButtonArray.get(j).setFocusable(false);
            ButtonArray.get(j).setBackground(Color.LIGHT_GRAY);
            ButtonArray.get(j).addActionListener(this);

            GamePlayPage.add(ButtonArray.get(j));
        }

        GamePage.add(GamePlayPage);

        // OTHER DEFAULT VARIABLES //
        Winner = "Player ";
        ReplayQuestion = "";

        PlayFlag = false;

        // START NEW GAME //
        SetVisible();
    }


    @Override
    public void actionPerformed(ActionEvent e) {    // CLICKING OF BUTTONS ACTIONS //
        for (int i = 0; (i < ButtonArray.size()); i++) {
            if (e.getSource() == ButtonArray.get(i)) {
                if (!(ArrayIsFull())) {
                    if (PlayFlag) {
                        if (ButtonArray.get(i).getText().equals(" ")) {   // CHECKS IF THE BOX IS EMPTY //
                            ButtonArray.get(i).setText("O");
                            PlayFlag = false;
                        }
                    }
                    if (!(PlayFlag)) {
                        if (ButtonArray.get(i).getText().equals(" ")) {
                            ButtonArray.get(i).setText("X");
                            PlayFlag = true;
                        }
                    }

                    if (!(GameWon() == null)) {   // IF THE GAME WAS WON CHECKS FROM WHO FOR THE MESSANGE //
                        switch (GameWon()) {
                            case "X":
                                Winner = Winner + "X Won! ";
                                break;

                            case "O":
                                Winner = Winner + "0 Won!  ";
                                break;
                        }

                        do {
                            ReplayQuestion = JOptionPane.showInputDialog("** " + Winner
                                    + "Do you want to Replay? **" , "Yes");
                        } while ( !(ReplayQuestion.equalsIgnoreCase("YES"))
                                    && !(ReplayQuestion.equalsIgnoreCase("NO")) );

                        try {
                            if (!(ReplayQuestion.equalsIgnoreCase("YES"))) { // IF ANYTHING BUT YES THEN STOPS THE PROGRAM //
                                GamePage.dispose();
                                    return;
                            }
                        } catch (NullPointerException v) {
                            GamePage.dispose();
                                return;
                        }

                        RestartGame(); // ELSE THE LAST CASE IS TO HAVE TYPED YES SO IT RESTARTS THE GAME //
                            return;
                    }
                }
            }
        }

        if (ArrayIsFull()) {  // CHECKS FOR A TIE //
            do {
                   ReplayQuestion = JOptionPane.showInputDialog("** "
                        + "It's A Tie! " + "Do you want to Replay? **" ,"Yes");
            } while ( !(ReplayQuestion.equalsIgnoreCase("YES"))
                    && !(ReplayQuestion.equalsIgnoreCase("NO")) );

            try {
                if (!(ReplayQuestion.equalsIgnoreCase("YES"))) {
                    GamePage.dispose(); // IF PLAYER TYPE ANYTHING BUT YES //
                        return;
                }
            } catch (NullPointerException v) {
                GamePage.dispose(); // IF AN ERROR OCCURES //
                    return;
            }

            RestartGame();  // RESTARTING GAME //
        }
    }


}

