package com.example.alaeddine.restapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Data.CustomListViewAdapter;
import Model.Series;


public class MainActivity extends AppCompatActivity {

    private CustomListViewAdapter adapter;
    private ArrayList<Series> series = new ArrayList<>();
    private ArrayList<String> seriesIds;
    private ListView listView;
    private TextView selectedCity;

    private String urlSerie = "https://api.betaseries.com/shows/list?key=cf4258cf28b7&limit=20&start=100&format=json";
    private String urlSerieImage = "https://api.betaseries.com/shows/pictures?key=cf4258cf28b7&id=";
    private String urlSerieInfo = "http://api.betaseries.com/shows/display?key=cf4258cf28b7&id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListViewAdapter(MainActivity.this, R.layout.list_row, series);
        listView.setAdapter(adapter);
        //getSeries(urlGenerator(seriesIds));
        getAllSeriesId(new VolleyCallback() {
            @Override
            public void onSuccess(ArrayList<String> result) {
                getSeries(urlGenerator(result));
            }
        });

        //urlGenerator(seriesIds);

    }


    // I dont know if this is the best whay to do the trick but yeah .. I hope
    private void getAllSeriesId(final VolleyCallback callback) {
        seriesIds = new ArrayList<>();
        String finalIdUrl = urlSerie;
        JsonObjectRequest seriesIdRequest = new JsonObjectRequest(Request.Method.GET, finalIdUrl, (JSONObject)null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray arryOfIds = response.getJSONArray("shows");

                    for (int i = 0; i < arryOfIds.length(); i++) {
                        seriesIds.add(arryOfIds.getJSONObject(i).get("id").toString());
                    }
                    callback.onSuccess(seriesIds);
                    //Log.v("lolll : ", oneId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(seriesIdRequest);
    }


    private ArrayList<String> urlGenerator(ArrayList<String> ids) {

        final ArrayList<String> SeriesUrl = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            SeriesUrl.add(urlSerieInfo + ids.get(i));
        }
        return SeriesUrl;
    }


    private void getSeries(ArrayList<String> Urls) {
        //clear data first
        series.clear();
        // String finalUrl = urlSerieInfo+id;

        JsonObjectRequest seriesRequest;

        for (int i=0; i < Urls.size(); i++) {

             seriesRequest = new JsonObjectRequest(Request.Method.GET, Urls.get(i), (JSONObject)null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject showObject = response.getJSONObject("show");
                        String title = showObject.getString("title");
                        String type = showObject.getJSONArray("genres").get(0).toString();
                        String info = showObject.getString("description");
                        String url = showObject.getString("resource_url");
                        String seasons = showObject.getString("seasons");
                        String network = showObject.getString("network");



                        String imgUrl = urlSerieImage+showObject.getString("id");

                        Log.v("Data ID: ", showObject.getString("id"));
                        Log.v("Data: ", title);
                        Log.v("Data: ", type);
                        Log.v("Data: ", info);
                        Log.v("Data: ", url);
                        Log.v("Data: ", seasons);
                        Log.v("Data: ", network);
                        Log.v("Data: ", imgUrl);

                        Series serie = new Series();

                        serie.setTitle(title);
                        serie.setType(type);
                        serie.setInfo(info);
                        serie.setUrl(url);
                        serie.setSeasons(seasons);
                        serie.setNetwork(network);
                        serie.setSeriesImage(imgUrl);

                        series.add(serie);

                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            AppController.getInstance().addToRequestQueue(seriesRequest);

        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface VolleyCallback{
        void onSuccess(ArrayList<String> result);
    }
}
