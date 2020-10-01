package by.lexer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    final static String DICT = "Program dictionary n = ";
    final static String LENGTH = "Program length N = ";
    final static String VOLUME = "Volume of the program V = ";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text dictionaryStr;

    @FXML
    private Text n1Value;

    @FXML
    private Text n2Value;

    @FXML
    private Text nu1Value;

    @FXML
    private Text nu2Value;

    @FXML
    private Text lengthString;

    @FXML
    private Text scopeString;

    @FXML
    private TableView<Item> tableOperator;

    @FXML
    private TableColumn<Item, Integer> j;

    @FXML
    private TableColumn<Item, String> operator;

    @FXML
    private TableColumn<Item, Integer> f1j;

    @FXML
    private TableView<Item> tableOperand;

    @FXML
    private TableColumn<Item, Integer> i;

    @FXML
    private TableColumn<Item, String> operand;

    @FXML
    private TableColumn<Item, Integer> f2i;

    private static ObservableList<Item> dataOperator = FXCollections.observableArrayList();

    private static ObservableList<Item> dataOperand  = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        j.setCellValueFactory(new PropertyValueFactory<>("id"));
        operator.setCellValueFactory(new PropertyValueFactory<>("item"));
        f1j.setCellValueFactory(new PropertyValueFactory<>("counter"));
        i.setCellValueFactory(new PropertyValueFactory<>("id"));
        operand.setCellValueFactory(new PropertyValueFactory<>("item"));
        f2i.setCellValueFactory(new PropertyValueFactory<>("counter"));
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void aboutDevelopers(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About developers");
        alert.setContentText("This program was developed by Bakyt Madi and Maiski Vlad, group 951007");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void aboutProgram(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About program");
        alert.setContentText("This program shows halstead metrics for a program written in Kotlin");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(Main.getStage());
        if (file != null && file.getAbsolutePath().matches("^.+.kt$")) {
            dataOperand.clear();
            dataOperator.clear();
            StringBuilder sb = new StringBuilder("");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String s = br.readLine();
                while(s != null) {
                    sb.append(s).append("\n");
                    s = br.readLine();
                }
            } catch (Exception e) {
                System.err.println("File error");
            }
            Lexer l = new Lexer(sb.toString());
            Parser p = new Parser(l.getTokens());
            Metric m = new Metric(p.getArgTokens());
            System.out.printf("lexems:\n%s\nargs:\n%s\n", l.toString(), p.toString());
            getN1().setText(Integer.toString(m.getN1()));
            getN2().setText(Integer.toString(m.getN2()));
            getNu1().setText(Integer.toString(m.getNu1()));
            getNu2().setText(Integer.toString(m.getNu2()));
            getDictionaryStr().setText(DICT + m.getNu());
            getLengthString().setText(LENGTH + m.getN());
            getScopeString().setText(VOLUME + m.getV());
            fillData(m.getOperatorsArgs(), m.getOperandsArgs());
            fillTables();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error file");
            alert.setHeaderText(null);
            alert.setContentText((file == null ? "You haven't selected a file."
                    : "Selected file is not a Kotlin program.") + " Please, try again.");
            alert.showAndWait();
        }
    }

    public static ObservableList<Item> getDataOperator() {
        return dataOperator;
    }

    public static ObservableList<Item> getDataOperand() {
        return dataOperand;
    }

    private static void fillData(ArrayList<Argument> operators, ArrayList<Argument> operands) {
        int id = 1;
        for (Argument op: operators) {
            dataOperator.add(new Item(op.value, op.amount, id++));
        }
        id = 1;
        for (Argument op: operands) {
            dataOperand.add(new Item(op.value, op.amount, id++));
        }
    }



    public Text getDictionaryStr() {
        return dictionaryStr;
    }

    public Text getLengthString()  {
        return lengthString;
    }

    public Text getScopeString() {
        return scopeString;
    }

    public Text getN1() {
        return n1Value;
    }

    public Text getN2() {
        return n2Value;
    }

    public Text getNu1() {
        return nu1Value;
    }

    public Text getNu2() {
        return nu2Value;
    }

    public void fillTables() {
        tableOperator.setItems(dataOperator);
        tableOperand.setItems(dataOperand);
    }
}
