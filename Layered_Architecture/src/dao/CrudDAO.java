package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> {
    ArrayList getAll() throws SQLException, ClassNotFoundException;

    Boolean insert(T dto) throws SQLException, ClassNotFoundException;

    Boolean exist(ID id) throws SQLException, ClassNotFoundException;

    void delete(ID id) throws SQLException, ClassNotFoundException;

    Boolean Update(T dto) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    T search(ID id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
