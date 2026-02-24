package fr.maxlego08.koth.hook;

import fr.maxlego08.koth.api.KothPlugin;
import fr.maxlego08.koth.api.KothTeam;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public enum TeamPlugin {

    LANDS("Lands", "fr.maxlego08.koth.hook.teams.LandHook"),
    HUSKTOWN("HuskTowns", "fr.maxlego08.koth.hook.teams.HuskTownHook"),
    SUPERIORSKYBLOCK("SuperiorSkyblock2", "fr.maxlego08.koth.hook.teams.SuperiorSkyblock2Hook"),
    BETTERTEAMS("BetterTeams", "fr.maxlego08.koth.hook.teams.BetterTeamHook"),
    FACTIONS("Factions", "fr.maxlego08.koth.hook.teams.SaberFactionHook"),
    FACTIONSUUID("FactionsUUID", "fr.maxlego08.koth.hook.teams.FactionsUUIDHook"),
    SIMPLECLANS("SimpleClans", "fr.maxlego08.koth.hook.teams.SimpleClanHook"),
    GANGSPLUS("GangsPlus", "fr.maxlego08.koth.hook.teams.GangsHook"),
    ULTIMATE_CLANS("UltimateClans", "fr.maxlego08.koth.hook.teams.UltimateClan"),

    ;

    private final String pluginName;
    private final String className;

    TeamPlugin(String pluginName, String className) {
        this.pluginName = pluginName;
        this.className = className;
    }

    public String getPluginName() {
        return pluginName;
    }

    public boolean isEnable() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(this.pluginName);
        return plugin != null && plugin.isEnabled();
    }

    public KothTeam init(KothPlugin plugin) {
        try {
            Class<?> clazz = Class.forName(this.className);
            return (KothTeam) clazz.getConstructor(KothPlugin.class).newInstance(plugin);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
