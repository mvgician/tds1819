package application.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.controller.AppVideo;
import application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
public class ViewController {

	private double oldHeight = 0;
	private double oldWidth = 0;
	private double xOffset = 0; 
	private double yOffset = 0;
	private AppVideo controller = AppVideo.getUnicaInstancia();
	boolean isProfileOpen = false;
	
	/* STACKPANE EXTERIOR PARA JFXDIALOG */
    @FXML
    private StackPane rootStackPane;
	
    /* BORDERPANE EXTERIOR */
    @FXML
    private BorderPane rootBorderPane;
	
	/* BOTONES DE LA TOPBAR */
    @FXML
    private JFXButton minimizeButton, maximizeButton, closeButton;
    
    /* BOTONES DE LA SIDEBAR */
    @FXML
    private JFXButton mislistas, premium, explorar, recientes, login, nuevalista;
    
    /* STACKPANE PARA CONTENER TODAS LAS VENTANAS */
    @FXML
    private StackPane stackpane;  
    @FXML
    private AnchorPane testwindow; 
    
    /* VENTANA DE LOGIN */ 
    @FXML
    private GridPane loginView; // Contenedor padre de la ventana de login
    @FXML
    private Label loginLabelNick, loginLabelPassword, registerLabel;  
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXTextField loginNick;   
    @FXML
    private JFXPasswordField loginPassword;
  
    /* VENTANA DE REGISTRO */
    @FXML
    private GridPane registerView; // Contenedor padre de la ventana de registro
    @FXML
    private Label registerLabelNick, registerLabelPassword, registerLabelPassRepeat, registerLabelName, registerLabelDate;   
    @FXML
    private JFXTextField registerNick, registerName, registerSurname, registerEmail;      
    @FXML
    private JFXPasswordField registerPassword, registerPasswordRepeat;
    @FXML
    private JFXDatePicker registerDatePicker;
    @FXML
    private JFXButton registerRegister, registerCancel;
   
    /* VENTANA DE PERFIL */
    @FXML
    private GridPane profileView; // Contenedor de la vista de perfil
    @FXML
    private Label profileNick, profileTitle;
    @FXML
    private JFXTextField profileEmail;
    @FXML
    private JFXDatePicker profileDatePicker;   
    @FXML
    private JFXPasswordField profilePassword, profilePassRepeat;
    @FXML
    private JFXButton profileUpdate, profileLogout;
    
    // Manejo de eventos  
    @FXML
    void openLoginView(ActionEvent event) {
    	// Ocultamos el elemento que hubiese en el frente
    	Node oldFront = stackpane.getChildren().get(stackpane.getChildren().size() - 1);
    	oldFront.setDisable(true);
    	oldFront.setVisible(false);
    	
    	// Abrimos una ventana u otra en funci�n del estado
    	if (!isProfileOpen) {
        	loginView.setDisable(false);
        	loginView.setVisible(true);
        	loginView.toFront();
        	hideLoginLabels();
    	} else {
    		profileView.setVisible(true);
    		profileView.setDisable(false);
    		profileView.toFront();
    	}
    }

    @FXML
    void openExplorarView(ActionEvent event) {
    	// Ocultamos el elemento que hubiese en el frente
    	Node oldFront = stackpane.getChildren().get(stackpane.getChildren().size() - 1);
    	oldFront.setDisable(true);
    	oldFront.setVisible(false);
    	//test
    	testwindow.setDisable(false);
    	testwindow.setVisible(true);
    	testwindow.toFront();
    }

    @FXML
    void openMislistasView(ActionEvent event) {
    	//TODO
    }

    @FXML
    void openRecientesView(ActionEvent event) {
    	//TODO
    }

    @FXML
    void openNuevalistaView(ActionEvent event) {
    	//TODO
    }

    @FXML
    void openPremiumView(ActionEvent event) {
    	//TODO
    }
    
    @FXML
    void loginEnter(ActionEvent event) {
    	// Comprobamos que ambos campos hayan sido introducidos
    	if (loginNick.getText().equals("") || loginPassword.getText().equals("")) {
    		// Si no, mostramos la etiqueta de "*Campo obligatorio" correspondiente
    		if (loginNick.getText().equals("")) {
    			loginLabelNick.setVisible(true);
    		}
    		
    		if (loginPassword.getText().equals("")) {
    			loginLabelPassword.setVisible(true);
    		}
    	} else {
    		// Hay datos introducidos, intentamos identificarnos.
    		if (controller.verificarUsuario(loginNick.getText(), loginPassword.getText())) {
    			// Login v�lido
    			openProfileView();
    		} else {
    			// Login inv�lido
    			showDialog("Login inv�lido", "El nombre y/o la contrase�a son incorrectas");
    		}
    	}
    }
    	
    @FXML
    void openRegisterView(MouseEvent event) {
    	// Ocultamos el elemento que hubiese en el frente
    	Node oldFront = stackpane.getChildren().get(stackpane.getChildren().size() - 1);
    	oldFront.setDisable(true);
    	oldFront.setVisible(false);
    	// Traemos la ventana de registro al frente
    	registerView.setDisable(false);
    	registerView.setVisible(true);
    	registerView.toFront(); 	
    	// Ocultamos todas las etiquetas de "*Campo obligatorio" del registro
    	hideRegisterLabels();
    }

    @FXML
    void registerCancel(ActionEvent event) {
    	// Ocultamos el elemento que hubiese en el frente
    	Node oldFront = stackpane.getChildren().get(stackpane.getChildren().size() - 1);
    	oldFront.setDisable(true);
    	oldFront.setVisible(false);
    	// Traemos la ventana de login al frente
    	loginView.setDisable(false);
    	loginView.setVisible(true);
    	loginView.toFront();
    }
    

