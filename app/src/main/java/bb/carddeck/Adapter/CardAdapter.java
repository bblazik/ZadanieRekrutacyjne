package bb.carddeck.Adapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bb.carddeck.R;
import bb.carddeck.model.Card;

public class CardAdapter extends ArrayAdapter<Card> {
    private final List<Card> list;
    private final Activity context;

    class ViewHolder {
        protected TextView name;
        protected ImageView cardImg;
    }

    public CardAdapter(Activity context, List<Card> ls) {
        super(context, R.layout.card_row, ls);
        this.context = context;
        this.list = ls;
    }

    public CardAdapter(Activity context){
        super(context, R.layout.card_row);
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void setItems(List<Card> cards) { //TODO check if there is a better way Observer?
        list.clear();
        list.addAll(cards);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.card_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.cardName);
            viewHolder.cardImg = (ImageView) view.findViewById(R.id.cardImg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getCode());
        holder.cardImg.setImageDrawable(LoadImageFromWebOperations(list.get(position).getImageUrl()));
        return view;
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
