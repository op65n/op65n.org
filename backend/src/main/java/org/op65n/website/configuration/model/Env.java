package org.op65n.website.configuration.model;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum Env {
    PRODUCTION("production"), STAGING("staging"), DEV("dev");

    private final String type;

    Env(final @NotNull String type) {
        this.type = type;
    }

    public static Env current() {
        return null;
        //return Configuration.environment;
    }

    public static void onProduction(final @NotNull Consume consume) {
        if (Env.current() == PRODUCTION) consume.accept();
    }

    public static void onStaging(final @NotNull Consume consume) {
        if (Env.current() == STAGING) consume.accept();
    }

    public static void onDev(final @NotNull Consume consume) {
        if (Env.current() == DEV) consume.accept();
    }

    public static <T> T resolve(final @NotNull Supplier<T> onProduction, final @NotNull Supplier<T> onStaging, final @NotNull Supplier<T> onDev) {
        if (Env.current() == PRODUCTION) return onProduction.get();
        if (Env.current() == STAGING) return onStaging.get();
        if (Env.current() == DEV) return onDev.get();

        throw new IllegalStateException(String.format("Illegal Environment (%s)!", Env.current()));
    }

    public static String resolveURL(final @NotNull String path) {
        if (Env.current() == PRODUCTION) return String.format("https://pk6b.si%s", path);
        if (Env.current() == STAGING) return String.format("https://staging.pk6b.si%s", path);
        //if (Env.current() == DEV) return String.format("http://127.0.0.1:%s%s", Configuration.PORT, path);

        throw new IllegalStateException(String.format("Illegal Environment (%s)!", Env.current()));
    }

    public String type() {
        return type;
    }

    @FunctionalInterface
    public interface Consume {
        void accept();
    }

}
