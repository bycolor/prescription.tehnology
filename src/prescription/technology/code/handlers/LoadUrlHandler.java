package prescription.technology.code.handlers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by novac on 14-Feb-15.
 */
public class LoadUrlHandler extends RequestHandlerBase {
    protected LoadUrlHandler(Context context) {
        super(context);
    }

    @Override
    public void handle(Intent intent) {
        if (intent.getAction() == "LoadURLEventHandler") {
            String page = intent.getStringExtra("page");
            if (page != null && page != "")
                getActivity().appView.loadUrl("file:///android_asset/www/" + page);
            else
                Log.v(getTAG(), "Not found");
            hasbeenhandled = true;
        }
    }
}
