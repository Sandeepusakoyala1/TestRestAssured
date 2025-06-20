package com.testAutomation.pojos;

public class dataPojo {
	
	private String firstname;
	private String lastname;
	private boolean depositpaid;
	private BookingDates bookingdates;
	
	public dataPojo(String firstname, String lastname, boolean depositpaid, BookingDates bookingdates) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public BookingDates getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}
	@Override
	public String toString() {
		return "dataPojo [firstname=" + firstname + ", lastname=" + lastname + ", depositpaid=" + depositpaid
				+ ", bookingdates=" + bookingdates + "]";
	}

}
