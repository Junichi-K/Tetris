package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {
    
    private GameArea area;
    private GameForm form;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    private int speedUpPerLevel = 100;
    private int pause = 1000;
    
    public GameThread(GameArea area, GameForm form) {
        this.area = area;
        this.form = form;
        
        form.updateScore(score);
        form.updateLevel(level);
    }
    
    @Override
    public void run() {
        
        while(true) {   //our main game loop and it runs as long as we keep playing
            
            area.spawnBlock();
            while(area.blockFall()){
                try {
                    Thread.sleep(pause);
                } 
                catch (InterruptedException ex) {
                    return;
                }
            
            }
            
            if(area.isBlockOutOfBounds()) {
                Tetris.gameOver(score);
                break;
            }
            
            area.moveBlockToBackground();
            score += area.checkBackgroundLines();
            form.updateScore(score);
            
            int lvl = score/scorePerLevel + 1;
            
            if(lvl > level) {
                level = lvl;
                form.updateLevel(level); 
                
                pause -= speedUpPerLevel;
            }
        }
    }
}
