/**
 * Sample Skeleton for 'Bar.fxml' Controller Class
 */

package it.polito.tdp.bar;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Event;
import it.polito.tdp.bar.model.GruppoClienti;
import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {
	
	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doSimula(ActionEvent event) {
    	model.cleanup();
    	
    	Random rn = new Random(42);
    	long lastTimeOfArrival = 0;
    	
    	// Genero 2000 eventi.
		for (int t = 0; t < 2000; ++t) {
			
			long timeOfArrival = lastTimeOfArrival + 1 + rn.nextInt(9);
			long duration = (long) (60 + Math.random() * 60);
			float tolerance = rn.nextFloat();
			int numberOfPeople =  1 + rn.nextInt(9);
			
			// Genro un nuovo gruppo di clienti
			GruppoClienti customerGroup = new GruppoClienti(timeOfArrival, duration, tolerance, numberOfPeople);
			
			// Creo un nuovo evento e lo inserisco nella coda.
			Event e = new Event(timeOfArrival, Event.eventTypeEnum.ARRIVO_GRUPPO_CLIENTI, customerGroup);
			model.addEvent(e);
		}
		
		// Avvio la simulazion
		model.simulate();
		
		// Ottengo il risultato
		txtResult.appendText(model.getStats().toString());
		
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model = model;
		
		// Aggiungo i tavoli al simulatore (model)
    	
		model.addTable(10);
    	model.addTable(10);

    	model.addTable(8);
    	model.addTable(8);
    	model.addTable(8);
    	model.addTable(8);

    	model.addTable(6);
    	model.addTable(6);
    	model.addTable(6);
    	model.addTable(6);

    	model.addTable(4);
    	model.addTable(4);
    	model.addTable(4);
    	model.addTable(4);
    	model.addTable(4);

	}

	public Model getModel() {
		return model;
	}
}
