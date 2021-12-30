package edu.illinois.cs.cs125.fall2020.mp.models;

//import edu.illinois.cs.cs125.fall2020.mp.application.CourseableApplication;


/**
 * Rating class for storing rating of courses.
 */
public class Rating {
  /**
   * Rating Indicating that course has not been rated yet.
   */
  public static final Double NOT_RATED = -1.0;
  private String id;
  private double rating;

  /**
   * Create an empty rating.
   */
  public Rating() {}

  /**
   * yes.
   * @param setId s
   * @param setRating s
   */
  public Rating(final String setId, final double setRating) {
    id = setId;
    rating = setRating;
  }

  /**
   * .
   * @return ID.
   */
  public String getId() {
    return id;
  }

  /**
   * .
   * @return rating.
   */
  public double getRating() {
    return rating;
  }
}
