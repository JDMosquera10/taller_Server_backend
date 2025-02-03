/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.ControllerProduct;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import productModel.Product;

/**
 *
 * @author DESARROLLADOR_J.ADVA
 */
public class ServerProduct {

    private int port;

    public ServerProduct(int port) {
        this.port = port;
    }

    public void initserverActions() {
        try {
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(port);
            ControllerProduct controller = new ControllerProduct();
            System.out.println("Server listing on port: " + port);
            while (true) {
                //Accept connection 
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected from: " + clientSocket.getInetAddress());
                //Defining input and output
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                String clientMessage = inputStream.readUTF();
                clientMessage = clientMessage.trim();
                String[] parts = clientMessage.split(":");
                String response = controller.actionscontroller(parts[0], parts);
                System.out.println("Response: " + response);
                outputStream.writeUTF(response);
                clientSocket.close();
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
