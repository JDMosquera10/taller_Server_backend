/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.ControllerProduct;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import productModel.Product;
import request.Request;
import request.Response;

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
            System.out.println("Server listing on port: " + port);
            while (true) {
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                System.out.println("Connected from: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (Exception e) {
            System.out.println("error de la data" + e.getMessage());
        }
    }

}

class ClientHandler implements Runnable {

    private SSLSocket clientSocket;

    public ClientHandler(SSLSocket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            ControllerProduct controller = new ControllerProduct();

            while (true) {
                try {
                    Request request = (Request) inputStream.readObject();
                    System.out.println("Acción recibida: " + request.getAction());

                    Response response = controller.actionscontroller(request);
                    if (request.getAction().equals("view")) {

                        System.out.println("respuetsa de la consulta de datos" + response.getListProduct().size());
                    }
                    outputStream.reset();
                    outputStream.writeObject(response);
                    outputStream.flush();
                } catch (EOFException e) {
                    System.out.println("Cliente desconectado.");
                    break;
                } catch (Exception e) {
                    System.out.println("Error al procesar solicitud: " + e.getMessage());
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Error en la conexión con el cliente: " + e.getMessage());
        }
    }
}
