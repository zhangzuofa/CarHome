package lanou.carhome.dbtools;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lanou.carhome.findcarfragment.newcarfragment.Person;
import lanou.carhome.main.MyApp;

/**
 * Created by dllo on 16/10/10.
 */
public class DBTools {
    private  static DBTool sDBTool;
    private LiteOrm mLiteOrm;
    private ExecutorService threedPool;
    private Handler mHandler;//用来做线程切换;


    private DBTool() {
        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getmContext(),"dataBase.db");

        threedPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+ 1);
        // 在构建Handler 时候参数传  Looper.getMainLooper() 这样可以保证该 HAndler 一定属于主线程
        mHandler = new Handler(Looper.getMainLooper());

        //   Message message = mHandler.obtainMessage();

    }
    public static DBTool getintance(){
        if (sDBTool == null){
            synchronized (DBTool.class){
                if (sDBTool == null){
                    sDBTool = new DBTool();

                }
            }
        }
        return sDBTool;
    }
    //插入数据
    public void  insertPerdson (final Person person){

        threedPool.execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(person);
            }
        });

    }
    public void insertPerson(List<Person> persons){
        mLiteOrm.insert(persons);
    }

    public void getAllPerson(final QueryListener<Person> queryListener){



        threedPool.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Person> persons =  mLiteOrm.query(Person.class);
                //handler 的post 方法可以 发送到主线程去执行
                mHandler.post(new HanlerRunnable<Person>(queryListener,persons));


            }
        });
    }
    //查询完成后的回调接口
    class  HanlerRunnable<T> implements  Runnable{
        private List<T>mTList;
        private QueryListener<T>mTQueryListenner;

        public HanlerRunnable(QueryListener<T> mTQueryListenner, List<T> mTList) {
            this.mTQueryListenner = mTQueryListenner;
            this.mTList = mTList;
        }

        @Override
        public void run() {
            mTQueryListenner.onQuery(mTList);


        }
    }

    public  interface QueryListener<T>{
        // 当查询完成后 将查询点我数据作为tata, 返回给activity
        void onQuery(List<T> persons);

    }


}
