package tetrisBlocks;

import tetris.TetrisBlock;

public class IShape extends TetrisBlock {

    public IShape() {
        super(new int[][] {{1, 1, 1, 1}});   //so simply using the word super without followed by any method, it will call the constructor of the parent class. This was we are able to access a private data member
                                            //of the parent class which we couldn't do since inherited classes don't inherit private data members of parent classes. So this constrctor calls the constructor of the 
                                            //parent class. Also there is no issue of what if a class inherits more than one class and which class' constructor would be called, since java doesn't allow multiple 
                                            //inheritance
                                            
                                            //also one very important thing to note is that when an object of a subclass is created, the resulting object will contain also the private data members of the parent 
                                            //class, even though we cannot access them which is pretty non-intuitive but that's just how it is(atleast for now). So this means that an Ishape object that has been 
                                            //created will have a data memeber called shape[][]
    }
    
    @Override
    public void rotate() {  //this is a very very very good example of a place to use the method overriding. All the children of the parent class have a rotate method but this one needs to have a special 
                            //implementation, so we simply override. Java is fun man
        super.rotate();
        
        if(getWidth() == 1) {
            setX(getX() + 1);
            setY(getY() - 1);
        }
        
        else {
            setX(getX() - 1);
            setY(getY() + 1);
        }
    }
}
