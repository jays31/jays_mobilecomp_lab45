package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentAdapter;
import ca.dal.mobilecomputing.db.Database;

public class StudentDetailActivity extends AppCompatActivity {
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        String id = getIntent().getStringExtra("studentID");

        mActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView studentList = findViewById(R.id.listview_courses);

        studentList.setAdapter(new StudentAdapter(mActivity, new Database().getCoursesByStudentId(id)));
    }
}
