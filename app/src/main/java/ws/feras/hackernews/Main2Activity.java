package ws.feras.hackernews;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class Main2Activity extends AppCompatActivity {
    static WebView myWebView;
    static ProgressBar myBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myWebView = (WebView) findViewById(R.id.webView);
        myBar = (ProgressBar) findViewById(R.id.progressBar);

        myWebView.setVisibility(View.GONE);
        myBar.setVisibility(View.VISIBLE);

        ActionBar getThatActionBar = getSupportActionBar();
        getThatActionBar.setDisplayHomeAsUpEnabled(true);

        Intent getBase = getIntent();
        String getItemsUrl = getBase.getStringExtra("url");
        String getItemTitle = getBase.getStringExtra("title");
        getThatActionBar.setTitle(getItemTitle);

        DownloadURLContent getUrlContent = new DownloadURLContent();
        getUrlContent.execute(getItemsUrl);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int getID = item.getItemId();
        if (getID == android.R.id.home)
        {
            this.finish();

        }
        return true;
    }
}
