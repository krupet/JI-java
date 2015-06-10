package ua.com.joinit.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by krupet on 10.06.2015.
 */
public class SQLScriptRunner {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "tests_db";
    private static final String DB_USER = "javadev";
    private static final String PASSWORD = "javadev";
    private static final String FILE_NAME = "/test_create_table.sql";

    public void runSQL() throws IOException {

        Connection conn = null;
        Statement stmt = null;
        InputStream in = null;
        List<String> queriesList = new ArrayList<>();

        try{

            in = this.getClass().getResourceAsStream(FILE_NAME);
            Scanner scanner = new Scanner(in, "utf-8").useDelimiter(";");
            while (scanner.hasNext()) {
                queriesList.add(scanner.next() + ";");
            }
            in.close();

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);

            for (String query : queriesList) {
                stmt.addBatch(query);
            }

            int[] count = stmt.executeBatch();
            conn.commit();

        } catch(SQLException se){
            se.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if (in != null) in.close();
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        SQLScriptRunner runner = new SQLScriptRunner();
        runner.runSQL();
    }
}
