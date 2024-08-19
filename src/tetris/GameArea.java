package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel; //Jpanel is kind of like the div tag in html that it groups things together
import tetrisBlocks.*;

public class GameArea extends JPanel {
    
    private int gridRows;
    private int gridCols;
    private int cellSize;
    
    private Color[][] background;
    
    private TetrisBlock block;
    
    private TetrisBlock[] blocks;
    
    public GameArea(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds()); //x, y, width, height also there are two definitions of setBounds() one which takes 4 integers, and other takes JPanel object
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridCols = columns;
        cellSize = this.getBounds().width/gridCols;
        gridRows = this.getBounds().height/cellSize;
        
        background = new Color[gridRows][gridCols];
        //spawnBlock();
        
        blocks = new TetrisBlock[] {new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()};  //now here is an interesting piece of information, if you look closely, you
                                                                                                                                        //will see that this blocks array, contains different kinds of objects, 
                                                                                                                                        //however the game still runs, why is that the case one might ask, the answer
                                                                                                                                        //to that can be seen in the initialization of the array. Since it says 
                                                                                                                                        //new TetrisBlock[] so this means that all the objects belong to the
                                                                                                                                        //TetrisBlock class but at the same time, if you look at the initialization 
                                                                                                                                        //of each object, you can see that each object belogns to a different class
                                                                                                                                        //The key here lies in the fact that all these classes inherit the
                                                                                                                                        //TetrisBlock class, so they are all "technically" TetrisBlock objects
                                                                                                                                        //and hence we can store their references in a single array
                                                                                                                                        
    }
    
    public void spawnBlock() {
        Random r = new Random();
        
        block = blocks[r.nextInt(7)];
        block.spawn(gridCols);
    }
    
    private boolean path_clear() {
        if(block.getY() + block.getHeight() == gridRows)
            return false;
        
        int[][] shape = block.getShape();
        
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int c = 0; c < w; c++) {
            for(int r = h - 1; r >= 0; r--) {
                if(shape[r][c] != 0) {
                    int x = c + block.getX();
                    int y = r + block.getY() + 1;
                    
                    if(y < 0)
                        break;
                    
                    if(background[y][x] != null)
                        return false;
                    
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkLeft() {
        if(block.getLeftEdge() == 0)
            return false;
        
        int[][] shape = block.getShape();
        
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int r = 0; r < h; r++) {
            for(int c = 0; c < w; c++) {
                if(shape[r][c] != 0) {
                    int x = c + block.getX() - 1;
                    int y = r + block.getY();
                    
                    if(y < 0 || x < 0)
                        break;
                    
                    if(background[y][x] != null)
                        return false;
                    
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkRight() {
        if(block.getRightEdge() == gridCols)
            return false;
        
        int[][] shape = block.getShape();
        
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int r = 0; r < h; r++) {
            for(int c = w - 1; c >= 0; c--) {
                if(shape[r][c] != 0) {
                    int x = c + block.getX() + 1;
                    int y = r + block.getY();
                    
                    if(y < 0 || x >= gridCols)
                        break;
                    
                    if(background[y][x] != null)
                        return false;
                    
                    break;
                }
            }
        }
        
        return true;
    }
    
    public boolean isBlockOutOfBounds() {
        if(block.getY() < 0) {
            block = null;
            return true;
        }
        
        return false;
    }
    
    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color c = block.getColor();
        
        for(int row = 0; row < h; row++) {
            for(int col = 0; col < w; col++) {
                if(shape[row][col] == 1)
                    background[row + yPos][col + xPos] = c;
            }
        }
    }
    
    public int checkBackgroundLines() {
        boolean lineFilled;
        int linesCleared = 0;
        
        for(int r = gridRows - 1; r >= 0; r--) {
            lineFilled = true;
            
            for(int c = 0; c < gridCols; c++) {
                if(background[r][c] == null) {
                    lineFilled = false;
                    break;
                }
            }
            
            if(lineFilled) {
                clearLine(r);
                shiftBGDown(r);
                clearLine(0);
                r++;    //this line will help when you create a complete block of more than 1 one(didn't understand it so tray again when verything's done)
                linesCleared++;
                
                repaint();
            }
        }  
        
        return linesCleared;    
    }
    
    private void clearLine(int r) {
        for(int c = 0; c < gridCols; c++) {
            background[r][c] = null;
        }
    }
    
    private void shiftBGDown(int r) {
        for(int row = r; row > 0; row--) {
            for(int c = 0; c < gridCols; c++) {
                background[row][c] = background[row - 1][c];
            }
        }
    }
    
    public boolean blockFall() {
        if(!path_clear()) {
            return false;
        }
        
        block.moveDown();
        repaint();
        
        return true;
    }
    
    public void moveBlockRight() {
        if(block == null)
            return;
        
        if(checkRight()) {
            block.moveRight();
            repaint();
        }
    }
    
    public void moveBlockLeft() {
        if(block == null)
            return;
        
        if(checkLeft()) {
            block.moveLeft();
            repaint();
        }
    }
    
    public void rotateBlock() { //have to fix a thing where  we don't check the background matrix when we rotate
        if(block == null)
            return;
        
        block.rotate();
        
        if(block.getLeftEdge() < 0)
            block.setX(0);
        
        if(block.getRightEdge() >= gridCols)
            block.setX(gridCols - block.getWidth());
        
        if(block.getBottomEdge() >= gridRows)
            block.setY(gridRows - block.getHeight());
        repaint();
    }
    
    public void dropBlockInsta() {
        if(block == null)
            return;
        
        while(path_clear())
            blockFall();
        
        repaint();
        
    }
    
    private void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();
        
        for(int row = 0; row < h; row++) {
            for(int col = 0; col < w; col++) {
                if(shape[row][col] == 1) {
                    int x = block.getX();
                    int y = block.getY();
                    
                    /*g.setColor(c);
                    g.fillRect((x + col) * cellSize, (y + row) * cellSize, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect((x + col) * cellSize, (y + row) * cellSize, cellSize, cellSize);*/
                    
                    drawCellSquare(g, c, x + col, y + row);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g) {
        Color temp;
        
        for(int row = 0; row < gridRows; row++) {
            for(int col = 0; col < gridCols; col++) {
                temp = background[row][col];
                
                if(temp != null) {
                    /*int x = col * cellSize;
                    int y = row * cellSize;*/
                    
                    /*g.setColor(color);
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);*/
                    
                    drawCellSquare(g, temp, col, row);
                    
                }
            }
        }
    } 
    
    private void drawCellSquare(Graphics g, Color color, int col, int row) {
        g.setColor(color);
        g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
        g.setColor(Color.black);
        g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
    }
 
    //the grapics g guy is kind of like a "painter" that draws stuff for us
    //writing @override is not inherently necessary, it is kind of like a comment which doesn't get ignored by the compiler and actually confirms for it that the below method is indeed being overriden. Also it is a good 
    //habit to include it whenever necessary because it underlines red whenever you say misspell the function name and what not
    @Override
    protected void paintComponent(Graphics g) { //this method works with relative positioning of the JPanel and so its x and y start where the Jpanel starts
        super.paintComponent(g);    //this calls the method from the super class(JPanel) cuz super.method() otherwise we'd just be doing recursion. Also responsible for painting the constructor i guess
        
        /*for(int y = 0; y < gridRows; y++) {
            for(int x = 0; x < gridCols; x++) {
                g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }   
        }*/
        drawBackground(g);
        drawBlock(g);
    }
}
