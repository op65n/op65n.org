package org.op65n.website;

import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;
import org.op65n.website.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static String[] CLI_ARGS;

    //private final Javalin javalin;
    //private final RestController controller;

    public static void main(final @NotNull String[] args) {
        CLI_ARGS = args;

        if (Configuration.PRINT_HELP.value()) return;
    }

}
