package edu.tacoma.uw.stephd27.fragmentslab;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import edu.tacoma.uw.stephd27.fragmentslab.course.CourseContent;

public class CourseActivity extends AppCompatActivity implements
        CourseListFragment.OnListFragmentInteractionListener,
AboutFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        this.setSupportActionBar(myToolbar);


        if (findViewById(R.id.course_fragment_container) != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.course_fragment_container, new CourseListFragment())
                    .commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: update whats shown in the fragment and support tablets
        switch (item.getItemId()) {
            case R.id.test:
                if (findViewById(R.id.course_fragment_container) == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.Menu_credit)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Dialog Opened.
                                }
                            }).create().show();
                } else {
                    if (getSupportFragmentManager().findFragmentByTag("MENU_USED") == null) {
                        FragmentTransaction transaction = getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.course_fragment_container, AboutFragment.getAboutFragment(), "MENU_USED")
                                .addToBackStack("MENU_USED");
                        transaction.commit();
                    }
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    @Override
    public void onListFragmentInteraction(CourseContent.CourseItem item) {
        CourseDetailFragment courseDetailFragment = null;

        if (findViewById(R.id.course_fragment_container) == null) {
            // If courseDetail frag is available, we're in two-pane layout...
            // Capture the course fragment from the activity layout
            courseDetailFragment = (CourseDetailFragment)
                    getSupportFragmentManager()
                    .findFragmentById(R.id.course_detail_frag);
            //Call a method in the course detail fragment to update its content
            courseDetailFragment.updateCourseItemView(item);
        } else {
            //If the frag is not available, we're in the one-pane layout and must swap frags

            /*Create a fragment adn give it an argument for the selected course
            * Replace whatever is in the fragment_container view with this fragment,
            * and add the transaction to the backstack so the user can navigate back*/

            courseDetailFragment = CourseDetailFragment.getCourseDetailFragment(item);

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.course_fragment_container, courseDetailFragment)
                    .addToBackStack(null);

            //commit the transaction
            transaction.commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
