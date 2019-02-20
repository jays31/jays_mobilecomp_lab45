package ca.dal.mobilecomputing.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ca.dal.mobilecomputing.model.StudentModel;

public class StudentDataQueries {
    public static final String IS_FIRST_TIME = "is first time";
    public static final String SHARED_PREF_STUDENT = "shared pref student";
    private final Context mContext;
    private SQLiteDatabase database;
    private StudentReaderDbHelper dbHelper;
    private String[] studentColumns = { StudentReaderDbHelper.StudentEntry._ID,
            StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_ID,
            StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_NAME,
            StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_AGE };

    private String[] courseColumns = { StudentReaderDbHelper.CourseEntry._ID,
            StudentReaderDbHelper.CourseEntry.COLUMN_COURSE_NAME,
            StudentReaderDbHelper.CourseEntry.COLUMN_COURSE_CODE };

    public StudentDataQueries (Context context) {
        mContext = context;
        dbHelper = new StudentReaderDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public StudentModel createStudent (StudentModel studentModel) {
        ContentValues values = new ContentValues();
        values.put(StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_ID, studentModel.getStudent_Id());
        values.put(StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_NAME, studentModel.getName());
        values.put(StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_AGE, studentModel.getAge());

        long insertID = database.insert(StudentReaderDbHelper.StudentEntry.TABLE_NAME, null,
                values);

        Log.i(getClass().getName(),String.valueOf(insertID));

        Cursor cursor = database.query(StudentReaderDbHelper.StudentEntry.TABLE_NAME,
                studentColumns, StudentReaderDbHelper.StudentEntry._ID + " = " + insertID, null,
                null, null, null);

        cursor.moveToFirst();
        StudentModel studentDataFromCursor = getStudentDataFromCursor(cursor);
        cursor.close();

        return studentDataFromCursor;
    }

    public boolean updateStudent(StudentModel studentModel) {
        ContentValues cv = new ContentValues();
        cv.put(StudentReaderDbHelper.StudentEntry._ID, studentModel.getId());
        cv.put(StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_ID, studentModel.getStudent_Id());
        cv.put(StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_NAME, studentModel.getName());
        cv.put(StudentReaderDbHelper.StudentEntry.COLUMN_STUDENT_AGE, studentModel.getAge());

        return database.update(StudentReaderDbHelper.StudentEntry.TABLE_NAME, cv,
                StudentReaderDbHelper.StudentEntry._ID + " = " + studentModel.getId(), null) > 0;
    }

    public boolean deleteStudent (StudentModel studentModel) {
        long id = studentModel.getId();
        return database.delete(StudentReaderDbHelper.StudentEntry.TABLE_NAME,
                StudentReaderDbHelper.StudentEntry._ID + " = " + id, null) > 0;
    }

    public List<StudentModel> getAllStudents() {
        List<StudentModel> studentModelList = new ArrayList<StudentModel>();

        Cursor cursor = database.query(StudentReaderDbHelper.StudentEntry.TABLE_NAME,
                studentColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            StudentModel studentModel = getStudentDataFromCursor (cursor);
            studentModelList.add(studentModel);
            cursor.moveToNext();
        }
        cursor.close();
        return studentModelList;
    }

    private StudentModel getStudentDataFromCursor (Cursor cursor) {
        return new StudentModel(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                cursor.getInt(3));
    }

    public boolean isAppRunningFirstTime() {
        boolean isFirst = mContext.getSharedPreferences(SHARED_PREF_STUDENT,
                Context.MODE_PRIVATE).getBoolean(IS_FIRST_TIME, true);

        if(isFirst) {
            mContext.getSharedPreferences(SHARED_PREF_STUDENT, Context.MODE_PRIVATE).edit().putBoolean(IS_FIRST_TIME, false).apply();
        }

        return isFirst;
    }
}