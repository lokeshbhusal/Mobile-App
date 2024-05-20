package com.moutamid.addplacesapp.Helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    // Static variables to hold geographic coordinates globally accessible within the app.
    public static double lat=0.0;
    public static double lng=0.0;
    public static double current_lat=0.0;
    public static double current_lng=0.0;
    /**
     * Checks application configuration from a remote JSON file and shows an alert based on the fetched values.
     * @param activity The activity context used to show AlertDialog.
     */

    public static void checkApp(Activity activity) {
        // App name identifier used to fetch specific configurations.
        String appName = "AddPlacesApp";
        // Starts a new thread to handle network operations.
        new Thread(() -> {
            URL google = null;
            try {
                // Attempts to create a URL object pointing to a predetermined JSON configuration file.
                google = new URL("https://raw.githubusercontent.com/Moutamid/Moutamid/main/apps.txt");
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                // Opens a stream to read from the URL if it is valid.
                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String input = null;
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        // Continuously reads each line from the input stream until there's none left.
                        if ((input = in != null ? in.readLine() : null) == null) break;
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(input);
            }
            try {
                if (in != null) {
                    // Close the input stream after reading.
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String htmlData = stringBuffer.toString();

            try {
                // Parses the complete JSON string data.
                JSONObject myAppObject = new JSONObject(htmlData).getJSONObject(appName);
                // Extracts a boolean flag and message from the configuration JSON.
                boolean value = myAppObject.getBoolean("value");
                String msg = myAppObject.getString("msg");

                if (value) {
                    // If the configuration flag 'value' is true, displays an alert dialog on the UI thread.
                    activity.runOnUiThread(() -> {
                        new AlertDialog.Builder(activity)
                                .setMessage(msg)
                                .setCancelable(false)
                                .show();
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).start(); // Starts the thread operation.
    }

}
