package ca.dal.mobilecomputing.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class StudentReaderDbHelper extends SQLiteOpenHelper {

    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_STUDENT_ID = "student_id";
        public static final String COLUMN_STUDENT_NAME = "student_name";
        public static final String COLUMN_STUDENT_AGE = "student_age";

    }

    public static abstract class CourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "course";
        public static final String COLUMN_COURSE_NAME = "course_name";
        public static final String COLUMN_COURSE_CODE = "course_code";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_STUDENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + StudentEntry.TABLE_NAME + " (" + StudentEntry._ID +
                    " INTEGER PRIMARY KEY" + COMMA_SEP + StudentEntry.COLUMN_STUDENT_ID + TEXT_TYPE + COMMA_SEP + StudentEntry.COLUMN_STUDENT_NAME + TEXT_TYPE + COMMA_SEP + StudentEntry.COLUMN_STUDENT_AGE + INTEGER_TYPE + " )";

    private static final String SQL_CREATE_COURSE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + StudentEntry.TABLE_NAME + " (" + CourseEntry._ID + " " +
                    " INTEGER PRIMARY KEY" + COMMA_SEP + CourseEntry.COLUMN_COURSE_CODE + TEXT_TYPE + COMMA_SEP + CourseEntry.COLUMN_COURSE_CODE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_STUDENT =
            "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;

    private static final String SQL_DELETE_COURSE =
            "DROP TABLE IF EXISTS " + CourseEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Students.db";

    public StudentReaderDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate (SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDENT_TABLE);
        db.execSQL(SQL_CREATE_COURSE_TABLE);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_STUDENT);
        db.execSQL(SQL_DELETE_STUDENT);
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
