package prescription.technology.code.webview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novac on 25-Jan-15.
 */
public class auth extends prefsBase {
    public auth(Context context) {
        super(context);
    }

    @JavascriptInterface
    public void OnLoginCompleted(String json) {
        Log.v(TAG, json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            String TOKEN = jsonObject.getString("token");
            this.addToLocalPrefs("USER", jsonObject.getString("user"));
            this.addToLocalPrefs("TOKEN", TOKEN);
            Intent loginCompletedItent = new Intent("LOGIN_COMPLETED");
            loginCompletedItent.putExtra("TOKEN", TOKEN);
            _context.sendBroadcast(loginCompletedItent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public String getLoggedUser() {
        return localPrefs.getString("USER", null);
    }

    @JavascriptInterface
    public String getToken() {
        return localPrefs.getString("TOKEN", null);
    }
}
