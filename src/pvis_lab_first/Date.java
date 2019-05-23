package pvis_lab_first;

public class Date {
	Date(String timeNow) {
		_setDate(timeNow);
	}

	void setDate(String timeNow) {
		_setDate(timeNow);
	}

	private int getDay() {
		return day;
	}
	private int getMonth() {
		return month;
	}
	private int getYear() {
		return year;
	}
	public String getDateString(){
		return getDay() + "." + getMonth() + "." + getYear();
	}


	private void _setDate(String timeNow) {
		try {
			day = Integer.parseInt(timeNow.substring(0, 2));
			month = Integer.parseInt(timeNow.substring(3, 5));
			year = Integer.parseInt(timeNow.substring(6, 10));
		} catch (Exception outOfRange) {
			day = -1;
			month = -1;
			year = -1;

		}
	}

	private int day;
	private int month;
	private int year;
}
