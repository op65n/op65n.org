package org.op65n.website.configuration.model;

import org.jetbrains.annotations.NotNull;
import org.op65n.website.configuration.util.CliArgs;

public interface ArgsSupplier<T> {

    T supply(final @NotNull CliArgs args);

}
