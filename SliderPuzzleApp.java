
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SliderPuzzleApp extends Application {
    private SliderPuzzleGame    model;
    private SliderPuzzleView    view;

    private GamePiece           selectedPiece;
    private boolean             justGrabbed;
    private int                 lastX;
    private int                 lastY;

    public void start(Stage primaryStage) {
        model = new SliderPuzzleGame();
        view = new SliderPuzzleView(model);

        // Add event handlers to the inner game board buttons
        for (int w=1; w<=(GameBoard.WIDTH); w++) {
            for (int h=1; h<=(GameBoard.HEIGHT); h++) {
                view.getGridSection(w, h).setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
                        handleGridSectionSelection(mouseEvent);
                    }
                });
                view.getGridSection(w, h).setOnMouseDragged(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent mouseEvent) {
								handleGridSectionMove(mouseEvent);
							
                    }
                });
            }
        }

        // Plug in the Start button and NeaxtBoard button event handlers
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.startBoard();
                view.update();
            }
        });
        view.getNextBoardButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.moveToNextBoard();
                view.update();
            }
        });

        primaryStage.setTitle("Slide Puzzle Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.WIDTH+2),60+SliderPuzzleView.GRID_UNIT_SIZE*(GameBoard.HEIGHT+2)));
        primaryStage.show();

        // Update the view upon startup
        view.update();
    }


    private void handleGridSectionSelection(MouseEvent mouseEvent) {
    	outerloop:
    	for(int x = 1; x < 7; x++){
    		for(int y = 1; y < 7; y++){
    			if(view.getGridSection(x, y) == mouseEvent.getSource()){
    				selectedPiece = model.getCurrentBoard().pieceAt(x - 1, y - 1);
    				lastX = (int)mouseEvent.getX();
    				lastY = (int)mouseEvent.getY();
    				justGrabbed = true;
    				break outerloop;
    			}
    		}
    	}
    }
    
    private void handleGridSectionMove(MouseEvent mouseEvent){
        int currentGridX = (int)mouseEvent.getX();
        int currentGridY = (int)mouseEvent.getY();
        int deltaX = Math.abs(currentGridX - lastX);
        int deltaY = Math.abs(currentGridY - lastY);
        
        // Slide horizontally
        if(deltaX > deltaY){
        	if(deltaX >= SliderPuzzleView.GRID_UNIT_SIZE){
        		// Slide right 
        		if(currentGridX - lastX > 0){
        			if(selectedPiece.canMoveRightIn(model.getCurrentBoard())){
        				selectedPiece.moveRight();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();
        				if(justGrabbed){
        					model.makeAMove();
        					justGrabbed = false;
        				}
        			}
        		// Slide left	
        		}else{
        			if(selectedPiece.canMoveLeftIn(model.getCurrentBoard())){
        				selectedPiece.moveLeft();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();
        				if(justGrabbed){
            				model.makeAMove();
            				justGrabbed = false;
            				}
        			}
        		}
        	}
        // Slide up	
        }else if(deltaY > deltaX){
        	if(deltaY >= SliderPuzzleView.GRID_UNIT_SIZE){
        		if(currentGridY - lastY > 0){
        			if(selectedPiece.canMoveDownIn(model.getCurrentBoard())){
        				selectedPiece.moveDown();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();
        				if(justGrabbed){
            				model.makeAMove();
            				justGrabbed = false;
            				}
        			}
        		// Slide down	
        		}else{
        			if(selectedPiece.canMoveUpIn(model.getCurrentBoard())){
        				selectedPiece.moveUp();
        				lastX = (int)mouseEvent.getX();
        				lastY = (int)mouseEvent.getY();	
        				if(justGrabbed){
            				model.makeAMove();
            				justGrabbed = false;
            				}
        			}
        		}
        	}
        }
        
        if(selectedPiece instanceof GoalPiece){
        	model.getCurrentBoard().checkCompletion(selectedPiece);
        	if(model.getCurrentBoard().isCompleted()){
        		model.completeBoard();
        	}
        }
        view.update();
    }
    
    public static void main(String[] args) { launch(args); }
}


