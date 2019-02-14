package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentCourseAdapter;
import ca.dal.mobilecomputing.db.Database;

public class StudentDetailActivity extends AppCompatActivity {
    private Activity mActivity;

    ListView courseList;
    TextView studentName, studentID, studentAge;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        mActivity   = this;
        studentName = findViewById(R.id.lblStudentName);
        studentAge  = findViewById(R.id.lblStudentAge);
        studentID   = findViewById(R.id.lblStudentB00);
        courseList  = findViewById(R.id.listview_courses);

        String id = getIntent().getStringExtra("studentID");
        Bundle dataBundle = getIntent().getBundleExtra("bundle");

        String name = dataBundle.getString("name","N/A");
        int age = dataBundle.getInt("age",-1);

        setSupportActionBar(toolbar);
        studentName.setText(name);
        studentID.setText(id);
        studentAge.setText(String.valueOf(age));

        courseList.setAdapter(new StudentCourseAdapter(mActivity, new Database().getCoursesByStudentId(id)));

    }
}