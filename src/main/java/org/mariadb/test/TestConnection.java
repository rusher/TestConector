package org.mariadb.test;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import java.sql.*;

public class TestConnection {
    private static Logger logger = LoggerFactory.getLogger(TestConnection.class);

    public static void main(String[] args) {
        logger.debug("test debug");
        if (args.length != 1) {
            System.out.println("parameter error. Must have 1 url String parameter. like \"jdbc:mariadb://192.168.0.5:3306/test?user=test\"");
            System.exit(1);
        }

        String url = args[0] ;
        Connection con = null;
        try {
            DriverManager.setLoginTimeout(15);

            con = DriverManager.getConnection (url);
            try (Statement stmt = con.createStatement()) {
                stmt.execute("SELECT 1");
            }
            logger.debug("connection established !");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    } // end of main

}

