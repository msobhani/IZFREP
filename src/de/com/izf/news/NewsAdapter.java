package de.com.izf.news;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.com.izf.R;
import de.com.izf.network.ImageDownloader;
 
public class NewsAdapter extends ArrayAdapter<News> {
 
        private final Context context;
        private final ArrayList<News> itemsArrayList;
 
        public NewsAdapter(Context context, ArrayList<News> itemsArrayList) {
 
            super(context, R.layout.news_item, itemsArrayList);
 
            this.context = context;
            this.itemsArrayList = itemsArrayList;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            // 1. Create inflater 
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater
            View rowView = inflater.inflate(R.layout.news_item, parent, false);
 
            // 3. Get the two text view from the rowView
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
            TextView txtIntro = (TextView) rowView.findViewById(R.id.txtIntro);
            ImageView imgThumb = (ImageView) rowView.findViewById(R.id.imgNewsItem);
 
            // 4. Set the text for textView 
            txtTitle.setText(itemsArrayList.get(position).getTitle());
            txtIntro.setText(itemsArrayList.get(position).getIntroText());
            ImageDownloader temp = new ImageDownloader(imgThumb,context,
            		itemsArrayList.get(position).getThumbImage());
            try {
				temp.setImag();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
            // 5. retrn rowView
            return rowView;
        }
}