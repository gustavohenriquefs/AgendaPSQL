package Controller;

import DAO.UsuarioDAO;
import JDBC.ConnectionFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class FXMLCadastroController implements Initializable {

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nome;

    @FXML
    private PasswordField txt_senha;

    @FXML
    private PasswordField txt_confirm;

    @FXML
    void cadastrar(ActionEvent event) {
        cadastrarUser();
    }

    @FXML
    void voltar() {
        agendapsql.AgendaPSQL.changeScreen("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Connection getConn() {
        Connection c = ConnectionFactory.getConnection();
        return c;
    }

    private void cadastrarUser() {
        String nome = txt_nome.getText(), email = txt_email.getText(), senha = txt_senha.getText(), confirmacao = txt_confirm.getText();

        if (nome.equals("") || senha.equals("") || "null".equals(nome)) {
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("failed: preencha o nome e a senha");
            al.show();
        } else {
            if (senha.equals(confirmacao)) {
                Usuario u = new Usuario(nome, email, senha);
                UsuarioDAO k = new UsuarioDAO();
                System.out.println(u.getNome());
                System.out.println(k.verificarUserCad(u).getNome());
                if (u.getNome().equals(k.verificarUserCad(u).getNome())) {
                    Alert al = new Alert(AlertType.ERROR);
                    al.setHeaderText("failed: esse nome de usuário já está sendo usado!");
                    al.show();
                } else {
                    if (k.add(u)) {
                        Alert al = new Alert(AlertType.CONFIRMATION);
                        al.setHeaderText("usuário cadastrado com sucesso!");
                        al.show();

                        txt_nome.setText("");
                        txt_email.setText("");
                        txt_senha.setText("");
                        txt_confirm.setText("");
                        voltar();
                    } else {
                        Alert al = new Alert(AlertType.ERROR);
                        al.setHeaderText("failed: usuário não cadastrado!");
                        al.show();
                    }
                }
            } else {
                Alert al = new Alert(AlertType.ERROR);
                al.setHeaderText("failes: as senhas não coincidem");
                al.show();
            }
        }
    }
}
