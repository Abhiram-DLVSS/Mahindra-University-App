package mu;

import android.os.AsyncTask;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetTask extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(params[0]);

            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "none";
    }
}
