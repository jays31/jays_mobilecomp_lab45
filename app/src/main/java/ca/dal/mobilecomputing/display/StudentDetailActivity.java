package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentAdapter;
import ca.dal.mobilecomputing.db.Database;

public class StudentDetailActivity extends AppCompatActivity {
    private Activity mActivity;

    ListView courseList;
    TextView studentName, studentID, studentAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        mActivity = this;
        studentName = findViewById(R.id.lblStudentName);
        studentID = findViewById(R.id.lblStudentB00);
        studentAge = findViewById(R.id.lblStudentAge);
        courseList = findViewById(R.id.listview_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //Pull data from the intent/bundle
        String id = getIntent().getStringExtra("studentID");
        Bundle dataBundle = getIntent().getBundleExtra("bundle");

        //Pull data from the bundle; allows for default values.
        String name = dataBundle.getString("name", "N/A");
        int age = dataBundle.getInt("age", -1);

        setSupportActionBar(toolbar);
        studentName.setText(name);
        studentID.setText(id);
        studentAge.setText(String.valueOf(age));
        courseList.setAdapter(new StudentAdapter(mActivity, new Database().getCoursesByStudentId(id)));
    }
}
