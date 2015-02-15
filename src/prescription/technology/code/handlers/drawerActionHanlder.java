package prescription.technology.code.handlers;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import prescription.technology.Index;

/**
 * Created by novac on 14-Feb-15.
 */
public class drawerActionHanlder extends RequestHandlerBase {
    protected drawerActionHanlder(Context context) {
        super(context);
    }

    @Override
    public void handle(Intent intent) {
        if (intent.getAction() == "DRAWER") {
            Index activity = getActivity();
            if (intent.hasExtra("open")) {
                if (intent.getStringExtra("open").equals("1")) {
                    activity.mDrawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    activity.mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
            } else
                activity.mDrawerLayout.closeDrawer(Gravity.LEFT);
            hasbeenhandled = true;
        }
    }
}
