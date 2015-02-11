package prescription.technology.code;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.*;
import android.widget.ListView;
import org.apache.cordova.Config;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import prescription.technology.R;
import prescription.technology.code.navigation.drawer.Adapter;
import prescription.technology.code.navigation.drawer.DrawerToggle;
import prescription.technology.code.navigation.drawer.Item;
import prescription.technology.code.webview.CustomCordovaWebView;
import prescription.technology.code.webview.WebViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by novac on 24-Jul-14.
 */
public abstract class PrescriptionTechnologyWithNavigationDrawer extends Activity implements CordovaInterface {

    public static CustomCordovaWebView __cart;
    public final String TAG = "PRESCRIPTION TECHNOLOGY";
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    //<editor-fold desc="Fields"
    public HashMap<String, View> NavigationDrawerViews = new HashMap<String, View>();
    public DrawerLayout mDrawerLayout;
    protected CustomCordovaWebView appView;
    protected ListView mDrawerList;
    boolean activityResultKeepRunning;
    boolean keepRunning;
    private CordovaPlugin activityResultCallback;
    private ActionBarDrawerToggle mDrawerToggle;
    private ConcurrentHashMap<String, BroadcastReceiver> broadcastReceiverHashMap;
    //</editor-fold>

    //<editor-fold desc="Override">
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appView = (CustomCordovaWebView) findViewById(R.id.cordova_main_webview);
        Config.init(this);
        appView.getSettings().setJavaScriptEnabled(true);
        WebViewInterface webViewInterface = new WebViewInterface(this.getApplicationContext());
        appView.addJavascriptInterface(webViewInterface, "prescription");
        appView.requestFocus();
        appView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean hasfocus = v.hasFocus();
                MotionEvent motionEvent = event;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!hasfocus) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new DrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        /*
        appView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) view;
                    Log.v(TAG, "cata was here in webview");
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        new AlertDialog.Builder(webView.getContext())
                                .setMessage("Are you sure you want to exit?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                        return true;
                    }
                }
                return false;
            }
        });
        String[] l = new String[1];
        l[0] = "test";
        mDrawerList.setAdapter(new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1, l));
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        */
        //PopulateLeftMenu();
        //mDrawerLayout.openDrawer(Gravity.LEFT);
    }


    //for on-screen keyboard
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Log.v(TAG, appView.getUrl());
                if (!appView.getUrl().equals(Constants.assets.index)) {
                    appView.loadUrl(Constants.assets.index);
                } else {
                    new AlertDialog.Builder(appView.getContext())
                            .setMessage("Are you sure you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLeftMenuSize();
        //populeaza harta de receiver-e din activity-ul curent
        broadcastReceiverHashMap = GetBroadcastsMap();
        for (String key : broadcastReceiverHashMap.keySet()) {
            //inregistreaza fiecare receiver
            registerReceiver(broadcastReceiverHashMap.get(key), new IntentFilter(key));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        for (String key : broadcastReceiverHashMap.keySet()) {
            //unregister fiecare receiver
            unregisterReceiver(broadcastReceiverHashMap.get(key));
            broadcastReceiverHashMap.remove(key);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLeftMenuSize();
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivityForResult(CordovaPlugin cordovaPlugin, Intent intent, int requestCode) {
        this.activityResultCallback = cordovaPlugin;
        this.activityResultKeepRunning = this.keepRunning;
        if (cordovaPlugin != null) {
            this.keepRunning = false;
        }
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void setActivityResultCallback(CordovaPlugin cordovaPlugin) {
        this.activityResultCallback = cordovaPlugin;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        CordovaPlugin callback = this.activityResultCallback;
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, intent);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Object onMessage(String s, Object o) {
        if (s.equalsIgnoreCase("exit")) {
            super.finish();
        }
        return null;
    }

    @Override
    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void onDestroy() {
        if (this.appView != null) {
            this.appView.removeAllViews();
            this.appView.handleDestroy();
            if (NavigationDrawerViews != null) {
                for (String key : NavigationDrawerViews.keySet()) {
                    CustomCordovaWebView cwv = (CustomCordovaWebView) NavigationDrawerViews.get(key);
                    cwv.removeAllViews();
                    cwv.handleDestroy();
                }
            }
        }
        super.onDestroy();
    }
    //</editor-fold>

    //<editor-fold desc="Abstract">
    protected abstract ConcurrentHashMap<String, BroadcastReceiver> GetBroadcastsMap();

    //</editor-fold>

    //<editor-fold desc="Public">
    public void AddNavigationDrawerView(String key, View v) {
        if (!NavigationDrawerViews.containsKey(key))
            NavigationDrawerViews.put(key, v);
    }

    //</editor-fold>
    //<editor-fold desc="Protected">
    protected void PopulateLeftMenu() {
        List<Item> items = new ArrayList<Item>();
        Item account = new Item();
        account.Id = "left_menu";
        account.Href = Constants.assets.leftMenu;
        items.add(account);
        mDrawerList.setAdapter(new Adapter(this,
                R.layout.left_drawer_item, items));
    }
    //</editor-fold>

    private void setLeftMenuSize() {
        Display display = getWindowManager().getDefaultDisplay();
        int x = 0;
        int y = 0;
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            display.getSize(point);
            x = point.x;
            y = point.y;
        } else {
            x = display.getWidth();
            y = display.getHeight();
        }
        int newx = (80 * x) / 100;
        mDrawerList.getLayoutParams().width = newx;

    }

}
