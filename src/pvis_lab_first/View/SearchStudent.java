package pvis_lab_first.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import pvis_lab_first.Controller.Controller;
import pvis_lab_first.Model.Person;
import pvis_lab_first.Model.SearchFilter;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchStudent {
    private Display display;
    private Shell shell;
    private Controller controller;
    TableComponent tableComponent;

    public SearchStudent(Display display, Controller controller){
        this.display = display;
        this.controller = controller;
        shell = new Shell(display);
        shell.setSize(1240,640);

        RowLayout rowLayoutHorizontal = new RowLayout(SWT.HORIZONTAL);
        rowLayoutHorizontal.spacing = 10;
        shell.setLayout(rowLayoutHorizontal);

        RowLayout rowLayoutVertical = new RowLayout(SWT.VERTICAL);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        RowLayout horizont = new RowLayout(SWT.HORIZONTAL);
        horizont.spacing = 20;
        Composite composite = new Composite(shell, SWT.NULL);
        composite.setLayout(horizont);

        RowData rowText = new RowData();
        rowText.width = 100;

        RowLayout layoutLabelText = new RowLayout(SWT.HORIZONTAL);
        layoutLabelText.spacing = 3;


        RowLayout layoutLabelName = new RowLayout(SWT.VERTICAL);
        layoutLabelName.spacing = 10;
        Composite nameGroupLabel = new Composite(composite, SWT.NULL);
        nameGroupLabel.setLayout(layoutLabelName);
        Label labelLastName = new Label(nameGroupLabel, SWT.NULL);
        labelLastName.setText("Фамилия: ");
        Label labelFirstName = new Label(nameGroupLabel, SWT.NULL);
        labelFirstName.setText("Имя: ");
        Label labelMiddleName = new Label(nameGroupLabel, SWT.NULL);
        labelMiddleName.setText("Отчество: ");

        Composite nameGroupText = new Composite(composite, SWT.NULL);
        nameGroupText.setLayout(rowLayoutVertical);
        Text lastName = new Text(nameGroupText, SWT.BORDER);
        lastName.setLayoutData(rowText);
        Text firstName = new Text(nameGroupText, SWT.BORDER);
        firstName.setLayoutData(rowText);
        Text middleName = new Text(nameGroupText, SWT.BORDER);
        middleName.setLayoutData(rowText);

        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        rowLayout.spacing = 15;


        Composite compositeDateButton = new Composite(composite, SWT.NONE);
        compositeDateButton.setLayout(rowLayoutVertical);

        Composite  compositeDate = new Composite(compositeDateButton, SWT.NONE);
        compositeDate.setLayout(horizont);

        Composite filterBirthdayGroup = new Composite(compositeDate, SWT.BORDER);
        filterBirthdayGroup.setLayout(rowLayoutVertical);
        Composite dateBirthdayGroup = new Composite(filterBirthdayGroup, SWT.NONE);
        dateBirthdayGroup.setLayout(layoutLabelText);
        Label labelDateBirthday = new Label(dateBirthdayGroup, SWT.NULL);
        labelDateBirthday.setText("Дата рождения: ");
        Combo[] comboDateBirtday = ComboDate.setDateNotSelect(dateBirthdayGroup);
        Composite endBirthday = new Composite(filterBirthdayGroup, SWT.NONE);
        RowLayout rowLayoutEndBirthday = new RowLayout(SWT.HORIZONTAL);
        rowLayoutEndBirthday.marginLeft = 224;
        endBirthday.setLayout(rowLayoutEndBirthday);
        Combo comboEndBirthday = ComboDate.setYearNotSelect(endBirthday);

        Composite filterReceiptGroup = new Composite(compositeDate, SWT.BORDER);
        filterReceiptGroup.setLayout(rowLayoutVertical);
        Composite dateReceiptGroup  = new Composite(filterReceiptGroup, SWT.NONE);
        dateReceiptGroup.setLayout(layoutLabelText);
        Label labelDateReceipt = new Label(dateReceiptGroup, SWT.NULL);
        labelDateReceipt.setText("Дата поступления: ");
        Combo[] comboDateReceipt = ComboDate.setDateNotSelect(dateReceiptGroup);
        Composite endReceipt = new Composite(filterReceiptGroup, SWT.NONE);
        RowLayout rowLayoutEndReceipt = new RowLayout(SWT.HORIZONTAL);
        rowLayoutEndReceipt.marginLeft = 242;
        endReceipt.setLayout(rowLayoutEndReceipt);
        Combo comboEndReceipt = ComboDate.setYearNotSelect(endReceipt);

        Composite filterExpiparationGroup = new Composite(compositeDate, SWT.BORDER);
        filterExpiparationGroup.setLayout(rowLayoutVertical);
        Composite dateExpiparationGroup = new Composite(filterExpiparationGroup, SWT.NONE);
        dateExpiparationGroup.setLayout(layoutLabelText);
        Label labelDateExpiparation = new Label(dateExpiparationGroup, SWT.NULL);
        labelDateExpiparation.setText("Дата окончания: ");
        Combo[] comboDateExpiparation = ComboDate.setDateNotSelect(dateExpiparationGroup);
        Composite endExpiparation = new Composite(filterExpiparationGroup, SWT.NONE);
        RowLayout rowLayoutEndExpiparation = new RowLayout(SWT.HORIZONTAL);
        rowLayoutEndExpiparation.marginLeft = 229;
        endExpiparation.setLayout(rowLayoutEndExpiparation);
        Combo comboEndExpiparation = ComboDate.setYearNotSelect(endExpiparation);

        Button searchRecord = new Button(compositeDateButton, SWT.PUSH);
        searchRecord.setText("Искать");
        RowData rowDataAddButton = new RowData();
        rowDataAddButton.width = 970;
        rowDataAddButton.height = 38;
        searchRecord.setLayoutData(rowDataAddButton);

        tableComponent = new TableComponent(shell);

        searchRecord.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SearchFilter searchFilter = new SearchFilter();
                searchFilter.setFirstName(firstName.getText());
                searchFilter.setLastName(lastName.getText());
                searchFilter.setMiddleName(middleName.getText());


                if(comboDateBirtday[0].getSelectionIndex() < 0){
                    searchFilter.setDayBirthday(null);
                }else{
                    int index = comboDateBirtday[0].getSelectionIndex();
                    int day = Integer.parseInt(comboDateBirtday[0].getItem(index));
                    searchFilter.setDayBirthday(day);
                }
                if(comboDateBirtday[1].getSelectionIndex() < 0){
                    searchFilter.setMonthBirthday(null);
                }else{
                    int index = comboDateBirtday[1].getSelectionIndex();
                    int month = Integer.parseInt(comboDateBirtday[1].getItem(index));
                    if(month == 12){
                        month = 0;
                    }
                    searchFilter.setMonthBirthday(month);
                }
                if(comboDateBirtday[2].getSelectionIndex() < 0){
                    searchFilter.setBeginBirthday(null);
                }else{
                    int index = comboDateBirtday[2].getSelectionIndex();
                    int year = Integer.parseInt(comboDateBirtday[2].getItem(index));
                    searchFilter.setBeginBirthday(new GregorianCalendar(year, 0, 0));
                }
                if(comboEndBirthday.getSelectionIndex() < 0){
                    searchFilter.setEndBirthday(null);
                }else{
                    int index = comboEndBirthday.getSelectionIndex();
                    int year = Integer.parseInt(comboEndBirthday.getItem(index));
                    searchFilter.setEndBirthday(new GregorianCalendar(year, 0, 0));
                }


                if(comboDateReceipt[0].getSelectionIndex() < 0){
                    searchFilter.setDayReceipt(null);
                }else{
                    int index = comboDateReceipt[0].getSelectionIndex();
                    int day = Integer.parseInt(comboDateReceipt[0].getItem(index));
                    searchFilter.setDayReceipt(day);
                }
                if(comboDateReceipt[1].getSelectionIndex() < 0){
                    searchFilter.setMonthReceipt(null);
                }else{
                    int index = comboDateReceipt[1].getSelectionIndex();
                    int month = Integer.parseInt(comboDateReceipt[1].getItem(index));
                    if(month == 12){
                        month = 0;
                    }
                    searchFilter.setMonthReceipt(month);
                }
                if(comboDateReceipt[2].getSelectionIndex() < 0){
                    searchFilter.setBeginReceipt(null);
                }else{
                    int index = comboDateReceipt[2].getSelectionIndex();
                    int year = Integer.parseInt(comboDateReceipt[2].getItem(index));
                    searchFilter.setBeginReceipt(new GregorianCalendar(year, 0, 0));
                }
                if(comboEndReceipt.getSelectionIndex() < 0){
                    searchFilter.setEndReceipt(null);
                }else{
                    int index = comboEndReceipt.getSelectionIndex();
                    int year = Integer.parseInt(comboEndReceipt.getItem(index));
                    searchFilter.setEndReceipt(new GregorianCalendar(year, 0, 0));
                }


                if(comboDateExpiparation[0].getSelectionIndex() < 0){
                    searchFilter.setDayExpiparation(null);
                }else{
                    int index = comboDateExpiparation[0].getSelectionIndex();
                    int day = Integer.parseInt(comboDateExpiparation[0].getItem(index));
                    searchFilter.setDayExpiparation(day);
                }
                if(comboDateExpiparation[1].getSelectionIndex() < 0){
                    searchFilter.setMonthExpiparation(null);
                }else{
                    int index = comboDateExpiparation[1].getSelectionIndex();
                    int month = Integer.parseInt(comboDateExpiparation[1].getItem(index));
                    if(month == 12){
                        month = 0;
                    }
                    searchFilter.setMonthExpiparation(month);
                }
                if(comboDateExpiparation[2].getSelectionIndex() < 0){
                    searchFilter.setBeginExpiparation(null);
                }else{
                    int index = comboDateExpiparation[2].getSelectionIndex();
                    int year = Integer.parseInt(comboDateExpiparation[2].getItem(index));
                    searchFilter.setBeginExpiparation(new GregorianCalendar(year, 0, 0));
                }
                if(comboEndExpiparation.getSelectionIndex() < 0){
                    searchFilter.setEndExpiparation(null);
                }else{
                    int index = comboEndExpiparation.getSelectionIndex();
                    int year = Integer.parseInt(comboEndExpiparation.getItem(index));
                    searchFilter.setEndExpiparation(new GregorianCalendar(year, 0, 0));
                }

                controller.searchForFilter(searchFilter);

            }
        });

    }
    public void setData(ArrayList<Person> personArrayList){
        tableComponent.setData(personArrayList);
    }
    public void start(){
        shell.open();
        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
