package application;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;



import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;

public class Controller implements Initializable{
	@FXML
    private FontAwesomeIcon play;

    @FXML
    private FontAwesomeIcon pause;

    @FXML
    private FontAwesomeIcon arrowRight;

    @FXML
    private FontAwesomeIcon arrowLeft;

    @FXML
    private FontAwesomeIcon referesh;

    @FXML
    private Slider volumeslider;
    
    private File file;
    
    private MediaPlayer mp;
    private Media m;
    
    @FXML
    private MediaView mediaView;

    @FXML
    private FontAwesomeIcon stop;
    
    @FXML
    public void playAction(MouseEvent e)
    {
    	play.setVisible(false);
    	pause.setVisible(true);
    	
    	mp.play();
    	
    	
    	
   
    	
    }
    
    public void lastVideo(MouseEvent e)
    {
    	mp.seek(mp.getTotalDuration());
    	mp.stop();
    }
    public void fastVideo(MouseEvent e)
    {
    	mp.setRate(1.2);
    	
    }
    public void slowVideo(MouseEvent e)
    {
    	mp.setRate(0.9);
    	
    }
    public void reloadVideo(MouseEvent e)
    {
    	mp.seek(mp.getStartTime());
    	mp.play();
    	
    }
    
    
    public void openVideo(ActionEvent e)
    {
    	FileChooser filechooser=new FileChooser();
    	filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Videos File","*.mp4","*.mkv"));
    	file=filechooser.showOpenDialog(null);
    	String path=file.getAbsolutePath();
    	path=path.replace("\\", "/");
    	
    	if(file!=null)
    	{
    		m=new Media(new File(path).toURI().toString());
    		mp=new MediaPlayer(m);
    		mediaView.setMediaPlayer(mp);
    		mp.play();
    		play.setVisible(false);
    		pause.setVisible(true);
    		
    		volumeslider.setValue(mp.getVolume()*100);
    		
    		volumeslider.valueProperty().addListener(new InvalidationListener()
    				{

						@Override
						public void invalidated(Observable arg0) {
							// TODO Auto-generated method stub
							
							mp.setVolume(volumeslider.getValue()/100);
							
							
						}
    					
    				}
    				
    				);
    		
    		
    		DoubleProperty width=mediaView.fitWidthProperty();
    		DoubleProperty height=mediaView.fitHeightProperty();
    		width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
    		height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
    	
    		
    	}
    }
    @FXML
    public void pauseAction(MouseEvent e)
    {
    	play.setVisible(true);
    	pause.setVisible(false);
    	mp.pause();
    }
    
    
    
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		pause.setVisible(false);
		
	}

}



