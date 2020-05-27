/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * Name: Alan Pedersen
 * ID: P225139
 * Date: 27/05/2020
 * Java III
 * Project AT3 Question 3
 * 
 * inter process communication
 * client application 
 * code to build and manage the main form
 * 
 */

public class MainForm {
    // list and list view objects

    // list of client received messages
    // list view object to display the list of received messages
    private static final ListView<String> messageListView = 
            new ListView<String>();
    // expose the message list object
    private static ObservableList<String> messageList = 
            FXCollections.observableArrayList();

    // text field elements
    // pipe server name
    private static TextField tfPipeName = new TextField();
    // user name input box
    private static TextField tfUserName = new TextField();
    // password input box
    private static TextField tfPassword = new PasswordField();
    // message input area
    private static TextArea tfMessage = new TextArea();
    // message received label
    private static Text messLabel = new Text("OK");
    
    // buttons
    // login button
    private static Button btnLogIn = new Button("login");
    // log out button
    private static Button btnLogOut = new Button("log out");
    // clear received messages
    private static Button btnClearReceivedMessages = new Button("clear");
    // clear send messages box
    private static Button btnClearSendMessages = new Button("clear");
    // send a message
    private static Button btnSendMessage = new Button("send");
    // close the application
    private static Button btnClose = new Button("close");

    // build the GUI
    public static void buildGui(GridPane layoutGrid)
    {
        // initialise the grid container for the interface elements
        layoutGrid.setAlignment( Pos.CENTER);
        layoutGrid.setHgap(10);
        layoutGrid.setVgap(10);
        layoutGrid.setPadding(new Insets( 25, 25, 25, 25));
        
        // add the server controls
        addServerControlsToGui(layoutGrid);
        
        // add the message controls
        addMessageReceivedToGui(layoutGrid);
        
        // add the close button
        addCloseToGui(layoutGrid);
        
        // add the event listeners to the form
        addEventListeners();
        
    }
    
    // add the server controls to the GUI
    private static void addServerControlsToGui(GridPane layoutGrid)
    {
        // create the server controls layour grid
        GridPane pipePane = new GridPane();
        // set the spacing between the grid elements and the border padding
        pipePane.setHgap(10);
        pipePane.setVgap(10);
        pipePane.setPadding(new Insets( 10, 5, 5, 5));
        
        // set the grid border
        // create the border stroke
        BorderStroke pipeBS = new BorderStroke( Color.BLACK, 
                BorderStrokeStyle.SOLID, 
                new CornerRadii(5), new BorderWidths(1));
        pipePane.setBorder( new Border( pipeBS));

        // pipe pane label
        Text pipeHeader = new Text("user access");
        // pipe field label
        Text pipeLabel = new Text("socket status:");
        // add text to the grid
        pipePane.add( pipeHeader, 0, 0);
        pipePane.add( pipeLabel, 0, 1);
        
        // set the text field width
        tfPipeName.setPrefWidth( 200);
        // set the default status message
        tfPipeName.setText("not connected");
        // disable the field
        tfPipeName.setDisable(true);
        
        // add the text field
        pipePane.add( tfPipeName, 1, 1);
        
        // user name label
        Text userName = new Text("name:");
        // user password label
        Text userPassword = new Text("password:");
        
        // add the name label, text field & login button
        pipePane.add( userName, 0, 2);
        pipePane.add( tfUserName, 1, 2);
        pipePane.add( btnLogIn, 2, 2);

        // add the password label, text field & logout button
        pipePane.add( userPassword, 0, 3);
        pipePane.add( tfPassword, 1, 3);
        pipePane.add( btnLogOut, 2, 3);

        // set the button widths
        btnLogIn.setPrefWidth(80);
        btnLogOut.setPrefWidth(80);

        // set the buttons to logged out status
        btnLogIn.setDisable(false);
        btnLogOut.setDisable(true);
        
        // add the server controls to the main grid
        layoutGrid.add( pipePane, 0, 0);
        
    }
    
