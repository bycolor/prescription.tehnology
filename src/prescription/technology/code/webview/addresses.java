package prescription.technology.code.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by novac on 25-Jan-15.
 */
public class addresses extends prefsBase {
    public addresses(Context context) {
        super(context);
    }

    @JavascriptInterface
    public void saveAddresses(String jsonAddresses) {
        addToLocalPrefs("ADDRESSES", jsonAddresses);
    }

    @JavascriptInterface
    public String getAddresses() {
        return this.localPrefs.getString("ADDRESSES", null);
    }

}
