package com.polyglot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses") // Specify the table name

public class courses {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
	@SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "course_about")
	private String courseAbout;

	@Column(name = "course_price")
	private Double coursePrice;

	@Column(name = "course_duration_in_days")
	private int courseDurationInDays;

	@Column(name = "course_image_link")
	private String courseImageLink;

	@Column(name = "content_description", columnDefinition = "text") // Specify column name and use columnDefinition for
																		// text type
	private String contentDescription;

	public courses(String courseName, String courseAbout, Double course_price, Long id, int courseDurration_in_days,
			String courseImageLink) {

		this.courseName = courseName;
		this.courseAbout = courseAbout;
		this.coursePrice = course_price;
		this.id = id;
		this.courseDurationInDays = courseDurration_in_days;
		this.courseImageLink = courseImageLink;
	}

	public courses(String courseName, String courseAbout, Double course_price, Long id, int courseDurration_in_days,
			String courseImageLink, String contectDiscription) {
		this.courseName = courseName;
		this.courseAbout = courseAbout;
		this.coursePrice = course_price;
		this.id = id;

		this.courseDurationInDays = courseDurration_in_days;
		this.courseImageLink = courseImageLink;
		this.contentDescription = contectDiscription;
	}

	public courses() {

	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getContectDiscription() {
		return contentDescription;
	}

	public void setContectDiscription(String contectDiscription) {
		this.contentDescription = contectDiscription;
	}

	public String getCourseAbout() {
		return courseAbout;
	}

	public void setCourseAbout(String courseAbout) {
		this.courseAbout = courseAbout;
	}

	public Double getCourse_price() {
		return coursePrice;
	}

	public void setCourse_price(Double course_price) {
		this.coursePrice = course_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCourseDurration_in_days() {
		return courseDurationInDays;
	}

	public void setCourseDurration_in_days(int courseDurration_in_days) {
		this.courseDurationInDays = courseDurration_in_days;
	}

	public String getCourseImageLink() {
		return courseImageLink;
	}

	public void setCourseImageLink(String courseImageLink) {
		this.courseImageLink = courseImageLink;
	}

	@Override
	public String toString() {
		return "courses [getCourseName()=" + getCourseName() + ", getCourseAbout()=" + getCourseAbout()
				+ ", getCourse_price()=" + getCourse_price() + ", getId()=" + getId()
				+ ", getCourseDurration_in_days()=" + getCourseDurration_in_days() + ", getCourseImageLink()="
				+ getCourseImageLink() + "]";
	}

}
