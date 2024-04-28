package com.polyglot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table

public class courses {
	private String courseName;
	private String  courseAbout;
	private Double course_price;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
	@SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
	private Long id ;
	private int  courseDurration_in_days;
	private String courseImageLink;
	private String contectDiscription;
	public courses(String courseName, String courseAbout, Double course_price, Long id, int courseDurration_in_days,
			String courseImageLink) {
		
		this.courseName = courseName;
		this.courseAbout = courseAbout;
		this.course_price = course_price;
		this.id = id;
		this.courseDurration_in_days = courseDurration_in_days;
		this.courseImageLink = courseImageLink;
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
		return contectDiscription;
	}
	public void setContectDiscription(String  contectDiscription) {
		this.contectDiscription = contectDiscription;
	}
	public String getCourseAbout() {
		return courseAbout;
	}
	public void setCourseAbout(String courseAbout) {
		this.courseAbout = courseAbout;
	}
	public Double getCourse_price() {
		return course_price;
	}
	public void setCourse_price(Double course_price) {
		this.course_price = course_price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCourseDurration_in_days() {
		return courseDurration_in_days;
	}
	public void setCourseDurration_in_days(int courseDurration_in_days) {
		this.courseDurration_in_days = courseDurration_in_days;
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
