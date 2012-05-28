package jp.katahirado.android.tsubunomi.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import jp.katahirado.android.tsubunomi.Const;
import jp.katahirado.android.tsubunomi.R;
import jp.katahirado.android.tsubunomi.SharedManager;
import jp.katahirado.android.tsubunomi.TweetManager;
import jp.katahirado.android.tsubunomi.activity.TsubunomiActivity;

/**
 * Created with IntelliJ IDEA.
 * Author: yuichi_katahira
 */
public class MenuDialog extends Dialog implements AdapterView.OnItemClickListener {
    protected Activity activity;
    protected TweetManager tweetManager;
    private String[] menuItems;
    protected static final int REPLY = 0;
    protected SharedManager sharedManager;

    public MenuDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dialog);

        sharedManager = new SharedManager(activity.getSharedPreferences(Const.PREFERENCE_NAME,
                activity.MODE_PRIVATE));
        tweetManager = new TweetManager(sharedManager);
        ListView menuList = (ListView) findViewById(R.id.menu_dialog_list);
        menuItems = new String[]{activity.getString(R.string.reply), activity.getString(R.string.retweet)};
        menuList.setAdapter(new ArrayAdapter<String>(activity.getApplicationContext(),
                android.R.layout.simple_list_item_1, menuItems));
        menuList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    }

    protected void replyToStartActivity(long id, String screenName, String message) {
        Intent intent = new Intent(activity, TsubunomiActivity.class);
        intent.putExtra(Const.IN_REPLY_TO_STATUS_ID, id);
        intent.putExtra(Const.SCREEN_NAME, screenName);
        intent.putExtra(Const.MESSAGE, message);
        activity.startActivity(intent);
    }
}
