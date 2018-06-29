package haylz.tipcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class TipResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_result);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        double[] money = extras.getDoubleArray("extra_money");
        double rate = extras.getDouble("extra_rate");

        Log.i("TipResultActivity", "money" + Arrays.toString(money));
        Log.i("TipResultActivity", "rate" + rate);

        //display rate
        //display employee list with hours
    }
}
