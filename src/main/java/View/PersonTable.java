package View;

import Model.DataBaseHelper;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.*;

public class PersonTable {
DataBaseHelper dataBaseHelper=new DataBaseHelper();
    public Node create() {
        TableView<Person> tableView = new TableView<>();

        // ID Column
        TableColumn<Person, Integer> idCol = new TableColumn<>("رمز");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(100);

        // Name Column
        TableColumn<Person, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);

        // Address Column
        TableColumn<Person, String> addressCol = new TableColumn<>("العنوان");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(250);

        // Gender Column
        TableColumn<Person, String> genderCol = new TableColumn<>("الجنس");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        genderCol.setPrefWidth(50);

        TableColumn<Person, String> phoneCol = new TableColumn<>("رقم الهاتف");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(100);

        // Birthday Column
        TableColumn<Person, Date> birthDayDateCol = new TableColumn<>("تاريخ الميلاد");
        birthDayDateCol.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));
        birthDayDateCol.setPrefWidth(200);

        // Add columns
        tableView.getColumns().addAll(idCol, nameCol, addressCol, genderCol,phoneCol, birthDayDateCol);

        TableColumn<Person, Void> colBtn = new TableColumn<>("حذف");

        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((event) -> {
                            Person person = getTableView().getItems().get(getIndex());
                            System.out.println("Deleting person: " + person.getName());

                            try {
                                dataBaseHelper.deletePerson(person.getId());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            // وبعدها تحديث الجدول
                            getTableView().getItems().remove(person);
                            tableView.setPrefHeight(tableView.getItems().size() * tableView.getFixedCellSize() + 30);

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        colBtn.setPrefWidth(80);

        tableView.getColumns().add(colBtn);

        // Set data
        tableView.setItems(getPersonList());

        // Layout and orientation
        tableView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        // Prevent vertical overflow
        tableView.setFixedCellSize(30);
        int rows = tableView.getItems().size();
        tableView.setPrefHeight(rows * tableView.getFixedCellSize() + 30);

        // Prevent horizontal scroll overflow
        tableView.setPrefWidth(idCol.getPrefWidth()
                + nameCol.getPrefWidth()
                + addressCol.getPrefWidth()
                + genderCol.getPrefWidth()
                + phoneCol.getPrefWidth()
                + birthDayDateCol.getPrefWidth());
        return tableView;
    }

    private ObservableList<Person> getPersonList() {
        ObservableList<Person> list = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Person")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String phone =rs.getString("phone");
                Date date = rs.getDate("BIRTHDAY_DATE");
                list.add(new Person(id, name, address, gender,phone, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
