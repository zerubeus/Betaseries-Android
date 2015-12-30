package Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.example.alaeddine.restapp.AppController;

import java.util.ArrayList;

import Model.Series;

/**
 * Created by alaeddine on 30/12/2015.
 */
public class CustomListViewAdapter extends ArrayAdapter<Series> {

    private LayoutInflater inflater;
    private ArrayList<Series> data;
    private Activity mContext;
    private int layoutResourceId;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListViewAdapter(Context context, int resource, int textViewResourceId, Series[] objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
