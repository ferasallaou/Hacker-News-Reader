package ws.feras.hackernews;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Feras on 9/12/16.
 */
public class DownloadURLContent extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection httpURLConnection;
        String result = "";

        try {
            url = new URL(strings[0]);
            //httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = url.openStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader readData = new BufferedReader(reader);

            String line = readData.readLine();

            while (line != null)
            {

                result += line;
                line = readData.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

    @Override
    protected void onPostExecute(String s) {
       Main2Activity.myWebView.getSettings().setJavaScriptEnabled(true);
        Main2Activity.myWebView.setWebViewClient(new WebViewClient());

        Main2Activity.myWebView.setVisibility(View.VISIBLE);
        Main2Activity.myBar.setVisibility(View.GONE);
        Main2Activity.myWebView.loadData(s, "text/html", "UTF-8");
        super.onPostExecute(s);
    }

}
