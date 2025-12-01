package at.foundry.core.file;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class FoundryFile {

    private static final Logger LOG = LoggerFactory.getLogger(FoundryFile.class);
    private static final String PLUGIN_FOLDER = System.getenv("APPDATA") + "/Foundry/Plugins";
    static String pluginName;
    private static File pluginFolder;

    static {
        pluginFolder = new File(PLUGIN_FOLDER + "/" + pluginName);
    }

    public static boolean isPluginFolderPresent() {
        return pluginFolder.exists();
    }

    public static File getPluginFolder() {
        createPluginFolder();
        return pluginFolder;
    }

    public static void addToPluginFolder(FoundryFile.PluginFile ... files) {
        createPluginFolder();
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

    public static File getFromPluginFolder(String name) {
        createPluginFolder();
        return Arrays.stream(Objects.requireNonNull(pluginFolder.listFiles()))
                .filter(f -> f.getAbsoluteFile().getAbsolutePath().contains(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean createPluginFolder() {
        return createNewFile(pluginFolder);
    }

    private static boolean createNewFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            LOG.error("Could not create new file", e);
            throw new RuntimeException(e);
        }
    }

    public record PluginFile(@NotNull String name, String extension) {
    }

}
