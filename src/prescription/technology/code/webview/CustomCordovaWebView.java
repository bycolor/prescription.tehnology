package prescription.technology.code.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import org.apache.cordova.CordovaWebView;
import prescription.technology.Index;
import prescription.technology.code.Constants;

/**
 * Created by novac on 04-Aug-14.
 */
public class CustomCordovaWebView extends CordovaWebView {
    public String CurrentPage = "file:///android_asset/www/index.html";

    public CustomCordovaWebView(Context context) {
        super(context);
    }

    public CustomCordovaWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomCordovaWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int temp_ScrollY = getScrollY();
            scrollTo(getScrollX(), getScrollY() + 1);
            scrollTo(getScrollX(), temp_ScrollY);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void handleDestroy() {
        super.handleDestroy();
    }

    public void loadCurrentPage() {
        this.loadUrl(CurrentPage + "#fromCache=true");
    }

    @Override
    public boolean backHistory() {
        //Log.v(TAG, this.getUrl());
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Log.v(TAG, this.getUrl());
                if (!this.getUrl().equals(Constants.assets.index)) {
                    this.loadUrl(Constants.assets.index);
                } else {
                    final CustomCordovaWebView _this = this;
                    new AlertDialog.Builder(this.getContext())
                            .setMessage("Are you sure you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Index index = (Index) _this.getContext();
                                    index.finish();
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

}
