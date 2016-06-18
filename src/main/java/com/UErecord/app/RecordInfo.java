package com.UErecord.app;

import java.util.ArrayList;

public class RecordInfo {

	private String name;
	private String enrollmentNumber;
	private String degree;
	private String syllabus;
	private String englishLevel;
	private String course;
	private String state;
	private ArrayList<RecordSubjects> curriculum = new ArrayList<RecordSubjects>();
	private ArrayList<CreditsBreakdown> credits = new ArrayList<CreditsBreakdown>();

	public class RecordSubjects {

		private String year;
		private String course;
		private String group;
		private String subject;
		private String credit;
		private String type;
		private String decemberCall;
		private String ordinaryCall;
		private String extraordinaryCallOne;
		private String extraordinaryCallTwo;
		private String wastedCalls;

		public RecordSubjects(String year, String course, String group, String subject, String credit, String type,
				String decemberCall, String ordinaryCall, String extraordinaryCallOne, String extraordinaryCallTwo,
				String wastedCalls) {
			setYear(year);
			setCourse(course);
			setGroup(group);
			setSubject(subject);
			setCredit(credit);
			setType(type);
			setDecemberCall(decemberCall);
			setOrdinaryCall(ordinaryCall);
			setExtraordinaryCallOne(extraordinaryCallOne);
			setExtraordinaryCallTwo(extraordinaryCallTwo);
			setWastedCalls(wastedCalls);
			//RecordInfo.this.curriculum.add(this);
		}

		public void setYear(String year) {
			this.year = year;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public void setCredit(String credit) {
			this.credit = credit;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setDecemberCall(String decemberCall) {
			this.decemberCall = decemberCall;
		}

		public void setOrdinaryCall(String ordinaryCall) {
			this.ordinaryCall = ordinaryCall;
		}

		public void setExtraordinaryCallOne(String extraordinaryCallOne) {
			this.extraordinaryCallOne = extraordinaryCallOne;
		}

		public void setExtraordinaryCallTwo(String extraordinaryCallTwo) {
			this.extraordinaryCallTwo = extraordinaryCallTwo;
		}

		public void setWastedCalls(String wastedCalls) {
			this.wastedCalls = wastedCalls;
		}

	}

	public class CreditsBreakdown {
		
		private String type;
		private String total;
		private String passed;
		private String enrolled;
		private String pending;
		
		public CreditsBreakdown(String type, String total, String passed, String enrolled, String pending) {
			setType(type);
			setTotal(total);
			setPassed(passed);
			setEnrolled(enrolled);
			setPending(pending);
		}
		public void setType(String type) {
			this.type = type;
		}
		public void setTotal(String basic) {
			this.total = basic;
		}
		public void setPassed(String mandatory) {
			this.passed = mandatory;
		}
		public void setEnrolled(String optional) {
			this.enrolled = optional;
		}
		public void setPending(String openCredits) {
			this.pending = openCredits;
		}
		
		
	}

	
	public String getName() {
		return name;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public String getDegree() {
		return degree;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public String getEnglishLevel() {
		return englishLevel;
	}

	public String getCourse() {
		return course;
	}

	public String getState() {
		return state;
	}

	public ArrayList<RecordSubjects> getCurriculum() {
		return curriculum;
	}

	public ArrayList<CreditsBreakdown> getCredits() {
		return credits;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setState(String state) {
		this.state = state;
	}


	public void addSubject(RecordSubjects subject) {
		this.curriculum.add(subject);
	}
	
	public void addCreditBreakdown(CreditsBreakdown creditBreakdown) {
		this.credits.add(creditBreakdown);
	}
	
	
}
