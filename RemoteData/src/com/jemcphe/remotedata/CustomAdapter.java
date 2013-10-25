package com.jemcphe.remotedata;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	// Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Associate> associateList = null;
    private ArrayList<Associate> arraylist;
 
    /*
     * CustomAdapter Class is used to enhance the UI.
     * This gives me a more robust visual and a more meaningful
     * UI.
     */
    public CustomAdapter(Context context,
            List<Associate> associateList) {
        mContext = context;
        this.associateList = associateList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Associate>();
        this.arraylist.addAll(associateList);
    }
 
    // Define TextViews
    public class ViewHolder {
        TextView name;
        TextView phone;
        TextView email;
        TextView compId;
        TextView status;
    }
 
    /*********** Helper Methods ****************/
    @Override
    public int getCount() {  
        return associateList.size();
    }
 
    @Override
    public Associate getItem(int position) {
        return associateList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
        	// Create view holder
            holder = new ViewHolder();
            // inflate that view with custom list_row layout
            view = inflater.inflate(R.layout.list_row, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.list_name);
            holder.phone = (TextView) view.findViewById(R.id.list_phone);
            holder.email = (TextView) view.findViewById(R.id.list_email);
            holder.compId = (TextView) view.findViewById(R.id.list_compId);
            holder.status = (TextView) view.findViewById(R.id.list_status);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(associateList.get(position).getFirstName() + " " + associateList.get(position).getLastName());
        holder.phone.setText(associateList.get(position).getPhone());
        holder.email.setText(associateList.get(position).getEmail());
        holder.compId.setText(associateList.get(position).getCompId());
        holder.status.setText(associateList.get(position).getStatus());
 
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, ViewAssociateActivity.class);
                // Pass objectId data
                intent.putExtra("objectId", associateList.get(position).getId());
                // Pass firstName data
                intent.putExtra("firstName", (associateList.get(position).getFirstName()));
                // Pass lastName data
                intent.putExtra("lastName", (associateList.get(position).getLastName()));
                // Pass phone data
                intent.putExtra("phone", (associateList.get(position).getPhone()));
                // Pass email data
                intent.putExtra("email", (associateList.get(position).getEmail()));
             // Pass compId data
                intent.putExtra("compId", (associateList.get(position).getCompId()));
             // Pass status data
                intent.putExtra("status", (associateList.get(position).getStatus()));
                // Start ViewAssociateActivity Class
                mContext.startActivity(intent);
            }
        });
 
        return view;
    }
}
