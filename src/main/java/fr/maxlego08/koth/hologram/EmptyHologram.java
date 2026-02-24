package fr.maxlego08.koth.hologram;

import fr.maxlego08.koth.api.Koth;
import fr.maxlego08.koth.api.KothHologram;

/**
 * No-op implementation of KothHologram used when no hologram plugin is available.
 */
public class EmptyHologram implements KothHologram {

    @Override
    public void start(Koth koth) {
        // No hologram plugin available - do nothing
    }

    @Override
    public void end(Koth koth) {
        // No hologram plugin available - do nothing
    }

    @Override
    public void update(Koth koth) {
        // No hologram plugin available - do nothing
    }

    @Override
    public void onDisable() {
        // No hologram plugin available - do nothing
    }

}
