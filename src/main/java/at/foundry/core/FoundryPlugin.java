package at.foundry.core;

import jakarta.validation.constraints.NotNull;

public abstract class FoundryPlugin {

    public void start() {

    }
    public void stop() {

    }
    @NotNull
    public abstract String getName();
    @NotNull
    public abstract String getVersion();

    public String getDescription() {
        return null;
    }

    public String getAuthor() {
        return null;
    }

    public String getLogoLocation() {
        return null;
    }

    @NotNull
    public abstract String getFxmlEntryFile();

}
