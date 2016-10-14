package lanou.carhome.recommedfragment.smallrecommedfrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;

/**
 * Created by dllo on 16/10/8.
 */
public class SmallRecommedAdapter  extends RecyclerView.Adapter{
    private Context mContext;
    private ReconmmedBean bean;
    OnClickLisenerRecycleView onClickLisenerRecycleView;

    public void setOnClickLisenerRecycleView(OnClickLisenerRecycleView onClickLisenerRecycleView) {
        this.onClickLisenerRecycleView = onClickLisenerRecycleView;
    }

    public void setBean(ReconmmedBean bean) {
        this.bean = bean;
    }

    public SmallRecommedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        return bean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 6){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_threepicviewholder,null);
            ThreeViewHolder threeViewHolder = new ThreeViewHolder(view);
            return threeViewHolder;

        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommed,null);
            OneViewHolder oneViewHolder= new OneViewHolder(view);
            return oneViewHolder;

        }
  //      return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type == 6){
            ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
            threeViewHolder.titleTv.setText(bean.getResult().getNewslist().get(position).getTitle());
            threeViewHolder.timeTv.setText(bean.getResult().getNewslist().get(position).getTime());
            threeViewHolder.picNumTv.setText("" + bean.getResult().getNewslist().get(position).getReplycount() + "图片");
             String str =bean.getResult().getNewslist().get(position).getIndexdetail();
             String []str1 = str.split("㊣");
             String str2 = str1[2];
             String[] strWz = str2.split(",");
            Picasso.with(mContext).load(strWz[0]).into(threeViewHolder.img1);
            Picasso.with(mContext).load(strWz[1]).into(threeViewHolder.img2);
            Picasso.with(mContext).load(strWz[2]).into(threeViewHolder.img3);

        }else {
            OneViewHolder oneViewHolder = (OneViewHolder) holder;
            oneViewHolder.tv_titcle.setText(bean.getResult().getNewslist().get(position).getTitle());
        if (bean.getResult().getNewslist().get(position).getMediatype() == 3){
            oneViewHolder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "播放");
        }else if (bean.getResult().getNewslist().get(position).getMediatype() == 6){
            oneViewHolder.tv_comment.setText( "图说"+ " " + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");

        }else {
            oneViewHolder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");
        }

            oneViewHolder.tv_time.setText(bean.getResult().getNewslist().get(position).getTime());

        if (!bean.getResult().getNewslist().get(position).getSmallpic().isEmpty()){

            Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(oneViewHolder.img);
        }
        oneViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisenerRecycleView.onClick(position,holder);
            }
        });

        }

    }

    @Override
    public int getItemCount() {
          return bean == null ? 0 :bean.getResult().getNewslist().size();
    }
    class OneViewHolder extends RecyclerView.ViewHolder{
        TextView tv_titcle;
        TextView tv_comment;
        TextView tv_time;
        ImageView img;
        LinearLayout ll;

        public OneViewHolder(View itemView) {
            super(itemView);
            tv_titcle = (TextView) itemView.findViewById(R.id.recommed_item_title_tv);
            tv_comment = (TextView) itemView.findViewById(R.id.recommed_item_pinglun_tv);
            tv_time = (TextView) itemView.findViewById(R.id.recommed_item_time_tv);
            img = (ImageView) itemView.findViewById(R.id.recommed_item_pic_img);
            ll = (LinearLayout) itemView.findViewById(R.id.samll_fragment_item_ll);


        }
    }
    class ThreeViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv;
        TextView picNumTv;
        TextView timeTv;
        ImageView img1;
        ImageView img2;
        ImageView img3;


        public ThreeViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_three_title_tv);
            timeTv = (TextView) itemView.findViewById(R.id.item_three_tv_time);
            picNumTv = (TextView) itemView.findViewById(R.id.item_three_tv_num);
            img1 = (ImageView) itemView.findViewById(R.id.item_three_img_one);
            img2 = (ImageView) itemView.findViewById(R.id.item_three_img_two);
            img3 = (ImageView) itemView.findViewById(R.id.item_three_img_three);
        }
    }


//    public void setBean(ReconmmedBean bean) {
//        this.bean = bean;
//    }
//
//    public SmallRecommedAdapter(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommed,null);
//       ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        holder.tv_titcle.setText(bean.getResult().getNewslist().get(position).getTitle());
//        if (bean.getResult().getNewslist().get(position).getMediatype() == 3){
//            holder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "播放");
//        }else if (bean.getResult().getNewslist().get(position).getMediatype() == 6){
//            holder.tv_comment.setText( "图说"+ " " + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");
//
//        }else {
//            holder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");
//        }
//
//        holder.tv_time.setText(bean.getResult().getNewslist().get(position).getTime());
//
//        if (!bean.getResult().getNewslist().get(position).getSmallpic().isEmpty()){
//
//            Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(holder.img);
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return bean == null ? 0 :bean.getResult().getNewslist().size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tv_titcle;
//        TextView tv_comment;
//        TextView tv_time;
//        ImageView img;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tv_titcle = (TextView) itemView.findViewById(R.id.recommed_item_title_tv);
//            tv_comment = (TextView) itemView.findViewById(R.id.recommed_item_pinglun_tv);
//            tv_time = (TextView) itemView.findViewById(R.id.recommed_item_time_tv);
//            img = (ImageView) itemView.findViewById(R.id.recommed_item_pic_img);
//
//        }
//    }
}
