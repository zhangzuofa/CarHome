package lanou.carhome.recommedfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
    private View view1;
    private ListView listView1;
    private ArrayList<String> arr;

    @Override
    protected int setLayout() {
        return R.layout.recommedfrag;
    }

    @Override
    protected void initDate() {
        listView.setMode(PullToRefreshListView.Mode.BOTH);


        initSend();

        innitReFresh();


    }

    @Override
    protected void initView() {
        listView = bindView(R.id.Refresh_headline);
        view1 = LayoutInflater.from(getContext()).inflate(R.layout.luoboitem,null);
        banner = (Banner) view1.findViewById(R.id.banner_item);







    }
    public void initSend(){
        GsonRequest<ReconmmedBean> gsonRequest =new GsonRequest<ReconmmedBean>(URLValues.URL_NEW, ReconmmedBean.class,
                new Response.Listener<ReconmmedBean>() {



                    @Override
                    public void onResponse(ReconmmedBean response) {
                        RecommedAdapter adapter = new RecommedAdapter(getContext());
                        adapter.setBean(response);

                        listView.onRefreshComplete();

                        listView.setAdapter(adapter);

                        arr = new ArrayList<>();
                        for (int i = 0; i <response.getResult().getFocusimg().size() ; i++) {

                            arr.add(response.getResult().getFocusimg().get(i).getImgurl());

                        }


                        listView1 = listView.getRefreshableView();
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.RIGHT);
                        banner.setImages(arr);

                        listView1.addHeaderView(view1);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);

    }
    public void innitReFresh(){

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

           innitAddhead();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    public  void innitAddhead(){
        GsonRequest<ReconmmedBean> gsonRequest =new GsonRequest<ReconmmedBean>(URLValues.URL_NEW, ReconmmedBean.class,
                new Response.Listener<ReconmmedBean>() {



                    @Override
                    public void onResponse(ReconmmedBean response) {
                        RecommedAdapter adapter = new RecommedAdapter(getContext());
                        adapter.setBean(response);

                        listView.onRefreshComplete();
                        Toast.makeText(getContext(), "刷新了10000条", Toast.LENGTH_SHORT).show();

                        listView.setAdapter(adapter);





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);




    }
}
