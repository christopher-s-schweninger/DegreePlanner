import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WriteFile extends DataConstants 
{
    //Need to update, not in video as of 3/15 due to loss of member (Benjamin King)
    public static boolean writeUser(UserList userList)  // Second & Third
	{
        JSONObject jsonObject;
        FileWriter studentWriter = new FileWriter(STUDENT_FILE_NAME);
        FileWriter facFileWriter = new FileWriter(FACULTY_FILE_NAME);
        // do we need to make this a specific size?
        JSONArray usersArray = new JSONArray();
        HashMap<UUID, User> userEntry = userList.getUserList();
        for(HashMap.Entry<UUID, User> userMap : userEntry.entrySet())
        {
            //jsonObject = new JSONObject();

            if(userMap.getValue().getUserType().toString() == "STUDENT")
            {
                usersArray.add(writeStudent(userMap.getValue(), jsonObject));
                continue;
            }
            return false;
            //usersArray.add(usersArray.toJSONString);
        }
        studentWriter.write(usersArray.toJSONString);
        studentWriter.close();
        usersArray = null;
        for(HashMap.Entry<UUID, User> userMap : userEntry.entrySet())
        {
            if(userMap.getValue().getUserType().toString() == "PROFESSOR" || userMap.getValue().getUserType().toString() == "ADVISOR")
            {
                usersArray.add(writeFaculty(userMap.getValue(), jsonObject));
                continue;     
            }
            return false;
        }
        facFileWriter.write(usersArray.toJSONString);
        facFileWriter.close();
        
        return true;
    }

    public static JSONObject writeStudent(User user, JSONObject jsonObject)  // Second
	{
        try
        {
            if(user == null)
            {
                return null;
            }
            //FileWriter file = new FileWriter(STUDENT_FILE_NAME);

            jsonObject.put(USER_UUID, user.getUUID());
            jsonObject.put(USER_ID, user.getUserID());
            jsonObject.put(FIRSTNAME, user.firstName);
            jsonObject.put(LASTNAME, user.lastName);
            jsonObject.put(USER_EMAIL, user.getUserEmail());
            jsonObject.put(USER_PASSWORD, user.getUserPass());
            jsonObject.put(USER_TYPE, user.getUserType());
            //jsonObject.put(DEGREE_PLAN, user.writeDegreePlan(user.))
            //file.write(jsonObject.toJSONString());
            //file.close();
            return jsonObject;
        }
        catch (Exception e)  // If something goes wrong throws this error
		{
			e.printStackTrace();
		}
        return false;
    }

    public static JSONObject writeFaculty(User user, JSONObject jsonObject)
    {

        try
        {
            if(user == null)
            {
                return null;
            }
            //FileWriter file = new FileWriter(FACULTY_FILE_NAME);
            if(user.userType.toString() == "PROFESSOR")
            {
                // should I be calling toString on all of these user attributes?
                jsonObject.put(USER_UUID, user.getUUID());
                jsonObject.put(USER_ID, user.getUserID());
                jsonObject.put(FIRSTNAME, user.firstName);
                jsonObject.put(LASTNAME, user.lastName);
                jsonObject.put(USER_EMAIL, user.getUserEmail());
                jsonObject.put(USER_PASSWORD, user.getUserPass());
                jsonObject.put(USER_TYPE, user.getUserType());
                // again not sure how to access coursesInstructing through user object
                //jsoneObject.put(COURSES_INSTRUCTING, Faculty.coursesInstructing);
                //file.write(jsonObject.toJSONString());
                //file.close();
                return jsonObject;
            }
            else if(user.userType.toString() == "ADVISOR")
            {
                
                jsonObject.put(USER_UUID, user.getUUID());
                jsonObject.put(USER_ID, user.getUserID());
                jsonObject.put(FIRSTNAME, user.firstName);
                jsonObject.put(LASTNAME, user.lastName);
                jsonObject.put(USER_EMAIL, user.getUserEmail());
                jsonObject.put(USER_PASSWORD, user.getUserPass());
                jsonObject.put(USER_TYPE, user.getUserType());
                
                // not sure how to access a faculty attribute through a faculty user. should i make a user = new Faculty() 
                // at the top of the if statement?
                //jsoneObject.put(ADVISING_STUDENTS, Faculty.advisingStudents);
                //file.write(jsonObject.toJSONString());
                //file.close();
                return jsonObject;
            }        
        }
        catch (Exception e)  // If something goes wrong throws this error
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeCourse()
    {
        JSONObject jsonObject;        
        // do we need to make this a specific size?
        JSONArray courseArray = new JSONArray();
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> coursesIn = courseList.getCourses();
        
        for(Course course : coursesIn)
        {
            jsonObject = new JSONObject();
            jsonObject.put(COURSE_UUID, course.getCourseUUID());
            jsonObject.put(COURSEID, course.getCourseID());
            jsonObject.put(COURSE_NAME, course.getCourseName());
            jsonObject.put(COURSE_DESCRIPTION, course.getCourseDescription());
            JSONObject jsonPrereq = new JSONObject();
            JSONArray jsonPrereqArray = new JSONArray();
            for(HashMap<UUID, String> prereq : course.coursePrereqUUID)
            {
                int i = 1;
                boolean orFlag = true;
                for(HashMap.Entry<UUID, String> prereqEntry : prereq.entrySet())
                {
                    jsonPrereq.put(COURSE_UUID, prereqEntry.getKey());
                    jsonPrereq.put(GRADE, prereqEntry.getValue());
                    if(i != prereq.size())
                    {
                        orFlag = true;
                    }
                    else
                    {
                        orFlag = false;
                    }
                    jsonPrereq.put(ORNEXT, orFlag);

                    i++;
                }
                jsonPrereqArray.add(jsonPrereq.toJSONString());
            }

            jsonObject.put(COURSE_PREREQ, jsonPrereqArray.toJSONString());


            JSONObject jsonCoreq = new JSONObject();
            JSONArray jsonCoreqArray = new JSONArray();
            for(HashMap<UUID, String> coreq : course.courseCoreqUUID)
            {
                int i = 1;
                boolean orFlag = false;
                for(HashMap.Entry<UUID, String> coreqEntry : coreq.entrySet())
                {
                    jsonCoreq.put(COURSE_UUID, coreqEntry.getKey());
                    jsonCoreq.put(GRADE, coreqEntry.getValue());
                    if(i != coreq.size())
                    {
                        orFlag = true;
                    }
                    else
                    {
                        orFlag = false;
                    }
                    jsonCoreq.put(ORNEXT, orFlag);
                    i++;
                }
                jsonCoreqArray.add(jsonCoreq.toJSONString());
            }
            jsonObject.put(COURSE_COREQ, jsonCoreqArray.toJSONString());
            jsonObject.put(COURSE_HOURS, course.getCourseHours());            
            JSONArray semProvidedArray = new JSONArray();
            ArrayList<String> semProv = course.getSemestersProvided();
            for(String semester : semProv)
            {
                semProvidedArray.add(semester);
            }
            jsonObject.put(SEMESTER_PROVIDED, semProvidedArray.toJSONString());
            courseArray.add(jsonObject);
        }
        try (FileWriter courseWriter = new FileWriter("jsonFiles/tempcourse.json"))
        {
            courseWriter.write(courseArray.toJSONString());
            courseWriter.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    

/*
    private static DegreePlan writeDegreePlan(DegreePlan degreePlan)
	{
		try
		{
			//JSONObject degreePlanJSON = degreePlan;
			HashMap<UUID, String> compCourses = new HashMap<UUID, String>();
            for(HashMap.Entry<UUID, String> compCoursesMap : compCourses.entrySet())
            {
                //degreePlan.put(COMPLETED_COURSES, compCoursesMap.courseUUID);
            }
			//compCourses = writeCompCourse((JSONArray)degreePlanJSON.put(COMPLETED_COURSES));  // JSONArray of JSONObjects -> HashMap<UUID, String>
			ArrayList<UUID> currentCourses = (JSONArray)degreePlanJSON.get(CURRENT_COURSES);
			ArrayList<UUID> incompleteCourses = (JSONArray)degreePlanJSON.get(INCOMPLETE_COURSES);
			ArrayList<Warnings> warnings = (JSONArray)degreePlanJSON.get(WARNINGS);
			int completedHours = ((Long)degreePlanJSON.get(COMPLETED_HOURS)).intValue();  // String -> int
			int currentHours = ((Long)degreePlanJSON.get(CURRENT_HOURS)).intValue();
			int totalDegreeHours = ((Long)degreePlanJSON.get(TOTAL_DEGREE_HOURS)).intValue();
			Major currentMajor = Major.valueOf((String)degreePlanJSON.get(CURRENT_MAJOR));  // String -> Major
			Concentration currentConcentration = Concentration.valueOf((String)degreePlanJSON.get(CURRENT_CONCENTRATION));  // String -> Major

			degreePlan = new DegreePlan(compCourses, currentCourses, incompleteCourses, warnings, completedHours, 
			currentHours, totalDegreeHours, currentMajor, currentConcentration);

			return degreePlan;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static HashMap<UUID, String> writeCompCourse(HashMap<UUID, String> compCoursesIn)  // compCoursesIn = JSONArray of JSONObjects. The JSONObjects = courseUUID & recievedGrade
	{
		HashMap<UUID, String> tempMap;
		try
		{
			JSONArray compCourses = compCoursesIn;
			tempMap = new HashMap<UUID, String>();
			for (int i = 0; i < compCourses.size(); i++)  // Getting each course and its recieved grade
			{
				JSONObject compCourse = (JSONObject)compCourses.get(i);
				UUID courseUUID = UUID.fromString((String)compCourse.get(COURSE_UUID));
				String grade = (String)compCourse.get(GRADE);

				tempMap.put(courseUUID, grade);  // Adding it to the HashMap
			}
			return tempMap;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
*/



}
