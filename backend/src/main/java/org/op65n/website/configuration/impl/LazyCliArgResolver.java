package org.op65n.website.configuration.impl;

import org.jetbrains.annotations.NotNull;
import org.op65n.website.configuration.model.ArgsSupplier;
import org.op65n.website.configuration.model.Conf;
import org.op65n.website.configuration.util.CliArgs;

import java.util.Optional;
import java.util.function.Supplier;

public final class LazyCliArgResolver<A> implements Conf<A> {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static final Optional<?> FALLBACK = Optional.empty();

    public static <T> Optional<T> fallback() {
        //noinspection unchecked
        return (Optional<T>) FALLBACK;
    }

    private final ArgsSupplier<Optional<A>> resolver;
    private final Supplier<A> fallback;

    private A arg;

    public LazyCliArgResolver(final @NotNull ArgsSupplier<@NotNull Optional<@NotNull A>> resolver, final @NotNull Supplier<@NotNull A> fallback) {
        this.resolver = resolver;
        this.fallback = fallback;
    }

    @Override
    public @NotNull A value() {
        if (arg == null) arg = resolver.supply(CliArgs.self()).orElseGet(fallback);

        return arg;
    }

}
