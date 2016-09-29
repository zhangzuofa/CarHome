package lanou.carhome.find.everyoneadpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;
import lanou.carhome.find.FindBean;

/**
 * Created by dllo on 16/9/25.
 */
public class ServeAdapter extends RecyclerView.Adapter<ServeAdapter.ViewHolder> {
    Context mContext;
    FindBean bean;
    private int num;

    public void setNum(int num) {
        this.num = num;
    }

    public void setBean(FindBean bean) {
        this.bean = bean;
    }

    public ServeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_serve,null);
      ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(bean.getResult().getCardlist().get(num).getData().get(position).getImageurl()).into(holder.img);





    }

    @Override
    public int getItemCount() {



            return bean == null? 0:bean.getResult().getCardlist().get(num).getData().size();





    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_serve_img);

        }
    }
}
