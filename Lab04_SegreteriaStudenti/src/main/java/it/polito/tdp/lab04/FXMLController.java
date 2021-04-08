/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaIscittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCheck;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtRisultato;
    
    @FXML
    void doCheck(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String m = txtMatricola.getText();
    	Integer matricola;
    	if (m.length()==0) {
    		txtRisultato.setText("Devi inserire una matricola!!");
    		txtMatricola.clear();
    		return;
    	}
    	
    	try{
    		matricola = Integer.parseInt(m);
    	}catch (NumberFormatException ne){
    		txtRisultato.setText("La matricola deve essere solo numerica ");
    		txtMatricola.clear();
    		return;
    	}
    	
    	
    	
    	if (!model.esisteMatricola(matricola)) {
    		txtRisultato.setText("Non esiste nessuno studente con la matricola inserita ");
    		return;
    	}
    	
    	txtNome.setText(model.getStudenteByMatricola(matricola).getNome());
    	txtCognome.setText(model.getStudenteByMatricola(matricola).getCognome());
    		

    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String m = txtMatricola.getText();
    	Integer matricola;
    	if (m.length()==0) {
    		txtRisultato.setText("Devi inserire una matricola!!");
    		txtMatricola.clear();
    		return;
    	}
    	
    	try{
    		matricola = Integer.parseInt(m);
    	}catch (NumberFormatException ne){
    		txtRisultato.setText("La matricola deve essere solo numerica ");
    		txtMatricola.clear();
    		return;
    	}
    	
    	if (!model.esisteMatricola(matricola)) {
    		txtRisultato.setText("Non esiste nessuno studente con la matricola inserita ");
    		return;
    	}
    	List <Corso> corsi = model.getCorsiStudente(matricola);
    	
    	if(corsi.size()==0) {
    		txtRisultato.setText("Lo studente non è iscritto a nessun corso");
    		return;
    	}
    	
    	for (Corso c: corsi) {
    		txtRisultato.appendText(c.descriviti()+"\n");
    	}

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	Corso corso = boxCorsi.getValue();
    	
    	if (corso==null) {
    		txtRisultato.setText("Scegli un corso!!");
    		return;
    	}
    	List<Studente> studenti =model.getStudentiIscrittiAlCorso(corso.getCodins());
    	
    	if(studenti.size()==0) {
    		txtRisultato.setText("Non ci sono iscritti al corso!");
    		return;
    	}
    		
    	
    	for (Studente s: studenti) {
    		txtRisultato.appendText(s.toString()+"\n");
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	Corso corso = boxCorsi.getValue();
    	
    	if (corso==null) {
    		txtRisultato.setText("Scegli un corso!!");
    		return;
    	}
    	
    	String m = txtMatricola.getText();
    	Integer matricola;
    	if (m.length()==0) {
    		txtRisultato.setText("Devi inserire una matricola!!");
    		txtMatricola.clear();
    		return;
    	}
    	
    	try{
    		matricola = Integer.parseInt(m);
    	}catch (NumberFormatException ne){
    		txtRisultato.setText("La matricola deve essere solo numerica ");
    		txtMatricola.clear();
    		return;
    	}
    	if (model.studenteIscritto(corso.getCodins(),matricola))
    		txtRisultato.setText("Lo studente è già iscritto a questo corso!!");
    	else {
    		model.iscriviStudente(matricola, corso.getCodins());
    		txtRisultato.setText("Iscrizione avvenuta con successo!!");
    	}
    		
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtRisultato.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	boxCorsi.setValue(null);
    }
    
    public void setModel (Model m) {
    	this.model=m;
    	
    	boxCorsi.getItems().addAll(model.getTuttiICorsi());
    	btnCheck.setStyle("-fx-background-color: green");
    	txtRisultato.setStyle("-fx-font-family: monospace");
    }

    @FXML
    void initialize() {
    	assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscittiCorso != null : "fx:id=\"btnCercaIscittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
