package fr.maxlego08.koth.api;

import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Optional;

/**
 * Interface representing the KothPlugin.
 * This allows hooks to depend on the API module without creating circular dependencies.
 */
public interface KothPlugin extends Plugin {

    /**
     * Gets a service provider by class.
     *
     * @param clazz The service class
     * @param <T>   The service type
     * @return The service instance
     */
    <T> T getProvider(Class<T> clazz);

    /**
     * Gets the list of all KOTHs.
     *
     * @return List of KOTHs
     */
    List<Koth> getKoths();

    /**
     * Gets a KOTH by name.
     *
     * @param name The KOTH name
     * @return Optional containing the KOTH if found
     */
    Optional<Koth> getKoth(String name);

    /**
     * Called when a team is disbanded.
     *
     * @param teamId The team ID
     */
    void onTeamDisband(String teamId);

}
