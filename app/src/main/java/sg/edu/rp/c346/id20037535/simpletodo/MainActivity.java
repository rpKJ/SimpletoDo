package sg.edu.rp.c346.id20037535.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText task;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lvTask;
    Spinner spin;
    ArrayList<String> tasklist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        btnClear = findViewById(R.id.buttonClearItem);
        spin = findViewById(R.id.spinner);
        lvTask = findViewById(R.id.listViewTasks);

        ArrayAdapter aaTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tasklist);

        lvTask.setAdapter((aaTask));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newTask = task.getText().toString();
                tasklist.add(newTask);
                aaTask.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(tasklist.isEmpty()){
                    Toast.makeText(getBaseContext(), "You don't have any task to remove.", Toast.LENGTH_SHORT).show();
                }else if(tasklist.size() < Integer.parseInt(task.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Wrong index number", Toast.LENGTH_SHORT).show();
                }else{
                    String newTask = task.getText().toString();
                    int pos = Integer.parseInt(newTask);
                    tasklist.remove(pos);
                    aaTask.notifyDataSetChanged();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tasklist.clear();
                aaTask.notifyDataSetChanged();
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        task.setText("");
                        task.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        task.setText("");
                        task.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }
}