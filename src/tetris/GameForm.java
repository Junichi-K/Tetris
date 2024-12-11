
package tetris;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;


public class GameForm extends JFrame {
    
    private GameArea newArea;
    private GameThread ref;
    
    
    public GameForm() {
        initComponents();
        
        newArea = new GameArea(gameAreaPlaceholder, 10);
        this.add(newArea);   //this creates a GameArea object when this constructor is called because we will need the GameArea for its execution. My first time definining a class in another file and //
                                    //instantiating it in another file. (Just development things I guess(Idk I just leetcode -> stupid fuck))

        initControls();
    }
    
    private void initControls() {
        InputMap im = this.getRootPane().getInputMap(); //this map contains key strokes(action performed using a kb). Each keystroke that we add to our input map gets a "name"
        ActionMap am = this.getRootPane().getActionMap();   //Similarly each action specified here gets a "name" as well
        //The program finally maps the keystrokes to actions using names as an identifier(Why not simply map keystrokes to actions directly is a question for the future)
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");   //the second parameter is the "name"
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        
        
        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                newArea.moveBlockRight();   
            }
            
        });
        
        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {

                newArea.moveBlockLeft();
            }
            
        });
        
        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                newArea.rotateBlock();   
            }
            
        });
        
        am.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                newArea.dropBlockInsta();
            }
            
        });
    }
    
    public void startGame() {
        newArea.initBackgroundArray();
        ref = new GameThread(newArea, this);//wow this is something very interesting I've never thought of wow
        ref.start();
    }
    
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
    
    public void updateLevel(int level) {
        levelLabel.setText("Level: " + level);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(255, 255, 255));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameAreaPlaceholder.setAutoscrolls(true);

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        scoreLabel.setText("Score: 0");

        levelLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        levelLabel.setText("Level: 1");

        btnMainMenu.setText("Main Menu");
        btnMainMenu.setFocusable(false);
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreLabel)
                    .addComponent(levelLabel))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(scoreLabel)
                        .addGap(65, 65, 65)
                        .addComponent(levelLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnMainMenu)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        ref.interrupt();
        this.setVisible(false);
        Tetris.showStartup();
    }//GEN-LAST:event_btnMainMenuActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {    //the parameter is an instance of an anomymous class a one which sort of extends runnable
            public void run() {
                //Now I don't know everything about this piece of code. But from what I understood, Swing processing is done in a seperate thread called the EDT(Event Dispatch Thread). So this means, there shouldn't be any
                //long lasting computations happening over here or else you would block the GUI and the program wouuld become unresponsive. So in order to tackle this situation, we run our computations in a different thread
                //and in this case, it is the the 4 classes mentioned here which leaves the GUI to say responsive. Updating the GUI happens only in the EDT. Suppose our this thread calls for some event to be done in the GUI
                //Now what we do is, we push this event into the Swings Event list. and after all the previous pending GUI events are processed, your event will be processed just like in any other queue. new Runnable() creates 
                //an instance of an anonymous class that implements the runnable interface. and the run method of this anonymous class as defined here contains the code that gets added to the EDT
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}


//Woooooooooo BABY. I alwasys wanted to create an app which opens a Window. Kinda like when you click a setup and a window opens for isntallation. Or when you opened a game from like the stone age, a small window used to
//appear right. Well guess, that window just apparead on my screen but the sad part is there is nothing on it so yeah...