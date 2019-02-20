package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentCourseAdapter;
import ca.dal.mobilecomputing.db.Database;
import ca.dal.mobilecomputing.model.StudentModel;

public class EditPageActivity extends AppCompatActivity {
    private Activity mActivity;
    private Database database;
    private StudentModel student;

    TextView studentID2, studentDisplay2, studentName2, studentAge2;
    EditText editName, editAge;
    Button save2;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);

        mActivity   = this;
        database = new Database(mActivity);

        studentID2   = findViewById(R.id.lblB002);
        studentDisplay2 = findViewById(R.id.lblStudentB002);
        studentName2 = findViewById(R.id.lblName2);
        studentAge2  = findViewById(R.id.lblAge2);

        editName = findViewById(R.id.lblStudentName2);
        editAge = findViewById(R.id.lblStudentAge2);

        save2 = findViewById(R.id.save2);

        setSupportActionBar(toolbar);

        String studentB00 = getIntent().getStringExtra("B00");
        student = database.getStuModelByID(studentB00);

        studentDisplay2.setText(student.getStudent_Id());

        editName.setText(student.getName());
        editAge.setText(String.valueOf(student.getAge()));

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAge = Integer.parseInt(editAge.getText().toString());
                student.setAge(newAge);
                student.setName(editName.getText().toString());

                if(database.updateStudent(student)){
                    Intent detailsIntent = new Intent(EditPageActivity.this,StudentDetailActivity.class);
                    Bundle detailsBundle = new Bundle();
                    detailsIntent.putExtra("studentId",student.getStudent_Id());
                    detailsBundle.putString("name",student.getName());
                    detailsBundle.putInt("age",student.getAge());
                    detailsIntent.putExtra("bundle",detailsBundle);
                    startActivity(detailsIntent);
                }
                else{
                    Toast.makeText(mActivity,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}