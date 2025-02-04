/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import productModel.Product;

/**
 *
 * @author DESARROLLADOR_J.ADVA
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    ArrayList<Product> listProduct;
    Product product;
    boolean status;
    String menssage;

    public Response() {
    }

    public Response(ArrayList<Product> list, boolean status, String menssage, Product product) {
        this.listProduct = list;
        this.product = product;
        this.status = status;
        this.menssage = menssage;
    }

    public void setListProduct(ArrayList<Product> list) {
        this.listProduct = list;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public ArrayList<Product> getListProduct() {
        return this.listProduct;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getMenssage() {
        return this.menssage;
    }
}
