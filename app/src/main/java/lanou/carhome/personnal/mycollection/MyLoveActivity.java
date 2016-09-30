package lanou.carhome.personnal.mycollection;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;

/**
 * Created by dllo on 16/9/30.
 */
public class MyLoveActivity extends BaseActivity {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.personnal_mylove_activity;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.myLoveTB);
        vp = bindView(R.id.myLoveVP);





    }

    @Override
    protected void initDate() {
        ArrayList<Fragment>fragments = new ArrayList<>();
        ArrayList<String>title = new ArrayList<>();
        title.add("车系");
        title.add("车型");
        title.add("口碑");
        title.add("文章");
        title.add("优创+");
        title.add("视频");
        title.add("说客");
        title.add("论坛");
        title.add("帖子");

        for (int i = 0; i < 8; i++) {
            fragments.add(new MyLoveFragment());

        }
   MyCollectionAdapter myCollectionAdapter = new MyCollectionAdapter(getSupportFragmentManager());
       myCollectionAdapter.setFragments(fragments);
        myCollectionAdapter.setTitles(title);
        vp.setAdapter(myCollectionAdapter);
        tb.setupWithViewPager(vp);
    }
}
