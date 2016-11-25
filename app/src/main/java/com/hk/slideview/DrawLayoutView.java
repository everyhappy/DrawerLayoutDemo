package com.hk.slideview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.ContentAdapter;
import model.ContentModel;

public class DrawLayoutView extends Activity {

    private List<ContentModel> list;
    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_layout_view);
        ListView listView=(ListView) findViewById(R.id.left_listview);
        initData();
        adapter=new ContentAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void initData() {
        list=new ArrayList<ContentModel>();
        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻"));
        list.add(new ContentModel(R.drawable.infusion_selected, "订阅"));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片"));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频"));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖"));
        list.add(new ContentModel(R.drawable.personal_selected, "投票"));
    }
}
