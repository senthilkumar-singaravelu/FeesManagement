package com.skiply.receipt.entity;

import java.math.BigDecimal;

public class Student_Receipt_Dto{
	private Integer studentId;
	    private String studentName;
	    private String grade;
	    private Integer transactionId;
	    private BigDecimal amount;
	    private String transactionDate;
	    private String cardType;
	    private String referenceNumber;
	    private String status;
		public Integer getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(Integer transactionId) {
			this.transactionId = transactionId;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public String getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getReferenceNumber() {
			return referenceNumber;
		}
		public void setReferenceNumber(String referenceNumber) {
			this.referenceNumber = referenceNumber;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(int mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getSchoolName() {
			return schoolName;
		}
		public void setSchoolName(String schoolName) {
			this.schoolName = schoolName;
		}
		private int mobileNumber;
	    private String schoolName;
		public Integer getStudentId() {
			return studentId;
		}
		public void setStudentId(Integer studentId) {
			this.studentId = studentId;
		}
		public String getStudentName() {
			return studentName;
		}
		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}


}
