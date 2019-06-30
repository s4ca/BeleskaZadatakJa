package com.example.beleskazadatakja.Activnosti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.beleskazadatakja.MainActivity;
import com.example.beleskazadatakja.R;
import com.example.beleskazadatakja.db.Beleksa;
import com.example.beleskazadatakja.db.DataBaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;


public class DodajBelesku extends AppCompatActivity {

    Toolbar toolbar;
    DataBaseHelper dataBaseHelper;

    EditText etNaslovU;
    EditText etOpisU;
    EditText etDatumU;
    Button btnCancelU;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_belesku);

        etNaslovU = findViewById(R.id.etNaslovU);
        etOpisU = findViewById(R.id.etOpisU);
        etDatumU= findViewById(R.id.etDatumU);


        btnCancelU = findViewById(R.id.btnCancelU);
        btnCancelU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DodajBelesku.this, MainActivity.class);
                startActivity(intent2);
            }
        });

         setupToolbar();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dodaj, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_save):
                addBeleska();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarDodaj);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_home);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }

    public DataBaseHelper getDatabaseHelper() {
        if (dataBaseHelper == null) {
            dataBaseHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        }
        return dataBaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBaseHelper!= null){
            OpenHelperManager.releaseHelper();
            dataBaseHelper=null;
        }
    }

    public void addBeleska (){
       Beleksa b = new Beleksa();

       b.setNaslov(etNaslovU.getText().toString());
       b.setOpis(etOpisU.getText().toString());
       b.setDatum(etDatumU.getText().toString());

        try {
            getDatabaseHelper().getmBeleskaDao().create(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}

















