package pvis_lab_first.Model;

import java.util.Calendar;

public class SearchFilter {
    public SearchFilter(){

    }
    private String firstName;
    private String lastName;
    private String middleName;
    private Calendar endBirthday;
    private Calendar endReceipt;
    private Calendar endExpiparation;
    private Calendar beginBirthday;
    private Calendar beginReceipt;
    private Calendar beginExpiparation;

    public Calendar getBeginBirthday() {
        return beginBirthday;
    }

    public void setBeginBirthday(Calendar beginBirthday) {
        this.beginBirthday = beginBirthday;
    }

    public Calendar getBeginReceipt() {
        return beginReceipt;
    }

    public void setBeginReceipt(Calendar beginReceipt) {
        this.beginReceipt = beginReceipt;
    }

    public Calendar getBeginExpiparation() {
        return beginExpiparation;
    }

    public void setBeginExpiparation(Calendar beginExpiparation) {
        this.beginExpiparation = beginExpiparation;
    }

    private Integer dayBirthday;
    private Integer monthBirthday;
    private Integer dayReceipt;
    private Integer monthReceipt;
    private Integer dayExpiparation;
    private Integer monthExpiparation;

    public Integer getDayBirthday() {
        return dayBirthday;
    }

    public void setDayBirthday(Integer dayBirthday) {
        this.dayBirthday = dayBirthday;
    }

    public Integer getMonthBirthday() {
        return monthBirthday;
    }

    public void setMonthBirthday(Integer monthBirthday) {
        this.monthBirthday = monthBirthday;
    }

    public Integer getDayReceipt() {
        return dayReceipt;
    }

    public void setDayReceipt(Integer dayReceipt) {
        this.dayReceipt = dayReceipt;
    }

    public Integer getMonthReceipt() {
        return monthReceipt;
    }

    public void setMonthReceipt(Integer monthReceipt) {
        this.monthReceipt = monthReceipt;
    }

    public Integer getDayExpiparation() {
        return dayExpiparation;
    }

    public void setDayExpiparation(Integer dayExpiparation) {
        this.dayExpiparation = dayExpiparation;
    }

    public Integer getMonthExpiparation() {
        return monthExpiparation;
    }

    public void setMonthExpiparation(Integer monthExpiparation) {
        this.monthExpiparation = monthExpiparation;
    }

    public Calendar getEndBirthday() {
        return endBirthday;
    }

    public void setEndBirthday(Calendar endBitrhday) {
        this.endBirthday = endBitrhday;
    }

    public Calendar getEndReceipt() {
        return endReceipt;
    }

    public void setEndReceipt(Calendar endReceipt) {
        this.endReceipt = endReceipt;
    }

    public Calendar getEndExpiparation() {
        return endExpiparation;
    }

    public void setEndExpiparation(Calendar endExpiparation) {
        this.endExpiparation = endExpiparation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
