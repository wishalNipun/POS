package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBo {
    private final CustomerDAO CRUDDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = CRUDDAO.getAll();
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return allCustomers;
    }
    @Override
    public boolean insertCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDDAO.insert(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDDAO.Update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
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
