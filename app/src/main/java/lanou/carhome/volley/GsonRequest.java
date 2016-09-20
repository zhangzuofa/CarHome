package lanou.carhome.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/9/20.
 */
public class GsonRequest<T>extends Request<T> {
    private final Response.Listener<T> mListener;
    private Class<T> mTClass;


    public GsonRequest(int method,
                       String url,
                       Class<T> clazz,//为了Gson解析时使用
                       Response.Listener<T> mListener,
                       Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mTClass = clazz;
    }

    public GsonRequest(String url,
                       Class<T> clazz,//为了Gson解析时使用
                       Response.Listener<T> mListener,
                       Response.ErrorListener listener){
        this(Method.GET,url,clazz,mListener,listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        Gson gson = new Gson();

        return Response.success(gson.fromJson(parsed,mTClass)
                , HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
