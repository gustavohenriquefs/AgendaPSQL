package Controller;

import DAO.UsuarioDAO;
import agendapsql.AgendaPSQL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_nome;

    @FXML
    private PasswordField txt_senha;

    @FXML
    private PasswordField txt_confirm;

    @FXML
    private Button btn_cad;

    @FXML
    private Button btn_entrar;

    @FXML
    void cadastro(ActionEvent event) {
        agendapsql.AgendaPSQL.changeScreen("cadastro");
    }

    @FXML
    void sair() {
        agendapsql.AgendaPSQL.changeScreen("close");
    }

    @FXML
    void initialize() {

    }

    @FXML
    void login(ActionEvent event) {
        String nome = txt_nome.getText();
        if (nome.equals("") || txt_senha.getText().equals("")) {
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("failed: preencha todos os dados!");
            al.show();
        } else {
            if (txt_senha.getText().equals(txt_confirm.getText())) {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario k = new Usuario();
                k.setNome(nome);
                k.setSenha(txt_senha.getText());
                Usuario n = dao.verificarUser(k);
                if (n.getNome() != null) {
                    Controller.FXMLHomeController.id = n.getId();
                    txt_nome.setText("");
                    txt_senha.setText("");
                    txt_confirm.setText("");
                    AgendaPSQL.changeScreen("home");
                } else {
                    Alert al = new Alert(AlertType.ERROR);
                    al.setHeaderText("failed: login inválido!");
                    al.show();
                }
            } else {
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText("failed: as senhas não coincidem");
                al.show();
            }
        }
    }
}