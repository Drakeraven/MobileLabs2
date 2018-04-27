package edu.tacoma.uw.stephd27.webserviceslab.course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    public static final String ID = "id";
    public static final String SHORT_DESC = "shortDesc";
    public static final String LONG_DESC = "longDesc";
    public static final String PRE_REQS = "prereqs";

    public String mCourseId;
    public String mShortDescription;
    public String mLongDescription;
    public String mPrereqs;

    public Course(String mCourseId, String mShortDescription, String mLongDescription, String mPrereqs) {
        this.mCourseId = mCourseId;
        this.mShortDescription = mShortDescription;
        this.mLongDescription = mLongDescription;
        this.mPrereqs = mPrereqs;
    }

    public static List<Course> parseCourseJSON(String courseJSON) throws JSONException {
        List<Course> courseList = new ArrayList<Course>();
        if (courseJSON != null) {

            JSONArray arr = new JSONArray(courseJSON);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Course course = new Course(obj.getString(Course.ID), obj.getString(Course.SHORT_DESC)
                        , obj.getString(Course.LONG_DESC), obj.getString(Course.PRE_REQS));
                courseList.add(course);
            }

        }

        return courseList;
    }

    public String getmCourseId() {
        return mCourseId;
    }

    public void setmCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmLongDescription() {
        return mLongDescription;
    }

    public void setmLongDescription(String mLongDescription) {
        this.mLongDescription = mLongDescription;
    }

    public String getmPrereqs() {
        return mPrereqs;
    }

    public void setmPrereqs(String mPrereqs) {
        this.mPrereqs = mPrereqs;
    }
}
