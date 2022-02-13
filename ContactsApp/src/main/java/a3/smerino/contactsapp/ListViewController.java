package a3.smerino.contactsapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class ListViewController {
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    @FXML
    ListView<Contact> contactsList;
    @FXML
    GridPane newContact;
    @FXML
    TextField txtFirstName, txtLastName, txtPhone;

    ContactComparator comparator = new ContactComparator();


    @FXML
    public void initialize(){
        contactsList.setItems(contacts);
        setNewContactVis(false);
        loadListFromJSON();
        contacts.sort(comparator);
    }

    private void loadListFromJSON(){
        try (Scanner scanner = new Scanner(new File("contacts.json"))) {
            while(scanner.hasNextLine()){
                String contactString = scanner.nextLine();
                Contact tmp = new Contact(new JSONObject(contactString));
                contacts.add(tmp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } ;
    }

    @FXML
    public void shutdown(){
        System.out.println("Shutting down");
        try(FileWriter file = new FileWriter("contacts.json")){
            for(int i=0; i<contacts.size(); i++){
                file.write(contacts.get(i).getJSONString());
                if(i != contacts.size() - 1){
                    file.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onNewContact(){
        setNewContactVis(true);
    }

    @FXML
    protected void onSaveContact(){
        Contact tmp = new Contact(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText());
        contacts.add(tmp);
        setNewContactVis(false);
        txtFirstName.setText(null);
        txtLastName.setText(null);
        txtPhone.setText(null);
        contacts.sort(comparator);
    }

    @FXML
    protected void onEditContact(){
        Contact selectedContact = contactsList.getSelectionModel().getSelectedItem();
        if(selectedContact != null){
            onNewContact();
            txtFirstName.setText(selectedContact.getFirstName());
            txtLastName.setText(selectedContact.getLastName());
            txtPhone.setText(selectedContact.getPhone());
            onDeleteContact();
        }
    }

    @FXML
    protected void onDeleteContact(){
        Contact selectedContact = contactsList.getSelectionModel().getSelectedItem();
        if(selectedContact != null){
            contacts.remove(selectedContact);
        }

    }

    public void setNewContactVis(Boolean vis){
        newContact.setVisible(vis);
        newContact.setManaged(vis);

    }
}
