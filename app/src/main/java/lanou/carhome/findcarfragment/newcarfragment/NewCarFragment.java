package lanou.carhome.findcarfragment.newcarfragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.findcarfragment.newcarfragment.hotadapter.HotBrandAdapter;
import lanou.carhome.findcarfragment.newcarfragment.hotadapter.HotBrandBean;
import lanou.carhome.findcarfragment.newcarfragment.hotadapter.MainCarAdapter;
import lanou.carhome.findcarfragment.newcarfragment.hotadapter.MainCarBean;
import lanou.carhome.volley.GsonRequest;
import lanou.carhome.volley.URLValues;
import lanou.carhome.volley.VollaySingleton;

/**
 * Created by dllo on 16/9/27.
 */


public class NewCarFragment extends BaseFragment{
    private HashMap<String, Integer> selector;// 存放含有索引字母的位置
    private LinearLayout layoutIndex;
    private ListView listView;
    private TextView tv_show;
    private ListViewAdapter adapter;
    private String[] indexStr = { "选","热","主","史", "A", "B", "C", "D", "F", "G", "H",
             "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "W", "X", "Y", "Z" };
    private List<Person> persons = null;
    private List<Person> newPersons = new ArrayList<Person>();
    private int height;// 字体高度
    private boolean flag = false;
    private RecyclerView hotBrandRecyclerV;
    private RecyclerView mainCarRecyclerV;
    private ArrayList<Person> carPic;

    @Override
    protected int setLayout() {
        return R.layout.newcar_fragment;
    }

    @Override
    protected void initView() {

        layoutIndex = bindView(R.id.layout);
        layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
        listView = bindView(R.id.listView);
     //   tv_show = (TextView) findViewById(R.id.tv);
        tv_show = bindView(R.id.tv);
        tv_show.setVisibility(View.GONE);
        View viewTop = LayoutInflater.from(getContext()).inflate(R.layout.newcar_topview,null);
        listView.addHeaderView(viewTop);
        hotBrandRecyclerV = bindView(R.id.car_top_hotbrand_rv,viewTop);
        mainCarRecyclerV = bindView(R.id.findnewcar_topview_main_car,viewTop);

        persons = new ArrayList<Person>();
        carPic = new ArrayList<>();
        requestInternetCarBrandSort();


    }

    private void requestInternetCarBrandSort() {
        GsonRequest<NewCarBean> gsonRequest = new GsonRequest<NewCarBean>(URLValues.NEWCAR_BRAND_URL, NewCarBean.class, new Response.Listener<NewCarBean>() {

            private Person pCar;
            private Person p1;

            @Override
            public void onResponse(NewCarBean response) {


                for (int i = 0; i <response.getResult().getBrandlist().size() ; i++) {


                    for (int j = 0; j < response.getResult().getBrandlist().get(i).getList().size(); j++) {
                        p1 = new Person(response.getResult().getBrandlist().get(i).getList().get(j).getName());

//                        pCar = new Person(response.getResult().getBrandlist().get(i).getList().get(j).getImgurl());
                        p1.setPic(response.getResult().getBrandlist().get(i).getList().get(j).getImgurl());
                        persons.add(p1);

                    }
                }
                String[] allNames = sortIndex(persons);

                sortList(allNames);

                selector = new HashMap<String, Integer>();
                for (int j = 0; j < indexStr.length; j++) {// 循环字母表，找出newPersons中对应字母的位置
                    for (int i = 0; i < newPersons.size(); i++) {
                        if (newPersons.get(i).getName().equals(indexStr[j])) {
                            selector.put(indexStr[j], i);
                        }
                    }

                }

                adapter = new ListViewAdapter(getContext(), newPersons);

//                adapter.setCars(carPic);
                //   Log.d("MainActivity", "newPersons:" + newPersons);
                listView.setAdapter(adapter);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VollaySingleton.getInstance().addRequest(gsonRequest);

    }


    private void sortList(String[] allNames) {
        for (int i = 0; i < allNames.length; i++) {
            if (allNames[i].length() != 1) {
                for (int j = 0; j < persons.size(); j++) {
                    if (allNames[i].equals(persons.get(j).getPinYinName())) {
                        Person p = new Person(persons.get(j).getName(), persons
                                .get(j).getPinYinName());
                        p.setPic(persons.get(j).getPic());
                        newPersons.add(p);

                    }
                }
            } else {
                newPersons.add(new Person(allNames[i]));

            }
        }
    }



    /**
     * 获取排序后的新数据
     *
     * @param persons
     * @return
     */
    public String[] sortIndex(List<Person> persons) {
        TreeSet<String> set = new TreeSet<String>();
        // 获取初始化数据源中的首字母，添加到set中
        for (Person person : persons) {
            set.add(StringHelper.getPinYinHeadChar(person.getName()).substring(
                    0, 1));
        }
        // 新数组的长度为原数据加上set的大小
        String[] names = new String[persons.size() + set.size()];
        int i = 0;
        for (String string : set) {
            names[i] = string;
            i++;
        }
        String[] pinYinNames = new String[persons.size()];
        for (int j = 0; j < persons.size(); j++) {
            persons.get(j).setPinYinName(
                    StringHelper
                            .getPingYin(persons.get(j).getName().toString()));
            pinYinNames[j] = StringHelper.getPingYin(persons.get(j).getName()
                    .toString());
        }
        // 将原数据拷贝到新数据中
        System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
        // 自动按照首字母排序
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        return names;
    }

    public void getIndexView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, height);
        for (int i = 0; i < indexStr.length; i++) {
            final TextView tv = new TextView(getContext());
            tv.setLayoutParams(params);
            tv.setText(indexStr[i]);
            tv.setPadding(10, 0, 10, 0);
            layoutIndex.addView(tv);
            layoutIndex.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event)

                {
                    float y = event.getY();
                    int index = (int) (y / height);
                    if (index > -1 && index < indexStr.length) {// 防止越界
                        String key = indexStr[index];
                        if (selector.containsKey(key)) {
                            int pos = selector.get(key);
                            if (listView.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
                                listView.setSelectionFromTop(
                                        pos + listView.getHeaderViewsCount(), 0);
                            } else {
                                listView.setSelectionFromTop(pos, 0);// 滑动到第一项
                            }
                            tv_show.setVisibility(View.VISIBLE);
                            tv_show.setText(indexStr[index]);
                        }
                    }
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#00ffffff"));
                            break;

                        case MotionEvent.ACTION_MOVE:

                            break;
                        case MotionEvent.ACTION_UP:
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#00ffffff"));
                            tv_show.setVisibility(View.GONE);
                            break;
                    }
                    return true;
                }
            });
        }
    }



    @Override
    protected void initDate() {


        ViewTreeObserver observer = layoutIndex.getViewTreeObserver();


        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!flag) {// 这里为什么要设置个flag进行标记，我这里不先告诉你们，请读者研究，因为这对你们以后的开发有好处
                    height = layoutIndex.getMeasuredHeight() / indexStr.length;
                    getIndexView();
                    flag = true;
                }
                return true;
            }
        });

        requestInternetHotBran();
        requestInterntMainCar();


    }

    private void requestInterntMainCar() {
        GsonRequest<MainCarBean> gsonRequest = new GsonRequest<MainCarBean>(URLValues.MAINCAR_URL, MainCarBean.class, new Response.Listener<MainCarBean>() {
            @Override
            public void onResponse(MainCarBean response) {
                MainCarAdapter mainCarAdapter = new MainCarAdapter(getContext());
                mainCarAdapter.setBean(response);
                mainCarRecyclerV.setAdapter(mainCarAdapter);
                LinearLayoutManager mainCarManager = new LinearLayoutManager(getContext());
                mainCarRecyclerV.setLayoutManager(mainCarManager);
                mainCarManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);
    }

    private void requestInternetHotBran() {
        GsonRequest<HotBrandBean> gsonRequest = new GsonRequest<HotBrandBean>(URLValues.HOTBRAND_URL, HotBrandBean.class, new Response.Listener<HotBrandBean>() {
            @Override
            public void onResponse(HotBrandBean response) {
                HotBrandAdapter hotBrandAdapter = new HotBrandAdapter(getContext());

                hotBrandAdapter.setBean(response);
                hotBrandRecyclerV.setAdapter(hotBrandAdapter);
                GridLayoutManager hotBrandManager = new GridLayoutManager(getContext(),5);
                hotBrandRecyclerV.setLayoutManager(hotBrandManager);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VollaySingleton.getInstance().addRequest(gsonRequest);

    }


}
