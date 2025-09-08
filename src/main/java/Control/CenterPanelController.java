package Control;

import Model.DataBaseHelper;
import Model.Person;
import View.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.*;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

public class CenterPanelController {

    private DataBaseHelper dataBaseHelper;
    private final CenterPanel centerPanel;

    public CenterPanelController(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;

        ObservableList<Node> children = centerPanel.getDashBoard().getChildren();
        Button lastButton = (Button) children.get(children.size() - 1);
        lastButton.setOnAction(e -> this.generateReport());

        dataBaseHelper=new DataBaseHelper();
    }

    // Prepare and show the dashboard view
    public void showDashboard() {
        centerPanel.setContent(centerPanel.getDashBoard());
    }

    public void displayPersons(){
        PersonTable tableCreator = new PersonTable();
        Node tableView = tableCreator.create();
        centerPanel.setContent(tableView);
    }
    // Show the person form view
    public void showPersonForm() {
        centerPanel.setContent(centerPanel.getPersonForm());

        ObservableList<Node> children = centerPanel.getPersonForm().getChildren();
        if (!children.isEmpty()) {
            Button addPersonButton = (Button) children.get(children.size() - 1);
            addPersonButton.setOnAction(e -> addPersonButtonFunction());
        }
    }

    public void addPersonButtonFunction() {
       AddPersonPage personForm=  centerPanel.getPersonForm();
       TextField nameField=personForm.getNameField();
       TextField idField=personForm.getIdField();
       TextField addressField=personForm.getAddressField();
       TextField phoneField=personForm.getPhoneField();
        ComboBox<String> genderBox=personForm.getGenderBox();
        DatePicker datePicker=personForm.getBirthDatePicker();

       String name=nameField.getText();
       String gender=genderBox.getValue();
       String address=addressField.getText();
       String phone=phoneField.getText();

       int id= Integer.parseInt(idField.getText());

        LocalDate localDate =datePicker.getValue();
        Date birthdate_Day = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (personForm.getNameField().getText().isEmpty() || personForm.getIdField().getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("تحذير");
                alert.setHeaderText(null);
                alert.setContentText("يرجى تعبئة جميع الحقول المطلوبة.");
                alert.showAndWait();
            } else {
                try {
                    int i = dataBaseHelper.insertPerson(new Person(id, name, address, gender, phone,birthdate_Day));

                    System.out.println(i+"تم حفظ البيانات:");
                    System.out.println("الاسم: " + name);
;                    System.out.println("الرقم الوطني: " + id);
                    System.out.println("الجنس: " + gender);
                    System.out.println("تاريخ الميلاد: " + birthdate_Day);
                    System.out.println("العنوان: " + address);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("تم الحفظ");
                    alert.setHeaderText(null);
                    alert.setContentText("تم حفظ بيانات الشخص بنجاح!");
                    alert.showAndWait();

                    nameField.setText("");
                    idField.setText("");
                    genderBox.getSelectionModel().clearSelection();
                    datePicker.setValue(null);
                    addressField.setText("");
                    phoneField.setText("");

                }catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }

    }
    // Show a generic section label
    public void showSection(String name) {
        Label label = new Label("فتح القسم: " + name);
        label.getStyleClass().add("section-label");
        centerPanel.setContent(label);
    }

    public void newReportClicked(){
        ReportMenu reportMenu=new ReportMenu(this);
        centerPanel.setContent(reportMenu);
    }

    public void openKhula(){
TemplateView templateView=new TemplateView( "C:\\Users\\Lenovo\\Documents\\Intellij Codes\\try dotx template\\my_template.docx");
   centerPanel.setContent(templateView);
    }

    public void openTalaq(){
        centerPanel.setContent(new Label("طلااااق"));

    }
    // Create the dashboard UI node with buttons and stats cards


    // Method to generate Jasper report
    private void generateReport() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/test_reports.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, "test_reports.pdf");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("تم التوليد");
            alert.setHeaderText(null);
            alert.setContentText("تم توليد تقرير الزواج بنجاح!");
            alert.showAndWait();

        } catch (JRException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText("فشل التوليد");
            alert.setContentText("حدث خطأ أثناء توليد التقرير.");
            alert.showAndWait();
        }
    }

    public void searchPerson() {
        PersonSearchBox personSearchBox=new PersonSearchBox();
        centerPanel.setContent(personSearchBox);
    }
}
