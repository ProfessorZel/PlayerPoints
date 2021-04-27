package ru.astakhovmd.HexFix;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * This class will automatically register as a placeholder expansion
 * when a jar including this class is added to the directory
 * {@code /plugins/PlaceholderAPI/expansions} on your server.
 * <br>
 * <br>If you create such a class inside your own plugin, you have to
 * register it manually in your plugins {@code onEnable()} by using
 * {@code new YourExpansionClass().register();}
 */
public class DatePoints extends PlaceholderExpansion {

    protected final static String dataLayerFolderPath = "plugins" + File.separator + "PPextension";
    final static String configFilePath = dataLayerFolderPath + File.separator + "config.yml";

    String def_val;

    /**
     * Since this expansion requires api access to the plugin "SomePlugin"
     * we must check if said plugin is on the server or not.
     *
     * @return true or false depending on if the required plugin is installed.
     */
    @Override
    public boolean canRegister(){

        FileConfiguration config =  YamlConfiguration.loadConfiguration(new File(configFilePath));
        FileConfiguration outConfig = new YamlConfiguration();
        outConfig.options().header("");

        this.def_val = (String) config.get("default");
        outConfig.set("default", this.def_val);

        FileConfiguration sections = (FileConfiguration) config.get("calendar");
        if (sections!=null){
            Set<String> fl = sections.getKeys(false);

            for (String key: fl) {
                FileConfiguration section = (FileConfiguration) config.get(key);

            }
        }

        try {
            outConfig.save(new File(configFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return "ProfessorZel (Discord: ProfessorZel#0040";
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>The identifier has to be lowercase and can't contain _ or %
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "pp";
    }

    /**
     * if the expansion requires another plugin as a dependency, the
     * proper name of the dependency should go here.
     * <br>Set this to {@code null} if your placeholders do not require
     * another plugin to be installed on the server for them to work.
     * <br>
     * <br>This is extremely important to set your plugin here, since if
     * you don't do it, your expansion will throw errors.
     *
     * @return The name of our dependency.
     */
    @Override
    public String getRequiredPlugin(){
        return null;
    }

    /**
     * This is the version of this expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return "1.0.0";
    }


    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     *
     *
     * @return possibly-null String of the requested identifier.
     */
    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        identifier = identifier.replaceFirst("#[a-z0-9]{6}", "");

        return  identifier;

    }
}