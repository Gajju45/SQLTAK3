package com.android.gajju45.sqltask3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText etID, etDATE, etMOBILE, etEMAIL, etPURPOSE, etCOMMENT, etLOCATION, etcompany, et_meeting;
    Button btnInsert, btnView, btn_update, btnDelete, btn_Company, btn_meeting;
    Spinner spinner1, spinner2;

    String[] companyname = new String[]{"Company"};
    String[] meetname = new String[]{"Meeting"};

    List<String> companylist;
    List<String> meetlist;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        etID = findViewById(R.id.ID);
        etDATE = findViewById(R.id.DATE_TIME);
        etMOBILE = findViewById(R.id.MOBILENO);
        etEMAIL = findViewById(R.id.EMAILID);
        etPURPOSE = findViewById(R.id.PRPOSE);
        etCOMMENT = findViewById(R.id.COMMENTS);
        etLOCATION = findViewById(R.id.LOCATION);
        et_meeting = findViewById(R.id.edMeet);
        etcompany = findViewById(R.id.edCompany);


        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        btn_update = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);
        btn_meeting = findViewById(R.id.meet_BTN);
        btn_Company = findViewById(R.id.company_BTN);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);


        companylist = new ArrayList<>(Arrays.asList(companyname));

        meetlist = new ArrayList<>(Arrays.asList(meetname));


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, companylist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meetlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter);

        btn_Company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String company = etcompany.getText().toString();

                companylist.add(company);

                arrayAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, company+" Added", Toast.LENGTH_LONG).show();
            }
        });

        btn_meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String meet = et_meeting.getText().toString();

                meetlist.add(meet);

                arrayAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, meet+" Added", Toast.LENGTH_LONG).show();
            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinsert = myDb.insertData(etDATE.getText().toString(), etMOBILE.getText().toString(), etEMAIL.getText().toString(), etPURPOSE.getText().toString(), etCOMMENT.getText().toString(), etLOCATION.getText().toString(), et_meeting.getText().toString(), etcompany.getText().toString());

                if (isinsert == true) {
                    Toast.makeText(MainActivity.this, "Insert", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No insert", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = myDb.getAllData();

                if (cursor.getCount() == 0) {
                    showMessage("Error", "Empty Data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID: " + cursor.getString(0) + "\n");
                    buffer.append("DATE:" + cursor.getString(1) + "\n");
                    buffer.append("MOBILENUM: " + cursor.getString(2) + "\n");
                    buffer.append("EMAILID: " + cursor.getString(3) + "\n");
                    buffer.append("PURPOSE: " + cursor.getString(4) + "\n");
                    buffer.append("COMMENT: " + cursor.getString(5) + "\n");
                    buffer.append("LOCATION: " + cursor.getString(6) + "\n");
                    buffer.append("COMPANY: " + cursor.getString(8) + "\n");
                    buffer.append("MEETING: " + cursor.getString(7) + "\n");
                }

                showMessage("Data", buffer.toString());

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDb.updateData(etID.getText().toString(), etDATE.getText().toString(), etMOBILE.getText().toString(), etEMAIL.getText().toString(), etPURPOSE.getText().toString(), etCOMMENT.getText().toString(), etLOCATION.getText().toString());
                if (isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Data update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletRow = myDb.deleteData(etID.getText().toString());
                if (deletRow > 0) {
                    Toast.makeText(MainActivity.this, "Data Delete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //for view data
    public void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}