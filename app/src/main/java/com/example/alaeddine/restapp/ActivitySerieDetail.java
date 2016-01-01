package com.example.alaeddine.restapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import Model.Series;

public class ActivitySerieDetail extends AppCompatActivity {

    private Series serie;
    private TextView serieName;
    private TextView type;
    private TextView network;
    private TextView info;
    private NetworkImageView serieImage;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_serie_detail);

        serie = (Series) getIntent().getSerializableExtra("serieObj");

        serieName = (TextView) findViewById(R.id.detsHeadliner);
        serieImage = (NetworkImageView) findViewById(R.id.detsSerieImage);
        type = (TextView) findViewById(R.id.detsType);
        network = (TextView) findViewById(R.id.detsWhen);
        info = (TextView) findViewById(R.id.detsInfo);

        serieName.setText("Series Name : " + serie.getTitle());
        serieImage.setImageUrl(serie.getSeriesImage(), imageLoader);
        type.setText("Type : " + serie.getType());
        network.setText("Network : " + serie.getNetwork());
        info.setText("Info : " + serie.getInfo());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_serie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_websiteId) {

            String url = serie.getUrl();

            if (!url.equals("")) {
                Intent i =  new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Website not available", Toast.LENGTH_LONG).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }

}
