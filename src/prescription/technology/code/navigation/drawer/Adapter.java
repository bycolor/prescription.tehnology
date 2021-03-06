package prescription.technology.code.navigation.drawer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import prescription.technology.R;
import prescription.technology.code.PrescriptionTechnologyWithNavigationDrawer;
import prescription.technology.code.webview.CustomCordovaWebView;
import prescription.technology.code.webview.WebViewInterface;

import java.util.List;

/**
 * Created by novac on 26-Jul-14.
 */
public class Adapter extends ArrayAdapter<Item> {

    private static final String TAG = "ADAPTER";
    private List<Item> internal_items;
    private PrescriptionTechnologyWithNavigationDrawer internal_context;

    public Adapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        internal_context = (PrescriptionTechnologyWithNavigationDrawer) context;
    }

    public Adapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        internal_items = items;
        internal_context = (PrescriptionTechnologyWithNavigationDrawer) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Item p = getItem(position);
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.left_drawer_item, null);
        }
        if (p != null) {
            if (p.getTag() == null) { //prevent view reloading on scroll and orientation changes
                CustomCordovaWebView cordovaWebView = (CustomCordovaWebView) v.findViewById(R.id.cordova_left_item_webview);
                if (cordovaWebView != null) {
                    Log.v(TAG, "LOAD URL FOR:" + p.Id);
                    WebViewInterface webViewInterface = new WebViewInterface(internal_context);
                    cordovaWebView.addJavascriptInterface(webViewInterface, "prescription");
                    cordovaWebView.getSettings().setJavaScriptEnabled(true);
                    cordovaWebView.loadUrl(p.Href);
                    internal_context.AddNavigationDrawerView(p.Id, cordovaWebView);
                    p.setTag(cordovaWebView);
                }
            }

            /*
            CustomCordovaWebView cordovaWebView = (CustomCordovaWebView) v.findViewById(R.id.cordova_left_item_webview);
            if (cordovaWebView != null) {
                Log.v(TAG, "LOAD URL FOR:" + p.Id);
                WebViewInterface webViewInterface = new WebViewInterface(internal_context);
                cordovaWebView.getSettings().setJavaScriptEnabled(true);
                cordovaWebView.addJavascriptInterface(webViewInterface, "prescription");
                cordovaWebView.loadUrl(p.Href);
                internal_context.AddNavigationDrawerView(p.Id, cordovaWebView);
                p.setTag(cordovaWebView);
            }
            */
        }
        return v;

    }

    public View getViewById(String Id) {
        int position = 0;
        for (int i = 0; i < getCount(); ) {
            if (getItem(i).Id == Id)
                position = i;
            break;
        }
        return getView(position, null, null);
    }
}
