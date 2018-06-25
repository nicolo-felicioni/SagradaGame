package it.polimi.se2018.view.cli;

import java.util.List;

public abstract class ComplexOption extends Option {
    protected List<Option> subOptions;

    public ComplexOption(CommandLineInterface cli) {
        super(cli);
    }
}
