/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package request;

import java.io.Serializable;
import java.util.Optional;
import productModel.Product;

/**
 *
 * @author DESARROLLADOR_J.ADVA
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    String action;
    Product product;
    String nameFilter;

    public Request(String action, String nameFilter) {
        this.action = action;
        this.nameFilter = nameFilter;
    }

    public Request(String action, Product product, String nameFilter) {
        this.action = action;
        this.product = product;
        this.nameFilter = nameFilter;
    }

    public String getAction() {
        return this.action;
    }

    public String getNameFilter() {
        return this.nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
