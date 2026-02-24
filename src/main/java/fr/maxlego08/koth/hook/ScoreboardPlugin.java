package fr.maxlego08.koth.hook;

import fr.maxlego08.koth.api.KothPlugin;
import fr.maxlego08.koth.api.KothScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public enum ScoreboardPlugin {

    FEATHERBOARD("FeatherBoard", "fr.maxlego08.koth.hook.scoreboard.FeatherBoardHook"),
    TAB("TAB", "fr.maxlego08.koth.hook.scoreboard.TabHook"),
    STERNALBOARD("SternalBoard", "fr.maxlego08.koth.hook.scoreboard.SternalBoardHook"),
    TITLEMANAGER("TitleManager", "fr.maxlego08.koth.hook.scoreboard.TitleManagerHook"),

    ;

    private final String pluginName;
    private final String className;

    ScoreboardPlugin(String pluginName, String className) {
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

    public KothScoreboard init(KothPlugin plugin) {
        try {
            Class<?> clazz = Class.forName(this.className);
            return (KothScoreboard) clazz.getConstructor(KothPlugin.class).newInstance(plugin);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