    // add the message received controls to the GUI
    private static void addMessageReceivedToGui(GridPane layoutGrid)
    {
        // create the message controls grid
        GridPane messPane = new GridPane();
        // set the spacing between the grid elements and the border padding
        messPane.setHgap(10);
        messPane.setVgap(10);
        messPane.setPadding(new Insets( 10, 5, 5, 5));
        
        // set the grid border
        // create the border stroke
        BorderStroke messBS = new BorderStroke( Color.BLACK, 
                BorderStrokeStyle.SOLID, 
                new CornerRadii(5), new BorderWidths(1));
        messPane.setBorder( new Border( messBS));

        // pipe pane label
        Text messHeader = new Text("messages");
        // add text to the grid
        messPane.add( messHeader, 0, 0, 3, 1);

        // message received label
        Text messRecLabel = new Text("messages received:");
        // add text to the grid
        messPane.add( messRecLabel, 0, 1, 2, 1);
        // add the message clear button
        messPane.add( btnClearReceivedMessages, 2, 1);
        
        // tie the message list to the view object
        messageListView.setItems(messageList);
        
        // add the message received list
        messPane.add( messageListView, 0, 2, 3, 1);

        // message received label
        Text messSendLabel = new Text("send box:");
        // add text to the grid
        messPane.add( messSendLabel, 3, 1);

        // add the buttons
        messPane.add(btnSendMessage, 4, 1);
        messPane.add(btnClearSendMessages, 5, 1);
        
        // add the send text area
        messPane.add( tfMessage, 3, 2, 3, 1);

        
        // set the button sizes
        btnClearReceivedMessages.setPrefWidth(80);
        btnSendMessage.setPrefWidth(80);
        btnClearSendMessages.setPrefWidth(80);

        // set the buttons to logged out status
        btnSendMessage.setDisable(true);

        // set the list view width
        messageListView.setMinWidth(300);
        messageListView.setMaxWidth(300);
        
        // set the text area width & height
        tfMessage.setMinWidth(300);
        tfMessage.setMaxWidth(300);
        tfMessage.setMaxHeight(100);

        // right align the message clear button
        GridPane.setHalignment( btnClearReceivedMessages, HPos.RIGHT);

        // top align the message send box
        GridPane.setValignment( tfMessage, VPos.TOP);
        
        // add the message controls to the main grid
        layoutGrid.add( messPane, 0, 1);
        
    }
    
    
    // add the close button and message text field
    private static void addCloseToGui(GridPane layoutGrid)
    {
        // add text to the grid
        layoutGrid.add( messLabel, 0, 2);

        // add the close button
        layoutGrid.add(btnClose, 1, 2);
        
        // set the button size
        btnClose.setPrefWidth(80);
        
        // right align the close button
        GridPane.setHalignment( btnClose, HPos.RIGHT);
        
    }

    
    // set the event listener code
    private static void addEventListeners()
    {
        // add the event listeners for each button

        // log in button
        btnLogIn.setOnAction((ActionEvent event) -> {
            UserLogIn();});

        // log out button
        btnLogOut.setOnAction((ActionEvent event) -> {
            UserLogOut();});

        // clear received messages button
        btnClearReceivedMessages.setOnAction((ActionEvent event) -> {
            ClearMessageReceived();});

        // clear sent message button
        btnClearSendMessages.setOnAction((ActionEvent event) -> {
            ClearMessageSent();});

        // send message button
        btnSendMessage.setOnAction((ActionEvent event) -> {
            SendMessage();});

        // close application button
        btnClose.setOnAction((ActionEvent event) -> {
            CloseApplication();});
        
    }
    
    // user login 
    private static void UserLogIn()
    {
        if( MessageClient.testingFlag)
            System.out.println("user log in");
        
        // call the login method
        MessageClient.LogInToServer();
        
    }
    
    // user logout 
    private static void UserLogOut()
    {
        if( MessageClient.testingFlag)
            System.out.println("user log out");
        
        // call the logout method
        MessageClient.LogOutOfServer();
        
    }
    
    // clear message received list
    public static void ClearMessageReceived()
    {
        if( MessageClient.testingFlag)
            System.out.println("clear message received");
        
        // clear the list of messages
        messageList.clear();
        
    }
    
    // clear message sent box
    public static void ClearMessageSent()
    {
        if( MessageClient.testingFlag)
            System.out.println("clear message sent");
        
        // clear the text area
        tfMessage.clear();
        
    }
    
    // send a message
    private static void SendMessage()
    {
        if( MessageClient.testingFlag)
            System.out.println("send message");
        
        // send the message
        MessageClient.SendMessageToServer();
        
    }
    
    // close the aplication
    private static void CloseApplication()
    {
        if( MessageClient.testingFlag)
            System.out.println("close application");
        
        // close the application
        System.exit(0);
        
    }
    
    // show an error message
    public static void ShowErrorMessage( String message)
    {
        // set the message
        messLabel.setText(message);
        
    }
    
    // read a value from the text fields
    // get the value from the user name field
    public static String GetUserName() throws Exception
    {
        // declare local variables
        String text;
        // read the text field
        text = tfUserName.getText();
        
        if( text.isBlank())
            throw new Exception("please enter a user name");
        else
            return text;
        
    }

    // get the value from the user password field
    public static String GetUserPw() throws Exception
    {
        // declare local variables
        String text;
        // read the text field
        text = tfPassword.getText();
        
        if( text.isBlank())
            throw new Exception("please enter a user password");
        else
            return text;
        
    }
    
    // get the message to send
    public static String GetSendMessage() throws Exception
    {
        // declare local variables
        String text;
        // read the text field
        text = tfMessage.getText();
        
        if( text.isBlank())
            throw new Exception("please enter message to send");
        else
            return text;
        
    }
    
    // clear the user inputs
    public static void ClearUserFields()
    {
        // clear the fields
        tfUserName.clear();
        tfPassword.clear();
        
    }
    
    // interfaces changes for a valid user login
    public static void SetUserLogin()
    {
        // enable the buttons
        btnLogIn.setDisable(true);
        btnLogOut.setDisable(false);
        btnSendMessage.setDisable(false);
        
        // update the socket status
        SetSocketStatusConnected();
        
    }
    
    // interfaces changes for a user log out
    public static void SetUserLogout()
    {
        // enable the buttons
        btnLogIn.setDisable(false);
        btnLogOut.setDisable(true);
        btnSendMessage.setDisable(true);
        
        // udate the socket status
        SetSocketStatusDisconnect();
        
    }

    // show a received message
    public static void ShowReceivedMessage( String message)
    {
        messageList.add(message);
        
    }

    // update socket connection status disconnected
    public static void SetSocketStatusDisconnect()
    {
        tfPipeName.setText("not connected");
    }

    // update socket connection status
    public static void SetSocketStatusConnected()
    {
        tfPipeName.setText("connected on port: " + MessageClient.HOST_PORT);
        
    }
    
}
