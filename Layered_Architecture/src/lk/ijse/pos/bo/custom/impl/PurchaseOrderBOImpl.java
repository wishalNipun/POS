package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PurchaseOrderBo;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.dao.custom.impl.OrderDetailDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBo {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

   //private final CustomerDAO customerDAO = new CustomerDAOImpl();
   /* private final ItemDAO itemDAO = new ItemDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderDetailDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    private final QueryDAO queryDAO = new QueryDAOImpl();*/

    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnection.getDbConnection().getConnection();

            /*if order id already exist*/
            if (orderDAO.exist(dto.getOrderId())) {

            }

            connection.setAutoCommit(false);

            Boolean save = orderDAO.insert(new Order(dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId()));
            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detailDTO : dto.getOrderDetails()) {

                Boolean save1 = orderDetailsDAO.insert(new OrderDetail(detailDTO.getOrderId(),detailDTO.getItemCode(),detailDTO.getQty(),detailDTO.getUnitPrice()));

                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

          //Search & Update Item

                ItemDTO item = searchItem(detailDTO.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());

                Boolean update = itemDAO.Update(new Item(item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice()));
                if (!update) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;



    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer ent = customerDAO.search(id);
        return new CustomerDTO(ent.getId(), ent.getName(), ent.getAddress());

    }
    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item ent = itemDAO.search(code);
        return new ItemDTO(ent.getCode(), ent.getDescription(), ent.getUnitPrice(), ent.getQtyOnHand());
     
    }
    @Override
    public boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }
    @Override
    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }
    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
         ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer ent:all
             ) {
            allCustomers.add(new CustomerDTO(ent.getId(), ent.getName(), ent.getAddress()));
        }
        return allCustomers;
    }
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item ent : all) {
            allItems.add(new ItemDTO(ent.getCode(), ent.getDescription(), ent.getUnitPrice(), ent.getQtyOnHand()));
        }
        return allItems;
    }
}
