package it.polimi.se2018.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.game.GameEvent;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.PublicObjectiveCard;
import it.polimi.se2018.model.Space;
import it.polimi.se2018.model.ToolCard;

/**
 * @author Davide Yi Xian Hu
 */
public class Json {

    /**
     * Return a gson object. It has registered the type adapter the model needs.
     * Can cast every object of the model into json.
     * @return the gson object.
     */
    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ViewUpdaterInterface.class, new ViewUpdaterAdapter());
        gsonBuilder.registerTypeAdapter(Space.class, new SpaceAdapter());
        //gsonBuilder.registerTypeAdapter(WindowPattern.class, new WindowPatternAdapter());
        gsonBuilder.registerTypeAdapter(PlayerState.class, new PlayerStateAdapter());
        gsonBuilder.registerTypeAdapter(ToolCard.class, new ToolCardAdapter());
        gsonBuilder.registerTypeAdapter(PublicObjectiveCard.class, new PublicObjectiveCardAdapter());
        gsonBuilder.registerTypeAdapter(GameEvent.class, new GameEventAdapter());
        return gsonBuilder.create();
    }

}
