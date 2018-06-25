package haylz.tipcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class DoTipsActivity extends AppCompatActivity {

    String totMoney;
    String totHours;
    double[] hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_tips);

        //input for total money
        EditText totalMoney = (EditText)findViewById(R.id.totalMoney);
        totMoney = totalMoney.getText().toString();

        //input for total hours
        EditText totalHours = (EditText)findViewById(R.id.totalHours);
        totHours = totalHours.getText().toString();

        //initialize employee list
        EmployeeList list = EmployeeList.getInstance();
        final ListView theListView = (ListView) findViewById(R.id.listEmployees);

        View empty = findViewById(R.id.textNoneSaved);
        theListView.setEmptyView(empty);

        final List<Employee> dankList = list.load(getApplicationContext()); //= LOAD LIST

        EmployeeListAdapter adapter = new EmployeeListAdapter(this, dankList);
        theListView.setAdapter(adapter);
    }
}