    @FXML
    void registerUser(ActionEvent event) {
    	// Comprobamos que ninguno de los campos obligatorios est� vac�o.
    	// Si alguno lo est�, activamos su etiqueta de "*Campo obligatorio"
    	boolean valid = true;
    	if (registerNick.getText().equals("")) {
    		registerLabelNick.setVisible(true);
    		valid = false;
    	}
    	if (registerPassword.getText().equals("")) {
    		registerLabelPassword.setVisible(true);
    		valid = false;
    	}
    	if (registerPasswordRepeat.getText().equals("")) {
    		registerLabelPassRepeat.setVisible(true);
    		valid = false;
    	}
    	if (registerName.getText().equals("")) {
    		registerLabelName.setVisible(true);
    		valid = false;
    	}
    	if (registerDatePicker.getValue() == null) {
    		registerLabelDate.setVisible(true);
    		valid = false;
    	}
    	// Comprobamos que ambas contrase�as sean la misma
    	if (valid && ! registerPassword.getText().equals(registerPasswordRepeat.getText())) {
    		valid = false;	
    		showDialog("Registro inv�lido", "Las contrase�as introducidas no coinciden");
    		
    	}
    	// Si todo ha ido bien, intentamos registrar al usuario
    	if (valid) {
			if (controller.registrarUsuario(registerNick.getText(), registerPassword.getText(), registerName.getText(),
					registerSurname.getText(), registerDatePicker.getValue(), registerEmail.getText())) {
    			// Registro v�lido
    			openProfileView();
    		} else {
    			// Registro inv�lido (shouldn't happen)
    			showDialog("Registro inv�lido", "No se ha podido registrar al usuario");
    		}
    	}
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
    	((Stage)((JFXButton)event.getSource()).getScene().getWindow()).setIconified(true);  	
    }

    @FXML
	void maximizeWindow(ActionEvent event) {
		Stage stg = ((Stage) ((JFXButton) event.getSource()).getScene().getWindow());
		// Si la ventana estaba maximizada, restauramos su tama�o anterior
		if (stg.isMaximized()) {
			stg.setWidth(oldWidth);
			stg.setHeight(oldHeight);
			stg.setMaximized(false);
		} else {
			// Sino, la maximizamos y almacenamos el tama�o anterior
			oldWidth = stg.getWidth();
			oldHeight = stg.getHeight();
			stg.setMaximized(true);
		}
	}

    @FXML
    void closeWindow(ActionEvent event) {
    	((Stage)((JFXButton)event.getSource()).getScene().getWindow()).close();
    }
    
    @FXML
    void saveWindowPosition(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    
    @FXML
    void dragWindow(MouseEvent event) {
    	//System.out.println(event.getSource().toString());
    	
    	Region rg = (Region) event.getSource();
    	rg.getScene();
    	Stage stg = (Stage) rg.getScene().getWindow();
        stg.setX(event.getScreenX() - xOffset);
        stg.setY(event.getScreenY() - yOffset);
    }
    
	void openProfileView() {
		Usuario usuarioActual = controller.getUsuarioActual();
		isProfileOpen = true;
		// Escondemos el elemento anterior
		Node oldFront = stackpane.getChildren().get(stackpane.getChildren().size() - 1);
		oldFront.setDisable(true);
		oldFront.setVisible(false);

		// Hacemos la vista visible
		profileView.setVisible(true);
		profileView.setDisable(false);
		profileView.toFront();

		// Actualizamos elementos
		login.setText("Mi perfil");
		profileEmail.setPromptText(usuarioActual.getEmail());
		profileNick.setText(usuarioActual.getLogin());
		profileDatePicker.setPromptText(usuarioActual.getFechaNac().toString());
		profileTitle.setText("Bienvenido, " + usuarioActual.getNombre());
	}
      
    @FXML
    void profileLogout(ActionEvent event) {
    	//controller.salirCuenta();
    	isProfileOpen = false;
    	login.setText("Log in");
    	openLoginView(null);
    }

    @FXML
    void profileUpdate(ActionEvent event) {
    	//TODO
    }
    
    // Funcionalidad auxiliar
    
    // Ocultar todas las labels de "*Este campo es obligatorio" de la vista del login
    void hideLoginLabels() {
    	loginLabelNick.setVisible(false);
    	loginLabelPassword.setVisible(false);
    }
    
    // Ocultar todas las labels de "*Este campo es obligatorio" de la vista de registro
    void hideRegisterLabels() {
    	registerLabelNick.setVisible(false);
    	registerLabelPassword.setVisible(false);
    	registerLabelPassRepeat.setVisible(false);
    	registerLabelName.setVisible(false);
    	registerLabelDate.setVisible(false);
    	
    }
    
    // Generar un JFXDialog sobre la aplicaci�n
    void showDialog(String title, String content) {    
        JFXDialogLayout dialogContent = new JFXDialogLayout(); 
        dialogContent.setHeading(new Text(title));       
        dialogContent.setBody(new Text(content));
        dialogContent.setStyle("-fx-font: 14 system;");
        
        JFXButton close = new JFXButton("Cerrar");          
        close.setStyle("-fx-background-color: #f6444f; -fx-text-fill: #FFFFFF; -fx-font: 14 system;");
        close.setPrefSize(100, 25);
        close.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        
        dialogContent.setActions(close);
        
        JFXDialog dialog = new JFXDialog(rootStackPane, dialogContent, JFXDialog.DialogTransition.BOTTOM);
        
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        dialog.show();
    }
}