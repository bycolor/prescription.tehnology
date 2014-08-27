package prescription.technology.code.webview;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.webkit.JavascriptInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by novac on 07-Aug-14.
 */
public class WebViewInterface {
    public final String UPDATECARTACTION = "1";
    private final String TAG = WebViewInterface.class.getSimpleName();
    private Context _context;

    public WebViewInterface(Context context) {
        this._context = context;
    }

    @JavascriptInterface
    public void sendMessage(final String action) {
        Intent i = new Intent("LEFT_MENU_BROADCAST_RECEIVER");
        i.putExtra("ACTION", action);
        _context.sendBroadcast(i);
        Log.v(TAG, "BROADCAST:" + action + " HAS BEEN SENT");
    }

    @JavascriptInterface
    public void sendMessage(final String action, String js) {
        Intent i = new Intent("LEFT_MENU_BROADCAST_RECEIVER");
        i.putExtra("ACTION", action);
        i.putExtra("JS_STATEMENTS", js);
        _context.sendBroadcast(i);
        Log.v(TAG, "BROADCAST:" + action + " HAS BEEN SENT");
    }

    public String XmlToJson(final String xml) throws JSONException {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String jsonPrettyPrintString = xmlJSONObj.toString();
        return jsonPrettyPrintString;
    }

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
