package com.example.doanmb.Activity.Admin.Vocab;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanmb.Adapter.Vocab.AddTheoChuDeAdapter;
import com.example.doanmb.DataBase.DBHelper;
import com.example.doanmb.Model.Vocab;
import com.example.doanmb.R;

import java.util.ArrayList;

public class ActivityAddChuDe extends AppCompatActivity implements AddTheoChuDeAdapter.Listener {
    ImageView imgAddChuDe;
    RecyclerView recyclerView;

    ArrayList<Vocab> vocabs;

    DBHelper dbHelper;

    public AddTheoChuDeAdapter addTheoChuDeAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaddchudevocab);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A770EF")));
        setTitle("Add and Edit Vocab");

        imgAddChuDe = findViewById(R.id.imgAddChuDe);
        dbHelper = new DBHelper(ActivityAddChuDe.this);
        recyclerView = findViewById(R.id.rc_add);
        vocabs = dbHelper.getChuDe();
        addTheoChuDeAdapter = new AddTheoChuDeAdapter(ActivityAddChuDe.this, vocabs, this);
        recyclerView.setAdapter(addTheoChuDeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityAddChuDe.this, LinearLayoutManager.VERTICAL, false));

        imgAddChuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddChuDe.this, ActivityLayOutAddChuDe.class);
                intent.putExtra("flag", 1);
                startActivity(intent);
            }
        });

    }


    @Override
    public void OnItemListener(int pos, Vocab contact) {

    }

    @Override
    public void OnDeleteListener(Vocab vocab) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAddChuDe.this);
        builder.setTitle("Warning!");
        builder.setMessage("Do you really want to delete this topic?");
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.deleteCHUDE(vocab);
                vocabs.clear();
                addTheoChuDeAdapter.notifyDataSetChanged();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

