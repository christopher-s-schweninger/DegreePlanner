package testClasses;
import java.util.ArrayList;
import java.util.UUID;

import testClasses.Course;
import testClasses.User;

public class AdvisementPlan
{
    private UUID AdvisementPlanUUID;
    private User student;
    private User advisor;
    public ArrayList<Course> courses;
    public String notes;

    public AdvisementPlan(UUID planID, User student, User advisor, ArrayList<Course> courses, String attachedNotes)
    {
        this.AdvisementPlanUUID = planID;
        this.student = student;
        this.advisor = advisor;
        this.courses = courses;
        this.notes = attachedNotes;
    }

    public UUID getPlanID()
    {
        return this.AdvisementPlanUUID;
    }

    public User getPlanStudent()
    {
        return this.student;
    }

    public User getPlanAdvisor()
    {
        return this.advisor;
    }

    public ArrayList<Course> getCourses()
    {
        return this.courses;
    }

    public String getNotes()
    {
        return this.notes;
    }

    public void renderListView(AdvisementPlanList advisementPlanList)
    {

    }

    public void renderAdvisementPlan()
    {

    }

    public void renderEditPlan(AdvisementPlan advisementPlan)
    {

    }


    public AdvisementPlan renderNewPlan()
    {
        return null;
    }

    // return a course?
    public Course searchCourse(String courseID)
    {
        return null;
    }

    public void renderCourseTable(Course course)
    {

    }

    public void saveCourse(Course course)
    {

    }

    public String renderNotesEntry()
    {

        return "";
    }


    public AdvisementPlan savePlan(AdvisementPlan advisementPlan)
    {

        return null;
    }

    public void generatePDF(AdvisementPlan advisementPlan)
    {

    }

    // obsolete because of renderCourseTable? also should take course object parameter
    public void renderCourseDetails(String courseID)
    {

    }
}