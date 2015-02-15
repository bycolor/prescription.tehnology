package prescription.technology.code.handlers;

import android.content.Context;

/**
 * Created by novac on 14-Feb-15.
 */
public class LeftWebViewHandler extends ObservableHandler {

    private static LeftWebViewHandler instance = null;

    public LeftWebViewHandler(Context context) {
        super(context);
    }

    public static LeftWebViewHandler getInstance(Context context) {
        if (instance == null)
            instance = new LeftWebViewHandler(context);
        return instance;
    }
}
