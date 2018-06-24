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


public class EmployeeListAdapter extends BaseAdapter {


    private List<Employee> mItems;
    private Context mContext;
    private LayoutInflater mInflater;

    public EmployeeListAdapter(Context context, List<Employee> items) {
        mContext = context;
        mItems = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public ImageView delImageView;
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
            convertView = mInflater.inflate(R.layout.employee_list_adapter, parent, false);

            holder = new ViewHolder();
            holder.delImageView = (ImageView) convertView.findViewById(R.id.delImageView);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.subtitleTextView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        ImageView delImageView = holder.delImageView;

        final Employee item = mItems.get(position);

        titleTextView.setText(item.name);
        subtitleTextView.setText(item.number);


        holder.delImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(v, item);
            }
        });

        return convertView;
    }

    //dialog for deleting from employeelist
    public void deleteDialog(View view, Employee mItem) {
        final View v = view;
        final Employee item = mItem;
        new AlertDialog.Builder(mContext)
                .setTitle("Delete employee")
                .setMessage("Are you sure you want to delete '" + item.name + " from employee list?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EmployeeList list = EmployeeList.getInstance();
                        list.delete(item, mContext);
                        Toast.makeText(v.getContext(), "Deleted " + item.name + " from your list", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        // delete entry
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
