package com.ajiashi.controlpanelstudent.post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiashi.controlpanelstudent.R;
import com.ajiashi.controlpanelstudent.mode.ModeResult;
import com.ajiashi.controlpanelstudent.mode.News;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class PostResults extends AppCompatActivity {

    private TextView tvDate, tvTime;
    private EditText editTextname , inputEditlink_result, inputEditText_src;
    private TextInputEditText editTextDescription;
    private Spinner spinner , spinner_class , spinneradverb;
    private Button btngosave;
    private String title;
    private CheckBox checkresult;
    private int number_class = 0;
    private int numberadverb = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_results);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

        String date = dateFormat.format(calendar.getTime());
        String ntime = timeFormat.format(calendar.getTime());
        String time = ntime.replace("am", "AM").replace("pm", "PM");

        btngosave = (Button)findViewById(R.id.btn_save_result);

        String[] items = getResources().getStringArray(R.array.muhafazat);
        spinner = (Spinner) findViewById(R.id.spinnerPriority);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        String[] items_class = getResources().getStringArray(R.array.classs);
        spinner_class = (Spinner) findViewById(R.id.spinnerClass);
        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items_class);
        adapter_class.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(adapter_class);

        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                number_class = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        String[] itemsadverb = getResources().getStringArray(R.array.spinneradverb);
        spinneradverb = (Spinner) findViewById(R.id.spinneradverb);
        ArrayAdapter<String> adapteradverb = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemsadverb);
        adapteradverb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneradverb.setAdapter(adapteradverb);

        spinneradverb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                numberadverb = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        //editTextname = findViewById(R.id.name_muhafazat);
        inputEditText_src = findViewById(R.id.src_result);
        inputEditlink_result = findViewById(R.id.link_result);
        checkresult = findViewById(R.id.check_result);
        btngosave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String title = editTextname.getText().toString();
                String text_link = inputEditlink_result.getText().toString();
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String src = inputEditText_src.getText().toString();
                String priority = spinner.getSelectedItem().toString();
                boolean check = false;
                if (checkresult.isChecked()){
                    check = true;
                }else {
                    check = false;
                }

                if (priority.trim().isEmpty()){
                    Toast.makeText(PostResults.this,"برجاء ملاء الحقول",Toast.LENGTH_SHORT).show();
//                }else if (text_link.trim().isEmpty()){
//                    Toast.makeText(PostResults.this,"برجاء ملاء الحقول",Toast.LENGTH_SHORT).show();
//                }else if (src.trim().isEmpty()){
//                    Toast.makeText(PostResults.this,"برجاء ملاء الحقول",Toast.LENGTH_SHORT).show();
                }else {
                    saveResultData(priority,text_link,src,check,number_class,numberadverb);
                }

            }
        });

    }

    private void saveResultData(String name_maheft ,String link_result,String  src ,boolean chekc_rsult,int number,int numberadverb){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ModeResult modeResult = new ModeResult(name_maheft, chekc_rsult,link_result , src,number,numberadverb);

        db.collection("result")
                .add(modeResult)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(PostResults.this,"تم النشر",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostResults.this,"فشل النشر",Toast.LENGTH_LONG).show();
                    }
                });

        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
    }

}