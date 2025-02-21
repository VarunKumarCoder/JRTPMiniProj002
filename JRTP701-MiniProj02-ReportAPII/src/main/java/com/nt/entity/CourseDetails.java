
package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="JRTP_COURSE_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CourseDetails {
	@Id
	@GeneratedValue
	private Integer courseId;
	@Column(length=30)
	private String courseName;
	@Column(length=30)
	private String location;
	@Column(length=30)
	private String courseCategory;
	@Column(length=30)
	private String facultyName;
	private Double fee;
	@Column(length=30)
	@NonNull
	private String adminName;
	private Long adminContact;
	@Column(length=30)
	private String trainingMode;
	private LocalDateTime startDate;
	private String courseStatus;
	@CreationTimestamp
	@Column(insertable=true,updatable = false)
	private LocalDateTime creationDate;
	@Column(insertable=false,updatable = true)
	@UpdateTimestamp
	private LocalDateTime updationDate;
	@Column(length=30)
	private String createdBy;
	@Column(length=30)
	private String ipdatedBy;
	
}
