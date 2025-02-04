/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.taller_server;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import productModel.Product;
import server.ServerProduct;

/**
 * Clase principal del servicio.
 * @author DESARROLLADOR_J.ADVA
 */
public class Taller_Server {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Properties p = new Properties();
            try {
                p.load((new FileInputStream(new File("properties.properties"))));
            } catch (Exception e) {
                System.out.println("error del sistema  " + e.getMessage());
            }

            String setRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
            String setPassword = p.getProperty("SSL_PASWORD");

            System.setProperty("javax.net.ssl.keyStore", setRoute);
            System.setProperty("javax.net.ssl.keyStorePassword", setPassword);
            System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
            System.setProperty("javax.net.ssl.trustStore", setRoute);
            System.setProperty("javax.net.ssl.trustStorePassword", setPassword);
            System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
            ServerProduct server = new ServerProduct(9090);
            server.initserverActions();
        });
    }

}
