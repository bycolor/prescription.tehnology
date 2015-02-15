package prescription.technology.code.handlers;

import android.content.Context;
import android.content.Intent;

/**
 * Created by novac on 15-Feb-15.
 */
public abstract class RequestHandlerBase extends RequestBase implements IHandler {
    protected boolean hasbeenhandled = false;

    protected RequestHandlerBase(Context context) {
        super(context);
    }

    public abstract void handle(Intent intent);

    @Override
    public boolean HasBeenHandled() {
        return hasbeenhandled;
    }
}
