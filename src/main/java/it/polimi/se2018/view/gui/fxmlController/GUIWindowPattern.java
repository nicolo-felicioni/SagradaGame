package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Space;
import it.polimi.se2018.model.WindowPattern;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.net.MalformedURLException;
import java.net.URL;

public class GUIWindowPattern {

    private static final int ROW_NUMBER= 5;
    private static final int COLUMN_NUMBER= 4;
    private int savedRow;
    private int savedColumn;
    GridPane whichWindowPattern;

    public GUIWindowPattern(GridPane gridPane){
        whichWindowPattern=gridPane;
        savedColumn=0;
        savedRow=0;
    }

    private void showWindowPattern(WindowPattern windowPattern) throws MalformedURLException {
        Space[][] spaces= windowPattern.getAllSpaces();
        for (int i=0;i<COLUMN_NUMBER;i++){
            for (int j=0;j<ROW_NUMBER;j++){
                URL url=new URL("/resources/images/Die/"+spaces[i][j].getColorRestriction()+spaces[i][j].isValueRestricted()+".jpg");
                ImageView imageView=new ImageView(String.valueOf(url));
                whichWindowPattern.add(imageView,i,j);
                whichWindowPattern.setFillHeight(imageView,true);
                whichWindowPattern.setFillWidth(imageView,true);
                imageView.setOnMouseClicked(event -> {
                    savedRow=whichWindowPattern.getRowIndex(imageView);
                    savedColumn=whichWindowPattern.getColumnIndex(imageView);
                });
            }
        }
    }
    public int getRow(){
        return savedRow;
    }
    public int getColumn(){
        return savedColumn;
    }
    public void resetRowAndColumn(){
        savedColumn=0;
        savedRow=0;
    }
}
