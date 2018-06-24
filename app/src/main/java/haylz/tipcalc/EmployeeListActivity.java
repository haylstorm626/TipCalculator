package haylz.tipcalc;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    //String[] tempTestArray = {"Mike","Aimee","Doug","Will"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        EmployeeList list = EmployeeList.getInstance();
        final ListView theListView = (ListView) findViewById(R.id.listEmployees);

        View empty = findViewById(R.id.textNoneSaved);
        theListView.setEmptyView(empty);

        final List<Employee> dankList = list.load(getApplicationContext()); //= LOAD LIST

        EmployeeListAdapter adapter = new EmployeeListAdapter(this, dankList);
        theListView.setAdapter(adapter);

        FloatingActionButton addEmployeeButton = findViewById(R.id.addEmployeeButton);
        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee(dankList);
            }
        });

    }

    public void addEmployee(List<Employee> list){
        final List<Employee> nlist = list;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Employee");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Set up the input
        final EditText inputName = new EditText(this);
        inputName.setHint("Employee name");
        inputName.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(inputName);

        // Set up the input
        final EditText inputNum = new EditText(this);
        inputNum.setHint("Employee id");
        inputNum.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(inputNum);



        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String num = inputNum.getText().toString();
                String name = inputName.getText().toString();
                int lNum = nlist.size() + 1;
                Employee emp = new Employee (name, num, lNum);
                nlist.add(emp);
                recreate();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setView(layout);
        builder.show();
    }

}
