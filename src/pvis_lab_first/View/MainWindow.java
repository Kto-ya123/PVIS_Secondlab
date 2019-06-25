package pvis_lab_first.View;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.xml.sax.SAXException;
import pvis_lab_first.Controller.Controller;
import pvis_lab_first.Model.Person;

import javax.xml.parsers.ParserConfigurationException;


public class MainWindow {
	private Display display;
	private Shell shell;
	private Controller controller;
	private String path = "C://Users/Artur/eclipse-workspace/pvis_lab_first/src/pvis_lab_first/";
	private TableComponent tableComponent;

	
	public MainWindow(Display display, Controller controller) {
		this.controller = controller;
		this.display = display;
		shell = new Shell(display);
		shell.setSize(1240, 620);

		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 5;
		rowLayout.spacing = 10;
		shell.setLayout(rowLayout);

		final Image addIcon = new Image(this.display, path + "plus_icon.png");
		final Image deleteIcon = new Image(this.display, path + "delete_sign.png");
		final Image searchIcon = new Image(this.display, path + "search_icon.png");

		final ToolBar bar = new ToolBar(shell,SWT.HORIZONTAL);
		bar.setSize(400,50);
		bar.setLocation(0,0);
		final ToolItem addToolItem = new ToolItem(bar, SWT.PUSH);
		addToolItem.setImage(addIcon);
		final ToolItem deleteToolItem = new ToolItem(bar, SWT.PUSH);
		deleteToolItem.setImage(deleteIcon);
		final ToolItem searchToolItem = new ToolItem(bar, SWT.PUSH);
		searchToolItem.setImage(searchIcon);

		Menu menu = new Menu(shell,SWT.BAR);
		// create a file menu and add an exit item
		final MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("&File");
		final Menu filemenu = new Menu(shell, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		final MenuItem openMenuItem = new MenuItem(filemenu, SWT.PUSH);
		openMenuItem.setText("&Open\tCTRL+O");
		openMenuItem.setAccelerator(SWT.CTRL+'O');
		final MenuItem saveMenuItem = new MenuItem(filemenu, SWT.PUSH);
		saveMenuItem.setText("&Save\tCTRL+S");
		saveMenuItem.setAccelerator(SWT.CTRL+'S');
		final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		final MenuItem exitMenuItem = new MenuItem(filemenu, SWT.PUSH);
		exitMenuItem.setText("E&xit");
		// create an edit menu and add cut copy and paste items
		final MenuItem action = new MenuItem(menu, SWT.CASCADE);
		action.setText("&Action");
		final Menu editmenu = new Menu(shell, SWT.DROP_DOWN);
		action.setMenu(editmenu);
		final MenuItem addMenuItem = new MenuItem(editmenu, SWT.PUSH);
		addMenuItem.setText("&Add");
		final MenuItem deleteMenuItem = new MenuItem(editmenu, SWT.PUSH);
		deleteMenuItem.setText("&Delete");
		final MenuItem searchMenuItem = new MenuItem(editmenu, SWT.PUSH);
		searchMenuItem.setText("&Find");
		// create a Help menu and add an about item
		final MenuItem help = new MenuItem(menu, SWT.CASCADE);
		help.setText("&Help");
		final Menu helpmenu = new Menu(shell, SWT.DROP_DOWN);
		help.setMenu(helpmenu);
		final MenuItem aboutMenuItem = new MenuItem(helpmenu, SWT.PUSH);
		aboutMenuItem.setText("&About");
		shell.setMenuBar(menu);

		tableComponent = new TableComponent(shell);

		openMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileSave = new FileDialog(shell, SWT.OPEN);
				fileSave.setFilterNames(new String[] {"XML"});
				fileSave.setFilterExtensions(new String[] {"*.xml"});
				fileSave.setFilterPath("c:\\"); // Windows path
				fileSave.setFileName("your_file_name.xml");
				fileSave.open();
				fileSave.getFileName();
				try {
					controller.load(fileSave.getFilterPath()+"//" + fileSave.getFileName());
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (SAXException ex) {
					ex.printStackTrace();
				} catch (ParserConfigurationException ex) {
					ex.printStackTrace();
				}
			}
		});
		saveMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileSave = new FileDialog(shell, SWT.SAVE);
				fileSave.setFilterNames(new String[] {"XML"});
				fileSave.setFilterExtensions(new String[] {"*.xml"});
				fileSave.setFilterPath("c:\\"); // Windows path
				fileSave.setFileName("your_file_name.xml");
				fileSave.open();
				fileSave.getFileName();
				controller.save(fileSave.getFilterPath()+"//" + fileSave.getFileName());
			}
		});

		addToolItem.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	callAddWindow();
	         }
	     });

		addMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callAddWindow();
			}
		});

		deleteToolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callDeleteWindow();
			}
		});

		deleteMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callDeleteWindow();
			}
		});

		searchToolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callSearchWindow();
			}
		});

		searchMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callSearchWindow();
			}
		});
	}

	public void setData(ArrayList<Person> personArrayList){
		tableComponent.setData(personArrayList);
	}

	private void callAddWindow(){
		shell.setEnabled(false);
		controller.addStudent();
		shell.setEnabled(true);
		shell.setActive();
	}

	private void callSearchWindow(){
		shell.setEnabled(false);
		controller.searchStudent();
		shell.setEnabled(true);
		shell.setActive();
	}

	private void callDeleteWindow(){
		shell.setEnabled(false);
		controller.callDeleteRecords();
		shell.setEnabled(true);
		shell.setActive();
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
