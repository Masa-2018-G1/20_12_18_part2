package com.sheygam.masa_2018_g1_20_12_18_part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.MyClickListener {

    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3,LinearLayoutManager.HORIZONTAL,true);
        RecyclerView rv = findViewById(R.id.my_rv);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        RecyclerView.ItemDecoration divider =
                new DividerItemDecoration(this,((LinearLayoutManager) layoutManager).getOrientation());
        ((DividerItemDecoration) divider).setDrawable(getResources().getDrawable(R.drawable.divider_bg));
        rv.addItemDecoration(divider);

        findViewById(R.id.addBtn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.add(new User("Vasya","vasya@mail.com"),1);
                    }
                });

        findViewById(R.id.removeBtn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.remove(3);
                    }
                });

        adapter.setListener(this);

    }

    @Override
    public void onClick(int pos) {
        Toast.makeText(this, "Click " + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int pos) {
        Toast.makeText(this, "LongClick " + pos, Toast.LENGTH_SHORT).show();
    }
}
