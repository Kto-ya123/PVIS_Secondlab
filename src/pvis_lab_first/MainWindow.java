package pvis_lab_first;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;


public class MainWindow {
	private Display display;
	private Shell shell;
	private Controller controller;
	private Table tableStudent;
	private String path = "C://Users/Artur/eclipse-workspace/pvis_lab_first/src/pvis_lab_first/";

	
	public MainWindow(Display display, Controller controller) {
		this.controller = controller;
		this.display = display;
		shell = new Shell(display);
		shell.setSize(1200, 700);

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
		final MenuItem findMenuItem = new MenuItem(editmenu, SWT.PUSH);
		findMenuItem.setText("&Find");
		// create a Help menu and add an about item
		final MenuItem help = new MenuItem(menu, SWT.CASCADE);
		help.setText("&Help");
		final Menu helpmenu = new Menu(shell, SWT.DROP_DOWN);
		help.setMenu(helpmenu);
		final MenuItem aboutMenuItem = new MenuItem(helpmenu, SWT.PUSH);
		aboutMenuItem.setText("&About");
		shell.setMenuBar(menu);

		tableStudent = new Table(this.shell, SWT.BORDER | 6556);
		tableStudent.setLinesVisible(true);
		RowData rowDataTableFifthTask = new RowData();
		rowDataTableFifthTask.height = 363;
		tableStudent.setLayoutData(rowDataTableFifthTask);

		TableColumn column = new TableColumn(tableStudent, 0);
		column.setWidth(400);

		for (int i = 0; i < 3; ++i) {
			column = new TableColumn(tableStudent, 0);
			column.setWidth(250);
		}
		tableStudent.getColumn(0);
		setHandleTable();



		
		addToolItem.addSelectionListener(new SelectionAdapter() {
	        @Override
	        public void widgetSelected(SelectionEvent e) {
	        	callAddWindow();
	         }
	     });

		searchToolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.reload();
			}
		});

		addMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				callAddWindow();
			}
		});
	}

	public void reload(ArrayList<Person> personArrayList){
		setHandleTable();
		for(int index = 0; index < personArrayList.size(); index++){
			TableItem item = new TableItem(tableStudent, SWT.NONE);
			item.setText(0, personArrayList.get(index).getFullName());
			item.setText(1, personArrayList.get(index).getDateOfBirth().getDateString() );
			item.setText(2, personArrayList.get(index).getDateReceipt().getDateString());
			item.setText(3, personArrayList.get(index).getDateExpiparation().getDateString());
		}

	}

	private void setHandleTable(){
		tableStudent.removeAll();
		new TableItem(tableStudent, SWT.NONE);
		TableItem headline = tableStudent.getItem(0);
		headline.setText(0, "ФИО Студента");
		headline.setText(1, "Дата рождения");
		headline.setText(2, "Дата поступления");
		headline.setText(3, "Дата окончания вуза");
	}
	private void callAddWindow(){
		shell.setEnabled(false);
		controller.addStudent();
		shell.setEnabled(true);
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
