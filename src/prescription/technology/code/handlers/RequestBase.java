package prescription.technology.code.handlers;

import android.content.Context;
import prescription.technology.Index;

/**
 * Created by novac on 14-Feb-15.
 */
public abstract class RequestBase {
    protected Context RequestContext;

    protected RequestBase(Context context) {
        this.RequestContext = context;
    }

    protected Index getActivity() {
        return (Index) RequestContext;
    }

    protected String getTAG() {
        return this.getClass().getName();
    }
}
