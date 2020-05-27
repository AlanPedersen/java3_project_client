/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Name: Alan Pedersen
 * ID: P225139
 * Date: 27/05/2020
 * Java III
 * Project AT3 Question 3
 * 
 * inter process communication
 * client application 
 * main module
 * 
 */

public class MessageClient extends Application {


    // testing flag
    // set to true to see progress messages from within the code
    public static boolean testingFlag = false;

    // disconnect string
    public static final String DISCONNECT = "closeConnection";
    // login attempt outcome
    public static final String LOGIN_PASS = "pass";
    public static final String LOGIN_FAIL = "fail";
    // message listen thread
    private static ClientListener messageReceiver;
    
    
    // socket object to message server
    public static Socket clientSocket;
    // input and output streams
    public static DataInputStream readStream;
    public static DataOutputStream writeStream;
    // connection details
    public static final String HOST_NAME = "localhost";
    public static final int HOST_PORT = 8081;
    
    // accessable stage object
    Stage mainStage;

    public static void main(String[] args) {
        // start the GUI application
        Application.launch(args);

    }

    
    @Override
    // setup and run the GUI
    public void start(Stage primaryStage) {
        // create the help form
        Stage helpStage = HelpForm.MakeHelpForm();

        // set the accessable stage
        mainStage = primaryStage;
        
        // application title
        primaryStage.setTitle("IPC client");

        // create a grid container to hold the interface elements
        GridPane layoutGrid = new GridPane();
        
        // build the GUI
        MainForm.buildGui( layoutGrid);
        
        // set the scene
        Scene scene = new Scene( layoutGrid);
   
        // trap the 'F1' key show help form
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1) {
                if( testingFlag)
                    System.out.println("help!");
                // show the help form
                helpStage.show();
            }
        });

        // display the form
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    // log a user into the system
    public static void LogInToServer()
    {
        // declare local variables
        String userName;
        String userPw;
        String passwordValid;
        String message;
        
        // try to read values from the form
        try {
            // read the user name
            userName = MainForm.GetUserName();
            userPw = MainForm.GetUserPw();
            
            // attempt to connect to the host
            // open the socket
            clientSocket = new Socket(HOST_NAME,HOST_PORT);
            // open the data streams
            readStream = new DataInputStream(clientSocket.getInputStream());
            writeStream = new DataOutputStream(clientSocket.getOutputStream());
            
            // write the login details
            writeStream.writeUTF(userName);
            writeStream.writeUTF(userPw);
            
            // read reply from server
            passwordValid = readStream.readUTF();
            // read the login message
            message = readStream.readUTF();
            
            // echo the user friendly message
            MainForm.ShowReceivedMessage(message);
            
            // test for a sucessfull login
            if( passwordValid.equals(LOGIN_PASS))
            {
                // run a listener thread
                messageReceiver = new ClientListener(clientSocket, readStream, writeStream);
                // start the thread running
                messageReceiver.start();
                
                // set login status
                MainForm.SetSocketStatusConnected();
                
                // reset the user interface
                MainForm.SetUserLogin();
                
                // clear the user name and pw fields
                MainForm.ClearUserFields();
                
            }
            else
            {
                // failed login
                // close resources
                writeStream.close();
                readStream.close();
                clientSocket.close();
                
                // reset the user interface
                MainForm.SetUserLogout();
                
            }
            
        }
        catch ( Exception ex)
        {
            MainForm.ShowErrorMessage(ex.getMessage());
            
        }
        
    }
    
    // log a user out of the system
    public static void LogOutOfServer()
    {
        // try to logout of the server
        try {
            // send a disconnect message
            writeStream.writeUTF( DISCONNECT);
            
            // close the streams
            writeStream.close();
            readStream.close();
            clientSocket.close();

            // reset the user interface
            MainForm.SetUserLogout();

            // set login status
            MainForm.SetSocketStatusDisconnect();
            
        }
        catch ( Exception ex)
        {
            MainForm.ShowErrorMessage(ex.getMessage());
            
        }
        
    }

    // send a message to the server
    public static void SendMessageToServer()
    {
        // declare local variables
        String message;

        // try to read the message to send
        try
        {
            // read the message
            message = MainForm.GetSendMessage();
            // send the message
            writeStream.writeUTF(message);
            // clear the message box
            MainForm.ClearMessageSent();
            
        }
        catch ( Exception ex)
        {
            MainForm.ShowErrorMessage(ex.getMessage());
            
        }
    }
    
}
