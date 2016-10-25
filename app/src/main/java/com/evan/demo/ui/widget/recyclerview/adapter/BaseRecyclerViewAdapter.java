package com.evan.demo.ui.widget.recyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evan.demo.ui.widget.recyclerview.IViewHolder;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemLongClickListener;

import java.util.List;

/**
 * RecyclerView的通用Adapter
 * Created by Evan on 2016/5/11.
 */
public abstract class BaseRecyclerViewAdapter<E> extends RecyclerView.Adapter {

    private Context mContext;
    private List<E> mData;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener; // 单击事件监听器
    private OnItemLongClickListener mOnItemLongClickListener; // 单击事件监听器

    private int[] mItemLayouts;

    public static final int ITEM_TYPE_HEADER = -1;
    public static final int ITEM_TYPE_FOOTER = -2;
    public static final int ITEM_TYPE_NORMAL = 0;

    public static final int NO_ID = -1;

    public BaseRecyclerViewAdapter(Context context) {
        this(context, NO_ID);
    }

    public BaseRecyclerViewAdapter(Context context, int layoutId) {
        this(context, null, layoutId);
    }

    public BaseRecyclerViewAdapter(Context context, List<E> data) {
        this(context, data, NO_ID);
    }

    public BaseRecyclerViewAdapter(Context context, List<E> data, int layoutId) {
        this.mContext = context;
        this.mData = data;

        if (layoutId >= 0) {
            mItemLayouts = new int[]{layoutId};
        }

        mInflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据源
     */
    public void setData(List<E> data) {
        mData = data;
    }

    /**
     * 添加数据源（在原有数据源中添加数据）
     */
    public void addData(List<E> data) {
        if (mData != null) {
            mData.addAll(data);
        } else {
            setData(data);
        }
    }

    public List<E> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * Please return RecyclerView loading layout Id array
     * 请返回RecyclerView加载的布局Id数组
     *
     * @return 布局Id数组
     */
    public int[] getItemLayouts() {
        return mItemLayouts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemLayouts = getItemLayouts();
        if (mItemLayouts == null) return null;

        int layoutId;
        if (mItemLayouts.length < 1) {
            return null;
        } else if (mItemLayouts.length == 1) {
            layoutId = mItemLayouts[0];
        } else {
            layoutId = mItemLayouts[viewType];
        }

        View itemView = mInflater.inflate(layoutId, parent, false);

        InnerViewHolder viewHolder = new InnerViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert((InnerViewHolder) holder, mData.get(position));
    }

    /**
     * 添加新的一项
     *
     * @param item
     * @param position
     */
    public void addItem(E item, int position) {
        mData.add(position, item);
        notifyItemInserted(position); // 通知适配器数据源中添加了新的数据
    }

    /**
     * 移除指定项
     *
     * @param position
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position); // 通知适配器数据源中有数据被移除了
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    /**
     * 设置Item中显示的内容
     *
     * @param holder
     * @param itemBean
     */
    public abstract void convert(InnerViewHolder holder, E itemBean);

    /**
     * 持有者类
     */
    public class InnerViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        SparseArray<View> mViews; // 保存ViewHolder持有的控件对象
        View mItemView; // 当前ViewHolder对应Item的View对象

        public InnerViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
            mItemView = itemView;

            initListener();
        }

        private void initListener() {
            if (mItemView != null) {
                mItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(view, ((ViewGroup) view.getParent()).indexOfChild(view));
                        }
                    }
                });

                mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (mOnItemLongClickListener != null) {
                            return mOnItemLongClickListener.onItemLongClick(view, ((ViewGroup) view.getParent()).indexOfChild(view));
                        }
                        return false;
                    }
                });
            }
        }

        @Override
        public View getItemView() {
            return mItemView;
        }

        public <T extends View> T getViewById(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mItemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int viewId, CharSequence text) {
            TextView tv = (TextView) getViewById(viewId);
            tv.setText(text);
        }

        public void setImageResource(int viewId, int resId) {
            ImageView iv = (ImageView) getViewById(viewId);
            iv.setImageResource(resId);
        }

        public void setImageBitmap(int viewId, Bitmap bm) {
            ImageView iv = (ImageView) getViewById(viewId);
            iv.setImageBitmap(bm);
        }

    }

}
