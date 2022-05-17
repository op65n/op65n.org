package org.op65n.website.configuration.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Arg {

    private static final Logger log = LoggerFactory.getLogger(Arg.class);

    private static final List<Arg> ARGS = new ArrayList<>();

    private final String arg;
    private final String description;
    private final @Nullable String valueDescription;

    private Arg(final @NotNull String arg, final @Nullable String valueDescription, final @NotNull String description) {
        this.arg = String.format("--%s", arg);
        this.valueDescription = valueDescription;
        this.description = description;
    }

    public String get() {
        return arg;
    }

    public String description() {
        return description;
    }

    public String helpLine() {
        if (valueDescription != null) return String.format("%s <%s> (%s)", arg, valueDescription, description);
        return String.format("%s (%s)", arg, description);
    }

    public static ArgBuilder.BuilderStep1 create(final @NotNull String arg) {
        return new ArgBuilder(arg).toBuilder();
    }

    public static void printHelp() {
        ARGS.forEach(argument -> log.info(argument.helpLine()));
    }

    public static class ArgBuilder {

        private final String arg;
        private String valueDescription = null;
        private String description = null;

        private ArgBuilder(String arg) {
            this.arg = arg;
        }

        public BuilderStep1 toBuilder() {
            return new BuilderStep1();
        }

        public class BuilderStep1 {
            private BuilderStep1() {}

            public BuilderStep2 description(final @NotNull String description) {
                ArgBuilder.this.description = description;
                return new BuilderStep2();
            }
        }

        public class BuilderStep2 {
            private BuilderStep2() {}

            public BuilderStep2 requiresValue(final @NotNull String valueDescription) {
                ArgBuilder.this.valueDescription = valueDescription;
                return this;
            }

            public Arg build() {
                final Arg createdArg = new Arg(arg, valueDescription, description);

                ARGS.add(createdArg);

                return createdArg;
            }
        }
    }

}
