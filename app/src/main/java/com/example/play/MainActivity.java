package com.example.play;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button addBtn, showDataBtn;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        showDataBtn = (Button) findViewById(R.id.showDataBtn);
        String showDataBtnText = res.getString(R.string.app_show_data_btn).toString();
        showDataBtn.setText(showDataBtnText);

        addBtn = (Button) findViewById(R.id.addBtn);
        String addBtnText = res.getString(R.string.app_add_btn).toString();
        addBtn.setText(addBtnText);

        editText = (EditText) findViewById(R.id.editText);


        mDatabaseHelper = new DatabaseHelper(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                if(editText.length() != 0){
                    AddData(text);
                    editText.setText("");
                } else {
                    toastMessage("The text field is empty");
                }
            }
        });

        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            toastMessage("Success");
        } else {
            toastMessage("Failed to insert");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
