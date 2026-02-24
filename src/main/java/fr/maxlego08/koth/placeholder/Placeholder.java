package fr.maxlego08.koth.placeholder;

import fr.maxlego08.koth.api.LocalPlaceholderApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.List;

public interface Placeholder {

	static Placeholder getPlaceholder() {
		return LP.placeholder == null ? LP.placeholder = (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null ? new Api() : new Local()) : LP.placeholder;
	}

	String setPlaceholders(Player player, String string);

	List<String> setPlaceholders(Player player, List<String> list);

	class Api implements Placeholder {

		private Method setPlaceholdersString;
		private Method setPlaceholdersList;
		private Class<?> placeholderAPIClass;

		public Api() {
			try {
				// Register DistantPlaceholder expansion
				Class<?> distantPlaceholderClass = Class.forName("fr.maxlego08.koth.placeholder.DistantPlaceholder");
				Object expansion = distantPlaceholderClass.getConstructor(LocalPlaceholderApi.class).newInstance(LocalPlaceholder.getInstance());
				Method registerMethod = expansion.getClass().getMethod("register");
				registerMethod.invoke(expansion);

				// Get PlaceholderAPI class and methods via reflection
				placeholderAPIClass = Class.forName("me.clip.placeholderapi.PlaceholderAPI");
				setPlaceholdersString = placeholderAPIClass.getMethod("setPlaceholders", org.bukkit.OfflinePlayer.class, String.class);
				setPlaceholdersList = placeholderAPIClass.getMethod("setPlaceholders", org.bukkit.OfflinePlayer.class, List.class);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		@Override
		public String setPlaceholders(Player player, String string) {
			try {
				return (String) setPlaceholdersString.invoke(null, player, string);
			} catch (Exception exception) {
				exception.printStackTrace();
				return string;
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public List<String> setPlaceholders(Player player, List<String> list) {
			try {
				return (List<String>) setPlaceholdersList.invoke(null, player, list);
			} catch (Exception exception) {
				exception.printStackTrace();
				return list;
			}
		}

	}

	class Local implements Placeholder {

		@Override
		public String setPlaceholders(Player player, String string) {
			return LocalPlaceholder.getInstance().setPlaceholders(player, string);
		}

		@Override
		public List<String> setPlaceholders(Player player, List<String> list) {
			return LocalPlaceholder.getInstance().setPlaceholders(player, list);
		}

	}

	class LP {
		public static Placeholder placeholder;
	}

}
