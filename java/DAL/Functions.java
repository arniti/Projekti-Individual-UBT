package DAL;

import BLL.ConnectionWithDatabase;
import BLL.CrudException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Functions extends ConnectionWithDatabase implements CRUD {
    private ObservableList<Movies> movies;

    public void order(){
        movies = FXCollections.observableArrayList();
    }
    public ObservableList getMovies(){
        return movies;
    }
    @Override
    public void create(String description) throws CrudException {
        try {
            Connection con = connection();
            if (con == null) {
                throw new CrudException("Nuk eshte mundesuar lidhja me database");
            }
            Statement stm = con.createStatement();
            stm.executeUpdate("insert into movies values(" + "'" + description + "')");
            movies.add(new Movies(Integer.parseInt(stm.getResultSet().getString(1)),stm.getResultSet().getString(2)));
            movies.get(Integer.parseInt(stm.getResultSet().getString(1))).setDescription(description);
            con.close();
            stm.close();
        } catch (Exception e) {
           // throw new CrudException("Gabim create");
        }
    }

    @Override
    public void readMovies() throws CrudException {
        try {
            Connection con = connection();
            if (con == null) {
                throw new CrudException("Nuk eshte mundesuar lidhja me database");
            }
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from movies");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                String id = rs.getString(1);
                int ids = Integer.parseInt(id);
                if(!movies.contains(new Movies(ids,rs.getString(2)))) {
                    movies.add(new Movies(ids,rs.getString(2)));
                }
            }
            con.commit();
            con.close();
            stm.close();
        } catch (Exception e) {
            throw new CrudException("ReadMovies Gabim");
        }
    }

    @Override
    public void readMovie(int id) throws CrudException {
        try {
            Connection con = connection();
            if(con == null) {
                throw new CrudException("Nuk eshte mundesuar lidhja me database");
            }
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from movies where Movie_ID = " + id);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
            con.close();
            stm.close();
            rs.close();
        } catch (Exception sql) {
            throw new CrudException("ReadMovie Gabim");
        }
    }

    @Override
    public void update(int id,String description) throws CrudException {
        try {
            Connection con = connection();
            if (con == null) {
                throw new CrudException("Nuk eshte mundesuar lidhja me database");
            }
            Statement stm = con.createStatement();
            stm.executeUpdate("update movies set [Movie Description] =" + "'"+description+"'"+ " where Movie_ID = " + id);
            for (int i = 0; i < movies.size() ; i++) {
                if(movies.get(i).getID() == id) {
                    movies.get(i).setDescription(description);
                }
            }
            con.commit();
            con.close();
            stm.close();
        } catch (Exception ce) {
            throw new CrudException("update Gabim");
        }
    }

    @Override
    public void delete(int id) throws CrudException{
        try {
            Connection con = connection();
            if (con == null) {
                throw new CrudException("Nuk eshte mundesuar lidhja me database");
            }
            Statement stm = con.createStatement();
            stm.executeUpdate("delete movies where Movie_ID = " + id);
            for (int i = 0; i < movies.size() ; i++) {
                if(movies.get(i).getID() == id) {
                    movies.remove(i);
                }
            }
            con.close();
            stm.close();
        } catch (Exception e) {
            throw new CrudException("Delete Gabim");
        }
    }
}
