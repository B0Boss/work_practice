package com.example.work1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class tmpSaveListActivity extends AppCompatActivity {

    private RecyclerView recyclerView_tmSaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp_save_list);

        recyclerView_tmSaveList =(RecyclerView)findViewById(R.id.recyclerView_tmSaveList);
        recyclerView_tmSaveList.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_tmSaveList.setAdapter(new recyclerView_tmSaveList_adapter());
    }

    private class recyclerView_tmSaveList_adapter extends RecyclerView.Adapter {
        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView_labelID_recyclerTmpSaveList;
            private final TextInputEditText textInputEditText_quantity_recyclerTmpSaveList;
            private final Button btn_delete_recyclerTmpSaveList,btn_change_recyclerTmpSaveList;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView_labelID_recyclerTmpSaveList =(TextView)itemView.findViewById(R.id.textView_labelID_recyclerTmpSaveList);
                textInputEditText_quantity_recyclerTmpSaveList =(TextInputEditText)itemView.findViewById(R.id.textInputEditText_quantity_recyclerTmpSaveList);
                btn_delete_recyclerTmpSaveList =(Button)itemView.findViewById(R.id.btn_delete_recyclerTmpSaveList);
                btn_change_recyclerTmpSaveList =(Button)itemView.findViewById(R.id.btn_change_recyclerTmpSaveList);
            }
        }@NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recycler_tmp_save_list,parent,false));
        }@Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }@Override
        public int getItemCount() {
            return 0;
        }
    }
}