package DAL;


import BLL.CrudException;

import java.sql.SQLException;

public interface CRUD {
    void create(String description) throws CrudException;
    void readMovie(int id) throws CrudException;
    void update(int id,String description) throws CrudException;
    void delete(int id) throws CrudException;
    void readMovies() throws CrudException;
}
