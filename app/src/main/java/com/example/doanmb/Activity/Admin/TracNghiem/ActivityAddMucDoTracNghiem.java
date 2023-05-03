package com.example.doanmb.Activity.Admin.TracNghiem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanmb.Activity.Admin.ActivityAdmin;
import com.example.doanmb.Adapter.TracNghiem.AddMultipleChoiceAdapter;
import com.example.doanmb.DataBase.DBHelper;
import com.example.doanmb.Model.MultipleChoice;
import com.example.doanmb.Model.Vocab;
import com.example.doanmb.R;

import java.util.ArrayList;

public class ActivityAddMucDoTracNghiem extends AppCompatActivity implements AddMultipleChoiceAdapter.Listener {
    ImageView imgAddChuDe;
    RecyclerView recyclerView;

    ArrayList<MultipleChoice> multipleChoices;

    public AddMultipleChoiceAdapter addMultipleChoiceAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaddchudevocab);

        setTitle("Level MultibleChoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAddChuDe = findViewById(R.id.imgAddChuDe);
        DBHelper dbHelper = new DBHelper(ActivityAddMucDoTracNghiem.this);
        recyclerView = findViewById(R.id.rc_add);
        multipleChoices = dbHelper.getMucDo();
        addMultipleChoiceAdapter = new AddMultipleChoiceAdapter(ActivityAddMucDoTracNghiem.this, multipleChoices, this);
        recyclerView.setAdapter(addMultipleChoiceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityAddMucDoTracNghiem.this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void OnItemListener(int pos, Vocab Vocab) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
