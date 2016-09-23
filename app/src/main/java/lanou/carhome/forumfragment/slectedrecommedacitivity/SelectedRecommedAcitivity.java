package lanou.carhome.forumfragment.slectedrecommedacitivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseActivity;
import lanou.carhome.forumfragment.threetyeight.SelectedThreeEightActivity;

/**
 * Created by dllo on 16/9/22.
 */
public class SelectedRecommedAcitivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgCha;
    private ListView lv;

    @Override
    protected int setLayout() {
        return R.layout.slectedrecommedactivity;
    }

    @Override
    protected void initView() {
        imgCha = bindView(R.id.slectedReAc_img);
        imgCha.setOnClickListener(this);
        lv = bindView(R.id.selectRelv);



    }

    @Override
    protected void initDate() {

      String[] array = getResources().getStringArray(R.array.alls);
       SelectedRecAdapter adapter = new SelectedRecAdapter(this);
        adapter.setArrayList(array);
        lv.setAdapter(adapter);
        inintLvLisenner();



    }

    private void inintLvLisenner() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(SelectedRecommedAcitivity.this, SelectedThreeEightActivity.class);
                intent.putExtra("哈哈",position);

                startActivity(intent);
                finish();


            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.slectedReAc_img:
                super.onBackPressed();

                break;
        }
    }
}
