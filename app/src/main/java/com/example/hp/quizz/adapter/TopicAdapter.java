package com.example.hp.quizz.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hp.quizz.R;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicCustomViewHolder> {

    Context mContext;
    ArrayList<String> mArrayList;
    ArrayList<GradientDrawable> mGradientDrawable;

    public TopicAdapter(Context context, ArrayList<String> arrayList){
        mArrayList=arrayList;
        mContext=context;
        mGradientDrawable=new ArrayList<>();
        fillGradientList(mContext);

    }

    public void  addTopic(String topic){
        mArrayList.add(topic);

    }
    @Override
    public TopicAdapter.TopicCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item_list_view,parent,false);
        return  new TopicCustomViewHolder(view);
    }

    private void fillGradientList(Context context){
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_1_start),ContextCompat.getColor(context,R.color.gradient_1_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_2_start),ContextCompat.getColor(context,R.color.gradient_2_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_3_start),ContextCompat.getColor(context,R.color.gradient_3_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_4_start),ContextCompat.getColor(context,R.color.gradient_4_end)));
    }

    GradientDrawable getTempGradientDrawable(int startColor,int endColor){
        GradientDrawable drawable=new GradientDrawable(GradientDrawable.Orientation.BR_TL,new int[]{startColor,endColor});
        drawable.setDither(true);
        drawable.setGradientCenter(drawable.getIntrinsicWidth()/8,drawable.getIntrinsicHeight()/2);
        drawable.setCornerRadius(20);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setUseLevel(true);
        return drawable;
    }



    @Override
    public void onBindViewHolder(TopicCustomViewHolder holder, int position) {
    String topicName=mArrayList.get(position);
    holder.mTextView.setText(topicName);
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
    holder.mTopicRelativeLayout.setBackground(mGradientDrawable.get(position%4));
    }else {
        holder.mTopicRelativeLayout.setBackgroundDrawable(mGradientDrawable.get(position%4));
    }
    holder.mImageView.setImageResource(R.drawable.ic_menu);

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class TopicCustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        private RelativeLayout mTopicRelativeLayout;

        public TopicCustomViewHolder(View itemView) {
            super(itemView);

            mTextView=(TextView)itemView.findViewById(R.id.topicTextView);
            mImageView=(ImageView)itemView.findViewById(R.id.topicImageView);
            mTopicRelativeLayout=(RelativeLayout)itemView.findViewById(R.id.topicRelativeLayout);
        }
    }
}
