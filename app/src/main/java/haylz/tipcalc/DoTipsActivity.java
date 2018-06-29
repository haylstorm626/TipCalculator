package haylz.tipcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class DoTipsActivity extends AppCompatActivity {

    EditText[] eachHours;
    double totMoney;
    double totHours;
    double[] hours;
    double[] money;
    double rate;
    int[] id = {R.id.hours0, R.id.hours1, R.id.hours2, R.id.hours3, R.id.hours4, R.id.hours5, R.id.hours6};
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_tips);

        final EditText totalMoney = (EditText)findViewById(R.id.totalMoney);
        final EditText totalHours = (EditText)findViewById(R.id.totalHours);

        //initialize employee list
        EmployeeList list = EmployeeList.getInstance();

        //final ListView theListView = (ListView) findViewById(R.id.listEmployees);

        //View empty = findViewById(R.id.textNoneSaved);
        //theListView.setEmptyView(empty);

        final List<Employee> dankList = list.load(getApplicationContext()); //= LOAD LIST
        size = dankList.size();

        hours = new double[size];
        money = new double[size];
        eachHours = new EditText[size];

        for(int i=0; i<size; i++){
            eachHours[i] = findViewById(id[i]);
            eachHours[i].setHint( dankList.get(i).name + "'s hours");
        }

        Button goButton = (Button)findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totMoney = Double.parseDouble(totalMoney.getText().toString());
                totHours = Double.parseDouble(totalHours.getText().toString());
                for(int i=0; i<size; i++){ hours[i] = Double.parseDouble(eachHours[i].getText().toString()); }
                calculate();
                Intent intent = new Intent(DoTipsActivity.this, TipResultActivity.class);
                Bundle extras = new Bundle();
                extras.putDoubleArray("extra_money", money);
                extras.putDouble("extra_rate", rate);
                intent.putExtras(extras);
                startActivity(intent);}
        });

        //EmployeeListAdapter adapter = new EmployeeListAdapter(this, dankList);
        //theListView.setAdapter(adapter);
    }

    public void calculate(){
        rate = totMoney/totHours;
        for(int i=0; i<size; i++){
            money[i] = rate*hours[i];
        }
    }
}