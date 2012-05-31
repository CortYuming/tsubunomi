package jp.katahirado.android.tsubunomi.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import jp.katahirado.android.tsubunomi.DBOpenHelper;
import jp.katahirado.android.tsubunomi.R;
import jp.katahirado.android.tsubunomi.SearchWordDao;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * Author: yuichi_katahira
 */
public class SearchWordManageActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private SearchWordDao searchWordDao;
    private ArrayList<String> wordList;
    private ArrayAdapter<String> wordAdapter;
    private EditText searchWordText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_word_manage);
        setTitle(getString(R.string.app_name) + " : Delete search word");

        listView = (ListView) findViewById(R.id.search_word_manage_list);
        Button searchButton = (Button) findViewById(R.id.search_word_search_button);
        searchWordText = (EditText) findViewById(R.id.manage_search_word_text);

        DBOpenHelper dbHelper = new DBOpenHelper(this);
        searchWordDao = new SearchWordDao(dbHelper.getWritableDatabase());
        wordList = searchWordDao.all();
        wordAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordList);

        searchButton.setOnClickListener(this);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(this);
        listView.requestFocus();
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideIME();
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
    }

    private void hideIME() {
        InputMethodManager manager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(searchWordText.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}