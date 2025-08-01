package io.github.mcengine.spigotmc.insignia.engine;

import io.github.mcengine.api.mcengine.MCEngineApi;
import io.github.mcengine.api.mcengine.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class MCEngineInsigniaSpigotMC extends JavaPlugin {

    /**
     * Called when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        new Metrics(this, 26245);
        saveDefaultConfig(); // Save config.yml if it doesn't exist

        boolean enabled = getConfig().getBoolean("enable", false);
        if (!enabled) {
            getLogger().warning("Plugin is disabled in config.yml (enable: false). Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Load extensions
        MCEngineApi.loadExtensions(
            this,
            "io.github.mcengine.api.insignia.addon.IMCEngineInsigniaAddOn",
            "addons",
            "AddOn"
            );
        MCEngineApi.loadExtensions(
            this,
            "io.github.mcengine.api.insignia.dlc.IMCEngineInsigniaDLC",
            "dlcs",
            "DLC"
            );

        MCEngineApi.checkUpdate(this, getLogger(), "github", "MCEngine", "insignia-engine", getConfig().getString("github.token", "null"));
    }

    /**
     * Called when the plugin is disabled.
     */
    @Override
    public void onDisable() {}
}
