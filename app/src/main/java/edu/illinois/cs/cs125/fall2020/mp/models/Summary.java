package edu.illinois.cs.cs125.fall2020.mp.models;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

/**
 * Model holding the course summary information shown in the course list.
 *
 * <p>You will need to complete this model for MP0.
 */
public class Summary implements SortedListAdapter.ViewModel {
  private String year;

  /**
   * Get the year for this Summary.
   *
   * @return the year for this Summary
   */
  public final String getYear() {
    return year;
  }

  private String semester;

  /**
   * Get the semester for this Summary.
   *
   * @return the semester for this Summary
   */
  public final String getSemester() {
    return semester;
  }

  private String department;

  /**
   * Get the department for this Summary.
   *
   * @return the department for this Summary
   */
  public final String getDepartment() {
    return department;
  }

  private String number;

  /**
   * Get the number for this Summary.
   *
   * @return the number for this Summary
   */
  public final String getNumber() {
    return number;
  }

  private String title;

  /**
   * Get the title for this Summary.
   *
   * @return the title for this Summary
   */
  public final String getTitle() {
    return title;
  }

  private String description;

  /**
   * Get the description for this Summary.
   *
   * @return the description for this Summary
   */
  public final String getDescription() {
    return description;
  }

  /**
   * Create an empty Summary.
   */
  @SuppressWarnings({"unused", "RedundantSuppression"})
  public Summary() {}

  /**
   * Create a Summary with the provided fields.
   *
   * @param setYear       the year for this Summary
   * @param setSemester   the semester for this Summary
   * @param setDepartment the department for this Summary
   * @param setNumber     the number for this Summary
   * @param setTitle      the title for this Summary
   */
  public Summary(
      final String setYear,
      final String setSemester,
      final String setDepartment,
      final String setNumber,
      final String setTitle) {
    year = setYear;
    semester = setSemester;
    department = setDepartment;
    number = setNumber;
    title = setTitle;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object o) {
    if (!(o instanceof Summary)) {
      return false;
    }
    Summary course = (Summary) o;
    return Objects.equals(year, course.year)
        && Objects.equals(semester, course.semester)
        && Objects.equals(department, course.department)
        && Objects.equals(number, course.number);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(year, semester, department, number);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> boolean isSameModelAs(@NonNull final T model) {
    return equals(model);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T> boolean isContentTheSameAs(@NonNull final T model) {
    return equals(model);
  }

  /**
   * This is a Javadoc.
   */
  public static final Comparator<Summary> COMPARATOR = (courseModel1, courseModel2) -> {
    if (courseModel1.department.compareTo(courseModel2.department) > 0) {
      return 1;
    } else if (courseModel1.department.compareTo(courseModel2.department) < 0) {
      return -1;
    }
    if (courseModel1.number.compareTo(courseModel2.number) > 0) {
      return 1;
    } else if (courseModel1.number.compareTo(courseModel2.number) < 0) {
      return -1;
    }
    return Integer.compare(courseModel1.title.compareTo(courseModel2.title), 0);
  };

  /**
   * This is also a Javadoc.
   * @param courses is the list of courses we want to filter.
   * @param text is the text we type in the search bar.
   * @return the courses filtered.
   */
  public static List<Summary> filter(
      @NonNull final List<Summary> courses, @NonNull final String text) {
    List<Summary> coursesMatched = new ArrayList<>();
    String cap = text.substring(0, 1).toUpperCase() + text.substring(1);
    for (Summary course : courses) {
      if (course.number.contains(text) || course.department.contains(text)
              || course.title.contains(cap) || text.contains(course.department + " " + course.number)) {
        coursesMatched.add(course);
      }
    }
    return coursesMatched;
  }
}
