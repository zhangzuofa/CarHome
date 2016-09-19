package lanou.carhome.forumfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.forumfragment.formed.ForumedFragment;
import lanou.carhome.forumfragment.selected.SelectedFrag;

/**
 * Created by dllo on 16/9/19.
 */
public class ForumFragment extends BaseFragment {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.forumfrag;
    }
    @Override
    protected void initView() {

        tb = bindView(R.id.forum_tb);
        vp = bindView(R.id.forum_vp);

    }

    @Override
    protected void initDate() {
        ForumFragAdapter adapter = new ForumFragAdapter(getFragmentManager());
        ArrayList<Fragment>fragments = new ArrayList<>();
        ArrayList<String>titlce = new ArrayList<>();
        titlce.add("精选");
        titlce.add("论坛");
        fragments.add(new ForumedFragment());
        fragments.add(new SelectedFrag());
        adapter.setFragments(fragments);
        adapter.setTitlce(titlce);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);






    }


}
