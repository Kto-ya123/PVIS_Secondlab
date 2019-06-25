package pvis_lab_first.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class ComboDate {

    public static Combo[] setComboDate(Composite widget){
        Combo[] date = new Combo[3];
        date[0] = setComboDay(widget);
        date[1] = setComboMonth(widget);
        date[2] = setComboYear(widget);
        return date;
    }

    public static Combo[] setDateNotSelect(Composite widget){
        Combo[] date = new Combo[3];
        date[0] = setDayNotSelect(widget);
        date[1] = setMonthNotSelect(widget);
        date[2] = setYearNotSelect(widget);
        return date;
    }

    public static Combo setComboDay(Composite widget){
        Combo day = setDayNotSelect(widget);
        day.select(0);
        return day;
    }

    public static Combo setComboMonth(Composite widget){
        Combo month = setMonthNotSelect(widget);
        month.select(0);
        return month;
    }

    public static Combo setComboYear(Composite widget){
        Combo year = setYearNotSelect(widget);
        year.select(0);
        return year;
    }

    public static Combo setDayNotSelect(Composite widget){
        Combo comboDay = new Combo(widget, SWT.DROP_DOWN | SWT.READ_ONLY);
        for (int day = 1; day <= 31; day++){
            comboDay.add(""+day);
        }
        return comboDay;
    }



    public static Combo setMonthNotSelect(Composite widget){
        Combo comboMonth = new Combo(widget, SWT.DROP_DOWN | SWT.READ_ONLY);
        for (int month = 1; month <= 12; month++){
            comboMonth.add(""+month);
        }
        return comboMonth;
    }

    public static Combo setYearNotSelect(Composite widget){
        Combo comboYear = new Combo(widget ,SWT.DROP_DOWN | SWT.READ_ONLY);
        for (int year = 1950; year <= 2030; year++){
            comboYear.add(""+year);
        }
        return comboYear;
    }
}
