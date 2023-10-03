
import DAO.CompromissoDAO;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Compromisso;
import model.Usuario;

public class main {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene cadastroScene;
    private static Scene homeScene;
    private static Scene listaCompromissosScene;

    public void start(Stage pStage) {

        try {
            if (m()) {
                stage = pStage;

                pStage.setTitle("AgendaPSQL");

                Parent fxmlHome = FXMLLoader.load(getClass().getResource("/View/FXMLHome.fxml"));
                homeScene = new Scene(fxmlHome, 640, 480);

                pStage.setScene(homeScene);
                pStage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean m() {

        return true;
    }

    // troca de telas
    public static void changeScreen(String src) {
        switch (src) {
            case "home":
                System.out.println("START");
                testDao dao = new testDao();
                ArrayList<Compromisso> lista = new ArrayList<Compromisso>();
                lista = dao.getList(3l);
                for (Compromisso p : lista) {
                    p.show();
                }
                stage.setScene(homeScene);
        }
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}
