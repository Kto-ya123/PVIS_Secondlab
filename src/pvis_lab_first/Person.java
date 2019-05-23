package pvis_lab_first;

public class Person {
	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	void setDateReceipt(Date dateReceipt) {
		this.dateReceipt = dateReceipt;
	}
	void setDateExpiparation(Date dateExpiparation) {
		this.dateExpiparation = dateExpiparation;
	}
	void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName(){
		return lastName+" "+ firstName +" "+ middleName;
	}

	public Date getDateReceipt(){
		return dateReceipt;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Date getDateExpiparation(){
		return dateExpiparation;
	}

	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateReceipt;
	private Date dateExpiparation;
	private Date dateOfBirth;
}
