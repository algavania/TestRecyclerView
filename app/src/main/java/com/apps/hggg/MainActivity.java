package com.apps.hggg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    ArrayList<ListModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_main);
        addData();

    }

    private void addData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet", "kuenya seh"));
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet 1", "kuenya seh"));
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet 2", "kuenya seh"));
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet 3", "kuenya seh"));
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet 4", "kuenya seh"));
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet 5", "kuenya seh"));
        arrayList.add(new ListModel("https://cdn.sallysbakingaddiction.com/wp-content/uploads/2019/02/red-velvet-cake-slice-2.jpg", "Red Velvet 6", "kuenya seh"));

        adapter = new ListAdapter(getApplicationContext(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(), arrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}