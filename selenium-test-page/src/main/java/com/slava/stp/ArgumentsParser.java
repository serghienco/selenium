package com.slava.stp;

import org.apache.commons.cli2.CommandLine;
import org.apache.commons.cli2.Option;
import org.apache.commons.cli2.OptionException;
import org.apache.commons.cli2.builder.DefaultOptionBuilder;
import org.apache.commons.cli2.builder.GroupBuilder;
import org.apache.commons.cli2.commandline.Parser;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Created by slavic on 7/24/14.
 */
public class ArgumentsParser {

    private static final Parser parser = new Parser();

    private static final Option RECURSIVE_OPTION = new DefaultOptionBuilder()
            .withShortName("r").withLongName("recursive").withRequired(false)
            .withDescription("Search Test Pages recursive in root folder").create();

    private static final Option NAME_OPTION = new DefaultOptionBuilder()
            .withShortName("n").withLongName("name").withRequired(false)
            .withDescription("Pattern name of Test Pages").create();

    private static final Option ARGUMENTS_OPTION = new DefaultOptionBuilder()
            .withShortName("a").withShortName("args").withLongName("arguments").withRequired(false)
            .withDescription("Arguments passed to Test Pages").create();

    private static final Option FOLDER_OPTION = new DefaultOptionBuilder()
            .withShortName("f").withLongName("folder").withRequired(true)
            .withDescription("Root folder where is located Test Pages").create();

    private static final Option HELP_OPTION = new DefaultOptionBuilder()
            .withShortName("h").withLongName("help").withRequired(false)
            .withDescription("Arguments description").create();


    private CommandLine commandLine;

    static {
        final GroupBuilder gb = new GroupBuilder().withName("options").withOption(RECURSIVE_OPTION).withOption(NAME_OPTION).withOption(ARGUMENTS_OPTION)
                .withOption(FOLDER_OPTION).withOption(HELP_OPTION);

        parser.setGroup(gb.create());
    }

    public void parse(String[] args) throws OptionException {
        commandLine = parser.parse(args);
    }

    public File getRoot() {
        return new File(commandLine.getValue(FOLDER_OPTION).toString());
    }

    public List<String> getNames() {
        return commandLine.getValues(NAME_OPTION);
    }

    public boolean isRecursive() {
        return commandLine.getSwitch(RECURSIVE_OPTION, false);
    }

    public Object[] getArguments() {
        return commandLine.getValues(ARGUMENTS_OPTION).toArray(new Object[0]);
    }

    public boolean isHelp() {
        return commandLine.getSwitch(HELP_OPTION, false);
    }
}
