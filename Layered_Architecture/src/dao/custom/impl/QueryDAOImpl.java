package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import entity.CustomEntity;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.oid=OrderDetails.oid where Orders.oid=?;", id);
        ArrayList<CustomEntity> orderRecords = new ArrayList();
        while (rst.next()) {
            orderRecords.add(new CustomEntity(rst.getString(1), LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6)));
        }
        return orderRecords;
    }
}
