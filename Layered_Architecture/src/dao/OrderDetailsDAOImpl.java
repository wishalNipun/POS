package dao;

import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements CrudDAO<OrderDetailDTO,String>{
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Boolean insert(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {

     return    SQLUtil.executeUpdate("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",dto.getOrderId(),dto.getItemCode(),dto.getUnitPrice(),dto.getQty());
    }

    @Override
    public Boolean exist(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Boolean Update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetailDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
