package bo;

import dao.custom.ItemDAO;
import dao.custom.impl.ItemDAOImpl;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl {
    private final ItemDAO itemDAO = new ItemDAOImpl();


    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    public boolean insertItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.insert(dto);
    }

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.Update(dto);
    }

    public boolean itemExist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    public void deleteItem(String code) throws SQLException, ClassNotFoundException {
        itemDAO.delete(code);
    }

    public String generateItemNewId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }
}
