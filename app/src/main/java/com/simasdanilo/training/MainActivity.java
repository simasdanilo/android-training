package com.simasdanilo.training;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupViews();
        this.populateMyWordList();
    }

    // Setup ---------------------------------------------------------------------------------------

    private void setupViews() {
        this.setupToolbar();
        this.setupFab();
        this.setupRecyclerView();
    }

    private void setupRecyclerView() {
        // Get a handle to the RecyclerView.
        this.mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        this.mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        this.mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int wordListSize = this.mWordList.size();
        // Add a new word to the wordList.
        this.mWordList.addLast("+ Word " + wordListSize);
        // Notify the adapter, that the data has changed.
        this.mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
        // Scroll to the bottom.
        this.mRecyclerView.smoothScrollToPosition(wordListSize);
    }

    // General -------------------------------------------------------------------------------------

    private void populateMyWordList() {
        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }
    }

}
