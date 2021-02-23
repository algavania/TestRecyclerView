package com.apps.hggg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamActivity extends AppCompatActivity {

    final String baseURL = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League";
    RecyclerView recyclerView;
    ListAdapter adapter;
    ArrayList<ListModel> arrayList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        AndroidNetworking.initialize(getApplicationContext());

        recyclerView = findViewById(R.id.rv_team);
        progressBar = findViewById(R.id.progress_bar);

        addData();
    }

    private void addData() {
        progressBar.setVisibility(View.VISIBLE);
        arrayList = new ArrayList<>();
        AndroidNetworking.get(baseURL)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resultArray = response.getJSONArray("teams");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject resultObj = resultArray.getJSONObject(i);
                        String name = resultObj.getString("strTeam");
                        String desc = resultObj.getString("strStadiumLocation");
                        String image = resultObj.getString("strTeamBadge");
                        arrayList.add(new ListModel(image, name, desc));
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter = new ListAdapter(getApplicationContext(), arrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Toast.makeText(getApplicationContext(), arrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d("error", e.toString());
                }
            }

            @Override
            public void onError(ANError anError) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("error", anError.toString());
            }
        });
    }
}