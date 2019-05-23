package pvis_lab_first;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class AddStudent {
	private Display display;
	private Shell shell;
	private Controller controller;

	public AddStudent(Display display, Controller controller) {
		this.controller = controller;
		this.display = display;
		shell = new Shell(display);
		shell.setSize(1000, 200);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		RowLayout horizont = new RowLayout(SWT.HORIZONTAL);
		horizont.spacing = 20;
		Composite composite = new Composite(shell, SWT.NULL);
		composite.setLayout(horizont);

		RowData rowText = new RowData();
		rowText.width = 100;

		RowLayout layoutLabelText = new RowLayout(SWT.HORIZONTAL);
		layoutLabelText.spacing = 3;

		Composite nameGroup = new Composite(composite, SWT.NULL);
		nameGroup.setLayout(layoutLabelText);
		Label labelName = new Label(nameGroup, SWT.NULL);
		labelName.setText("ФИО: ");
		Text name = new Text(nameGroup, SWT.BORDER);
		name.setLayoutData(rowText);

		Composite dateBirthday = new Composite(composite, SWT.NULL);
		dateBirthday.setLayout(layoutLabelText);
		Label labelDateBirthday = new Label(dateBirthday, SWT.NULL);
		labelDateBirthday.setText("Дата рождения: ");
		Text textDateBirthday  = new Text(dateBirthday, SWT.BORDER);
		textDateBirthday.setLayoutData(rowText);

		Composite dateReceipt  = new Composite(composite, SWT.NULL);
		dateReceipt.setLayout(layoutLabelText);
		Label labelDateReceipt = new Label(dateReceipt, SWT.NULL);
		labelDateReceipt.setText("Дата поступления: ");
		Text textDateReceipt = new Text(dateReceipt, SWT.BORDER);
		textDateReceipt.setLayoutData(rowText);

		Composite dateExpiparation = new Composite(composite, SWT.NULL);
		dateExpiparation.setLayout(layoutLabelText);
		Label labelDateExpiparation = new Label(dateExpiparation, SWT.NULL);
		labelDateExpiparation.setText("Дата окончания: ");
		Text textDateExpiparation = new Text(dateExpiparation, SWT.BORDER);
		textDateExpiparation.setLayoutData(rowText);

		Button addRecord = new Button(composite, SWT.PUSH);
		addRecord.setText("Add");
	    rowLayout.marginLeft = 10;
	    rowLayout.marginTop = 10;
	    rowLayout.spacing = 40;

	    shell.setLayout(rowLayout);

	    addRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Person person = new Person();
				String fullName = name.getText();
				int firstSpace = fullName.indexOf(' ');
				person.setLastName(fullName.substring(0, firstSpace));
				fullName = fullName.substring(firstSpace + 1);
				firstSpace = fullName.indexOf(' ');
				person.setFirstName(fullName.substring(0, firstSpace));
				fullName = fullName.substring(firstSpace + 1);
				person.setMiddleName(fullName);

				Date dateBirthday = new Date(textDateBirthday.getText());
				Date dateReceipt = new Date(textDateReceipt.getText());
				Date dateExpiparation = new Date(textDateExpiparation.getText());

				person.setDateOfBirth(dateBirthday);
				person.setDateReceipt(dateReceipt);
				person.setDateExpiparation(dateExpiparation);

				controller.addRecord(person);
			}
		});
	}
	public void start() {
		shell.open();
	}

}
