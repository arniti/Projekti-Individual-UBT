package DAL;
import BLL.ConnectionWithDatabase;
import BLL.ParseMovies;
import BLL.CrudException;
import org.openqa.selenium.NoSuchElementException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddingContent extends ConnectionWithDatabase{
    static ParseMovies parse = new ParseMovies();
    public void getDescriptions() throws CrudException{
        try {
            parse.crawlDescriptions();
            Connection con = connection();
            Statement statement = con.createStatement();
            for(int i = 0;i<parse.getPershkrimet().size();i++) {
                try {
                    System.out.println(parse.getPershkrimet().get(i).length());
                    if(parse.getPershkrimet().get(i).length()> 100) {
                        statement.executeUpdate("insert into movies values('" + parse.getPershkrimet().get(i).substring(0, 99) + "')");
                    } else {
                        statement.executeUpdate("insert into movies values('" + parse.getPershkrimet().get(i) + "')");
                    }
                }catch (NoSuchElementException | SQLException s) {
                    System.out.println("SQL");
                }
            }
        }catch (NoSuchElementException | SQLException | InterruptedException s) {
            s.printStackTrace();
        }
    }
}
