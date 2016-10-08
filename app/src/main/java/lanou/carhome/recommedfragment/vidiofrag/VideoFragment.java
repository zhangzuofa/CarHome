package lanou.carhome.recommedfragment.vidiofrag;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/10/8.
 */
public class VideoFragment extends BaseFragment {

    private PullToRefreshListView listView;

    @Override
    protected int setLayout() {
        return R.layout.viedeofragment;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.pullRefresh_videoFragment);


    }

    @Override
    protected void initDate() {
        initRequestInternet();

    }

    private void initRequestInternet() {
        GsonRequest<VideoBean> gsonRequest = new GsonRequest<VideoBean>(URLValues.URL_VIEDIO, VideoBean.class,
                new Response.Listener<VideoBean>() {
                    @Override
                    public void onResponse(VideoBean response) {
                        Log.d("VideoFragment", "response:" + response);
                        VideoAdapter videoAdapter =  new VideoAdapter(getContext());
                        videoAdapter.setBean(response);
                        listView.setAdapter(videoAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }
}
