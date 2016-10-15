package lanou.carhome.findcarfragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.findcarfragment.newcarfragment.NewCarFragment;
import lanou.carhome.findcarfragment.oldcarfragment.OldCarFragment;
import lanou.carhome.recommedfragment.SearchKeyActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class FIndCarFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tb;
    private ViewPager vp;
    private ImageView searchImg;

    @Override
    protected int setLayout() {
        return R.layout.findcarfrag;
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        tb = bindView(R.id.findCarFragmentTb);
        vp = bindView(R.id.findCarFragmentVp);
        FindCarAdapter adapter = new FindCarAdapter(getChildFragmentManager());
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewCarFragment());
        fragments.add(new OldCarFragment());
        adapter.setFragments(fragments);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("新车");
        titles.add("二手车");
        adapter.setTitle(titles);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
        vp.setOffscreenPageLimit(0);
        searchImg = bindView(R.id.findcar_fragemnt_search_img);
        searchImg.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findcar_fragemnt_search_img:
                Intent intent = new Intent(getContext(), SearchKeyActivity.class);
                startActivity(intent);
                break;
        }

    }
}
