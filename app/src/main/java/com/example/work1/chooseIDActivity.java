package com.example.work1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chooseIDActivity extends AppCompatActivity {

    private Context context = this;
    private RecyclerView recyclerView_chooseID;
    private ArrayList<Map<String, String>> sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_id);

        setTitle("選擇單號");

        sample = new ArrayList<>();
        for (int i=0;i<8;i++) {
            Map data = new HashMap();
            data.put("id",i+""+i);
            data.put("status","3/2/1");
            sample.add(data);
        }
        recyclerView_chooseID =(RecyclerView)findViewById(R.id.recyclerView_chooseID);
        recyclerView_chooseID.setLayoutManager(new LinearLayoutManager(context));
        recyclerView_chooseID.setAdapter(new recyclerAdapter());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView textView_ID_recycler,textView_status_recycler;
            private final Button btn_choose_recycler;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView_ID_recycler = itemView.findViewById(R.id.textView_ID_recycler);
                textView_status_recycler = itemView.findViewById(R.id.textView_status_recycler);
                btn_choose_recycler = itemView.findViewById(R.id.btn_choose_recycler);
            }
        }@NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_main,parent,false));
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView_ID_recycler.setText(sample.get(position).get("id"));
            holder.textView_status_recycler.setText(sample.get(position).get("status"));
            holder.btn_choose_recycler.setOnClickListener(view -> {
                setResult(Activity.RESULT_OK,new Intent().putExtra("id",sample.get(position).get("id")));
                finish();
            });
        }
        @Override
        public int getItemCount() {
            return sample.size();
        }
    }


}