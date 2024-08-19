package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x_offset, y_offset;
    private int[][][] shapes;
    private int currentRotation;
    
    private Color[] availableColors = {Color.green, Color.red, Color.blue, Color.yellow};
    
    public TetrisBlock(int[][] shape) {
        this.shape = shape; //this is used to access the class members and this distinguishes it from the function parameter
        
        initShapes();
    }
    
    public void initShapes() {
        shapes = new int[4][][];
        
        for(int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            
            shapes[i] = new int[r][c];
            
            for(int y = 0; y < r; y++) {
                for(int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }
            
            shape = shapes[i];
        }
    }
    
    public void spawn(int gridWidth) {
        Random r = new Random();
        color = availableColors[r.nextInt(availableColors.length)];
        
        currentRotation = r.nextInt(4);
        shape = shapes[currentRotation];
        
        y_offset = -getHeight();
        x_offset = (gridWidth - getWidth())/2;
    }
    
    public int[][] getShape() {
        return shape;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getHeight() {
        return shape.length;
    }
    
    public int getWidth() {
        return shape[0].length;
    }
    
    public int getX() {
        return x_offset;
    }
    
    public int getY() {
        return y_offset;
    }
    
    public void moveDown() {
        y_offset++;
    }
    
    public void moveRight() {
        x_offset++;
    }
    
    public void moveLeft() {
        x_offset--;
    }
    
    public void rotate() {
        currentRotation++;
        if(currentRotation > 3)
            currentRotation = 0;
        
        shape = shapes[currentRotation];
    }
    
    public int getLeftEdge() {
        return x_offset;
    }
    
    public int getRightEdge() {
        return x_offset + shape[0].length;
    }
    
    public int getBottomEdge() {
        return y_offset + getHeight();
    }
    
    public void setX(int newX) {
        x_offset = newX;
    }
    
    public void setY(int newY) {
        y_offset = newY;
    }
    
}
