package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

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

            addVisualOptions(options, cli);

        } else {
            //if the player hasn't chosen a window yet he has to choose one
            options.add(new ChooseWindowOption(cli));
        }
        return options;
    }

    public static List<Option> buildPlaceDieOptions(CommandLineInterface cli){
        List<Option> options = new ArrayList<>();
        options.add(new ReadDraftPoolDieOption(cli));
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
        return options;
    }

    public static List<Option> buildMoveDieIgnoreColorOptions(CommandLineInterface cli){
        List<Option> options = new ArrayList<>();
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
        return options;
    }

    public static List<Option> buildMoveDieIgnoreValueOptions(CommandLineInterface cli) {
        return buildMoveDieIgnoreColorOptions(cli);
    }

    public static List<Option> buildMoveDieOptions(CommandLineInterface cli) {
        return buildMoveDieIgnoreColorOptions(cli);
    }

    public static List<Option> buildSwapDraftDieOptions(CommandLineInterface cli) {
       List<Option> options = new ArrayList<>();
       options.add(new ReadDraftPoolDieOption(cli));
       options.add(new ReadRoundOption(cli));
       return options;
    }




    private static void addToolCardOptions(List<Option> options, CommandLineInterface cli, ToolCard card){
        if(card.isActive()){
            //todo debug
            Printer.println("DEBUG: entrati in addToolCardOptions");
            if(card.rerollAllDraftPoolDice())
                options.add(new RerollAllDraftPoolDiceOption(cli));
            if(card.increaseDieValue())
                options.add(new IncreaseDieValueOption(cli));
            if(card.decreaseDieValue())
                options.add(new DecreaseDieValueOption(cli));
            if(card.moveDieIgnoreColor())
                options.add(new MoveDieIgnoreColorOption(cli));
            if(card.moveDieIgnoreValue())
                options.add(new MoveDieIgnoreValueOption(cli));
            if(card.swapDraftDieWithRoundTrackDie())
                options.add(new SwapDraftDieWithRoundTrackDieOption(cli));
            if(card.rerollDraftedDie())
                options.add(new RerollDraftedDieOption(cli));
            if(card.flipDraftedDie())
                options.add(new FlipDraftedDieOption(cli));
            if(card.placeDraftedDieNoAdjacent())
                options.add(new PlaceDraftedDieNoAdjacentOption(cli));
            if(card.moveADie())
                options.add(new MoveADieOption(cli));
        }

    }

    private static void addVisualOptions(List<Option> options, CommandLineInterface cli){
        options.add(new ShowDraftPoolOption(cli));
        options.add(new ShowPublicObjectiveCardsOption(cli));
        options.add(new ShowToolCardsOption(cli));
        options.add(new ShowYourWindowPatternOption(cli));
        options.add(new ShowOtherWindowPatternOption(cli));
    }


}
