package assignment6;

import javafx.scene.paint.Color;

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {
    	int x = this.getTopLeftX();
    	int y = this.getTopLeftY() + this.getHeight() - 1;
    	boolean result = true;
    	
    	if((y + 1) > 5){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if(((gp.getTopLeftX() <= x) && ((gp.getTopLeftX() + gp.getWidth() - 1) >= x)) && (gp.getTopLeftY() == y + 1)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == x) && (gp.getTopLeftY() == y + 1)){
    					return false;
    					}
    				}
    			}
    		}
        return result;
    }
    public boolean canMoveUpIn(GameBoard b) {
    	int x = this.getTopLeftX();
    	int y = this.getTopLeftY();
    	boolean result = true;
    	
    	if((y - 1) < 0){
    		return false;
    	}else{
    		for(GamePiece gp : b.getGamePieces()){
    			if(gp instanceof HorizontalGamePiece){
    				if((gp.getTopLeftX() <= x) && ((gp.getTopLeftX() + gp.getWidth() - 1) >= x) && (gp.getTopLeftY() == y - 1)){
    					return false;
    				}
    			}else if(gp instanceof VerticalGamePiece){
    				if((gp.getTopLeftX() == x) && ((gp.getTopLeftY() + gp.getHeight() - 1) == y - 1)){
    					return false;
    				}
    			}
    		}
    	}
        return result;
    }
}