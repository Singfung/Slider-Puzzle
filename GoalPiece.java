
import javafx.scene.paint.Color;

public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
    }
    public boolean canMoveRightIn(GameBoard b) {
    	int x = this.getTopLeftX() + this.getWidth() - 1;
    	int y = this.getTopLeftY();
    	boolean result = true;
    	
    	if((x + 1) > 6){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if(gp.getTopLeftX() == x + 1 && gp.getTopLeftY() == y){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == x + 1) && (gp.getTopLeftY() <= y && (gp.getTopLeftY() + gp.getHeight() - 1 >= y))){
    					return false;
    				}
    			}
    		}
    	}
        return result;
    }
}
