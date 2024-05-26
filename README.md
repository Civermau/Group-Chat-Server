# Java Simple Chat Server

This program creates a simple chat server that allows multiple clients to connect and send messages to each other. The server listens on port 7777 by default.

## Requirements
User __must__ have JDK 22 to run this program 

Can get it from here:
> https://www.oracle.com/jp/java/technologies/downloads/

## Features:

- Supports multiple clients
- Displays server information (IP address and port) upon startup
- Displays client connection and disconnection messages
- Displays client usernames and messages in color for better readability
- Displays date and time on the console for better log information

## Explanation of the Code

- Program.java: This class contains the main function of the server. It creates a ServerSocket object on port 7777, initializes a list to store connected clients (connectedClients), and starts a ClientListener thread to listen for incoming connections.
- Client.java: This class represents a connected client. It stores the client's socket, username, and a buffer for receiving messages. It also has a run method that continuously reads messages from the client and broadcasts them to the server console.
- ClientListener.java: This class is a thread responsible for accepting new client connections. It waits for a new connection on the server socket, reads the username sent by the client, creates a new Client object, adds it to the connectedClients list, and starts a new thread for that client.
UI.java: This class provides ANSI color codes for formatting server output messages in the console.

![image](https://github.com/Civermau/GroupChatServer/assets/66493296/2cadeb69-f77f-4748-9c85-68673a2c7777)

