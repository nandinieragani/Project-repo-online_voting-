package com.java.onlinevoting;

import java.sql.*;

public class Votecounting {
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

   public void countVotes() {
       String sql = "SELECT candidates.name, COUNT(votes.candidate_id) AS vote_count " +
                    "FROM votes " +
                    "JOIN candidates ON votes.candidate_id = candidates.id " +
                    "GROUP BY votes.candidate_id";
       try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           while (rs.next()) {
               System.out.println(rs.getString("name") + ": " + rs.getInt("vote_count") + " votes");
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
}