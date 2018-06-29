package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GUIDraftPool {

    GridPane draftPool;
    private int savedColumn;

    public GUIDraftPool(GridPane gridPane){
        this.draftPool=gridPane;
        savedColumn=0;
    }
    private void showDraftPool(DraftPool draftPool) throws MalformedURLException {
        List<Die> draft= draftPool.getAllDice();
        int i=0;
        for (Die die:draft){
            URL url=new URL("/resources/images/Die/"+die.getColor().toString()+die.getValue().toString()+".jpg");
            ImageView imageView=new ImageView(String.valueOf(url));
            this.draftPool.addColumn(i,imageView);
            this.draftPool.setFillHeight(imageView,true);
            this.draftPool.setFillWidth(imageView,true);
            imageView.setOnMouseClicked(event -> {});
            savedColumn=this.draftPool.getColumnIndex(imageView);
        }

    }
    public int getColimn(){
        return savedColumn;
    }
    public void reset(){
        savedColumn=0;
    }
}
