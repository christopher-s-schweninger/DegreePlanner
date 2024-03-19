import java.util.ArrayList;

public class Semester {
    public int semesterNum;
    public ArrayList<ArrayList<Course>> semesterCourses;

    public Semester(int inSemesterNum, ArrayList<ArrayList<Course>> inCourses)
    {
        this.semesterNum = inSemesterNum;
        this.semesterCourses = inCourses;
    }

    public int getSemesterNum()
    {
        return this.semesterNum;
    }

    public ArrayList<ArrayList<Course>> getCourses()
    {
        return this.semesterCourses;
    }

}