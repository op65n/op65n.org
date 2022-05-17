package org.op65n.website.configuration.util;

import org.jetbrains.annotations.NotNull;
import org.op65n.website.Main;
import org.op65n.website.configuration.model.Arg;

import java.util.List;
import java.util.Optional;

public final class CliArgs {

    private final List<String> args;

    private static CliArgs self = null;

    public static CliArgs self() {
        if (self == null) self = new CliArgs();
        return self;
    }

    public CliArgs() {
        this.args = List.of(Main.CLI_ARGS);
    }

    public List<String> raw() {
        return args;
    }

    public boolean isPresent(final @NotNull Arg key) {
        return args.stream().anyMatch(arg -> arg.equalsIgnoreCase(key.get()));
    }

    public Optional<String> findValue(final @NotNull Arg key) {
        for (int index = 0; index < args.size(); index++) {
            if (!args.get(index).equals(key.get())) continue;

            final int target = index + 1;
            if (target > args.size()) break;

            final String value = args.get(target);

            return value.startsWith("--") ? Optional.empty() : Optional.of(value);

        }

        return Optional.empty();
    }

}
