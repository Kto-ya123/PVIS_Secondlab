package pvis_lab_first.Controller;

import org.eclipse.swt.widgets.Display;
import org.xml.sax.SAXException;
import pvis_lab_first.Model.Model;
import pvis_lab_first.Model.Person;
import pvis_lab_first.Model.SearchFilter;
import pvis_lab_first.View.AddStudent;
import pvis_lab_first.View.DeleteStudent;
import pvis_lab_first.View.MainWindow;
import pvis_lab_first.View.SearchStudent;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Controller {
	private MainWindow mainWindow;
	private AddStudent addStudent;
	private SearchStudent searchStudent;
	private DeleteStudent deleteStudent;
	private Display display;
	private Model model;

	public Controller() {
		display = new Display();
		model = new Model();
		callMainWindow();
	}

	public void save(String path){
		model.save(path);
	}

	public void load(String path) throws IOException, SAXException, ParserConfigurationException {
		model.load(path);
		mainWindow.setData(model.getAllPersons());
	}

	public void callMainWindow(){
		mainWindow = new MainWindow(display, this);
		reload();
		mainWindow.start();

	}

	public void callDeleteRecords(){
		deleteStudent = new DeleteStudent(this.display,this);
		deleteStudent.start();
	}

	public void addStudent() {
		addStudent = new AddStudent(display, this);
		addStudent.start();
	}

	public void searchStudent() {
		searchStudent = new SearchStudent(display, this);
		searchStudent.start();
	}

	public void reload(){
		mainWindow.setData(model.getAllPersons());
	}

	public void addRecord(Person person){
		model.addPerson(person);
		reload();
	}

	public void deleteRecordsForFilter(SearchFilter searchFilter){
		int numberDelete = model.deletePersonForFilter(searchFilter);
		mainWindow.setData(model.getAllPersons());
		deleteStudent.setRezult(numberDelete);
	}

	public void searchForFilter(SearchFilter searchFilter){
		searchStudent.setData(model.getPersonForFilter(searchFilter));
	}

}
