package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString(1);
            String description = rst.getString(2);
            BigDecimal price = rst.getBigDecimal(3);
            int qtyOnHand = rst.getInt(4);
            allItems.add(new Item(code, description, qtyOnHand, price));
        }
        return allItems;
    }
    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {
      SQLUtil.executeUpdate("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public Boolean insert(Item entity) throws SQLException, ClassNotFoundException {

    return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }
    @Override
    public Boolean Update(Item entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
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
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?", code);
        if (rst.next()) {
            return new Item(rst.getString(1), rst.getString(2), rst.getInt(3),rst.getBigDecimal(4));
        }
        return null;
    }


}
