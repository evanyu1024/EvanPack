package com.evan.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowGifActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.gridView)
    GridView mGridView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    private String[] items = {"Glide加载"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gif);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: // Glide加载
                // Glide.with(this).load()
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
