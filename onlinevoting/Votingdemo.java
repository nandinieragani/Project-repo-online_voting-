package com.java.onlinevoting;

import java.util.Scanner;
 
public class Votingdemo {
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserRegistration userRegistration = new UserRegistration();
        Votingmodule voting = new Votingmodule();
        Votecounting voteCounting = new Votecounting();
        Administration administration = new Administration();
 
        while (true) {
            System.out.println("Welcome to the Online Voting System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Cast Vote");
            System.out.println("4. View Results");
            System.out.println("5. Admin Login");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
 
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    userRegistration.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (userRegistration.login(loginUsername, loginPassword)) {
                        System.out.println("Login successful. Welcome, " + loginUsername);
                    }
                    break;
                case 3:
                    System.out.print("Enter your voter ID: ");
                    int voterId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (userRegistration.validateVoterId(voterId)) {
                        System.out.print("Enter your username: ");
                        String voterUsername = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String voterPassword = scanner.nextLine();
                        if (userRegistration.login(voterUsername, voterPassword)) {
                            voting.displayCandidates();
                            System.out.print("Enter candidate ID to vote for: ");
                            int candidateId = scanner.nextInt();
                            voting.castVote(voterId, candidateId);
                        } else {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid voter ID. Please try again.");
                    }
                    break;
                case 4:
                    voteCounting.countVotes();
                    break;
                case 5:
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();
                    if (userRegistration.isAdmin(adminUsername, adminPassword)) {
                        System.out.println("Admin login successful. Welcome, " + adminUsername);
                        System.out.print("Enter candidate name: ");
                        String candidateName = scanner.nextLine();
                        System.out.print("Enter candidate party: ");
                        String candidateParty = scanner.nextLine();
                        administration.addCandidate(candidateName, candidateParty);
                    } else {
                        System.out.println("Invalid admin credentials");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the system");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
 
