package dao;

import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements CrudDAO<OrderDTO,String>{
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Boolean insert(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Boolean exist(String s) throws SQLException, ClassNotFoundException {
        return null;
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
}
