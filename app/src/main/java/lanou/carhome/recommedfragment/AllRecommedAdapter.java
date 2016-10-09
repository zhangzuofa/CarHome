package lanou.carhome.recommedfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import lanou.carhome.recommedfragment.market.MarketFragement;
import lanou.carhome.recommedfragment.quickfrag.QuickNewsFragment;
import lanou.carhome.recommedfragment.sayfrag.SayFragment;
import lanou.carhome.recommedfragment.smallrecommedfrag.SmallRecommedFrag;
import lanou.carhome.recommedfragment.unihubfrag.UnihubFrafment;
import lanou.carhome.recommedfragment.vidiofrag.VideoFragment;
import lanou.carhome.volley.URLValues;

/**
 * Created by dllo on 16/9/30.
 */
public class AllRecommedAdapter  extends FragmentPagerAdapter{

   // ArrayList<Fragment> fragments ;
    ArrayList<String>title;
 String []url = {URLValues.URL_NEW,URLValues.URL_YC,URLValues.URL_SAY,URLValues.URL_VIEDIO,URLValues.URL_QUICK,
 URLValues.URL_MARKET,URLValues.URL_NEWS,URLValues.URL_TESTCAR,URLValues.URL_SHOP,URLValues.URL_USECAR,URLValues.URL_TECHNOLOGY,
 URLValues.URL_CULTURE,URLValues.URL_CHANGE};
    private Fragment[] fragments1;


    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

//    public void setFragments(ArrayList<Fragment> fragments) {
//        this.fragments = fragments;
//
//    }

    public AllRecommedAdapter(FragmentManager fm) {
        super(fm);
        fragments1 = new Fragment[url.length];
    }

    @Override
    public Fragment getItem(int position) {

        if (position==0){
            fragments1[0] = new SmallRecommedFrag();
        }else if (position==1){
            fragments1[1] = new UnihubFrafment();
        }else if (position==2){
            fragments1[2] = new SayFragment();
        }else if (position==3){
            fragments1[3] = new VideoFragment();
        }else if (position==4){
            fragments1[4] = new QuickNewsFragment();
        }else {
            fragments1[position] = MarketFragement.newInstance(url[position]);
        }
        return fragments1[position];



      //  return fragments.get(position);
    //  return MarketFragement.newInstance(url[position]);
    }

    @Override
    public int getCount() {

        return fragments1 == null ? 0 : fragments1.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
