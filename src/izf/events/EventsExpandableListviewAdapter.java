package izf.events;


import izf.network.ImageDownloader;

import java.util.HashMap;
import java.util.List;

import com.example.izf.R;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class EventsExpandableListviewAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<Events> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Events, List<String>> _listDataChild;
 
    public EventsExpandableListviewAdapter(Context context, List<Events> listDataHeader,
            HashMap<Events, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.news_detail_items, null);
        }
        
        TextView txtIntro = (TextView) convertView.findViewById(R.id.txtIntro);
        txtIntro.setText(_listDataHeader.get(groupPosition).getIntroText());
        
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.txtBody);
 
        txtListChild.setText(clearText(_listDataHeader.get(groupPosition).getBody()));
        return convertView;
    }
    
    private String clearText(String input)
    {
    	String ans = "";
    	boolean check = false;
    	for(int i = 0 ;i < input.length() ; i++)
    	{
    		if(input.charAt(i) == '<')
    			check = true;
    		if(!check)
    		{
    			ans += input.charAt(i);
    		}
    		if(input.charAt(i) == '>')
    			check = false;
    	}
    	return ans;
    }
    
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
    	
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.news_item, null);
        }
        
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        //TextView txtIntro = (TextView) convertView.findViewById(R.id.txtIntro);
        ImageView imgThumb = (ImageView) convertView.findViewById(R.id.imgNewsItem);
 
        txtTitle.setText(_listDataHeader.get(groupPosition).getTitle());
        //txtIntro.setText(_listDataHeader.get(groupPosition).getIntroText());
        ImageDownloader temp = new ImageDownloader(imgThumb);
        temp.execute(_listDataHeader.get(groupPosition).getThumbImage());
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
