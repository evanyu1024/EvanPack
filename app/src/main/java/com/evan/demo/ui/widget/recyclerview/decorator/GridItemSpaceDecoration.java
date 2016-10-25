package com.evan.demo.ui.widget.recyclerview.decorator;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView的Grid样式Item之间的间距
 * Created by evanyu on 16/10/11.
 */
public class GridItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private final int verticalItemSpacingInPx;
    private final int horizontalItemSpacingInPx;

    public GridItemSpaceDecoration(int verticalItemSpacingInPx, int horizontalItemSpacingInPx) {
        this.verticalItemSpacingInPx = verticalItemSpacingInPx;
        this.horizontalItemSpacingInPx = horizontalItemSpacingInPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // 获取网格视图的列数
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            // 获取当前item在RecyclerView中的positon
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int itemPos = layoutParams.getViewLayoutPosition();

            int left = 0, top = 0, right = 0, bottom = 0;
            // 设置列间距
            if (spanCount > 1) {
                if (itemPos % spanCount == 0) {
                    // 第一列
                    right = horizontalItemSpacingInPx / 2;
                } else if ((itemPos + 1) % spanCount == 0) {
                    // 最后一列
                    left = horizontalItemSpacingInPx / 2;
                } else {
                    // 当中列
                    left = horizontalItemSpacingInPx / 2;
                    right = horizontalItemSpacingInPx / 2;
                }
            }
            // 设置行间距
            // item的数量
            int itemCount = parent.getChildCount();
            // 行数
            int rowCount = (int) Math.ceil(itemCount * 1.0 / spanCount);
            if (itemPos < spanCount) {
                // 第一行
                // bottom = verticalItemSpacingInPx / 2;
            } /*else if (itemPos >= spanCount * (rowCount - 1)) {
                // 最后一行
                top = verticalItemSpacingInPx / 2;
            } */ else {
                // 当中行
                top = verticalItemSpacingInPx;
                // bottom = verticalItemSpacingInPx / 2;
            }

            outRect.set(left, top, right, bottom);
        }
    }
}
