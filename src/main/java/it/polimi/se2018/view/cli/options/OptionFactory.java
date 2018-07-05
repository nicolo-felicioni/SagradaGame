package it.polimi.se2018.view.cli.options;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.cli.CommandLineInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionFactory {


    private static final String DECREASE_DIE_VALUE_NAME = "Decrease the value of a die.";
    private static final String DECREASE_DIE_VALUE_SELECT = "Select the die you want to decrease.";
    private static final String FLIP_DRAFTED_DIE_NAME = "Flip a drafted die.";
    private static final String FLIP_DRAFTED_DIE_SELECT = "Select the die you want to flip.";
    private static final String INCREASE_DIE_VALUE_NAME = "Increase the value of a die.";
    private static final String INCREASE_DIE_VALUE_SELECT = "Select the die you want to increase.";
    private static final String MOVE_DIE_NAME = "Move a die respecting all restrictions.";
    private static final String MOVE_DIE_SELECT = "Select the point of the die you want to move, then select the space where you want to put it.";
    private static final String MOVE_DIE_IGNORE_COLOR_NAME = "Move a die ignoring color restriction.";
    private static final String MOVE_DIE_IGNORE_VALUE_NAME = "Move a die ignoring value restriction.";
    private static final String MOVE_DIE_MATCH_COLOR_NAME = "Move a die that matches its color with one die on round track.";
    private static final String PLACE_DIE_AFTER_FIRST_NAME = "Place another die. You will skip your second turn.";
    private static final String PLACE_DIE_AFTER_FIRST_SELECT = "Select a die from the draftpool.";
    private static final String PLACE_DIE_NO_ADJACENT_NAME = "Place a die in a space without adjacent dice";
    private static final String PLACE_DIE_NO_ADJACENT_SELECT = "Select a die.";
    private static final String PLACE_DIE_NAME = "Place a die.";
    private static final String PLACE_DIE_SELECT = "Select a die, then a space in your pattern.";
    private static final String REROLL_DRAFTED_DIE_SELECT = "Select a die you want to roll.";
    private static final String REROLL_DRAFTED_DIE_NAME = "Roll a drafted die.";
    private static final String RETURN_DIE_NAME = "Return a die and get a new one from the dice bag.";
    private static final String RETURN_DIE_SELECT = "Select the die to return.";
    private static final String SWAP_WITH_ROUND_TRACK_NAME = "Swap a drafted die with a round track die.";
    private static final String SWAP_WITH_ROUND_TRACK_SELECT = "Select a die from the draft pool and then a die from the round track.";




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
                        options.add(new PlaceDieOption(cli, PLACE_DIE_NAME, PLACE_DIE_SELECT));
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
        options.add(new ReadXOptionNoGoBack(cli));
        options.add(new ReadYOptionNoGoBack(cli));
    }


    /**
     * this private method adds to a list of options the options available because of the activated tool card.
     * @param options a list of option
     * @param cli the command line interface
     * @param card the active tool card
     */
    private static void addToolCardOptions(List<Option> options, CommandLineInterface cli, ToolCard card){
        if(card.isActive()){

            if(card.rerollAllDraftPoolDice())
                options.add(new RerollAllDraftPoolDiceOption(cli));
            if(card.increaseDieValue())
                options.add(new IncreaseDieValueOption(cli, INCREASE_DIE_VALUE_NAME, INCREASE_DIE_VALUE_SELECT));
            if(card.decreaseDieValue())
                options.add(new DecreaseDieValueOption(cli, DECREASE_DIE_VALUE_NAME, DECREASE_DIE_VALUE_SELECT));
            if(card.moveDieIgnoreColor())
                options.add(new MoveDieIgnoreColorOption(cli, MOVE_DIE_IGNORE_COLOR_NAME, MOVE_DIE_SELECT));
            if(card.moveDieIgnoreValue())
                options.add(new MoveDieIgnoreValueOption(cli, MOVE_DIE_IGNORE_VALUE_NAME, MOVE_DIE_SELECT));
            if(card.swapDraftDieWithRoundTrackDie())
                options.add(new SwapDraftDieWithRoundTrackDieOption(cli, SWAP_WITH_ROUND_TRACK_NAME, SWAP_WITH_ROUND_TRACK_SELECT));
            if(card.rerollDraftedDie())
                options.add(new RerollDraftedDieOption(cli, REROLL_DRAFTED_DIE_NAME, REROLL_DRAFTED_DIE_SELECT));
            if(card.flipDraftedDie())
                options.add(new FlipDraftedDieOption(cli, FLIP_DRAFTED_DIE_NAME, FLIP_DRAFTED_DIE_SELECT));
            if(card.placeDraftedDieNoAdjacent())
                options.add(new PlaceDieNoAdjacentOption(cli, PLACE_DIE_NO_ADJACENT_NAME, PLACE_DIE_NO_ADJACENT_SELECT));
            if(card.moveADie())
                options.add(new MoveADieOption(cli, MOVE_DIE_NAME, MOVE_DIE_SELECT));
            if(card.moveTwoDiceMatchColorOnRoundTrack())
                options.add(new MoveDieMatchColorOnRoundTrackOption(cli, MOVE_DIE_MATCH_COLOR_NAME, MOVE_DIE_SELECT));
            if(card.returnDieAndGetNewFromDiceBag())
                options.add(new ReturnDieAndGetNewOption(cli, RETURN_DIE_NAME, RETURN_DIE_SELECT));
            if(card.placeDieAfterFirstTurn())
                options.add(new PlaceDieAfterFirstTurnOption(cli, PLACE_DIE_AFTER_FIRST_NAME, PLACE_DIE_AFTER_FIRST_SELECT));
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
        options.add(new ShowRoundTrackOption(cli));
    }


}
