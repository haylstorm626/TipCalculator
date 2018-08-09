package haylz.tipcalc;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class TipListAdapter extends BaseAdapter {


    private List<Employee> mItems;
    private Context mContext;
    private LayoutInflater mInflater;

    public TipListAdapter(Context context, List<Employee> items) {
        mContext = context;
        mItems = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView hoursTextView;
        public TextView tipsTextView;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.tip_list_adapter, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.subtitleTextView);
            holder.hoursTextView = (TextView) convertView.findViewById(R.id.hoursTextView);
            holder.tipsTextView = (TextView) convertView.findViewById(R.id.tipsTextView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView hoursTextView = holder.hoursTextView;
        TextView tipsTextView = holder.tipsTextView;

        final Employee item = mItems.get(position);

        titleTextView.setText(item.name);
        subtitleTextView.setText(item.number);
        hoursTextView.setText(Double.toString(item.hours));
        tipsTextView.setText(Double.toString(item.tips));

        return convertView;
    }

}
