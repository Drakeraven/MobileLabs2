package edu.tacoma.uw.stephd27.fragmentslab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.tacoma.uw.stephd27.fragmentslab.course.CourseContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseDetailFragment extends Fragment {
    public static final String COURSE_DETAIL_PARAM = "course_detail_param";

    public CourseDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_detail, container, false);
    }

    public static CourseDetailFragment getCourseDetailFragment(CourseContent.CourseItem courseItem) {
        CourseDetailFragment fragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(COURSE_DETAIL_PARAM, courseItem);
        fragment.setArguments(args);
        return fragment;
    }

    public void updateCourseItemView(CourseContent.CourseItem item) {
        TextView courseIdTextView = (TextView) getActivity().findViewById(R.id.course_item_id);
        courseIdTextView.setText(item.id);

        TextView courseTitleTextView = (TextView) getActivity().findViewById(R.id.course_item_content);
        courseTitleTextView.setText(item.content);

        TextView courseShortDescTextView = (TextView) getActivity().findViewById(R.id.course_item_details);
        courseShortDescTextView.setText(item.details);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments() != null) {
            CourseContent.CourseItem courseItem = (CourseContent.CourseItem)
                    getArguments().get(COURSE_DETAIL_PARAM);
            if (courseItem != null) {
                updateCourseItemView(courseItem);
            } else {
                updateCourseItemView(CourseContent.ITEMS.get(0));
            }
        }
    }
}
