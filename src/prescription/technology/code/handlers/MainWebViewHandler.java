package prescription.technology.code.handlers;

import android.content.Context;

/**
 * Created by novac on 14-Feb-15.
 */
public class MainWebViewHandler extends ObservableHandler {


    //<editor-fold desc="singleton">
    private static MainWebViewHandler instance = null;

    public MainWebViewHandler(Context context) {
        super(context);
    }

    public static synchronized MainWebViewHandler getInstance(Context context) {
        if (instance == null)
            instance = new MainWebViewHandler(context);
        return instance;
    }
    //</editor-fold>
}
