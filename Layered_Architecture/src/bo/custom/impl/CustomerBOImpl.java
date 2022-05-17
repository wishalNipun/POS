package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBo {
    private final CustomerDAO CRUDDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return CRUDDAO.getAll();
    }
    @Override
    public boolean insertCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDDAO.insert(dto);
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDDAO.Update(dto);
    }
    @Override
    public boolean customerExist(String id) throws SQLException, ClassNotFoundException {
        return CRUDDAO.exist(id);
    }
    @Override
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        CRUDDAO.delete(id);
    }
    @Override
    public String generateCustomerNewId() throws SQLException, ClassNotFoundException {
        return CRUDDAO.generateNewId();
    }
}
