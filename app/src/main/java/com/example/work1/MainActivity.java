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
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_idSearch_main,btn_pickedUp_main,btn_pickupSequence_main,btn_tmpSaveList_main,btn_tmpSave_main,btn_pickupConfirm_main;
    private TextInputEditText editText_id_main,editText_destinationID_main,editText_storageID_main,editText_boxID_main,editText_labelID_main,editText_num_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_idSearch_main =(Button)findViewById(R.id.btn_idSearch_main);
        btn_pickedUp_main =(Button)findViewById(R.id.btn_pickedUp_main);
        btn_pickupSequence_main =(Button)findViewById(R.id.btn_pickupSequence_main);
        btn_tmpSaveList_main =(Button)findViewById(R.id.btn_tmpSaveList_main);
        btn_tmpSave_main =(Button)findViewById(R.id.btn_tmpSave_main);
        btn_pickupConfirm_main =(Button)findViewById(R.id.btn_pickupConfirm_main);

        editText_id_main =(TextInputEditText)findViewById(R.id.editText_id_main);
        editText_destinationID_main =(TextInputEditText)findViewById(R.id.editText_destinationID_main);
        editText_storageID_main =(TextInputEditText)findViewById(R.id.editText_storageID_main);
        editText_boxID_main =(TextInputEditText)findViewById(R.id.editText_boxID_main);
        editText_labelID_main =(TextInputEditText)findViewById(R.id.editText_labelID_main);
        editText_num_main =(TextInputEditText)findViewById(R.id.editText_num_main);

        hardwareImeOptions();
        editTextWatcher();

        buttonSearchWorkID();
        buttonTmpSaveList();
        buttonTmpSave();


    }



    private void hardwareImeOptions(){
        View.OnKeyListener getFocusHardware = (view, i, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && i == KeyEvent.KEYCODE_ENTER)
                switch (view.getId()){
                    case R.id.editText_id_main:
                        editText_id_main.clearFocus();
                        editText_destinationID_main.requestFocus();
                        break;
                    case R.id.editText_destinationID_main:
                        editText_destinationID_main.clearFocus();
                        editText_storageID_main.requestFocus();
                        break;
                    case R.id.editText_storageID_main:
                        editText_storageID_main.clearFocus();
                        editText_boxID_main.requestFocus();
                        break;
                    case R.id.editText_boxID_main:
                        editText_boxID_main.clearFocus();
                        editText_labelID_main.requestFocus();
                        break;
                    case R.id.editText_labelID_main:
                        editText_labelID_main.clearFocus();
                        editText_num_main.requestFocus();
                        break;
                    case R.id.editText_num_main:
                        editText_num_main.clearFocus();
                        btn_tmpSave_main.requestFocus();
                        break;
                }
            return false;
        };
        editText_id_main.setOnKeyListener(getFocusHardware);
        editText_destinationID_main.setOnKeyListener(getFocusHardware);
        editText_storageID_main.setOnKeyListener(getFocusHardware);
        editText_boxID_main.setOnKeyListener(getFocusHardware);
        editText_labelID_main.setOnKeyListener(getFocusHardware);
        editText_num_main.setOnKeyListener(getFocusHardware);
    }

    private void editTextWatcher() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }@Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }@Override
            public void afterTextChanged(Editable editable) {
                if ( editText_id_main.getText().length() != 0 && editText_destinationID_main.getText().length() != 0 &&
                        editText_storageID_main.getText().length() != 0 && editText_boxID_main.getText().length() != 0 &&
                        editText_labelID_main.getText().length() != 0 && editText_num_main.getText().length() != 0 ){
                    btn_tmpSave_main.setEnabled(true);
                }else
                    btn_tmpSave_main.setEnabled(false);
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

    private void buttonTmpSaveList() {
        ActivityResultLauncher<Intent> tmpSave = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){

                    }
                });
        btn_tmpSaveList_main.setOnClickListener(view -> tmpSave.launch(new Intent(this,tmpSaveListActivity.class)));
    }

    private void buttonTmpSave() {

    }

}