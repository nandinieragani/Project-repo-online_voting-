package com.java.onlinevoting;

import java.sql.*;

public class UserRegistration {
    private static final String ADMIN_USERNAME = "nandu";
    private static final String ADMIN_PASSWORD = "nandu@123";
 
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
 
    public void registerUser(String username, String password) {
        String sqlInsert = "INSERT INTO users(username, password) VALUES(?, ?)";
        String sqlSelect = "SELECT id FROM users WHERE username = ?";
 
        try (Connection conn = this.connect();
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
             PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
 
            pstmtInsert.setString(1, username);
            pstmtInsert.setString(2, password);
            pstmtInsert.executeUpdate();
 
            pstmtSelect.setString(1, username);
            ResultSet rs = pstmtSelect.executeQuery();
            if (rs.next()) {
                int voterId = rs.getInt("id");
                System.out.println("Registration successful for " + username + ". Your voter ID is: " + voterId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful. Welcome, " + username);
                return true;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
 
    public boolean isAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
 
    public boolean validateVoterId(int voterId) {
        String sql = "SELECT id FROM users WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, voterId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
 