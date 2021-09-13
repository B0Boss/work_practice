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

        dataList = new ArrayList<>();
        initialUI();
        boundUIListener();
        ImeOptions();
        editTextWatcher();

    }

    private void boundUIListener() {
        btn_idSearch_main.setOnClickListener(this::btn_search_onclick);
        btn_tmpSave_main.setOnClickListener(this::btn_tmpSave_onclick);
        btn_tmpSaveList_main.setOnClickListener(this::btn_tmpSaveList_onclick);

    }

    private void btn_tmpSaveList_onclick(View view) {
        ActivityResultLauncher<Intent> tmpSave = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        dataList = CachePot.getInstance().pop(ArrayList.class);
                    }
                });

            CachePot.getInstance().push(dataList);
            tmpSave.launch(new Intent(this, tmpSaveListActivity.class));


    }

    private void btn_tmpSave_onclick(View view) {
        String label = editText_labelID_main.getText().toString(),
                num = editText_num_main.getText().toString();
        editText_labelID_main.setText("");
        editText_num_main.setText("");
        editText_labelID_main.requestFocus();

        for (TmpItem item:dataList) {
            if (label.equals(item.editText_labelID)) {
                Toast.makeText(this, "此標籤已輸入", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        dataList.add( new TmpItem(label,num) );
        Toast.makeText(this, "暫存成功!", Toast.LENGTH_SHORT).show();
    }

    private void btn_search_onclick(View view) {
        ActivityResultLauncher<Intent> getWorkID = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        editText_id_main.setText(result.getData().getStringExtra("id"));
                        editText_destinationID_main.requestFocus();
                    }
                });
        getWorkID.launch(new Intent(this,chooseIDActivity.class));
    }

    private void initialUI() {
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
                    btn_tmpSave_main.performClick();
                    break;
            }
            return true;
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
                editText_id_main.setEnabled(false);
                editText_destinationID_main.setEnabled(false);
                editText_storageID_main.setEnabled(false);
                editText_boxID_main.setEnabled(false);
                editText_labelID_main.setEnabled(false);
                editText_num_main.setEnabled(false);
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


}
class Tool{
    private Tool() {}
    public static Tool instance = new Tool();

}