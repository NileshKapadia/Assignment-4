/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nilesh;

import DatabaseConnection.DatabaseConnection;
import com.product.product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * REST Web Service
 *
 * @author NILESH
 */
@Path("generic")
public class GenericResourse {
  
    DatabaseConnection connections = new DatabaseConnection();
    product pro = new product();
    ArrayList<product> products = new ArrayList<>();
    Connection conn;
    @Context
    private UriInfo context;


   

    /**
     * Creates a new instance of GenericResourse
     */
    public GenericResourse() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
         conn = connections.getConnection();
    }

    /**
     * Retrieves representation of an instance of com.nilesh.GenericResourse
     * @return an instance of java.lang.String
     */
    @GET
   @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
     public ArrayList<product> getXml() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        //TODO return proper representation object
       
         Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery("select * from product");

        ResultSetMetaData rsmd = rs.getMetaData();
        int col = rsmd.getColumnCount();
        while (rs.next()) {

            product pro = new product(rs.getInt("productid"), rs.getString("name"), rs.getString("description"), rs.getInt("quantity"));
            products.add(pro);
        }

        return products;
    }
       @GET
    @Path("/products/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<product> oneProduct(@PathParam("id") int id) throws SQLException
    {
        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery("select * from product where productid="+id);

      
        while (rs.next()) {

            product pro = new product(rs.getInt("productid"), rs.getString("name"), rs.getString("description"), rs.getInt("quantity"));
            products.add(pro);
        }

        return products;
    }
    
     @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   
     public Response createProduct(String str) throws SQLException, ParseException, org.json.simple.parser.ParseException
    {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(str);
  
 Object id = json.get("id");
 String productid=id.toString();
 int Id=Integer.parseInt(productid);
 Object name = json.get("name");
 String productname=name.toString();
         Object description = json.get("description");
 String productdescription=description.toString();
         Object quantity = json.get("quantity");
 String productquantity=quantity.toString();
 int Qnt=Integer.parseInt(productquantity);
  Statement smt = conn.createStatement();
  smt.executeUpdate("INSERT INTO product VALUES ('"+Id+"','"+productname+"','"+productdescription+"','"+Qnt+"' )");
  return Response.status(Response.Status.CREATED).build();
    }
    
    

    /**
     * PUT method for updating or creating an instance of GenericResourse
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   
    public void putProduct(@QueryParam("id") int id,@QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("quantity") int quantity) throws SQLException
    {
       Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery("update product set productid ="+id+", name ="+name+", description ="+description+", quantity ="+quantity+" where productid ="+id);
    }
}
