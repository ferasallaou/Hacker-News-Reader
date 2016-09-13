package ws.feras.hackernews;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Feras on 9/12/16.
 */
public class GetLatestNews extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... urls) {

        URL url;
        HttpURLConnection httpURLConnection;
        String result = "";
        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1)
                {
                char res = (char) data;
                    result += res;
                    data = reader.read();
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
        super.onPostExecute(s);
        String[] letGetInfo = getDataFromJson(s);
        MainActivity.titlesArray.clear();
        for (int n = 0; n <letGetInfo.length; n++)
        {
            GetDetails downloadInfo = new GetDetails();
            downloadInfo.execute("https://hacker-news.firebaseio.com/v0/item/"+ letGetInfo[n]+ ".json?print=pretty");
        }
    }

    protected String[] getDataFromJson(String s)
    {
        String[] finalResult = new String[20];
        try {
            JSONArray myArr = new JSONArray(s);
            for (int i=0; i < 20; i++)
            {
                finalResult[i] = myArr.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalResult;
    }
}
