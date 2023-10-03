package agendapsql;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AgendaPSQL extends Application {

    private static Stage stage;
    private static Scene loginScene;
    private static Scene cadastroScene;
    private static Scene homeScene;
    
    @Override

    public void start(Stage pStage) throws IOException {
            stage = pStage;
            pStage.initStyle(StageStyle.UTILITY);
            // pStage.setTitle("AgendaPSQL");
            Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
            loginScene = new Scene(fxmlLogin, 750, 550);

            Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("/View/FXMLCadastro.fxml"));
            cadastroScene = new Scene(fxmlCadastro, 750, 550);

            Parent fxmlHome = FXMLLoader.load(getClass().getResource("/View/FXMLHome.fxml"));
            homeScene = new Scene(fxmlHome, 750, 550);
 
            //Parent fxmlListaC = FXMLLoader.load(getClass().getResource("/View/FXMLListaCompromissos.fxml"));
            // listaCompromissosScene = new Scene(fxmlListaC, 640, 480);
            pStage.setScene(loginScene);
            pStage.show();
    }

    // troca de telas
    public static void changeScreen(String src) {
        switch (src) {
            case "login":
                stage.setScene(loginScene);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                break;
            case "close":
                stage.close();
                break;
            // case "listaCompromissos":
            //   stage.setScene(listaCompromissosScene);
            //     break;
            case "home":
                stage.setScene(homeScene);
        }
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

}
