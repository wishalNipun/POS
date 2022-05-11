package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.ItemDAO;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString(1);
            String description = rst.getString(2);
            BigDecimal price = rst.getBigDecimal(4);
            int qtyOnHand = rst.getInt(3);
            allItems.add(new ItemDTO(code, description, price, qtyOnHand));
        }
        return allItems;
    }
    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {
      SQLUtil.executeUpdate("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public Boolean insert(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

    return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());

    }
    @Override
    public Boolean Update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getCode());
    }
    @Override
    public Boolean exist(String code) throws SQLException, ClassNotFoundException {
      ResultSet rst= SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?",code);
      return rst.next();
    }
    @Override
    public  String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?", code);
        if (rst.next()) {
            return new ItemDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(4), rst.getInt(3));
        }
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}