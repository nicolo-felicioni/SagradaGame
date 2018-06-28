package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.options.tooloptions.DecreaseDieValueOption;
import it.polimi.se2018.view.cli.options.tooloptions.IncreaseDieValueOption;
import it.polimi.se2018.view.cli.options.tooloptions.RerollAllDraftPoolDiceOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionFactory {

    public static List<Option> buildOptions(CommandLineInterface cli, PlayerState state) {

        List<Option> options = new ArrayList<>();

        if (state.hasChosenWindowPattern()) { //if the player already chose a window pattern

            Optional<ToolCard> optionalActiveCard = Arrays.stream(cli.getToolCards())
                    .filter(ToolCard::isActive).findAny();

            if (state.canPlaceDie() || state.canUseTool()) {//if it's this player's turn
                if (state.canPlaceDie()) {
                    options.add(new PlaceDieOption(cli));
                }
                if (state.canUseTool()) {
                    options.add(new UseToolOption(cli));
                }

                optionalActiveCard.ifPresent(toolCard -> addToolCardOptions(options, cli, toolCard));

                if (state.canEndTurn()) {
                    options.add(new EndTurnOption(cli));
                }
            }
        } else {
            //if the player hasn't chosen a window yet he has to choose one
            options.add(new ChooseWindowOption(cli));
        }
        return options;
    }

    private static void addToolCardOptions(List<Option> options, CommandLineInterface cli, ToolCard card){
        if(card.isActive()){
            if(card.rerollAllDraftPoolDice())
                options.add(new RerollAllDraftPoolDiceOption(cli));
            if(card.increaseDieValue())
                options.add(new IncreaseDieValueOption(cli));
            if(card.decreaseDieValue())
                options.add(new DecreaseDieValueOption(cli));
        }

    }


}
