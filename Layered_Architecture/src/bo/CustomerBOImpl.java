package bo;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomDTO;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl {
    private final CustomerDAO CRUDDAO = new CustomerDAOImpl();

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return CRUDDAO.getAll();
    }
    public boolean insertCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDDAO.insert(dto);
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDDAO.Update(dto);
    }

    public boolean customerExist(String id) throws SQLException, ClassNotFoundException {
        return CRUDDAO.exist(id);
    }

    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        CRUDDAO.delete(id);
    }
    public String generateCustomerNewId() throws SQLException, ClassNotFoundException {
        return CRUDDAO.generateNewId();
    }
}
