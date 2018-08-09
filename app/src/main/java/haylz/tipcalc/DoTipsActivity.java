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

    //functioning edittext features commented out

    EditText[] eachHours;
    double totMoney;
    double totHours;
    double rate;
    
    //
    int[] id = {R.id.hours0, R.id.hours1, R.id.hours2, R.id.hours3, R.id.hours4, R.id.hours5, R.id.hours6, R.id.hours7, R.id.hours8, R.id.hours9, R.id.hours10};

    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_tips);

        final EditText totalMoney = (EditText)findViewById(R.id.totalMoney);
        final EditText totalHours = (EditText)findViewById(R.id.totalHours);

        EmployeeList list = EmployeeList.getInstance();

        final List<Employee> dankList = list.load(getApplicationContext()); //= LOAD LIST
        size = dankList.size();

        eachHours = new EditText[size];

        /**/
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

                /**/
                for(int i=0; i<size; i++){
                    dankList.get(i).hours = Double.parseDouble(eachHours[i].getText().toString());
                }

                calculate(dankList);
                Intent intent = new Intent(DoTipsActivity.this, TipResultActivity.class);
                Bundle extras = new Bundle();
                extras.putDouble("extra_rate", rate);
                intent.putExtras(extras);
                startActivity(intent);}
        });
    }

    public void calculate(List<Employee> dankList){
        rate = totMoney/totHours;
        for(int i=0; i<size; i++){
            dankList.get(i).tips = rate*dankList.get(i).hours;
        }
    }
}