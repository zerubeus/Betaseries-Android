package Data;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.alaeddine.restapp.AppController;
import com.example.alaeddine.restapp.R;

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

    public CustomListViewAdapter(Activity context, int resource, ArrayList<Series> objs) {
        super(context, resource, objs);
        data = objs;
        mContext = context;
        layoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getPosition(Series item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Series getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row  = convertView;
        ViewHolder viewHolder = null;

        if ( row == null) {
            inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(layoutResourceId, parent, false);

            viewHolder = new ViewHolder();

            //Get referenes to our view
            viewHolder.seriesImage = (NetworkImageView)row.findViewById(R.id.seriesImage);
            viewHolder.title = (TextView)row.findViewById(R.id.headLinerText);
            viewHolder.type = (TextView)row.findViewById(R.id.sereiesType);
            viewHolder.when = (TextView)row.findViewById(R.id.seriesDate);
            viewHolder.season = (TextView)row.findViewById(R.id.seriesSeasons);

            row.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.serie = data.get(position);

        // we can now display data

        viewHolder.title.setText("Serie name : " + viewHolder.serie.getTitle());
        viewHolder.type.setText("Type : " + viewHolder.serie.getType());
        viewHolder.when.setText(" When : " + viewHolder.serie.getDate());
        viewHolder.season.setText("Season : " + viewHolder.serie.getSeasons());
        viewHolder.seriesImage.setImageUrl(viewHolder.serie.getUrl(), imageLoader);



        return row;
    }


    public class ViewHolder {
        Series serie;
        TextView title;
        TextView type;
        TextView when;
        TextView season;
        NetworkImageView seriesImage;
    }

}
