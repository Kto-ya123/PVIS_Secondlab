package pvis_lab_first;

import org.eclipse.swt.widgets.Display;

import java.util.ArrayList;

public class Controller {
	private MainWindow mainWindow;
	private AddStudent addStudent;
	private ArrayList persons;
	private Display display;
	public Controller() {
		persons = new ArrayList<Person>();
		display = new Display();
		mainWindow = new MainWindow(display, this);
		mainWindow.start();
	}

	public void addStudent() {
		addStudent = new AddStudent(display, this);
		addStudent.start();
	}

	public void reload(){
		mainWindow.reload(persons);
	}

	public void addRecord(Person person){
		persons.add(person);
		mainWindow.reload(persons);
	}

}
