package com.example.work1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kimkevin.cachepot.CachePot;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btn_idSearch_main,btn_pickedUp_main,btn_pickupSequence_main,btn_tmpSaveList_main,btn_tmpSave_main,btn_pickupConfirm_main;
    private TextInputEditText editText_id_main,editText_destinationID_main,editText_storageID_main,editText_boxID_main,editText_labelID_main,editText_num_main;
    private ArrayList<TmpItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_idSearch_main = findViewById(R.id.btn_idSearch_main);
        btn_pickedUp_main = findViewById(R.id.btn_pickedUp_main);
        btn_pickupSequence_main = findViewById(R.id.btn_pickupSequence_main);
        btn_tmpSaveList_main = findViewById(R.id.btn_tmpSaveList_main);
        btn_tmpSave_main = findViewById(R.id.btn_tmpSave_main);
        btn_pickupConfirm_main = findViewById(R.id.btn_pickupConfirm_main);

        editText_id_main = findViewById(R.id.editText_id_main);
        editText_destinationID_main = findViewById(R.id.editText_destinationID_main);
        editText_storageID_main = findViewById(R.id.editText_storageID_main);
        editText_boxID_main = findViewById(R.id.editText_boxID_main);
        editText_labelID_main = findViewById(R.id.editText_labelID_main);
        editText_num_main = findViewById(R.id.editText_num_main);

        dataList = new ArrayList<>();
        ImeOptions();
        editTextWatcher();
        buttonSearchWorkID();
        buttonTmpSave();
        buttonTmpList();

    }


    private void ImeOptions(){
        TextView.OnEditorActionListener FocusEditorAction = (textView, i, keyEvent) -> {
            if( !(null == keyEvent && i == 5) )
                if( !(keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                    keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) )
                        return false;
            switch (textView.getId()) {
                case R.id.editText_id_main:
                    editText_destinationID_main.requestFocus();
                    break;
                case R.id.editText_destinationID_main:
                    editText_storageID_main.requestFocus();
                    break;
                case R.id.editText_storageID_main:
                    editText_boxID_main.requestFocus();
                    break;
                case R.id.editText_boxID_main:
                    editText_labelID_main.requestFocus();
                    break;
                case R.id.editText_labelID_main:
                    editText_num_main.requestFocus();
                    break;
                case R.id.editText_num_main:
                    btn_tmpSave_main.requestFocus();
                    break;
            }
            return false;
        };
        
        editText_id_main.setOnEditorActionListener(FocusEditorAction);
        editText_destinationID_main.setOnEditorActionListener(FocusEditorAction);
        editText_storageID_main.setOnEditorActionListener(FocusEditorAction);
        editText_boxID_main.setOnEditorActionListener(FocusEditorAction);
        editText_labelID_main.setOnEditorActionListener(FocusEditorAction);
        editText_num_main.setOnEditorActionListener(FocusEditorAction);
    }

    private void editTextWatcher() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn_pickedUp_main.setEnabled(false);
                btn_pickupSequence_main.setEnabled(false);
                btn_tmpSaveList_main.setEnabled(false);
                btn_tmpSave_main.setEnabled(false);
                btn_pickupConfirm_main.setEnabled(false);
            }@Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }@Override
            public void afterTextChanged(Editable editable) {
                if ( editText_id_main.getText().length() == 0 )
                    return;
                if ( editText_destinationID_main.getText().length() == 0 )
                    return;
                if ( editText_storageID_main.getText().length() == 0 )
                    return;
                if ( editText_boxID_main.getText().length() == 0 )
                    return;
                btn_tmpSaveList_main.setEnabled(true);
                if ( editText_labelID_main.getText().length() == 0 )
                    return;
                if ( editText_num_main.getText().length() == 0 )
                    return;
                else {
                    btn_tmpSave_main.setEnabled(true);
                }
            }
        };
        editText_id_main.addTextChangedListener(watcher);
        editText_destinationID_main.addTextChangedListener(watcher);
        editText_storageID_main.addTextChangedListener(watcher);
        editText_boxID_main.addTextChangedListener(watcher);
        editText_labelID_main.addTextChangedListener(watcher);
        editText_num_main.addTextChangedListener(watcher);
    }

    private void buttonSearchWorkID() {
        ActivityResultLauncher<Intent> getWorkID = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK){
                    editText_id_main.setText(result.getData().getStringExtra("id"));
                    editText_destinationID_main.requestFocus();
                }
            });
        btn_idSearch_main.setOnClickListener(view -> getWorkID.launch(new Intent(this,chooseIDActivity.class)));
    }

    private void buttonTmpSave() {
        btn_tmpSave_main.setOnClickListener(view -> {

            for (TmpItem item:dataList) {
                if (editText_labelID_main.getText().toString().equals(item.editText_labelID)) {
                    Toast.makeText(this, "此標籤已輸入", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            dataList.add(new TmpItem(editText_labelID_main.getText()+"",
                    editText_num_main.getText()+"")
                );Toast.makeText(this, "暫存成功!", Toast.LENGTH_SHORT).show();
            editText_labelID_main.setText("");
            editText_num_main.setText("");
            editText_labelID_main.requestFocus();
        });

    }

    private void buttonTmpList() {

        ActivityResultLauncher<Intent> tmpSave = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK){
                    dataList = CachePot.getInstance().pop(ArrayList.class);
                }
            });

        btn_tmpSaveList_main.setOnClickListener(view -> {
            CachePot.getInstance().push(dataList);
            tmpSave.launch(new Intent(this, tmpSaveListActivity.class));

        });
    }

}