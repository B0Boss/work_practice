package com.example.work1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kimkevin.cachepot.CachePot;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class tmpSaveListActivity extends AppCompatActivity {

    private RecyclerView recyclerView_tmSaveList;
    private ArrayList<TmpItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp_save_list);

        setTitle("暫存清單");

        dataList = CachePot.getInstance().pop(ArrayList.class);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recyclerView_tmSaveList = findViewById(R.id.recyclerView_tmSaveList);
        recyclerView_tmSaveList.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_tmSaveList.setAdapter(new recyclerView_tmSaveList_adapter());

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
        CachePot.getInstance().push(dataList);
        setResult(Activity.RESULT_OK);
        super.onBackPressed();
    }


    private class recyclerView_tmSaveList_adapter extends RecyclerView.Adapter<recyclerView_tmSaveList_adapter.ViewHolder> {
        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView_labelID_recyclerTmpSaveList,textView_quantity_recyclerTmpSaveList;
            private final Button btn_delete_recyclerTmpSaveList;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView_labelID_recyclerTmpSaveList = itemView.findViewById(R.id.textView_labelID_recyclerTmpSaveList);
                textView_quantity_recyclerTmpSaveList = itemView.findViewById(R.id.textView_quantity_recyclerTmpSaveList);
                btn_delete_recyclerTmpSaveList = itemView.findViewById(R.id.btn_delete_recyclerTmpSaveList);
            }
        }@NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recycler_tmp_save_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView_labelID_recyclerTmpSaveList.setText(dataList.get(position).editText_labelID);
            holder.textView_quantity_recyclerTmpSaveList.setText(dataList.get(position).editText_quantity);
            holder.btn_delete_recyclerTmpSaveList.setOnClickListener(view -> {
                dataList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"刪除成功",Toast.LENGTH_SHORT).show();
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}