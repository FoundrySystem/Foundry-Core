package at.foundry.core.file;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * IO Class to create fast and simple Files in the foundry directory
 */
public class FoundryFile {

    private static final Logger LOG = LoggerFactory.getLogger(FoundryFile.class);
    private static final String PLUGIN_FOLDER = System.getenv("APPDATA") + "/Foundry/Plugins";
    private static File pluginFolder;

    /**
     * Checks if the plugin folder is present
     * @param pluginName the name of the plugin
     * @return true if present; false if not
     */
    public static boolean isPluginFolderPresent(final String pluginName) {
        pluginFolder = new File(PLUGIN_FOLDER + "/" + pluginName);
        return pluginFolder.exists();
    }

    /**
     * Gets the plugin Folder
     * @param pluginName the name of the plugin
     * @return Plugin file as Java File Object
     */
    public static File getPluginFolder(final String pluginName) {
        createPluginFolder(pluginName);
        return pluginFolder;
    }

    /**
     * Add a file to the plugin folder
     * @param pluginName the name of the plugin
     * @param files which file(s) are to add to the plugin folder
     */
    public static void addToPluginFolder(final String pluginName, FoundryFile.PluginFile ... files) {
        createPluginFolder(pluginName);
        for(FoundryFile.PluginFile file : files) {
            String extension = file.extension;
            if(extension == null) {
                extension = ".";
            }
            final String fullFilename = file.name + "." + (extension.contains(".")
                    ? extension.replaceAll("\\.", "") : extension);

            createNewFile(new File(pluginFolder.getAbsolutePath() + "/" + fullFilename));
        }
    }

    /**
     * Gets a File from the plugin folder
     * @param pluginName the name of the plugin
     * @param fileName the filename of the file you want to get
     * @return the specific file as Java File Object
     */
    public static File getFromPluginFolder(final String pluginName, final String fileName) {
        createPluginFolder(pluginName);
        return Arrays.stream(Objects.requireNonNull(pluginFolder.listFiles()))
                .filter(f -> f.getAbsoluteFile().getAbsolutePath().contains(fileName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Creates the Plugin Folder
     * @param pluginName the name of the plugin
     * @return the plugin folder
     *
     */
    public static File createPluginFolder(final String pluginName) {
        pluginFolder = new File(PLUGIN_FOLDER + "/" + pluginName);
        createNewFolder(pluginFolder);
        return pluginFolder;
    }

    /**
     * Creates a new File
     * @param file target file
     * @return true if created; otherwise false
     */
    private static boolean createNewFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            LOG.error("Could not create new file", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Creates a new File
     * @param file target file
     * @return true if created; otherwise false
     */
    private static boolean createNewFolder(File file) {
        return file.mkdir();
    }

    /**
     * Record for Plugin File data
     * @param name file name
     * @param extension extension (e. g. txt, json, xsl, ...)
     */
    public record PluginFile(@NotNull String name, String extension) {
    }

}
