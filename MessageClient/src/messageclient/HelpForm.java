/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclient;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Name: Alan Pedersen
 * ID: P225139
 * Date: 09/05/2020
 * Java III
 * Project AT3 Question 3
 * 
 * inter process communication
 * client application 
 * code to build and manage the help form
 * 
 */

public class HelpForm {

    public static Stage MakeHelpForm()
    {
        // declare variables
        Stage helpStage = new Stage();
        GridPane layoutGrid = new GridPane();
        layoutGrid.setPadding(new Insets( 25, 25, 25, 25));
        
        // set the title for the help window
        helpStage.setTitle("Client Application Help");
        
        // server help header
        Text serverHelpHeader = new Text("Client Application Help");
        serverHelpHeader.setFont( Font.font("Sans Serif",FontWeight.BOLD,16));
        layoutGrid.add(serverHelpHeader, 0, 0);

        // server start header
        Text serverStartHeader = new Text("\nLogging into the server");
        serverStartHeader.setFont( Font.font("Sans Serif",FontWeight.BOLD,12));
        layoutGrid.add(serverStartHeader, 0, 1);
        
        String serverStart = "to log into the server enter a username and password\n"
                           + "and click the 'login' button.\n\n"
                           + "the 'socket status' label will update to confirm\n"
                           + "the connection and display the port number\n\n"
                           + "a message will be displayed confirming the login\n\n"
                           + "if the login attempt is unsucessfull an error message\n"
                           + "will be displayed.\n\n"
                           + "to log out of the server click the 'log out' button\n";
        Text serverText = new Text(serverStart);
        serverText.setFont( Font.font("Sans Serif",FontWeight.NORMAL,12));
        layoutGrid.add(serverText, 0, 2);

        // server start header
        Text serverMessageHeader = new Text("Message Controls");
        serverMessageHeader.setFont( Font.font("Sans Serif",FontWeight.BOLD,12));
        layoutGrid.add(serverMessageHeader, 0, 3);
        
        String serverMessage = "Messaged are dispayed in the 'messages received' "
                             + "list.\n\n"
                             + "To send a message:\n"
                             + "enter the messge in the 'send box' control\n"
                             + "and click the 'send' button\n"
                             + "the message will be sent to the server";
        Text messageText = new Text(serverMessage);
        messageText.setFont( Font.font("Sans Serif",FontWeight.NORMAL,12));
        layoutGrid.add(messageText, 0, 4);
        
        Scene scene = new Scene( layoutGrid, 400, 500);
        helpStage.setScene(scene);
        
        return helpStage;
        
    }
    
}
