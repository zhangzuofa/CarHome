package lanou.carhome.recommedfragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.recommedfragment.moreactivity.EventBean;
import lanou.carhome.recommedfragment.moreactivity.MoreActivity;

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
    private ImageView img;
    private ArrayList<String> title;

    @Override
    protected int setLayout() {
        return R.layout.recommedfrag;
    }

    @Override
    protected void initDate() {
//
        title = new ArrayList<>();

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
        AllRecommedAdapter allAdapter = new AllRecommedAdapter(getChildFragmentManager());
        //   allAdapter.setFragments(fragments);
        allAdapter.setTitle(title);
        vp.setAdapter(allAdapter);
        tb.setupWithViewPager(vp);




    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
//        listView = bindView(R.id.Refresh_headline);
//        view1 = LayoutInflater.from(getContext()).inflate(R.layout.luoboitem,null);
//        banner = (Banner) view1.findViewById(R.id.banner_item);
        search_img = bindView(R.id.recommed_search);
        search_img.setOnClickListener(this);
        tb = bindView(R.id.allrecommed_tb);
        vp = bindView(R.id.allrecommed_vp);
        img = bindView(R.id.recommed_fragment_more_img);
        img.setOnClickListener(this);
        ImageView musicImg = bindView(R.id.recommed_music_img);
        musicImg.setOnClickListener(this);



    }


    @Subscribe
    public void setEventBean(EventBean eventBean){

        if(eventBean.getContent().equals(title.get(eventBean.getNum()+1))){


            vp.setCurrentItem(eventBean.getNum()+1);
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommed_search:
                Intent intent = new Intent(getContext(),SearchKeyActivity.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);
                break;
            case R.id.recommed_fragment_more_img:
             Intent intent1  = new Intent(getContext(),MoreActivity.class);
                startActivity(intent1);

                break;
            case R.id.recommed_music_img:
                Intent musicIntent = new Intent(getContext(),MusicActivity.class);
                startActivity(musicIntent);
                break;
        }

    }
}
