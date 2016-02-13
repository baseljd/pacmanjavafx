package com.gluonapplication;

import java.io.File;



import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GluonApplication extends Application {

    @Override
    public void start(Stage stage) {
        
    	//Initialose ScreenDimentions
    	 Position.initScreenDimentions();
    	
    	//Generate Map
    	MapGenerator.generateMap(stage).show();
    
    	//Add CLick Listener
    	MapGenerator.addClickListener();
    	
    	//Create Pacman
    	GamePlay.pacman = new Pacman();
    	
    	//Create Ghost
    	GamePlay.ghost1 = new Ghost();

    	//Redraw Map   	
    	MapGenerator.redrawMap();
    	
    	//Start Timeline
    	MapGenerator.startTimeline();
    	
    	//Play background music
    	//playMusic();
    	
    }
    
    public void playMusic(){
    	
    	String musicFile = "pacman_music.mp3";
    	Media sound = new Media(new File(musicFile).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
    	
    }
    
    
    
}
