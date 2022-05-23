package dao.custom;

import dao.SuperDAO;
import dto.CustomDTO;

import entity.CustomEntity;
import entity.Customer;


import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> searchOrderByOrderID(String id)throws SQLException,ClassNotFoundException;
}
