package org.op65n.website.configuration.model;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Conf<T> {

    @NotNull T value();

}
