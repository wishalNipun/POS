package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    public void delete(String code) throws SQLException, ClassNotFoundException ;
    public boolean insertItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;
    public Boolean Update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;
    public Boolean existItems(String code) throws SQLException, ClassNotFoundException;
    public  String generateNewId() throws SQLException, ClassNotFoundException;
}
