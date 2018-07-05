package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.cli.CommandLineInterface;
import it.polimi.se2018.view.cli.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionFactory {


    /**
     * a private constructor that hides the implicit one.
     */
    private OptionFactory(){
        super();
    }

    /**
     * Creates the options available for the player menu in the command line interface.
     *
     * If it is not the player's turn, the options available will be only visual, such as showing the
     * window pattern or showing the draft pool, while if it is the player's turn he can place a die
     * or use a tool card.
     * After activating a tool card this method will also add the options made available by the card.
     * The method has also a boolean parameter 'exit'. If it is true this method will also allow the
     * player to end the game.
     *
     *
     * @param cli the command line interface
     * @param state the player state
     * @param exit a boolean flag for the end game option
     * @return a list of options available for the player
     */
    public static List<Option> buildPlayerMenuOptions(CommandLineInterface cli, PlayerState state, boolean exit) {

        List<Option> options = new ArrayList<>();

        if(!exit){
            if (state.hasChosenWindowPattern()) { //if the player already chose a window pattern

                Optional<ToolCard> optionalActiveCard = Arrays.stream(cli.getToolCards())
                        .filter(ToolCard::isActive).findAny();

                if ((state.canPlaceDie() || state.isDiePlaced()) || state.canUseTool()) {//if it's this player's turn

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
        }else{
            addVisualOptions(options, cli);
            options.add(new EndGameOption(cli));
        }

        return options;
    }


    /**
     * Creates the options of the complex option PlaceDie.
     *
     * @see PlaceDieOption
     * @param cli the command line interface
     * @return a list of options for placing a die
     */
    public static List<Option> buildPlaceDieOptions(CommandLineInterface cli){
        List<Option> options = new ArrayList<>();
        options.add(new ReadDraftPoolDieOption(cli));
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
        return options;
    }

    /**
     * Creates the options of the complex option MoveDieIgnoreColor.
     *
     * @see MoveDieIgnoreColorOption
     * @param cli the command line interface
     * @return a list of options for moving a die ignoring color restrictions.
     */
    public static List<Option> buildMoveDieIgnoreColorOptions(CommandLineInterface cli){
        List<Option> options = new ArrayList<>();
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
        return options;
    }

    /**
     * Creates the options of the complex option MoveDieIgnoreValue.
     *
     * @see MoveDieIgnoreValueOption
     * @param cli the command line interface
     * @return a list of options for moving a die ignoring value restrictions.
     */
    public static List<Option> buildMoveDieIgnoreValueOptions(CommandLineInterface cli) {
        return buildMoveDieIgnoreColorOptions(cli);
    }

    /**
     * Creates the options of the complex option MoveADie.
     *
     * @see MoveADieOption
     * @param cli the command line interface
     * @return a list of options for moving a die.
     */
    public static List<Option> buildMoveDieOptions(CommandLineInterface cli) {
        return buildMoveDieIgnoreColorOptions(cli);
    }


    /**
     * Creates the options of the complex option SwapDraftDieWithRoundTrackDieOption.
     *
     * @see SwapDraftDieWithRoundTrackDieOption
     * @param cli the command line interface
     * @return a list of options for swapping a die on the draft pool with one on the round track.
     */
    public static List<Option> buildSwapDraftDieOptions(CommandLineInterface cli) {
       List<Option> options = new ArrayList<>();
       options.add(new ReadDraftPoolDieOption(cli));
       options.add(new ReadRoundOption(cli));
       return options;
    }


    /**
     * This method adds a list of option for returning a die to the dice bag and getting a new one.
     *
     * @see ReturnDieAndGetNewOption
     * @param options the initial list of options
     * @param cli the command line interface
     * @param draftedDie the drafted die
     */
    public static void addReturnDieAndGetNewOptions(List<Option> options, CommandLineInterface cli, Die draftedDie){
        options.add(new ChooseDraftedDieValueOption(cli, draftedDie));
        options.add(new ReadXOption(cli));
        options.add(new ReadYOption(cli));
    }


    /**
     * this private method adds to a list of options the options available because of the activated tool card.
     * @param options a list of option
     * @param cli the command line interface
     * @param card the active tool card
     */
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
                options.add(new PlaceDieNoAdjacentOption(cli));
            if(card.moveADie())
                options.add(new MoveADieOption(cli));
            if(card.moveTwoDiceMatchColorOnRoundTrack())
                options.add(new MoveDieMatchColorOnRoundTrackOption(cli));
            if(card.returnDieAndGetNewFromDiceBag())
                options.add(new ReturnDieAndGetNewOption(cli));
        }

    }

    /**
     * this private method adds to a list of options the visual options.
     *
     * The visual options, i.e. showing the draft pool or the window pattern, will be always available.
     *
     * @param options a list of option
     * @param cli the command line interface
     */
    private static void addVisualOptions(List<Option> options, CommandLineInterface cli){
        options.add(new ShowPrivateObjectiveOption(cli));
        options.add(new ShowYourWindowPatternOption(cli));
        options.add(new ShowFavorTokensOption(cli));
        options.add(new ShowDraftPoolOption(cli));
        options.add(new ShowPublicObjectiveCardsOption(cli));
        options.add(new ShowToolCardsOption(cli));
        options.add(new ShowOtherWindowPatternOption(cli));
    }


}
