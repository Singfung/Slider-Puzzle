
import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }
    
    public boolean canMoveLeftIn(GameBoard b) {
    	int x = this.getTopLeftX();
    	int y = this.getTopLeftY();
		boolean result = true;
    	
    	if((x - 1) < 0){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if(((gp.getTopLeftX() + gp.getWidth() - 1) == (x - 1)) && (gp.getTopLeftY() == y)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == (x - 1)) && ((gp.getTopLeftY() <= y) && (gp.getTopLeftY() + gp.getHeight() - 1) >= y)){
    					return false;
    				}
    			}
    		}
    	}
    	return result;
    }
    
    public boolean canMoveRightIn(GameBoard b) {
    	int x = this.getTopLeftX() + this.getWidth() - 1;
    	int y = this.getTopLeftY();
    	boolean result = true;
    	
    	if((x + 1) > 5){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if((gp.getTopLeftX() == x + 1) && (gp.getTopLeftY() == y)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == (x + 1)) && (gp.getTopLeftY() <= y) && ((gp.getTopLeftY() + gp.getHeight() - 1) >= y)){
    					return false;
    				}
    			}
    		}
    	}
    	
    	return result;
    }
    
    
    
    
    
}
