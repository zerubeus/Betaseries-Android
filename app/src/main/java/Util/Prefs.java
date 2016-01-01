package Util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by alaeddine on 30/12/2015.
 */
public class Prefs {

    SharedPreferences preferences;


    public Prefs(Activity activity) {
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public void setLimit(String limit) {
        preferences.edit().putString("limit", limit).commit();
    }

    // in case the user ddt't choose a type
    public String getLimit() {
        // we gonna use a default type Drama series
        return preferences.getString("limit", "50");
    }

}
