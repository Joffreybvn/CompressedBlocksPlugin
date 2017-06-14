package io.github.joffrey4.compressedblocks.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import io.github.joffrey4.compressedblocks.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VersionChecker {

    public static void init(Main plugin) {

        if (doesANewVersionExists(plugin)) {
            System.out.print("[CompressedBlocks] A new version of the plugin exists !");
        } else {
            System.out.print("[CompressedBlocks] Your plugin version is up to date.");
        }
    }

    /**
     * Check if a new version of the plugin exists on Spigot.
     *
     * @return true if a new version of the plugin exists.
     *         false if there's not a new version.
     *         false if the checking was nos possible.
     */
    private static Boolean doesANewVersionExists(Main plugin) {
        String pluginVersion = plugin.getDescription().getVersion();
        String spigotVersion = getLastSpigotVersion();

        if (pluginVersion != null && spigotVersion != null) {
            if (versionCompare(pluginVersion, spigotVersion) < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve the last version number of this plugin from the Spigot API (Spiget).
     * Link: https://api.spiget.org/v2/resources/41308/versions?size=1&sort=-releaseDate
     * Doc: https://spiget.org/documentation/#!/resources/get_resources_resource_versions
     *
     * @return A string as the last version number of the plugin, on spigot API.
     *         null if the version number weren't reached.
     */
    private static String getLastSpigotVersion() {

        JSONParser parser = new JSONParser();
        String version = null;

        try {
            URL spigotAPI = new URL("https://api.spiget.org/v2/resources/41308/versions?size=1&sort=-releaseDate");
            URLConnection connection = spigotAPI.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                JSONArray jsonArray = (JSONArray) parser.parse(inputLine);

                for (Object object : jsonArray) {
                    JSONObject result = (JSONObject) object;

                    version = (String) result.get("name");
                }
            }
            reader.close();

        } catch (IOException | ParseException ignored) {}
        return version;
    }

    /**
     * Compare two version strings.
     * Found on: https://stackoverflow.com/questions/6701948/efficient-way-to-compare-version-strings-in-java
     *
     * @param pluginVer A string of ordinal number separated by decimal points. This plugin version number.
     * @param spigotVer A string of ordinal number separated by decimal points. The spigot last version number.
     * @return The result is a negative integer if pluginVer is _numerically_ less than spigotVer.
     *         The result is a positive integer if pluginVer is _numerically_ greater than spigotVer.
     *         The result is zero if the strings are _numerically_ equal.
     */
    private static int versionCompare(String pluginVer, String spigotVer) {

        String[] vals1 = pluginVer.split("\\.");
        String[] vals2 = spigotVer.split("\\.");
        int i = 0;

        // Set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }

        // compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }

        // the strings are equal or one string is a substring of the other
        // ex: "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.length - vals2.length);
    }
}
