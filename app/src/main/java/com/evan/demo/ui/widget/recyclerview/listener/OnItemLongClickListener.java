package com.evan.demo.ui.widget.recyclerview.listener;

import android.view.View;

/**
 * RecyclerView的item长按事件监听接口
 * Created by evanyu on 16/10/10.
 */
public interface OnItemLongClickListener {

    boolean onItemLongClick(View view, int position);

}
