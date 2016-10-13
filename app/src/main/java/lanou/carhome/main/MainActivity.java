package lanou.carhome.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.find.FindFragment;
import lanou.carhome.findcarfragment.FIndCarFragment;
import lanou.carhome.findcarfragment.newcarfragment.NewCarFragment;
import lanou.carhome.forumfragment.ForumFragment;
import lanou.carhome.personnal.PersonFragment;
import lanou.carhome.recommedfragment.RecommedFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    NewCarFragment   newCarFragment = new NewCarFragment();
    private static final String TAG = MainActivity.class.getSimpleName();

    private long clickTime = 0; //记录第一次点击的时间

    @Override
    protected int setLayout() {

        return R.layout.activity_main;
    }



    @Override
    protected void initView() {
        RadioButton radioBtnRecom = bindView(R.id.main_radiobtn_recommed);
        RadioButton radioBtnForum = bindView(R.id.main_radiobtn_forum);
        RadioButton radioBtnFindCar = bindView(R.id.main_radiobtn_findcar);
        RadioButton radioBtnFind =bindView(R.id.main_radiobtn_find);
        RadioButton radioBtnPerson = bindView(R.id.main_radiobtn_personnal);
        radioBtnRecom.setOnClickListener(this);
        radioBtnForum.setOnClickListener(this);
        radioBtnFindCar.setOnClickListener(this);
        radioBtnFind.setOnClickListener(this);
        radioBtnPerson.setOnClickListener(this);





    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出汽车之家",
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
          //  Log.e(TAG, "exit application");
            this.finish();
//     System.exit(0);
        }
    }

    @Override
    protected void initDate() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_framelayout,new RecommedFragment());
        transaction.commit();



    }
    

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        switch (v.getId()){
            case R.id.main_radiobtn_recommed:
                transaction.replace(R.id.main_framelayout,new RecommedFragment());

                break;
            case R.id.main_radiobtn_forum:
                transaction.replace(R.id.main_framelayout,new ForumFragment());
                break;
            case R.id.main_radiobtn_findcar:
                transaction.replace(R.id.main_framelayout,new FIndCarFragment());
                break;
            case R.id.main_radiobtn_find:
                transaction.replace(R.id.main_framelayout,new FindFragment());
                break;
            case R.id.main_radiobtn_personnal:
                transaction.replace(R.id.main_framelayout,new PersonFragment());
                break;

            default:
                break;

        }
        transaction.commit();



    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
////        if(newCarFragment instanceof IOnFocusListenable) {
////            ((IOnFocusListenable) newCarFragment).onWindowFocusChanged(hasFocus);
////        }
//    }
}
