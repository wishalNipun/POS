package dao;

import db.DBConnection;
import model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements CrudDAO<OrderDTO,String>{
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Boolean insert(OrderDTO dto) throws SQLException, ClassNotFoundException {

      return   SQLUtil.executeUpdate("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId());
    }

    @Override
    public Boolean exist(String oid) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeQuery("SELECT oid FROM `Orders` WHERE oid=?",oid).next();
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Boolean Update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       ResultSet rst =SQLUtil.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1");


        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }
}
