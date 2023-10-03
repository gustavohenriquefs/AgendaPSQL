package Controller;

import DAO.CompromissoDAO;
import DAO.UsuarioDAO;
import agendapsql.AgendaPSQL;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Compromisso;
import model.Usuario;

public class FXMLHomeController implements Initializable {

    @FXML
    private Tab tab_home;

    @FXML
    private Tab tab_listComps;
    @FXML
    private Label lbl_nome;
    @FXML
    private TextField txt_titulo;

    @FXML
    private DatePicker txt_data;

    @FXML
    private TextArea txt_desc;

    @FXML
    private TableView<Compromisso> tabela;
    @FXML
    private TableColumn<Compromisso, Long> clm_id;
    @FXML
    private TableColumn<Compromisso, String> clm_titulo;
    @FXML
    private TableColumn<Compromisso, String> clm_desc;
    @FXML
    private TableColumn<Compromisso, String> clm_data;

    @FXML
    private TextField txt_id;

    @FXML
    void voltar() {
        agendapsql.AgendaPSQL.changeScreen("login");
    }

    /**/
    public static Long id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (id != null) {
            nomeUser(id);
            initTable(id);
        }

    }

    public void nomeUser(Long id_user) {
        UsuarioDAO dao = new UsuarioDAO();
        int a = id_user.intValue() - 1;
        Usuario k = dao.getList().get(a);
        lbl_nome.setText(k.getNome() + " !");
        System.out.println("O nome foi exposto");
    }

    public void initTable(Long id) {
        clm_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        clm_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        clm_desc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        clm_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        tabela.setItems(atualizaTabela(id));
    }

    ArrayList<Compromisso> l = new ArrayList<Compromisso>();

    public ObservableList<Compromisso> atualizaTabela(Long id) {
        CompromissoDAO dao = new CompromissoDAO();
        if (!l.isEmpty()) {
            l.clear();
            System.out.println("Limpou Tabela");
        }
        l.addAll(dao.getList(id));
        for (int i = 0; i < l.size(); i++) {
            l.get(i).show();
        }
        return FXCollections.observableArrayList(l);
    }
    
    // String myFormattedDate;

    @FXML
    void btn_cadastrar_compromisso() {
        System.out.println(txt_data);
        if ((txt_data.getValue() == null || " ".equals(txt_titulo.getText())) || "".equals(txt_desc.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("failed: por favor, preencha todas as informações!");
            alert.show();
        } else {
            System.out.println("_____________________________");
            LocalDate data = txt_data.getValue();
            String dtString = data.toString();
            CompromissoDAO dao = new CompromissoDAO();
            Compromisso c = new Compromisso(id, txt_titulo.getText(), txt_desc.getText(), dtString);

            if (dao.add(c)) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("compromisso salvo com sucesso!");
                alert.show();
                txt_titulo.setText("");
                txt_desc.setText("");

                txt_data.setValue(null);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("failed: ocorreu um problema para registrar o compromisso!");
                alert.show();
            }
        }
    }

    @FXML
    public void btn_voltar() {
        AgendaPSQL.changeScreen("login");
    }

    @FXML
    void listar() {
        initTable(id);
    }

    @FXML
    void btn_deletar_comp() {
        Long id_comp = Long.valueOf(txt_id.getText());
        CompromissoDAO dao = new CompromissoDAO();
        if (dao.deletarCompromisso(id, id_comp)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("compromisso deletado com sucesso!");
            alert.show();
            txt_id.setText("");
            initTable(id);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("failed: ocorreu um problema para deletar o compromisso!");
            alert.show();
        }
    }

    /*@FXML
    void close() {
        agendapsql.AgendaPSQL.changeScreen("close");
    }*/
    @FXML
    void txtLimTitle() {
        txt_titulo.setOnKeyTyped(event -> {
            int maxCharacters = 30;
            if (txt_titulo.getText().length() > maxCharacters) {
                event.consume();
            }
        });
    }

    @FXML
    void txtLimDesc() {
        txt_desc.setOnKeyTyped(event -> {
            int maxCharacters = 90;
            if (txt_desc.getText().length() > maxCharacters) {
                event.consume();
            }
        });
    }
}
