package prescription.technology.code.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import prescription.technology.Index;
import prescription.technology.code.webview.CustomCordovaWebView;

/**
 * Created by novac on 07-Aug-14.
 */
public class LEFT_MENU_BROADCAST_RECEIVER extends BroadcastReceiver {
    private final static String TAG = LEFT_MENU_BROADCAST_RECEIVER.class.getSimpleName();
    public CustomCordovaWebView webView;

    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("ACTION") && intent.hasExtra("VIEWID")) {
            String action = intent.getStringExtra("ACTION");
            String js_statements = intent.getStringExtra("JS_STATEMENTS");
            String viewId = intent.getStringExtra("VIEWID");
            Index activity = (Index) context;
            webView = (CustomCordovaWebView) activity.NavigationDrawerViews.get(viewId);
            Log.v(TAG, "js enabled:" + webView.getSettings().getJavaScriptEnabled());
            if (webView != null) {
                if (js_statements != null) {
                    Log.v(TAG, js_statements);
                    webView.sendJavascript(js_statements);
                } else { //fire javascript event with supplied name
                    Log.v(TAG, "dispatchEvent " + action);
                    //!! javascript events should be defined on root context in order this implementation to work
                    webView.sendJavascript("document.dispatchEvent(" + action + ")");
                }
            } else {
                Log.v(TAG, viewId + "is undefined");
            }

        }
    }
}
