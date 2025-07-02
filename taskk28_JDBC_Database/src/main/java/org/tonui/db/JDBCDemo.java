package org.tonui.db;


import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // 2. Set PostgreSQL Docker DB connection details
            String url = "jdbc:postgresql://localhost:5432/online_exam";
            String user = "admin";
            String password = "admin";

            // 3. Attempt to connect
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✔️  Connection to 'online_exam' database successful!");

            // 4. Prepare and execute SQL query
            String sql = "SELECT * FROM subjects";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // 5. Print the subjects
            System.out.println("Subjects table:");
            while (rs.next()) {
                int id = rs.getInt("subject_id");
                String code = rs.getString("subject_code");
                String name = rs.getString("subject_name");

                System.out.println("[" + id + "] " + code + " - " + name);
            }

        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        } finally {
            // 6. Always close resources
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
}
