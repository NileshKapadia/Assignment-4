/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author NILESH
 */
@Path("generic")
public class product {
    private String productDescription;
    private int quantity;
    

    
    /**
     * Creates a new instance of product
     */
    public product() {
    }

   public product(int id, String name, String description, int quantity){
        this.productId=id;
        this.productName=name;
        this.productDescription=description;
        this.quantity=quantity;
        
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    protected int productId;

    protected String productName;

    /**
     * Retrieves representation of an instance of com.product.product
     * @return an instance of java.lang.String
     */
    
}
