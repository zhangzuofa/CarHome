package lanou.carhome.recommedfragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.recommedfragment.smallrecommedfrag.SmallRecommedFrag;
import lanou.carhome.recommedfragment.usecar.UseCarFrag;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommedFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView listView;
    private Banner banner;
    private View view1;
    private ListView listView1;
    private ArrayList<String> arr;
    private ImageView search_img;
    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.recommedfrag;
    }

    @Override
    protected void initDate() {
//        listView.setMode(PullToRefreshListView.Mode.BOTH);
//        initSend();
//
//        innitReFresh();




    }

    @Override
    protected void initView() {
//        listView = bindView(R.id.Refresh_headline);
//        view1 = LayoutInflater.from(getContext()).inflate(R.layout.luoboitem,null);
//        banner = (Banner) view1.findViewById(R.id.banner_item);
        search_img = bindView(R.id.recommed_search);
        search_img.setOnClickListener(this);
        tb = bindView(R.id.allrecommed_tb);
        vp = bindView(R.id.allrecommed_vp);
        ArrayList<Fragment>fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();

        title.add("推荐");
        title.add("优创+");
        title.add("说客");
        title.add("视频");
        title.add("快报");
        title.add("行情");
        title.add("新闻");
        title.add("评测");
        title.add("导购");
        title.add("用车");
        title.add("技术");
        title.add("文化");
        title.add("改装");

        fragments.add(new SmallRecommedFrag());
        for (int i = 0; i < 11; i++) {

            fragments.add(new UseCarFrag());

        }
        AllRecommedAdapter allAdapter = new AllRecommedAdapter(getChildFragmentManager());
        allAdapter.setFragments(fragments);
        allAdapter.setTitle(title);
        vp.setAdapter(allAdapter);
        tb.setupWithViewPager(vp);






    }
//    public void initSend(){
//        GsonRequest<ReconmmedBean> gsonRequest =new GsonRequest<ReconmmedBean>(URLValues.URL_NEW, ReconmmedBean.class,
//                new Response.Listener<ReconmmedBean>() {
//
//
//
//                    @Override
//                    public void onResponse(ReconmmedBean response) {
//                        RecommedAdapter adapter = new RecommedAdapter(getContext());
//                        adapter.setBean(response);
//
//                        listView.onRefreshComplete();
//
//                        listView.setAdapter(adapter);
//
//                        arr = new ArrayList<>();
//                        for (int i = 0; i <response.getResult().getFocusimg().size() ; i++) {
//
//                            arr.add(response.getResult().getFocusimg().get(i).getImgurl());
//
//                        }
//
//
//                        listView1 = listView.getRefreshableView();
//                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//                        banner.setIndicatorGravity(BannerConfig.RIGHT);
//                        banner.setImages(arr);
//
//                        listView1.addHeaderView(view1);
//
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
//            }
//        });
//        VollaySingleton.getInstance().addRequest(gsonRequest);
//
//    }
//    public void innitReFresh(){
//
//        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//
//           innitAddhead();
//
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//
//            }
//        });
//    }

//    public  void innitAddhead(){
//        GsonRequest<ReconmmedBean> gsonRequest =new GsonRequest<ReconmmedBean>(URLValues.URL_NEW, ReconmmedBean.class,
//                new Response.Listener<ReconmmedBean>() {
//
//
//
//                    @Override
//                    public void onResponse(ReconmmedBean response) {
//                        RecommedAdapter adapter = new RecommedAdapter(getContext());
//                        adapter.setBean(response);
//
//                        listView.onRefreshComplete();
//                        Toast.makeText(getContext(), "刷新了10000条", Toast.LENGTH_SHORT).show();
//
//                        listView.setAdapter(adapter);
//
//
//
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//
//            }
//        });
//        VollaySingleton.getInstance().addRequest(gsonRequest);
//



  //  }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommed_search:
                Intent intent = new Intent(getContext(),SearchKeyActivity.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
        }

    }
}
