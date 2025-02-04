/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Optional;
import productModel.Product;
import request.Request;
import request.Response;

/**
 *
 * @author DESARROLLADOR_J.ADVA
 */
public class ControllerProduct {

    ArrayList<Product> listProducts = new ArrayList<>();

    public ControllerProduct() {

    }

    /**
     * controlador que orquesta las peticiones del cliente y retorna las
     * respectivas respuestas.
     *
     * @param request cuerpo de la petición.
     */
    public Response actionscontroller(Request request) {
        Response response = new Response();
        Product productRequest = request.getProduct();
        boolean status = false;
        switch (request.getAction()) {
            case "add":
                if (productRequest instanceof Product) {
                    status = addPoduct(productRequest);
                } else {
                    response.setMenssage("El tipo de dato enviado en el request no es correcto.");
                }
                if (status) {
                    response.setStatus(true);
                    response.setMenssage("El producto fue creado correctamente.");
                } else {
                    response.setMenssage("Ocurrio un error interno");
                }
                return response;
            case "update":
                if (productRequest instanceof Product) {
                    status = updatePoduct(productRequest);
                } else {
                    response.setMenssage("El tipo de dato enviado en el request no es correcto.");
                }
                if (status) {
                    response.setStatus(true);
                    response.setMenssage("El producto fue creado correctamente.");
                } else {
                    response.setMenssage("Ocurrio un error interno");
                }

            case "find":
                if (!request.getNameFilter().equals("")) {
                    Product producfinded = getPoductByName(request.getNameFilter());
                    response.setProduct(producfinded);
                    response.setStatus(true);
                    response.setMenssage("Busqueda exitosa.");

                } else {
                    response.setMenssage("Es requerido enviar un nombre para poder buscar el producto.");
                }
                return response;
            case "remove":
                status = removeProductById(Integer.parseInt(request.getNameFilter()));
                if (status) {
                    response.setStatus(true);
                    response.setMenssage("El producto fue eliminado correctamente.");
                } else {
                    response.setMenssage("Ocurrio un error interno.");
                }
                return response;
            case "view":
                System.out.println("antes de agregar" + listProducts.size());

                response.setListProduct(listProducts);
                System.out.println("lista de productos de la respuesta " + response.getListProduct().size());
                response.setStatus(true);
                return response;
            default:
                response.setStatus(true);
                response.setMenssage("Acción no válida.");
                return response;

        }
    }

    /**
     * Función que agrega un producto.
     *
     * @param product producto a agregar.
     * @return boolean
     */
    public boolean addPoduct(Product product) {
        try {
            // long code = generarCodigo(7);
            // Product newProduct = new Product(code, name, description, price, available);
            listProducts.add(product);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Función que edita un producto.
     *
     * @param product producto a agregar.
     * @return boolean
     */
    public boolean updatePoduct(Product product) {
        try {
            boolean response = listProducts.stream()
                    .filter(p -> p.getId() == product.getId())
                    .findFirst()
                    .map(pr -> {
                        pr.setName(product.getName());
                        pr.setDescription(product.getDescription());
                        pr.setPrice(product.getPrice());
                        pr.setAvailable(product.getAvailable());
                        return true;
                    })
                    .orElse(false);
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Función que edita un producto.
     *
     * @param name nombre del producto a buscar.
     * @return Product
     */
    public Product getPoductByName(String name) {
        try {
            return listProducts.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst().orElse(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Función que edita un producto.
     *
     * @param id id del producto a remover.
     * @return boolean
     */
    public boolean removeProductById(int id) {
        try {
            return listProducts.removeIf(product -> product.getId() == id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
