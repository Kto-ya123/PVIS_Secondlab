package pvis_lab_first.Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Model {
    private static ArrayList<Person> persons;
    private String path = "C://Users/Artur/eclipse-workspace/pvis_lab_first/src/pvis_lab_first/resource/";

    public Model(){
        persons = new ArrayList<>();
    }

    private static class XMLHandler extends DefaultHandler{
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("person")) {
                Person person = new Person();
                person.setFirstName(attributes.getValue("firstname"));
                person.setLastName(attributes.getValue("lastName"));
                person.setMiddleName(attributes.getValue("middleName"));
                GregorianCalendar dateBirth = new GregorianCalendar(Integer.parseInt(attributes.getValue("yearBirth"))+1900,
                        Integer.parseInt(attributes.getValue("monthBirth")),
                        Integer.parseInt(attributes.getValue("dayBirth")));
                GregorianCalendar dateReceipt = new GregorianCalendar(Integer.parseInt(attributes.getValue("yearReceipt"))+1900,
                        Integer.parseInt(attributes.getValue("monthReceipt")),
                        Integer.parseInt(attributes.getValue("dayReceipt")));
                GregorianCalendar dateExpiparation = new GregorianCalendar(Integer.parseInt(attributes.getValue("yearExp"))+1900,
                        Integer.parseInt(attributes.getValue("monthExp")),
                        Integer.parseInt(attributes.getValue("dayExp")));
                person.setDateOfBirth(dateBirth);
                person.setDateReceipt(dateReceipt);
                person.setDateExpiparation(dateExpiparation);
                persons.add(person);
            }
        }
    }

    public void load(String pathLoad) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File(pathLoad), handler);

    }
    public void save(String pathSave){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    doc.createElementNS("https://", "Languages");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);

            for(int index = 0; index < persons.size(); index++) {
                rootElement.appendChild(getLanguage(doc, ""+index, persons.get(index)));
            }

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(pathSave));

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getLanguage(Document doc, String id, Person person) {
        Element language = doc.createElement("person");

        // устанавливаем атрибут id
        language.setAttribute("id", id);

        // создаем элемент name
        language.setAttribute( "firstName", person.getFirstName());
        language.setAttribute("lastName", person.getLastName());
        language.setAttribute( "middleName", person.getMiddleName());
        language.setAttribute( "dayBirth", ""+person.getDateOfBirth().getTime().getDate());
        language.setAttribute( "monthBirth", ""+person.getDateOfBirth().getTime().getMonth());
        language.setAttribute("yearBirth", ""+person.getDateOfBirth().getTime().getYear());
        language.setAttribute( "dayReceipt", ""+person.getDateReceipt().getTime().getDate());
        language.setAttribute( "monthReceipt", ""+person.getDateReceipt().getTime().getMonth());
        language.setAttribute( "yearReceipt", ""+person.getDateReceipt().getTime().getYear());
        language.setAttribute("dayExp", ""+person.getDateExpiparation().getTime().getDate());
        language.setAttribute( "monthExp", ""+person.getDateExpiparation().getTime().getMonth());
        language.setAttribute( "yearExp", ""+person.getDateExpiparation().getTime().getYear());

        return language;
    }

    // утилитный метод для создание нового узла XML-файла
    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> allperson = new ArrayList<>(persons);
        return allperson;
    }

    public void addPerson(Person person){
        persons.add(person);
    }

    public int deletePersonForFilter(SearchFilter searchFilter) {
        ArrayList<Person> deletePerson = new ArrayList<>(getPersonForFilter(searchFilter));
        for(int i = 0; i < deletePerson.size(); i++){
            /*for (int j = 0; j < persons.size(); j++){

            }*/
            persons.remove(deletePerson.get(i));
        }
        return deletePerson.size();


    }
    public ArrayList<Person> getPersonForFilter(SearchFilter searchFilter){
        //return persons;
        ArrayList<Person> filterPersons = new ArrayList<>(persons);
        boolean isRedact = false;
        for(int index = 0; index < filterPersons.size(); index++){
            //start filter for name
            //
            if(!searchFilter.getFirstName().equals("")){
                isRedact = true;
                if(!filterPersons.get(index).getFirstName().equals(searchFilter.getFirstName())){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }
            if(!searchFilter.getLastName().equals("")){
                isRedact = true;
                if(!filterPersons.get(index).getLastName().equals(searchFilter.getLastName())){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }
            if(!searchFilter.getMiddleName().equals("")){
                isRedact = true;
                if(!filterPersons.get(index).getMiddleName().equals(searchFilter.getMiddleName())){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }
            //end filter for name
            //start filter for end time
            if(searchFilter.getEndBirthday() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateOfBirth().getTime().getYear() -1 >
                    searchFilter.getEndBirthday().getTime().getYear()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getEndReceipt() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateReceipt().getTime().getYear() -1 >
                        searchFilter.getEndReceipt().getTime().getYear()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getEndExpiparation() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateExpiparation().getTime().getYear()-1 >
                        searchFilter.getEndExpiparation().getTime().getYear()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }
            //end filter end time
            //begin filter begin time
            if(searchFilter.getBeginBirthday() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateOfBirth().getTime().getYear()-1 <
                        searchFilter.getBeginBirthday().getTime().getYear()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getBeginReceipt() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateReceipt().getTime().getYear()-1 <
                        searchFilter.getBeginReceipt().getTime().getYear()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getBeginExpiparation() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateExpiparation().getTime().getYear()-1 <
                        searchFilter.getBeginExpiparation().getTime().getYear()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }
            //end filter begin time
            //begin filter day and month
            if(searchFilter.getDayBirthday() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateOfBirth().getTime().getDate() !=
                    searchFilter.getDayBirthday()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getMonthBirthday() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateOfBirth().getTime().getMonth() !=
                        searchFilter.getMonthBirthday()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getDayReceipt() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateReceipt().getTime().getDate() !=
                        searchFilter.getDayReceipt()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getMonthReceipt() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateReceipt().getTime().getMonth() !=
                        searchFilter.getMonthReceipt()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getDayExpiparation() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateExpiparation().getTime().getDate() !=
                        searchFilter.getDayExpiparation()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }

            if(searchFilter.getMonthExpiparation() != null){
                isRedact = true;
                if(filterPersons.get(index).getDateExpiparation().getTime().getMonth() !=
                        searchFilter.getMonthExpiparation()){
                    filterPersons.remove(index);
                    index--;
                    continue;
                }
            }
            //end filter day and month
        }
        if(isRedact){
            return filterPersons;
        }else{
            return new ArrayList<Person>();
        }
    }
}
