package ca.dal.mobilecomputing.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import ca.dal.mobilecomputing.R;
import ca.dal.mobilecomputing.model.CourseModel;
import ca.dal.mobilecomputing.model.StudentModel;


public class StudentCourseAdapter extends BaseAdapter {

    private List<StudentModel> mStudentModelList;
    private List<CourseModel> mCourseModelList;
    private boolean mIsStudent;
    private Activity mActivity;

    public StudentCourseAdapter(Activity activity, List<StudentModel> studentModelList, boolean isStudent) {
        this.mActivity = activity;
        this.mStudentModelList = studentModelList;
        mIsStudent = isStudent;
    }

    public StudentCourseAdapter(Activity activity, List<CourseModel> courseModelList) {
        this.mActivity = activity;
        this.mCourseModelList = courseModelList;
    }

    @Override
    public int getCount() {
        return (mIsStudent ? mStudentModelList.size() : mCourseModelList.size());
    }

    @Override
    public Object getItem(int position) {
        return (mIsStudent ? mStudentModelList.get(position) : mCourseModelList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            viewholder = new ViewHolder();

            viewholder.textName = convertView.findViewById(R.id.text_name);
            viewholder.textId = convertView.findViewById(R.id.text_id);

            convertView.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        if (mIsStudent) setStudentData(viewholder, mStudentModelList.get(position));
        else setCourseData(viewholder, mCourseModelList.get(position));
        return convertView;
    }

    /**
     * Set name and id
     *
     * @param viewholder
     * @param studentModel
     */
    private void setStudentData(ViewHolder viewholder, StudentModel studentModel) {
        viewholder.textName.setText(studentModel.getName());
        viewholder.textId.setText(studentModel.getStudent_Id());
    }

    /**
     * Set course name and id
     *
     * @param viewholder
     * @param courseModel
     */
    private void setCourseData(ViewHolder viewholder, CourseModel courseModel) {
        viewholder.textName.setText(courseModel.getCourseName());
        viewholder.textId.setText(courseModel.getCourseId());
    }

    private static class ViewHolder {
        private TextView textName;
        private TextView textId;
    }

}
