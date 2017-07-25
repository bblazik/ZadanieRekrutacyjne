package bb.carddeck.logic;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by barte_000 on 13.07.2017.
 */

public class InternetState {
    Context mContext;

    public InternetState(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        Boolean state = netInfo != null && netInfo.isConnectedOrConnecting();
        if(!state){
            Toast.makeText(mContext, "Please enable you internet connection", Toast.LENGTH_LONG).show();
        }
        return state;
    }
}
