package pvis_lab_first.Model;

import java.util.Calendar;

public class Person {
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getFullName(){
		return lastName+" "+ firstName +" "+ middleName;
	}

	public Calendar getDateReceipt() {
		return dateReceipt;
	}

	public void setDateReceipt(Calendar dateReceipt) {
		this.dateReceipt = dateReceipt;
	}

	public Calendar getDateExpiparation() {
		return dateExpiparation;
	}

	public void setDateExpiparation(Calendar dateExpiparation) {
		this.dateExpiparation = dateExpiparation;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	private String firstName;
	private String lastName;
	private String middleName;
	private Calendar dateReceipt;
	private Calendar dateExpiparation;
	private Calendar dateOfBirth;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}
}
