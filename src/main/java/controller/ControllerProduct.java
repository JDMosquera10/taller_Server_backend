/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Optional;
import productModel.Product;

/**
 *
 * @author DESARROLLADOR_J.ADVA
 */
public class ControllerProduct {

    ArrayList<Product> listProducts = new ArrayList<>();

    public ControllerProduct() {

    }

    public String actionscontroller(String action, String[] parts) {
        switch (action) {
            case "add":
                return addPoduct(parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
            case "update":
                return updatePoduct(Long.parseLong(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4]), Integer.parseInt(parts[5]));
            case "find":
                return getPoductByName(parts[1]);
            case "remove":
                return removeProductById(Integer.parseInt(parts[1]));
            default:
                return "Acción no válida.";
        }
    }

    public String addPoduct(String name, String description, double price, int available) {
        try {
            long code = generarCodigo(7);
            Product newProduct = new Product(code, name, description, price, available);
            listProducts.add(newProduct);
            return "Producto agregado exitosamente";
        } catch (Exception e) {
            return "Ocurrio un error al intentar agregar un producto";
        }
    }

    public long generarCodigo(int longitud) {
        long minimo = (long) Math.pow(10, longitud - 1);
        long maximo = (long) Math.pow(10, longitud) - 1;
        return minimo + (int) (Math.random() * (maximo - minimo + 1));
    }

    public String updatePoduct(long id, String name, String description, double price, int available) {
        try {
            Optional<String> response = listProducts.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .map(pr -> {
                        pr.setName(name);
                        pr.setDescription(description);
                        pr.setPrice(price);
                        pr.setAvailable(available);
                        return "Producto actualizado correctamente.";
                    });

            String mensaje = response.orElse("Producto no encontrado.");

            return mensaje;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getPoductByName(String name) {
        try {
            Optional<Product> productFinded = listProducts.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst();

            if (productFinded.isPresent()) {
                return "Producto encontrado : " + productFinded.get();
            } else {
                return "Producto no encontrado.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String removeProductById(int id) {
        try {
            boolean isRemoved = listProducts.removeIf(product -> product.getId() == id);
            if (isRemoved) {
                return "Producto eliminado correctamente.";
            } else {
                return "Producto no encontrado.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
