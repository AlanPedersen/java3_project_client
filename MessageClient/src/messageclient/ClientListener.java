/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Name: Alan Pedersen
 * ID: P225139
 * Date: 27/05/2020
 * Java III
 * Project AT3 Question 3
 * 
 * inter process communication
 * client application 
 * code to listen for and display messages
 * 
 */

public class ClientListener extends Thread {
    // declare socket and stream variables
    protected Socket ServerSocket;
    protected DataInputStream readStream;
    protected DataOutputStream writeStream;
    
    // message returned to user
    private String message;
    // message read from stream
    private String readMessage;

    // constructor code
    // set the socket and data stream values
    public ClientListener(Socket newSocket, 
            DataInputStream newReadStream,
            DataOutputStream newWriteStream)
    {
        // set the socket object
        ServerSocket = newSocket;
        // set the data streams
        readStream = newReadStream;
        writeStream = newWriteStream;
        
    }

    public void run() {

        try {

            // run a loop listening for messages
            while(true)
            {
                // read a message 
                readMessage = readStream.readUTF();

                // test for a close signal
                if( MessageClient.DISCONNECT.equals(readMessage))
                {
                    // show disconnect message
                    MainForm.ShowReceivedMessage("disconnected by server");
                    // exit loop
                    break;

                }

                // display the message
                MainForm.ShowReceivedMessage(readMessage);

            }
            
        } 
        catch (IOException ex) 
        {
            // display the error message
            MainForm.ShowErrorMessage(ex.getMessage());
        
        } 
        finally 
        {
            try 
            {
                readStream.close();
                writeStream.close();

                // close the server socket if required
                if( !ServerSocket.isClosed())
                    ServerSocket.close();

                // reset the user interface
                MainForm.SetUserLogout();
                
            } 
            catch (IOException ex) 
            {
                // ex.printStackTrace();
                MainForm.ShowErrorMessage(ex.getMessage());
                
            }
    
        }
        
    }
    
}
