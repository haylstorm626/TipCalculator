package haylz.tipcalc;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class EmployeeList implements Serializable {

        private List<Employee> mItems;
        private final static EmployeeList myListInstance = new EmployeeList();
        public static EmployeeList getInstance() {
            return myListInstance;
        }

        private EmployeeList() {
        }

        public void add(Employee item, Context context){
            if (mItems == null){
                mItems = new ArrayList<>();
            }
            mItems.add(item);
            save(context);
            //accessed from TrackAdapter and AlbumTrackAdapter
        }
        public void delete(Employee item, Context context) {
            mItems.remove(item);
            save(context);

            //accessed from MyListActivity
        }

        public void save(Context context) {
            //save list

            /*
            SharedPreferences mPrefs = context.getSharedPreferences("theList", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(mItems);
            prefsEditor.putString("myJson", json);
            prefsEditor.apply();
            */

            //accessed from delete() and add()
        }

        public List<Employee> load(Context context) {
            if (mItems == null){
                mItems = new ArrayList<>();
                return mItems;
            }

            /*
            SharedPreferences mPrefs = context.getSharedPreferences("theList", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString("myJson", "");
            if (json.isEmpty()) {
                mItems = new ArrayList<>();
            } else {
                Type type = new TypeToken<List<Track>>() {}.getType();
                mItems = gson.fromJson(json, type);
            }*/

            return mItems;
        }
    }

