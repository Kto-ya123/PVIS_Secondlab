package pvis_lab_first.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import pvis_lab_first.Model.Person;

import java.util.ArrayList;
import java.util.Calendar;

public class TableComponent {
    private Table table;
    private Label labelRecord;
    private Label numberPage;
    private ArrayList<Person> persons;
    private int page;
    private int recordForPage = 15;
    private Shell shell;

    public TableComponent(Shell parent){
        this.shell = parent;
        persons = new ArrayList<>();
        page = 1;
        Composite mainComposite = new Composite(parent, SWT.NONE);
        RowLayout mainLayout = new RowLayout(SWT.VERTICAL);
        mainComposite.setLayout(mainLayout);
        table = new Table(mainComposite, SWT.BORDER | 6556);
        table.setLinesVisible(true);
        RowData rowDataTableFifthTask = new RowData();
        rowDataTableFifthTask.height = 363;
        table.setLayoutData(rowDataTableFifthTask);

        TableColumn column = new TableColumn(table, 0);
        column.setWidth(400);

        for (int i = 0; i < 3; ++i) {
            column = new TableColumn(table, 0);
            column.setWidth(263);
        }

        setHandleTable();

        Composite setPage = new Composite(mainComposite, SWT.NULL);
        RowLayout layoutLabelText = new RowLayout(SWT.HORIZONTAL);
        layoutLabelText.spacing = 2;
        setPage.setLayout(layoutLabelText);

        RowData buttonSetPage = new RowData();
        buttonSetPage.width = 100;
        Button firstPage = new Button(setPage, SWT.PUSH);
        firstPage.setText("<<");
        firstPage.setLayoutData(buttonSetPage);
        Button prevPage = new Button(setPage, SWT.PUSH);
        prevPage.setText("<");
        prevPage.setLayoutData(buttonSetPage);
        numberPage = new Label(setPage, SWT.NONE);
        RowData rowDataLabelPage = new RowData();
        rowDataLabelPage.width = 25;
        numberPage.setLayoutData(rowDataLabelPage);
        Button nextPage = new Button(setPage, SWT.PUSH);
        nextPage.setText(">");
        nextPage.setLayoutData(buttonSetPage);
        Button lastPage = new Button(setPage, SWT.PUSH);
        lastPage.setText(">>");
        lastPage.setLayoutData(buttonSetPage);
        Label labelRecordPage = new Label(setPage, SWT.NONE);
        labelRecordPage.setText("  Кол-во записей на страницу: ");
        Combo numberRecordPage = new Combo(setPage, SWT.READ_ONLY | SWT.DROP_DOWN);
        for (int i = 1; i < 30; i++){
            numberRecordPage.add("" + i);
        }
        numberRecordPage.select(recordForPage - 1);
        Button buttonRecordPage = new Button(setPage, SWT.PUSH);
        buttonRecordPage.setText("Применить");
        buttonRecordPage.setLayoutData(buttonSetPage);

        labelRecord = new Label(setPage, SWT.NONE);
        RowData rowDataLabelRecord = new RowData();
        rowDataLabelRecord.width = 200;
        labelRecord.setLayoutData(rowDataLabelRecord);


        buttonRecordPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                setRecordPage(numberRecordPage.getSelectionIndex() + 1);
                firstPage();
            }
        });

        firstPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                firstPage();
            }
        });

        lastPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                lastPage();
            }
        });

        nextPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                nextPage();
            }
        });

        prevPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                prevPage();
            }
        });
        reload();
    }

    private void setRecordPage(int number){
        recordForPage = number;
        reload();
    }
    private void nextPage(){
        if(page < maxPage()){
            page++;
            reload();
        }
        return;

    }

    private void prevPage(){
        if(page > 1){
            page--;
        }
        reload();
    }

    private void firstPage(){
        page = 1;
        reload();
    }

    private void lastPage(){
        page = maxPage();
        reload();
    }

    private int maxPage(){

        if (persons.size() == 0){
            return 1;
        }
        int max = persons.size()/recordForPage;
        if(persons.size()%recordForPage != 0){
            max++;
        }
        return max;
    }

    private void setHandleTable(){
        table.removeAll();
        new TableItem(table, SWT.NONE);
        TableItem headline = table.getItem(0);
        headline.setText(0, "ФИО Студента");
        headline.setText(1, "Дата рождения");
        headline.setText(2, "Дата поступления");
        headline.setText(3, "Дата окончания вуза");
    }

    public void setData(ArrayList<Person> personArrayList){
        page = 1;
        persons = personArrayList;
        reload();
    }
    private void reload(){

        labelRecord.setText(" Количество записей: " + persons.size());
        numberPage.setText("" + page + "/" + maxPage());
        setHandleTable();
        int startIndex = (page - 1)*recordForPage;
        for(int index = startIndex; index < persons.size() && index < startIndex + recordForPage; index++){
            TableItem item = new TableItem(table, SWT.NONE);
            Person person = persons.get(index);
            item.setText(0, person.getFullName());
            item.setText(1, getDateFromCalendar(person.getDateOfBirth()));
            item.setText(2, getDateFromCalendar(person.getDateReceipt()));
            item.setText(3, getDateFromCalendar(person.getDateExpiparation()));
        }
    }
    private String getDateFromCalendar(Calendar calendar){
        int day = calendar.getTime().getDate();
        int month = calendar.getTime().getMonth();
        if(month == 0){
            month = 12;
        }
        int year = calendar.getTime().getYear() + 1900;
        String dayString = ""+day;
        String monthString = ""+month;
        if(day < 10){
            dayString = "0" + dayString;
        }
        if(month < 10){
            monthString = "0" + monthString;
        }
        return dayString+"."+monthString+"."+year;

    }
}
