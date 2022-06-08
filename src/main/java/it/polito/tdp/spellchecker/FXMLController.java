package it.polito.tdp.spellchecker;
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdt.spellchecker.model.Dictionary;
import it.polito.tdt.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="btnspell"
    private Button btnspell; // Value injected by FXMLLoader

    @FXML // fx:id="sceltaLingua"
    private ComboBox<String> sceltaLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorrezioni"
    private TextArea txtCorrezioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtTesto"
    private TextArea txtTesto; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private TextField txtTime; // Value injected by FXMLLoader

    @FXML // fx:id="txterrori"
    private Label txterrori; // Value injected by FXMLLoader

	private Dictionary model;

    @FXML
    void clearText(ActionEvent event) {
    	txterrori.setText("text contains 0 errors");
    	txtTime.setText("");
    	txtCorrezioni.setText("");
    	txtTesto.setText("");
    }

    @FXML
    void spellAction(ActionEvent event) {
    	txtCorrezioni.setText("");
    	String k="";
    	String k1[];
    	ArrayList<String> s=new ArrayList<>();
    if(sceltaLingua.getValue().isEmpty()==false) {
    	try {
    		System.out.println(sceltaLingua.getValue());
    		model.loadDictionary(sceltaLingua.getValue());
    		k=txtTesto.getText();
    		k=k.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    		k=k.toLowerCase();
    		k1=k.split(" ");
    		for(int i=0;i<k1.length;i++) {
    			s.add(k1[i]);
    			System.out.println(k1[i]);
    		}
    		List<RichWord> l=new LinkedList<RichWord>();
    		l.addAll(model.spellCheckText(s));
    		txterrori.setText("sono presenti "+l.size()+" errori");
    		for(int i=0;i<l.size();i++) {
    			//System.out.println("si");
    			txtCorrezioni.appendText(l.get(i).getParola()+"\n");
    		}
    		txtTime.setText("tempo impiegato "+(System.currentTimeMillis()/1000000000));
    	}catch(Exception e){
	 		System.out.println("Errore nella lettura del file");
	 		return;
	 	}
    }else {
    	txtCorrezioni.setText("selezionare lingua");
    	return;
    }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnspell != null : "fx:id=\"btnspell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert sceltaLingua != null : "fx:id=\"sceltaLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorrezioni != null : "fx:id=\"txtCorrezioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txterrori != null : "fx:id=\"txterrori\" was not injected: check your FXML file 'Scene.fxml'.";

        String s="Italian";
        String s1="English";
        sceltaLingua.getItems().clear();
        sceltaLingua.getItems().add(s);
        sceltaLingua.getItems().add(s1);
        txtCorrezioni.setDisable(true);
    }
    
    public void setModel(Dictionary model) {
    	this.model=model;
    }
}



