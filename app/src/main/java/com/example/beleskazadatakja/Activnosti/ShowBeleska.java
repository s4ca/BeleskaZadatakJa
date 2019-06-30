package com.example.beleskazadatakja.Activnosti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.beleskazadatakja.MainActivity;
import com.example.beleskazadatakja.R;
import com.example.beleskazadatakja.db.Beleksa;
import com.example.beleskazadatakja.db.DataBaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class ShowBeleska extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvNaslovP;
    TextView tvOpisP;
    TextView tvDatumP;

    DataBaseHelper dataBaseHelper;
    Beleksa beleksa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_beleska);

        tvNaslovP = findViewById(R.id.tvNaslovO);
        tvOpisP = findViewById(R.id.tvOpisO);
        tvDatumP = findViewById(R.id.tvDatumO);


        setupToolbar();
        getBeleskas(getIntent());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_delete):
                delete();
                break;

            case (R.id.action_update):
                //izmeni belesku
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
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


    public void delete (){
        try {
            getDatabaseHelper().getmBeleskaDao().delete(beleksa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void getBeleskas (Intent intent){
        int beleksa_id = intent.getIntExtra("beleksa_id", 1);


        try {
           beleksa = getDatabaseHelper().getmBeleskaDao().queryForId(beleksa_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tvNaslovP.setText(beleksa.getNaslov());
        tvOpisP.setText(beleksa.getOpis());
        tvDatumP.setText(beleksa.getDatum());
    }
}






















