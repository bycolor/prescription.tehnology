package prescription.technology.code.handlers;

import android.content.Context;

/**
 * Created by novac on 15-Feb-15.
 */
public class ConfigHandlers {
    public static void RegisterHandlers(Context context) {
        MainWebViewHandler.getInstance(context).registerHandler("LoadURLEventHandler", new LoadUrlHandler(context));
        LeftWebViewHandler.getInstance(context).registerHandler("DRAWER", new drawerActionHanlder(context));
        LeftWebViewHandler.getInstance(context).registerHandler("MESSAGE", new messageActionHandler(context));
    }
}
