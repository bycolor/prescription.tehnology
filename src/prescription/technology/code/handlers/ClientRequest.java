package prescription.technology.code.handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by novac on 14-Feb-15.
 */
public class ClientRequest extends RequestBase {
    private static ClientRequest instance = null;

    public ClientRequest(Context context) {
        super(context);
    }

    public static ClientRequest getInstance(Context context) {
        if (instance == null)
            instance = new ClientRequest(context);
        return instance;
    }

    //factory method
    public void handle(final Intent intent, final HandlerContexts handlerContexts) {
        ((Activity) RequestContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (handlerContexts) {
                    case leftwebview:
                        LeftWebViewHandler.getInstance(RequestContext).notifyHandlers(intent);
                        break;
                    case mainwebview:
                        MainWebViewHandler.getInstance(RequestContext).notifyHandlers(intent);
                        break;
                }
            }
        });

    }
}
