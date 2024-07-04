package com.java.onlinevoting;

import java.sql.*;

public class Votingmodule {
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
 
    public void displayCandidates() {
        String sql = "SELECT * FROM candidates";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Candidates:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Party: " + rs.getString("party"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public void castVote(int voterId, int candidateId) {
        String sqlCheckVote = "SELECT * FROM votes WHERE user_id = ?";
        String sqlInsertVote = "INSERT INTO votes(user_id, candidate_id) VALUES(?, ?)";
 
        try (Connection conn = this.connect();
             PreparedStatement pstmtCheckVote = conn.prepareStatement(sqlCheckVote);
             PreparedStatement pstmtInsertVote = conn.prepareStatement(sqlInsertVote)) {
 
            pstmtCheckVote.setInt(1, voterId);
            ResultSet rs = pstmtCheckVote.executeQuery();
 
            if (rs.next()) {
                System.out.println("You have already cast your vote.");
            } else {
                pstmtInsertVote.setInt(1, voterId);
                pstmtInsertVote.setInt(2, candidateId);
                pstmtInsertVote.executeUpdate();
                System.out.println("Vote cast successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
 