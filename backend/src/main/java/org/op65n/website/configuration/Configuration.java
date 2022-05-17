package org.op65n.website.configuration;

import io.sentry.Sentry;
import org.op65n.website.configuration.model.Arg;
import org.op65n.website.configuration.model.Conf;
import org.op65n.website.configuration.model.Env;
import org.op65n.website.configuration.impl.LazyCliArgResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.op65n.website.configuration.impl.LazyCliArgResolver.fallback;

public class Configuration {

    private static final Logger log = LoggerFactory.getLogger(Configuration.class);

    private static final Arg HELP = Arg.create("help").description("shows help message").build();
    private static final Arg PROD = Arg.create("prod").description("runs application in a production environment").build();
    private static final Arg STAGING = Arg.create("staging").description("runs application in a staging environment").build();
    private static final Arg SENTRY_DSN = Arg.create("sentry-dsn").description("initializes sentry with provided dsn url").requiresValue("dsn url").build();

    public static final Conf<Boolean> PRINT_HELP = new LazyCliArgResolver<>(args -> {
        if (!args.isPresent(HELP)) return fallback();

        Arg.printHelp();
        return Optional.of(true);
    }, () -> false);

    public static final Conf<Env> ENV = new LazyCliArgResolver<>(args -> {
        if (args.isPresent(PROD)) return Optional.of(Env.PRODUCTION);
        if (args.isPresent(STAGING)) return Optional.of(Env.STAGING);

        return fallback();
    }, () -> Env.DEV);

    private static final Conf<Boolean> SENTRY = new LazyCliArgResolver<>(args -> {
        final Optional<String> dsn = args.findValue(SENTRY_DSN);

        if (dsn.isEmpty()) return fallback();

        Sentry.init(options -> {
            options.setDsn(dsn.get());
            options.setTracesSampleRate(1.0);
            options.setEnvironment(ENV.value().type());
        });

        return Optional.of(true);
    }, () -> false);
}
