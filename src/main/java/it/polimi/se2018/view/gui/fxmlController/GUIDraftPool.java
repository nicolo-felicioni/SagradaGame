package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.model.DraftPool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GUIDraftPool extends GridPane{

    /**
     * The draft pool.
     */
    private DraftPool draft;

    /**
     * FXML Grid Pane.
     */
    @FXML
    private GridPane draftPool;

    public GUIDraftPool() {
    }

    public void setDraftPool(DraftPool draft) {
        this.draft = draft.cloneDraftPool();
        List<Die> dice = draft.getAllDice();
        for(int i = 0; i < dice.size(); i++) {
            Die die = dice.get(i);
                GUIDie guiDie = new GUIDie();
                guiDie.setDie(die);
                this.draftPool.add(guiDie, i, 0, 1, 1);
        }
        this.draftPool.setGridLinesVisible(true);
    }

    @FXML
    public void onMouseClicked() {

    }
}
