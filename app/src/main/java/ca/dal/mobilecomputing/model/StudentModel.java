package ca.dal.mobilecomputing.model;

public class StudentModel {

    private long id;
    private String student_id;
    private String name;
    private int age;

    public StudentModel(String id, String name, int age) {
        this.student_id = id;
        this.name = name;
        this.age = age;
    }

    public StudentModel(long id, String student_id, String name, int age){
        this.id=id;
        this.student_id=student_id;
        this.name=name;
        this.age=age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudent_Id() {
        return student_id;
    }

    public void setStudent_Id(String id) {
        this.student_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
}
