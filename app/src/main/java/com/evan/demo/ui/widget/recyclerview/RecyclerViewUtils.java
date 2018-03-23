package com.evan.demo.ui.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author evanyu
 * @date 16/10/17
 */
public class RecyclerViewUtils {

    /**
     * 初始化垂直显示的列表
     */
    public static void initVerticalView(Context context, RecyclerView recyclerView) {
        LinearLayoutManager layout = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        // set layout manager
        recyclerView.setLayoutManager(layout);
        // set item animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // keep recyclerview fixed size
        recyclerView.setHasFixedSize(true);
    }

}
