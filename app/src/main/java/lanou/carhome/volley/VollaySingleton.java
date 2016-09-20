package lanou.carhome.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import lanou.carhome.main.MyApp;

/**
 * Created by dllo on 16/9/20.
 */
public class VollaySingleton {
    private static VollaySingleton mVollaySingleton;
    private RequestQueue mRequestQueue;

    private VollaySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());

    }
    public static VollaySingleton getInstance(){
        if (mVollaySingleton == null){
            synchronized (VollaySingleton.class){
                if (mVollaySingleton == null){
                    mVollaySingleton = new VollaySingleton();
                }
            }

        }
        return mVollaySingleton;
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
    public void addRequest(Request request){
        mRequestQueue.add(request);
    }
}
