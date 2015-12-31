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
import java.util.ArrayList;

import Data.CustomListViewAdapter;
import Model.Series;


public class MainActivity extends AppCompatActivity {

    private CustomListViewAdapter adapter;
    private ArrayList<Series> series = new ArrayList<>();
    private ListView listView;
    private TextView selectedCity;

    private String urlSerie = "https://api.betaseries.com/shows/list?key=cf4258cf28b7&limit=50&start=50&format=json";
    private String urlSerieImage = "https://api.betaseries.com/shows/pictures?key=cf4258cf28b7&id=";
    private String urlSerieInfo = "http://api.betaseries.com/shows/display?key=cf4258cf28b7&id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSeries("609");



    }

    /*
    // I dont know if this is the best whay to do the trick but yeah .. I hope
    private ArrayList<String> getAllSeriesId() {
        final ArrayList<String> seriesIds = new ArrayList<>();
        String finalIdUrl = urlSerie;
        JsonObjectRequest seriesIdRequest = new JsonObjectRequest(Request.Method.GET, finalIdUrl, (JSONObject)null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray arryOfIds = response.getJSONArray("shows");

                    for (int i = 0; i < arryOfIds.length(); i++) {
                        seriesIds.add(arryOfIds.getJSONObject(i).get("id").toString());
                    }
                    //Log.v("Lol : ", arryOfIds.toString());
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
        return seriesIds;
    } */

    /*
    private ArrayList<String> urlGenerator(ArrayList<String> ids) {
        final ArrayList<String> SeriesUrl = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            SeriesUrl.add(urlSerieInfo+ids.get(i));
        }

        return SeriesUrl;
    } */


    private void getSeries(String id) {
        //clear data first
        series.clear();
        String finalUrl = urlSerieInfo+id;

            JsonObjectRequest seriesRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, (JSONObject)null, new Response.Listener<JSONObject>() {
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

                        Log.v("Data: ", title);
                        Log.v("Data: ", type);
                        Log.v("Data: ", info);
                        Log.v("Data: ", url);
                        Log.v("Data: ", seasons);
                        Log.v("Data: ", network);
                        Log.v("Data: ", imgUrl);

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
}
