package haylz.tipcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmployeeListActivity extends AppCompatActivity {

    String[] tempTestArray = {"Mike","Aimee","Doug","Will"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.employee_listview, tempTestArray);

        ListView listView = findViewById(R.id.listEmployees);
        listView.setAdapter(adapter);

    }
}
