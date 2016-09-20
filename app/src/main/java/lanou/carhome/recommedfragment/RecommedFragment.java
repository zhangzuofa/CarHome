package lanou.carhome.recommedfragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommedFragment extends BaseFragment {

    private PullToRefreshListView listView;
    private Banner banner;

    @Override
    protected int setLayout() {
        return R.layout.recommedfrag;
    }

    @Override
    protected void initDate() {
        GsonRequest<ReconmmedBean> gsonRequest =new GsonRequest<ReconmmedBean>(URLValues.URL_NEW, ReconmmedBean.class,
                new Response.Listener<ReconmmedBean>() {

                    private ArrayList<String> arr;

                    @Override
                    public void onResponse(ReconmmedBean response) {
                        RecommedAdapter adapter = new RecommedAdapter(getContext());
                       adapter.setBean(response);




                        listView.setAdapter(adapter);
                        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.luoboitem,null);
                        for (int i = 0; i <response.getResult().getFocusimg().size() ; i++) {
                            arr = new ArrayList<>();
                          arr.add(response.getResult().getFocusimg().get(i).getImgurl());
                            Log.d("RecommedFragment", "arr:" + arr);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);




    }

    @Override
    protected void initView() {
        listView = bindView(R.id.Refresh_headline);
        banner = bindView(R.id.banner_item);



    }
}
