package prescription.technology.code.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.JavascriptInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novac on 25-Jan-15.
 */
public class cart extends prefsBase {
    public cart(Context context) {
        super(context);
    }

    @JavascriptInterface
    public void addToCart(String json) throws JSONException {
        String cart = this.localPrefs.getString("CART", null);
        JSONArray cartArray = null;
        if (cart != null)
            cartArray = new JSONArray(cart);
        else
            cartArray = new JSONArray();
        cartArray.put(new JSONObject(json));
        addToLocalPrefs("CART", cartArray.toString());//update cart
    }

    @JavascriptInterface
    public void removeFromCart(String productid) throws JSONException {
        String cart = localPrefs.getString("CART", null);
        if (cart != null) {
            JSONArray cartArray = new JSONArray(cart);
            JSONArray cartArrayLowApi = new JSONArray();
            for (int i = 0; i < cartArray.length(); i++) {
                JSONObject product = cartArray.getJSONObject(i);
                if (Build.VERSION.SDK_INT >= 19) {
                    if (product.getString("productid").equals(productid)) {
                        cartArray.remove(i);
                    }
                } else {
                    //completeaza lista cu cele care nu statisfac conditia
                    if (!product.getString("productid").equals(productid)) {
                        cartArrayLowApi.put(product);
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 19) {
                cartArrayLowApi = cartArray;
            }
            //update cart
            addToLocalPrefs("CART", cartArrayLowApi.toString());
        }
    }

    @JavascriptInterface
    public String getCartContent() {
        return localPrefs.getString("CART", null);
    }

    @JavascriptInterface
    public void ClearCartContent() {
        removeFromLocalPrefs("CART");
    }
}
