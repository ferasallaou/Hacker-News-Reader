package ws.feras.hackernews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> titlesArray;
    static ArrayList<String> idsArray;
    static ArrayList<String> urlsArray;
    static ArrayAdapter mainAdapter;
    static ListView mainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlesArray = new ArrayList<>();
        idsArray = new ArrayList<>();
        urlsArray = new ArrayList<>();

        titlesArray.add("No Items Yet ! ");
        mainListView = (ListView) findViewById(R.id.listView);
        mainAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titlesArray);
        mainListView.setAdapter(mainAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent = new Intent(getBaseContext(), Main2Activity.class);
                myIntent.putExtra("url", urlsArray.get(i));
                myIntent.putExtra("title", titlesArray.get(i));
                startActivity(myIntent);
            }
        });
        GetLatestNews dc = new GetLatestNews();
        dc.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
    }


}
