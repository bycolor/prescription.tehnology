package prescription.technology;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import prescription.technology.code.Constants;
import prescription.technology.code.PrescriptionTechnologyWithNavigationDrawer;
import prescription.technology.code.receivers.LEFT_MENU_BROADCAST_RECEIVER;
import prescription.technology.code.webview.WebViewInterface;

import java.util.concurrent.ConcurrentHashMap;

public class Index extends PrescriptionTechnologyWithNavigationDrawer {

    private final String TAG = Index.class.getSimpleName();
    private boolean FromCache = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appView.loadUrl(Constants.assets.index);
        FromCache = false;
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String TOKEN = sharedPreferences.getString("TOKEN", null);
        Log.v(TAG, "token:" + TOKEN != null ? TOKEN : "");
        PopulateLeftMenu();
        if (TOKEN == null)
            appView.loadUrl(Constants.assets.conectare);
        else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            WebViewInterface webViewInterface = new WebViewInterface(this);
            webViewInterface.sendMessage("updatecartEvent", "left_menu");
        }

        /*
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.v(TAG, query);
        }
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    protected ConcurrentHashMap<String, BroadcastReceiver> GetBroadcastsMap() {
        ConcurrentHashMap<String, BroadcastReceiver> map = new ConcurrentHashMap<String, BroadcastReceiver>();

        //<editor-fold desc="left_menuEventsHandler">
        LEFT_MENU_BROADCAST_RECEIVER br = new LEFT_MENU_BROADCAST_RECEIVER();
        map.put("LEFT_MENU_BROADCAST_RECEIVER", br);
        BroadcastReceiver drawer = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra("open")) {
                    if (intent.getStringExtra("open").equals("1")) {
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                    } else {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    }
                } else
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        };
        map.put("DRAWER", drawer);
        return map;
    }

}
