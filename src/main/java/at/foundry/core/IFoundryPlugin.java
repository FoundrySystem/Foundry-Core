package at.foundry.core;

import jakarta.validation.constraints.NotNull;

/**
 * Base interface for implementing Plugin <br >
 * This interface is base of the plugin
 */
public interface IFoundryPlugin {

    /**
     * Method gets triggert if plugins opens to screen
     */
    void start();

    /**
     * Method gets triggert if plugin gets removed from screen
     */
    void stop();

    /**
     * The plugin name which gets display in the side
     * @return  plugin name
     */
    @NotNull
    String getName();

    /**
     * The plugin version
     * @return  plugin version
     */
    @NotNull
    String getVersion();

    /**
     * Optional Description to describe what the plugin does
     * @return  plugin description
     */
    String getDescription();

    /**
     * The name of the author of the plugin
     * @return  plugin author
     */
    String getAuthor();

    /**
     * The absolute logo path of the icon
     * @return  plugin logo location
     */
    String getLogoLocation();

    /**
     * The FXML Entry file, the first view which should be displayed
     * @return  the plugin entry view
     */
    @NotNull
    String getFxmlEntryFile();
}
