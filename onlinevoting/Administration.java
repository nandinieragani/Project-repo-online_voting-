package com.java.onlinevoting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class Administration {
    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/voting";
        String user = "root";
        String password = "root";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    public void addCandidate(String candidateName, String party) {
        String sql = "INSERT INTO candidates(name, party) VALUES(?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, candidateName);
            pstmt.setString(2, party);
            pstmt.executeUpdate();
            System.out.println("Candidate added successfully: " + candidateName + " (" + party + ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}