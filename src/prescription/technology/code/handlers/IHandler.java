package prescription.technology.code.handlers;

import android.content.Intent;

/**
 * Created by novac on 14-Feb-15.
 */
public interface IHandler {
    public void handle(Intent intent);

    public boolean HasBeenHandled();
}
