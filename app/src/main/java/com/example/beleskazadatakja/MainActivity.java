package com.example.beleskazadatakja;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.beleskazadatakja.Activnosti.Dialog;
import com.example.beleskazadatakja.Activnosti.DodajBelesku;
import com.example.beleskazadatakja.Activnosti.SettingsActivity;
import com.example.beleskazadatakja.Activnosti.ShowBeleska;
import com.example.beleskazadatakja.db.Beleksa;
import com.example.beleskazadatakja.db.DataBaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {


    Toolbar toolbar;
    AlertDialog dijalog;
    List<Beleksa> beleksas;
    DataBaseHelper dataBaseHelper;
    RecyclerView rv_lista;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        fillDataRV();
        setUpRv();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_add):
                Intent intent = new Intent(this, DodajBelesku.class);
                startActivity(intent);
                break;

            case (R.id.action_about):
                showDialog();
                break;

            case (R.id.action_settings):
                Intent intent1 = new Intent(this, SettingsActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_home);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }

    private void showDialog(){
        if (dijalog == null){
            dijalog = new Dialog(this).prepereDialog();
        } else {
            if (dijalog.isShowing()){
                dijalog.dismiss();
            }
        }
        dijalog.show();
    }



    public void setUpRv (){
     rv_lista = findViewById(R.id.rv_lista);
     rv_lista.setLayoutManager(new LinearLayoutManager(this));
     if (myAdapter == null){
         try {
             myAdapter = new MyAdapter(getDatabaseHelper().getmBeleskaDao().queryForAll(), this);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         rv_lista.setAdapter(myAdapter);
     }else {
         myAdapter.notifyDataSetChanged();
     }
    }


    public void fillDataRV (){
        try {
            beleksas = getDatabaseHelper().getmBeleskaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public DataBaseHelper getDatabaseHelper() {
        if (dataBaseHelper == null) {
            dataBaseHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
        }
        return dataBaseHelper;
    }
    @Override
    public void onItemClick(Beleksa beleksa) {
        Intent intent = new Intent(this, ShowBeleska.class);
        intent.putExtra("beleksa_id", beleksa.getId());
        startActivity(intent);
    }
}
