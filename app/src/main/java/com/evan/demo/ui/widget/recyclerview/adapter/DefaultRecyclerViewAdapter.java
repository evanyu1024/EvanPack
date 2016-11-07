package com.evan.demo.ui.widget.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.ui.widget.recyclerview.IViewHolder;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的通用adapter（可添加header和footer）
 * Created by evanyu on 16/10/28.
 */
public abstract class DefaultRecyclerViewAdapter<E> extends BaseRecyclerViewAdapter<E> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private List<View> mHeaderViews; // HeaderView集合
    private List<View> mFooterViews; // FooterView集合

    public static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
    public static final int TYPE_FOOTER_VIEW = Integer.MIN_VALUE;

    public DefaultRecyclerViewAdapter(Context context) {
        this(context, null, NO_ID);
    }

    public DefaultRecyclerViewAdapter(Context context, int layoutId) {
        this(context, null, layoutId);
    }

    public DefaultRecyclerViewAdapter(Context context, List<E> data) {
        this(context, data, NO_ID);
    }

    public DefaultRecyclerViewAdapter(Context context, List<E> data, int layoutId) {
        super(context, data, layoutId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public void addHeaderView(View header) {

        if (header == null) {
            throw new RuntimeException("header is null");
        }

        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<>();
        }
        mHeaderViews.add(header);
        // this.notifyDataSetChanged();
    }

    public void addFooterView(View footer) {

        if (footer == null) {
            throw new RuntimeException("footer is null");
        }

        if (mFooterViews == null) {
            mFooterViews = new ArrayList<>();
        }
        mFooterViews.add(footer);
        // this.notifyDataSetChanged();
    }

    public void removeHeaderView(View view) {
        if (mHeaderViews != null) {
            mHeaderViews.remove(view);
            // this.notifyDataSetChanged();
        }
    }

    public void removeFooterView(View view) {
        if (mFooterViews != null) {
            mFooterViews.remove(view);
            // this.notifyDataSetChanged();
        }
    }

    public int getHeaderViewCount() {
        return mHeaderViews == null ? 0 : mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews == null ? 0 : mFooterViews.size();
    }

    @Override
    public int getItemCount() {
        return getHeaderViewCount() + getFooterViewsCount() + super.getItemCount();
    }

    public int getNormalItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int normalItemCount = super.getItemCount();
        int headerViewCount = getHeaderViewCount();
        if (position < headerViewCount) {
            return TYPE_HEADER_VIEW + position;
        } else if (headerViewCount <= position && position < headerViewCount + normalItemCount) {
            int innerItemViewType = getNormalItemViewType(position - headerViewCount);
            if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException(
                        "your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return innerItemViewType + Integer.MAX_VALUE / 2;
        } else {
            return TYPE_FOOTER_VIEW + position - normalItemCount;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int headerViewCount = getHeaderViewCount();
        if (viewType < TYPE_HEADER_VIEW + headerViewCount) {
            return new ViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW));
        } else if (viewType < Integer.MAX_VALUE / 2) {
            return new ViewHolder(mFooterViews.get(viewType - TYPE_FOOTER_VIEW - getHeaderViewCount()));
        } else {
            final RecyclerView.ViewHolder holder = super.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
            if (holder instanceof IViewHolder) {
                View itemView = ((IViewHolder) holder).getItemView();
                if (itemView != null) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick(view, holder.getAdapterPosition() - getHeaderViewCount());
                            }
                        }
                    });
                    itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            if (mOnItemLongClickListener != null) {
                                return mOnItemLongClickListener.onItemLongClick(view, holder.getAdapterPosition() - getHeaderViewCount());
                            }
                            return false;
                        }
                    });
                }
            }
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int headerViewCount = getHeaderViewCount();
        if (position >= headerViewCount && position < headerViewCount + super.getItemCount()) {
            super.onBindViewHolder(holder, position - headerViewCount);
        } else {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
