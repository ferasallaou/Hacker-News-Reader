package ws.feras.hackernews;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Feras on 9/12/16.
 */
public class GetDetails extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection httpURLConnection;
        String result = "";
        try {
            url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while(data != -1)
            {
                char readData = (char) data;
                result += readData;

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

        String[] resultArr = new String[2];
        String getId = "";
        JSONObject myObj = null;

        try {
            myObj = new JSONObject(s);
             getId = myObj.getString("id");
            resultArr[0] = myObj.getString("title");
            resultArr[1] = myObj.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.titlesArray.add(resultArr[0]);
        MainActivity.urlsArray.add(resultArr[1]);
        MainActivity.idsArray.add(getId);
        MainActivity.mainAdapter.notifyDataSetChanged();
    }
}
