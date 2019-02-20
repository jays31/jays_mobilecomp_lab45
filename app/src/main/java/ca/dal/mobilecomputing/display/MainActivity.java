package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentCourseAdapter;
import ca.dal.mobilecomputing.db.Database;
import ca.dal.mobilecomputing.model.StudentModel;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView studentList = findViewById(R.id.listview_students);
        database = new Database(mActivity);
        studentList.setAdapter(new StudentCourseAdapter(mActivity, database.getStudentModels(), true));

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3){
                Intent detailsIntent = new Intent(MainActivity.this, StudentDetailActivity.class);
                Bundle detailsBundle = new Bundle();
                StudentModel currStudent = database.getStudentModels().get(listIndex);
                String studentID = currStudent.getStudent_Id();
                String studentName = currStudent.getName();
                int studentAge = currStudent.getAge();

                detailsIntent.putExtra("studentID",studentID);
                detailsBundle.putString("name",studentName);
                detailsBundle.putInt("age",studentAge);
                detailsIntent.putExtra("bundle",detailsBundle);
                startActivity(detailsIntent);
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        database.closeDatabase();
    }
}
