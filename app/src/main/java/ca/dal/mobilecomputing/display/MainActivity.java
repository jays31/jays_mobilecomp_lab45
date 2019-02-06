package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentCourseAdapter;
import ca.dal.mobilecomputing.db.Database;
import ca.dal.mobilecomputing.model.StudentModel;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView studentList = findViewById(R.id.listview_students);
        final Database database = new Database();
        studentList.setAdapter(new StudentCourseAdapter(mActivity, database.getStudentModels(), true));


        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {
                Intent detailsIntent = new Intent(MainActivity.this, StudentDetailActivity.class);
                Bundle detailsBundle = new Bundle();

                //Using index of list item, look at the item's data model and get the student object
                StudentModel currStudent = database.getStudentModels().get(listIndex);
                String studentID = currStudent.getId();
                String studentName = currStudent.getName();
                int studentAge = currStudent.getAge();

                //One item in intent
                detailsIntent.putExtra("studentID", studentID);

                //The rest will go into the bundle
                detailsBundle.putString("name", studentName);
                detailsBundle.putInt("age", studentAge);

                //Put the bundle in the intent
                detailsIntent.putExtra("bundle", detailsBundle);

                startActivity(detailsIntent);
            }
        });

    }

}
