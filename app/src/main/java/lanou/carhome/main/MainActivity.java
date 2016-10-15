package lanou.carhome.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

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
    private RecommedFragment recommedFragment;
    private ForumFragment forumFragment;
    private FIndCarFragment fIndCarFragment;
    private FindFragment findFragment;
    private PersonFragment personFragment;
    private FragmentManager manager;
    public static final int FRAGMENT_RECOMMED=0;
    public static final int FRAGMENT_FORUM=1;
    public static final int FRAGMENT_FINDCAR=2;
    public static final int FRAGMENT_FIND = 3;
    public static final int FRAGMENT_PERSON = 4;

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
//        recommedFragment = new RecommedFragment();
//        forumFragment = new ForumFragment();
//        fIndCarFragment = new FIndCarFragment();
//        findFragment = new FindFragment();
//        personFragment = new PersonFragment();
        manager = getSupportFragmentManager();






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
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction =manager.beginTransaction();
//        transaction.replace(R.id.main_framelayout,new RecommedFragment());
//        transaction.commit();
        showFragment(FRAGMENT_RECOMMED);



    }
    

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.main_radiobtn_recommed:
                showFragment(FRAGMENT_RECOMMED);

//             if (recommedFragment !=null ){
//                 transaction.show(recommedFragment);
//             }
//               // transaction.replace(R.id.main_framelayout,recommedFragment);

                break;
            case R.id.main_radiobtn_forum:
                showFragment(FRAGMENT_FORUM);
//                if (forumFragment != null){
//                    transaction.add(R.id.main_framelayout,forumFragment);
//                }
//               // transaction.replace(R.id.main_framelayout,forumFragment);
                break;
            case R.id.main_radiobtn_findcar:
                showFragment(FRAGMENT_FINDCAR);
//                if (fIndCarFragment!= null){
//                    transaction.add(R.id.main_framelayout,fIndCarFragment);
//                }
//            //    transaction.replace(R.id.main_framelayout,fIndCarFragment);
                break;
            case R.id.main_radiobtn_find:
                showFragment(FRAGMENT_FIND);
//                if (findFragment!= null){
//                    transaction.add(R.id.main_framelayout,findFragment);
//                }
           //     transaction.replace(R.id.main_framelayout,findFragment);
                break;
            case R.id.main_radiobtn_personnal:
                showFragment(FRAGMENT_PERSON);
//                if (personFragment!=null){
//                    transaction.add(R.id.main_framelayout,personFragment);
//                }
              //  transaction.replace(R.id.main_framelayout,personFragment);
                break;

            default:
                break;

        }
      //  transaction.commit();




    }
    public void showFragment(int index){
        FragmentTransaction transaction =manager.beginTransaction();
        hideFragment(transaction);
        switch (index){

            case FRAGMENT_RECOMMED:

                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (recommedFragment==null){
                    recommedFragment=new RecommedFragment();
                    transaction.add(R.id.main_framelayout,recommedFragment);
                }else {
                    transaction.show(recommedFragment);
                }

                break;
            case FRAGMENT_FORUM:


                if (forumFragment==null){
                    forumFragment=new ForumFragment();
                    transaction.add(R.id.main_framelayout,forumFragment);
                }else {
                    transaction.show(forumFragment);
                }

                break;
            case FRAGMENT_FINDCAR:
                if (fIndCarFragment == null){
                    fIndCarFragment = new FIndCarFragment();
                    transaction.add(R.id.main_framelayout,fIndCarFragment);
                }else {
                    transaction.show(fIndCarFragment);

                }
                break;
            case FRAGMENT_FIND:
                if (findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.main_framelayout,findFragment);
                }else {
                    transaction.show(findFragment);
                }
                break;
            case FRAGMENT_PERSON:
                if (personFragment == null){
                    personFragment = new PersonFragment();
                    transaction.add(R.id.main_framelayout,personFragment);

                }else {
                    transaction.show(personFragment);
                }
                break;
        }

        transaction.commit();


    }

    private void hideFragment(FragmentTransaction transaction) {
        //如果不为空，就先隐藏起来
        if (recommedFragment!=null){
            transaction.hide(recommedFragment);
        }
        if(forumFragment!=null) {
            transaction.hide(forumFragment);
        }
        if (fIndCarFragment != null){
            transaction.hide(fIndCarFragment);
        }
        if (findFragment != null){
            transaction.hide(findFragment);
        }
        if (personFragment != null){
            transaction.hide(personFragment);
        }
    }


    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }


}
