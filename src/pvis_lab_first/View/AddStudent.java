package pvis_lab_first.View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import pvis_lab_first.Controller.Controller;
import pvis_lab_first.Model.Person;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddStudent {
	private Display display;
	private Shell shell;
	private Controller controller;

	public AddStudent(Display display, Controller controller) {
		this.controller = controller;
		this.display = display;
		shell = new Shell(display);
		shell.setSize(1240, 200);
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

		Composite dateBirthdayGroup = new Composite(compositeDate, SWT.BORDER);

		dateBirthdayGroup.setLayout(layoutLabelText);
		Label labelDateBirthday = new Label(dateBirthdayGroup, SWT.NULL);
		labelDateBirthday.setText("Дата рождения: ");
		Combo[] comboDateBirtday = ComboDate.setComboDate(dateBirthdayGroup);

		Composite dateReceiptGroup  = new Composite(compositeDate, SWT.BORDER);
		dateReceiptGroup.setLayout(layoutLabelText);
		Label labelDateReceipt = new Label(dateReceiptGroup, SWT.NULL);
		labelDateReceipt.setText("Дата поступления: ");
        Combo[] comboDateReceipt = ComboDate.setComboDate(dateReceiptGroup);

		Composite dateExpiparationGroup = new Composite(compositeDate, SWT.BORDER);
		dateExpiparationGroup.setLayout(layoutLabelText);
		Label labelDateExpiparation = new Label(dateExpiparationGroup, SWT.NULL);
		labelDateExpiparation.setText("Дата окончания: ");
        Combo[] comboDateExpiparation = ComboDate.setComboDate(dateExpiparationGroup);

		Button addRecord = new Button(compositeDateButton, SWT.PUSH);
		addRecord.setText("Добавить");
		RowData rowDataAddButton = new RowData();
		rowDataAddButton.width = 955;
		rowDataAddButton.height = 38;
		addRecord.setLayoutData(rowDataAddButton);


	    Composite rezultGroup = new Composite(shell, SWT.NULL);
	    rezultGroup.setLayout(layoutLabelText);
	    Label rezultForm = new Label(rezultGroup, SWT.NONE);
	    rezultForm.setText("Результат: ");
		Label rezultAdding = new Label(rezultGroup, SWT.NONE);
		RowData rowDataRezult = new RowData();
		rowDataRezult.width = 500;
		rezultAdding.setLayoutData(rowDataRezult);

	    shell.setLayout(rowLayout);

	    addRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Person person = new Person();
				if(firstName.getText().equals("") ||
						lastName.getText().equals("") ||
						middleName.getText().equals("")){
					rezultAdding.setText("Запись не была добавлена: Заполните все поля");
					return;
				}
				person.setFirstName(firstName.getText());
				person.setLastName(lastName.getText());
				person.setMiddleName(middleName.getText());
				int[] date = new int[3];
				for(int index = 0; index < 3; index++){
				    int select = comboDateBirtday[index].getSelectionIndex();
				    date[index] = Integer.parseInt(comboDateBirtday[index].getItem(select));
                }
                Calendar dateBirthday = new GregorianCalendar(date[2], date[1], date[0]);
                for(int index = 0; index < 3; index++){
                    int select = comboDateReceipt[index].getSelectionIndex();
                    date[index] = Integer.parseInt(comboDateReceipt[index].getItem(select));
                }
                Calendar dateReceipt = new GregorianCalendar(date[2], date[1], date[0]);
                for(int index = 0; index < 3; index++){
                    int select = comboDateExpiparation[index].getSelectionIndex();
                    date[index] = Integer.parseInt(comboDateExpiparation[index].getItem(select));
                }
                Calendar dateExpiparation = new GregorianCalendar(date[2], date[1], date[0]);
				person.setDateOfBirth(dateBirthday);
				person.setDateReceipt(dateReceipt);
				person.setDateExpiparation(dateExpiparation);

				Date dateNow = Calendar.getInstance().getTime();
				String timeNow = dateNow.getHours() + ":" + dateNow.getMinutes() + ":" + dateNow.getSeconds();
				rezultAdding.setText("Запись успешно добавлена - " + person.getFullName() + " " + timeNow);

				controller.addRecord(person);

			}
		});
	}

	public void start() {
		shell.open();
        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }
	}

}
