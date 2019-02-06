package ca.dal.mobilecomputing.display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.adapter.StudentAdapter;
import ca.dal.mobilecomputing.db.Database;

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
        studentList.setAdapter(new StudentAdapter(mActivity, database.getStudentModels(), true));


        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {
                Intent detailsIntent = new Intent(MainActivity.this, StudentDetailActivity.class);
                detailsIntent.putExtra("studentID", database.getStudentModels().get(listIndex).getId());

                startActivity(detailsIntent);
            }
        });

    }

}
