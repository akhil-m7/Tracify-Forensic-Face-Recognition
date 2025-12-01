package thirdeye.v2;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Upload_sketchController {

    @FXML
    private Button launchFaceMatch;

    @FXML
    private Button backButton;

    @FXML
    private void openFaceMatch() {
        try {
            // Path to the JAR file inside the project's target folder
            String jarPath = "C:\\Users\\Temp\\OneDrive\\Desktop\\project\\Project Code (forensic face sketch)\\ThirdEye_FaceMatch\\target\\ThirdEye_FaceMatch-1.0-SNAPSHOT.jar"; // Update this path

            // Create ProcessBuilder to execute the JAR file
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarPath);
            processBuilder.start();

            System.out.println("ThirdEyeFaceMatch application launched.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Failed to launch ThirdEyeFaceMatch.");
        }
    }

   @FXML
private void goBack() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/thirdeye/v2/menu.fxml")); // Adjust the path
        Parent root = loader.load();

        // Get the current stage from the back button
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Failed to load the Dashboard.");
    }
}
}

