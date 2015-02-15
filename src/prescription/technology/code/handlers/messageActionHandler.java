package prescription.technology.code.handlers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import prescription.technology.Index;
import prescription.technology.code.webview.CustomCordovaWebView;

/**
 * Created by novac on 14-Feb-15.
 */
public class messageActionHandler extends RequestHandlerBase {
    protected messageActionHandler(Context context) {
        super(context);
    }

    @Override
    public void handle(Intent intent) {
        if (intent.getAction() == "MESSAGE") {
            Index activity = getActivity();
            if (intent.hasExtra("ACTION") && intent.hasExtra("VIEWID")) {
                String action = intent.getStringExtra("ACTION");
                String js_statements = intent.getStringExtra("JS_STATEMENTS");
                String viewId = intent.getStringExtra("VIEWID");
                CustomCordovaWebView webView = (CustomCordovaWebView) activity.NavigationDrawerViews.get(viewId);
                Log.v(getTAG(), "js enabled:" + webView.getSettings().getJavaScriptEnabled());
                if (webView != null) {
                    if (js_statements != null) {
                        Log.v(getTAG(), js_statements);
                        webView.sendJavascript(js_statements);
                    } else { //fire javascript event with supplied name
                        Log.v(getTAG(), "dispatchEvent " + action);
                        //!! javascript events should be defined on root context in order this implementation to work
                        webView.sendJavascript("document.dispatchEvent(" + action + ")");
                    }
                } else {
                    Log.v(getTAG(), viewId + "is undefined");
                }

            }
            hasbeenhandled = true;
        }
    }
}
