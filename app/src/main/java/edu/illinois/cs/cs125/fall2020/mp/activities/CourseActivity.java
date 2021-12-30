package edu.illinois.cs.cs125.fall2020.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import edu.illinois.cs.cs125.fall2020.mp.R;
import edu.illinois.cs.cs125.fall2020.mp.application.CourseableApplication;
import edu.illinois.cs.cs125.fall2020.mp.databinding.ActivityCourseBinding;
import edu.illinois.cs.cs125.fall2020.mp.models.Course;
import edu.illinois.cs.cs125.fall2020.mp.models.Rating;
import edu.illinois.cs.cs125.fall2020.mp.models.Summary;
import edu.illinois.cs.cs125.fall2020.mp.network.Client;
import com.fasterxml.jackson.databind.ObjectMapper;




/**
 * yes.
 */
public class CourseActivity extends AppCompatActivity implements Client.CourseClientCallbacks,
        RatingBar.OnRatingBarChangeListener {
  private static final String TAG = CourseActivity.class.getSimpleName();

  // Binding to the layout in activity_main.xml
  private ActivityCourseBinding binding;

  /**
   * yes.
   *
   * @param unused yes
   */
  @Override
  protected void onCreate(final Bundle unused) {
    super.onCreate(unused);

    // Bind to the layout in activity_main.xml
    binding = DataBindingUtil.setContentView(this, R.layout.activity_course);
    ObjectMapper mapper = new ObjectMapper();
    Intent intent = getIntent();
    binding.rating.setOnRatingBarChangeListener(this);
    CourseableApplication application = (CourseableApplication) getApplication();
    Summary s = null;
    try {
      s = mapper.readValue(intent.getStringExtra("COURSE"), Summary.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    application.getCourseClient().getCourse(s, this);
    application.getCourseClient().getRating(s, application.getClientID(), this);
  }


  /**
   * yes.
   * @param summary yes
   * @param course yes
   */
  @Override
  public void courseResponse(final Summary summary, final Course course) {
    binding.title.setText((course.getDepartment() + " " + course.getNumber() + ": " + course.getTitle()));
    binding.description.setText(course.getDescription());
  }

  /**
   * yes.
   * @param summary s s
   * @param rating s s
   */
  @Override
  public void yourRating(final Summary summary, final Rating rating) {
    binding.rating.setRating((float) rating.getRating());
  }

  /**
   * .
   * @param ratingBar s
   * @param rating s
   * @param fromUser s
   */
  @Override
  public void onRatingChanged(final RatingBar ratingBar, final float rating, final boolean fromUser) {
    CourseableApplication application = (CourseableApplication) getApplication();
    ObjectMapper mapper = new ObjectMapper();
    Rating realRating = new Rating(application.getClientID(), ((double) rating));
    Intent intent = getIntent();
    Summary s = null;
    try {
      s = mapper.readValue(intent.getStringExtra("COURSE"), Summary.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    application.getCourseClient().postRating(s, realRating, this);
  }
}
