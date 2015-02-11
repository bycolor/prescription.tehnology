package prescription.technology.code.webview;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.webkit.JavascriptInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//import org.json.XML;

//import org.json.XML;

/**
 * Created by novac on 07-Aug-14.
 */
public class WebViewInterface extends prefsBase {
    public final String UPDATECARTACTION = "1";
    private final String TAG = WebViewInterface.class.getSimpleName();

    public WebViewInterface(Context context) {
        super(context);
    }

    @JavascriptInterface
    public cart cart() {
        return new cart(_context);
    }

    @JavascriptInterface
    public addresses addresses() {
        return new addresses(_context);
    }

    @JavascriptInterface
    public auth auth() {
        return new auth(_context);
    }

    @JavascriptInterface
    public void sendMessage(final String action, String viewId) {
        Intent i = new Intent("LEFT_MENU_BROADCAST_RECEIVER");
        i.putExtra("ACTION", action);
        i.putExtra("VIEWID", viewId);
        _context.sendBroadcast(i);
        Log.v(TAG, "BROADCAST:" + action + " HAS BEEN SENT");
    }

    @JavascriptInterface
    public void sendMessage(@NotNull String action, @NotNull String viewId, @Nullable String js) {
        Intent i = new Intent("LEFT_MENU_BROADCAST_RECEIVER");
        i.putExtra("ACTION", action);
        i.putExtra("VIEWID", viewId);
        i.putExtra("JS_STATEMENTS", js);
        _context.sendBroadcast(i);
        Log.v(TAG, "BROADCAST:" + action + " HAS BEEN SENT");
    }

    @JavascriptInterface
    public void OnDrawerAction(String action) {
        Intent i = new Intent("DRAWER");
        if (action.equals("open"))
            i.putExtra(action, "1");
        else
            i.putExtra(action, "0");
        _context.sendBroadcast(i);
        Log.v(TAG, "BROADCAST:" + action + " HAS BEEN SENT");
    }


    @JavascriptInterface
    public void loadUrlIntoMainWebView(String page) {
        Intent i = new Intent("LoadURLEventHandler");
        i.putExtra("page", page);
        _context.sendBroadcast(i);
    }

    @JavascriptInterface
    public String GetFileContent(final String path) {
        if (path.startsWith("file:///android_asset/www/")) {
            AssetManager assetManager = _context.getResources().getAssets();
            try {
                InputStream inputStream = assetManager.open(path.replace("file:///android_asset/www", "www"));
                Log.v(TAG, String.valueOf(inputStream.available()));
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
