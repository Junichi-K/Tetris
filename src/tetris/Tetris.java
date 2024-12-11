package tetris;

import javax.swing.JOptionPane; //used to create standard dialog boxes for gui. These dialog boxes can display messages, prompt for user input, or ask the user to make a decision through predefined buttons (like Yes/No/Cancel)

public class Tetris {
    
    private static GameForm gf; //we create static variables because A) they will be only created once, B) We will need access to them in other classes as well. This kind of class acts like the masterclass
                                //which is used to handle gui programs. (This approach is quite common for handline GUI operations where we store references to various types of JFrames as variables in the masterclass
    private static StartupForm sf;
    private static LeaderboardForm lf;
    
    private static AudioPlayer audio = new AudioPlayer();   //Audio player is a class we created to handle audio operations. and the object audio is just an instance of it
    
    public static void start() {    //this is the method that is responsible for starting the game. GameForm is a JFrameForm. So the first line simply makes it visible. and the second line startsup the game
        gf.setVisible(true);
        gf.startGame();
    }
    
    public static void showLeaderboard() {  //this method will display the leaderboard
        lf.setVisible(true);
    }
    
    public static void showStartup() {  //this method will take you to the startup screen           
        sf.setVisible(true);
    }
    
    public static void gameOver(int score) {    //this method is called whenever a user loses the game. We first obtain the playername from the input dialog box then stop showing the game becuase, ofcourse the game has ended
        playOver();
        
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease Enter your name: ");
        gf.setVisible(false);
        lf.addPlayerName(playerName, score);    //adding the entry to the leaderboard (1990s shit)
    }
    
    public static void playClear() {    //this line plays the game audio, We could have chose to play it in other class as well, but i guess it makes sense here cause the audio is being played in the "BG" and Tetris class handles the BG
        audio.playClearLine();
    }
    
    public static void playOver() { //the game over sound   
        audio.playGameOver();
    }

    public static void main(String[] args) {    //the main method of the entire class
        java.awt.EventQueue.invokeLater(new Runnable() {    //this is responsible for executing the Gui operations on an EDT event dispath thread(which handles the GUI in Swing)All hiding, showing, buttons 
                                                            // and all the tasks are done here, also the parameter is an instance of an anomymous class a one which sort of extends runnable
                                                            //the invoke later method ensures that tasks can be run asynchronously run on the EDT. new Runnable() creates an "anonymous class" that implements 
                                                            //Runnable. Inside it's definition we have the run method, the contents of which will be executed on the EDT
            public void run() {
                gf = new GameForm();
                sf = new StartupForm();
                lf = new LeaderboardForm();

                sf.setVisible(true);  //By default the startup screen is shown when the game initially starts
            }
        });
        
        
    }
    
}
