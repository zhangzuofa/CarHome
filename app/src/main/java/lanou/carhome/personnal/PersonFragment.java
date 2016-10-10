package lanou.carhome.personnal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import lanou.carhome.R;
import lanou.carhome.baseclass.BaseFragment;
import lanou.carhome.personnal.mycollection.MyLoveActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout myloveLl;
    private LinearLayout myCheapLl;
    private LinearLayout myHistoryLl;
    private LinearLayout myDraftLl;
    private LinearLayout mySetLl;





    @Override
    protected int setLayout() {
        return R.layout.personfragment;

    }

    @Override
    protected void initDate() {


    }

    @Override
    protected void initView() {
        myloveLl = bindView(R.id.personfragmen_mylove_ll);
        myCheapLl = bindView(R.id.personnal_mysheap_ll);
        myHistoryLl = bindView(R.id.personal_fragment_history_ll);
        myDraftLl = bindView(R.id.personal_fragment_draft_ll);
        mySetLl = bindView(R.id.personal_fragment_set_ll);
        myCheapLl.setOnClickListener(this);
        myloveLl.setOnClickListener(this);
        myHistoryLl .setOnClickListener(this);
        myDraftLl.setOnClickListener(this);
        mySetLl.setOnClickListener(this);
        ImageView imgYeJian =bindView(R.id.personal_fragment_yejian);
        imgYeJian.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personfragmen_mylove_ll:
                Intent myLoveIntent = new Intent(getActivity(),MyLoveActivity.class);
                startActivity(myLoveIntent);
                getActivity().overridePendingTransition(R.anim.anim_main_right,R.anim.anim_main_toleft);

                break;
            case R.id.personnal_mysheap_ll:
                break;
            case R.id.personal_fragment_history_ll:
                break;
            case R.id.personal_fragment_draft_ll:
                break;
            case R.id.personal_fragment_set_ll:
                break;
            case R.id.personal_fragment_yejian:

                break;


        }

    }
}
