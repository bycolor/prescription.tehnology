package prescription.technology.code.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import prescription.technology.Index;
import prescription.technology.code.navigation.drawer.CustomCordovaWebView;

/**
 * Created by novac on 07-Aug-14.
 */
public class LEFT_MENU_BROADCAST_RECEIVER extends BroadcastReceiver {
    private final static String TAG = LEFT_MENU_BROADCAST_RECEIVER.class.getSimpleName();
    public CustomCordovaWebView cartView;

    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("ACTION")) {
            Index activity = (Index) context;
            cartView = (CustomCordovaWebView) activity.NavigationDrawerViews.get("CART");
            String action = intent.getStringExtra("ACTION");
            String js_statements = intent.getStringExtra("JS_STATEMENTS");
            if (cartView != null) {
                if (js_statements != null)
                    cartView.sendJavascript(js_statements);
                else {
                    Log.v(TAG, "dispatchEvent " + action);
                    cartView.sendJavascript("document.dispatchEvent(" + action + ")");
                }
            } else {
                Log.v(TAG, "cartView is not set");
            }

        }
    }
}
