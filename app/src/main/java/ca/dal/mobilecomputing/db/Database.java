package ca.dal.mobilecomputing.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ca.dal.mobilecomputing.model.CourseModel;
import ca.dal.mobilecomputing.model.StudentModel;

public class Database {

    private static final Map<String, String> STUDENT_NAME = new HashMap<>();
    private static final Map<String, Integer> STUDENT_AGE = new HashMap<>();
    private static final Map<String, String> COURSES_DATA = new HashMap<>();
    private List<StudentModel> studentModelList = new ArrayList<>();
    private List<CourseModel> courseModelList = new ArrayList<>();

    static {
        STUDENT_NAME.put("B00773778", "Mihir");
        STUDENT_NAME.put("B00771723", "Ryan");
        STUDENT_NAME.put("B00453723", "Omobola");
    }

    static {
        STUDENT_AGE.put("B00773778", 63);
        STUDENT_AGE.put("B00771723", 56);
        STUDENT_AGE.put("B00453723", 54);
    }

    static {
        COURSES_DATA.put("CSCI 6801", "BioInformatics");
        COURSES_DATA.put("CSCI 6511", "Autonomous Robotics");
        COURSES_DATA.put("CSCI 6505", "Machine Learning");
        COURSES_DATA.put("CSCI 5708", "Mobile Computing");
        COURSES_DATA.put("CSCI 5308", "Quality Assurance");
        COURSES_DATA.put("CSCI 5306", "Software Comprehension");
    }


    public Database() {
        for (String key : STUDENT_NAME.keySet()) {  //NAME and AGE use the same keyset.
            studentModelList.add(new StudentModel(key, STUDENT_NAME.get(key), STUDENT_AGE.get(key)));
        }

        // Create a list of courses
        Set<String> courses = COURSES_DATA.keySet();
        List<String> coursesList = new ArrayList<String>();
        coursesList.addAll(courses);

        for (StudentModel model : studentModelList) {
            Set<Integer> totalCourses = generateCourse(2, courses.size());
            for (Integer course : totalCourses) {
                CourseModel courseModel = new CourseModel();
                courseModel.setId(model.getStudent_Id());
                courseModel.setCourseId(coursesList.get(course));
                courseModel.setCourseName(COURSES_DATA.get(coursesList.get(course)));
                courseModelList.add(courseModel);
            }
        }
    }

    public List<StudentModel> getStudentModels() {
        return studentModelList;
    }

    public List<CourseModel> getCourseModels() {
        return courseModelList;
    }

    /**
     * Get students courses based on id
     * @param id id of the student
     * @return A list of courses the student is taking
     */
    public List<CourseModel> getCoursesByStudentId(String id) {
        List<CourseModel> studentCourseModel = new ArrayList<>();
        for (CourseModel courseModel : courseModelList) {
            if (courseModel.getId().equals(id)) {
                studentCourseModel.add(courseModel);
            }
        }
        return studentCourseModel;
    }

    /**
     * Randomly Generate courses taken by each student
     *
     * @param totalCoursesPerStudent - Total Courses for each student
     * @param maxCourses             - Total courses
     * @return - Set of Index of random courses
     */
    private Set<Integer> generateCourse(int totalCoursesPerStudent, int maxCourses) {
        Set<Integer> courseIndexList = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < maxCourses; i++) {
            courseIndexList.add(random.nextInt(totalCoursesPerStudent));
        }
        return courseIndexList;
    }
}
