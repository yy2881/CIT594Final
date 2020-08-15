package edu.upenn.cit594.data;

public class Violation {
	public String date;
	public String fine;
	public String violation;
	public String plate_id;
	public String state;
	public String ticket_id;
	public String zip_code;
	public Violation(String date, String fine, String violation, String plate_id, String state, String ticket_id,
			String zip_code) {
		super();
		this.date = date;
		this.fine = fine;
		this.violation = violation;
		this.plate_id = plate_id;
		this.state = state;
		this.ticket_id = ticket_id;
		this.zip_code = zip_code;
	}
	
	
	
	
}
