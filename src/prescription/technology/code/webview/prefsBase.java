package prescription.technology.code.webview;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by novac on 25-Jan-15.
 */
public class prefsBase {
    protected SharedPreferences localPrefs;
    protected Context _context;
    protected String TAG = this.getClass().getName();

    public prefsBase(Context context) {
        _context = context;
        localPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //<editor-fold desc="local prefs management">
    protected void addToLocalPrefs(String name, String value) {
        SharedPreferences.Editor editor = localPrefs.edit();
        editor.putString(name, value);
        editor.apply();
    }

    protected void removeFromLocalPrefs(String name) {
        SharedPreferences.Editor editor = localPrefs.edit();
        editor.remove(name);
        editor.apply();
    }
    //</editor-fold>

}
