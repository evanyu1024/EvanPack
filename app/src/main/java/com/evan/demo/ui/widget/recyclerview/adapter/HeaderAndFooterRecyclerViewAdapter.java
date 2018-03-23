package com.evan.demo.ui.widget.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的通用adapter（可添加header和footer）
 * Created by evanyu on 16/10/28.
 */
public abstract class HeaderAndFooterRecyclerViewAdapter<E> extends BaseRecyclerViewAdapter<E> {

    private List<View> mHeaderViews; // HeaderView集合
    private List<View> mFooterViews; // FooterView集合

    public static final int TYPE_HEADER = Integer.MIN_VALUE;
    public static final int TYPE_FOOTER = Integer.MIN_VALUE;

    public HeaderAndFooterRecyclerViewAdapter(Context context) {
        this(context, null, NO_ID);
    }

    public HeaderAndFooterRecyclerViewAdapter(Context context, int layoutId) {
        this(context, null, layoutId);
    }

    public HeaderAndFooterRecyclerViewAdapter(Context context, List<E> data) {
        this(context, data, NO_ID);
    }

    public HeaderAndFooterRecyclerViewAdapter(Context context, List<E> data, int layoutId) {
        super(context, data, layoutId);
    }

    public void addHeaderView(View header) {

        if (header == null) {
            throw new NullPointerException("header is null");
        }

        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<>();
        }
        mHeaderViews.add(header);
        // this.notifyDataSetChanged();
    }

    public void addFooterView(View footer) {

        if (footer == null) {
            throw new NullPointerException("footer is null");
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

    public View getlastFooterView() {
        if (mFooterViews != null && mFooterViews.size() > 0) {
            return mFooterViews.get(mFooterViews.size() - 1);
        } else {
            return null;
        }
    }

    public int getHeaderViewCount() {
        return mHeaderViews == null ? 0 : mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews == null ? 0 : mFooterViews.size();
    }

    public int getNormalItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemCount() {
        return getHeaderViewCount() + getFooterViewsCount() + getNormalItemCount();
    }

    public int getItemPosition(RecyclerView.ViewHolder holder) {
        int position = -1;
        if (holder != null) {
            position = holder.getAdapterPosition() - getHeaderViewCount();
        }
        return position;
    }

    public int getNormalItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int normalItemCount = super.getItemCount();
        int headerViewCount = getHeaderViewCount();
        if (position < headerViewCount) {
            return TYPE_HEADER + position;
        } else if (headerViewCount <= position && position < headerViewCount + normalItemCount) {
            int innerItemViewType = getNormalItemViewType(position - headerViewCount);
            if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException(
                        "your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return innerItemViewType + Integer.MAX_VALUE / 2;
        } else {
            return TYPE_FOOTER + position - normalItemCount;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        // 当为GridLayoutManager的时候, 设置headerView和footerView占据整整一行.
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            // 获取原来的SpanSizeLookup,当不为null的时候,除了headerView和footerView都应该返回原来的spanSize
            final GridLayoutManager.SpanSizeLookup originalSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (itemViewType >= TYPE_HEADER && itemViewType < TYPE_HEADER + getHeaderViewCount() + getFooterViewsCount()) {
                        return gridLayoutManager.getSpanCount();
                    } else if (originalSizeLookup != null) {
                        return originalSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int headerViewCount = getHeaderViewCount();
        if (viewType < TYPE_HEADER + headerViewCount) {
            return new ViewHolder(mHeaderViews.get(viewType - TYPE_HEADER));
        } else if (viewType < Integer.MAX_VALUE / 2) {
            return new ViewHolder(mFooterViews.get(viewType - TYPE_FOOTER - getHeaderViewCount()));
        } else {
            InnerViewHolder holder = createInnerViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
            onCreateItemListeners(holder);
            return holder;
//            return super.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
//            if (holder instanceof IViewHolder) {
//                View itemView = ((IViewHolder) holder).getItemView();
//                if (itemView != null) {
//                    itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (mOnItemClickListener != null) {
//                                mOnItemClickListener.onItemClick(view, holder.getAdapterPosition() - getHeaderViewCount());
//                            }
//                        }
//                    });
//                    itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                        @Override
//                        public boolean onLongClick(View view) {
//                            if (mOnItemLongClickListener != null) {
//                                return mOnItemLongClickListener.onItemLongClick(view, holder.getAdapterPosition() - getHeaderViewCount());
//                            }
//                            return false;
//                        }
//                    });
//                }
//            }
//            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int headerViewCount = getHeaderViewCount();
        if (position >= headerViewCount && position < headerViewCount + super.getItemCount()) {
            super.onBindViewHolder(holder, position - headerViewCount);
        } /*else {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }*/
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
