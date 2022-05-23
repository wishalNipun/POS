package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            allCustomers.add(new Customer(id, name, address));
        }
        return allCustomers;
    }
    @Override
    public Boolean insert(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getAddress());
    }
    @Override
    public Boolean exist(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet= SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id);
        return resultSet.next();
    }
    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

        SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public Boolean Update(Customer entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());
    }
    @Override
    public  String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?",id);
        if (rst.next()){
            return  new Customer(id + "", rst.getString("name"), rst.getString("address"));
        }
       return null;
    }



    @Override
    public ArrayList<Customer> getAllCustomerUsingByAddress() {
        return null;
    }
}
