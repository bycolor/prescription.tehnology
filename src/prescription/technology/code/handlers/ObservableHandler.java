package prescription.technology.code.handlers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by novac on 15-Feb-15.
 */
public class ObservableHandler extends RequestBase {
    protected HashMap<String, IHandler> handlers;

    protected ObservableHandler(Context context) {
        super(context);
        handlers = new HashMap<String, IHandler>();
    }

    public void registerHandler(String action, IHandler handler) {
        if (!handlers.containsKey(action))
            handlers.put(action, handler);
        else
            Log.v(getTAG(), "handler already registered!");
    }

    public void unregisterHandler(String action, IHandler handler) {
        if (handlers.containsKey(action)) {
            handlers.remove(handler);
        } else
            Log.v(getTAG(), "handler not found");
    }

    public void notifyHandlers(Intent intent) {
        for (String action : handlers.keySet()) {
            IHandler iHandler = handlers.get(action);
            iHandler.handle(intent);
            if (iHandler.HasBeenHandled())
                break;
        }
    }
}
