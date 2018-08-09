package haylz.tipcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class TipResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_result);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        double rate = extras.getDouble("extra_rate");
        String iRate = Double.toString(rate);

        TextView rateTextView = (TextView) findViewById(R.id.rate);
        rateTextView.setText("Tip rate: " + iRate);

        EmployeeList list = EmployeeList.getInstance();
        final List<Employee> dopeList = list.load(getApplicationContext()); //= LOAD LIST

        int size = dopeList.size();

        final ListView theListView = (ListView) findViewById(R.id.listEmployees);

        TipListAdapter adapter = new TipListAdapter(this, dopeList);
        theListView.setAdapter(adapter);

    }
}
