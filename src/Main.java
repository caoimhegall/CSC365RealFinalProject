import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String credFile = args[0];
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(credFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.loadFromXML(fis);
            DaoManager daoManager = new DaoManager(new ConnectionFactory(
                    prop.getProperty("driver"),
                    prop.getProperty("url"),
                    prop.getProperty("user"),
                    prop.getProperty("pass")
            ));
            // Printing out all books currently in the database
            Dao<Book> bookDao = daoManager.getBookDao();
            Set<Book> books = bookDao.getAll();
            for (Book book : books) {
                System.out.println(book);
            }

            // Printing out all students currently in the database
            Dao<Student> studentDao = daoManager.getStudentDao();
            Set<Student> students = studentDao.getAll();
            for (Student student : students) {
                System.out.print(student);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}