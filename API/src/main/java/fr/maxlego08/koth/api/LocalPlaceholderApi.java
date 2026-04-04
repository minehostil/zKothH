package fr.maxlego08.koth.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Interface for local placeholder handling.
 */
public interface LocalPlaceholderApi {

    /**
     * Gets the plugin instance.
     *
     * @return The plugin
     */
    Plugin getPlugin();

    /**
     * Gets the placeholder prefix.
     *
     * @return The prefix
     */
    String getPrefix();

    /**
     * Handles a placeholder request.
     *
     * @param player The player
     * @param params The placeholder parameters
     * @return The replacement value
     */
    String onRequest(Player player, String params);

}
