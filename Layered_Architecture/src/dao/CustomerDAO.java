package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList getAllCustomers() throws SQLException, ClassNotFoundException;
    Boolean insertCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    Boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    Boolean Update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
}
