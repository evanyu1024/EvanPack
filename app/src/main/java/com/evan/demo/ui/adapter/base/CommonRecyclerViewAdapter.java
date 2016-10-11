package com.evan.demo.ui.adapter.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * RecyclerView的通用Adapter
 * Created by Evan on 2016/5/11.
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private Context mContext;
    private List<T> mData;
    private LayoutInflater mInflater;
    private int mLayoutId;
    private OnItemClickListener mListener; // 监听器

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        boolean onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CommonRecyclerViewAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data;
        mLayoutId = layoutId;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mLayoutId, parent, false);

        // 设置监听接口
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onItemClick(view, ((ViewGroup)view.getParent()).indexOfChild(view));
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mListener != null) {
                    return mListener.onItemLongClick(view, ((ViewGroup)view.getParent()).indexOfChild(view));
                }
                return false;
            }
        });

        InnerViewHolder viewHolder = new InnerViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert((InnerViewHolder)holder, mData.get(position));
    }

    /**
     * 添加新的一项
     * @param item
     * @param position
     */
    public void addItem(T item, int position) {
        mData.add(position, item);
        notifyItemInserted(position); // 通知适配器数据源中添加了新的数据
    }

    /**
     * 移除指定项
     * @param position
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position); // 通知适配器数据源中有数据被移除了
    }

    /**
     * 设置Item中显示的内容
     * @param holder
     * @param item
     */
    public abstract void convert(InnerViewHolder holder, T item);

    /**
     * 持有者类
     */
    public class InnerViewHolder extends RecyclerView.ViewHolder {

        SparseArray<View> mViews; // 保存ViewHolder持有的控件对象
        View mConvertView; // 当前ViewHolder对应Item的View对象

        public InnerViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
            mConvertView = itemView;
        }

        public View getViewById(int viewId){
            View view = mViews.get(viewId);
            if(view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }

        public void setText(int viewId, CharSequence text){
            TextView tv = (TextView)getViewById(viewId);
            tv.setText(text);
        }

        public void setImageResource(int viewId, int resId) {
            ImageView iv = (ImageView)getViewById(viewId);
            iv.setImageResource(resId);
        }

        public void setImageBitmap(int viewId, Bitmap bm) {
            ImageView iv = (ImageView)getViewById(viewId);
            iv.setImageBitmap(bm);
        }

    }

}
