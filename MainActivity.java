package com.example.databaseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Name, regid, semester, cnic, dob, Contact, degree;
    Button ADD, VIEW;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.name);
        Contact = findViewById(R.id.contact);
        cnic = findViewById(R.id.cnic);
        degree = findViewById(R.id.degree);
        semester = findViewById(R.id.semester);
        dob = findViewById(R.id.age);
        regid = findViewById(R.id.regid);
        ADD = findViewById(R.id.btnInsert);
        VIEW = findViewById(R.id.btnView);
        DB = new DBHelper(this);
AddAll();
VIEWAll();
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = Name.getText().toString();
                String regidTXT = regid.getText().toString();
                String semesterTXT = semester.getText().toString();
                String cnicTXT = cnic.getText().toString();
                String dobTXT = dob.getText().toString();
                String contactTXT = Contact.getText().toString();
                String degreeTXT = degree.getText().toString();


                Boolean checkinsertdata = DB.insertuserdata(nameTXT, regidTXT, semesterTXT, cnicTXT, dobTXT, contactTXT, degreeTXT);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        VIEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("name :" + res.getString(0) + "\n");
                    buffer.append("regid :" + res.getString(1) + "\n");
                    buffer.append("semester :" + res.getString(2) + "\n");
                    buffer.append("cnic :" + res.getString(3) + "\n");
                    buffer.append("dob:" + res.getString(4) + "\n");
                    buffer.append("contact :" + res.getString(5) + "\n");
                    buffer.append("degree:" + res.getString(6) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }

    private void VIEWAll() {
    }

    private void AddAll() {
    }

}