package com.scrubele;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private static final String database = "artists";
    private static final String user = "scrubele";
    private static final String password = "scrubele";

    private static final String url = "jdbc:mysql://localhost/artists?serverTimezone=UTC&useSSL=false";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            Scanner input = new Scanner(System.in);
            int x = 10000;
            while (x!=0){
                System.out.println("Enter 1-readData(),\n 2 -insertDataArtist()," +
                        "\n 3 - deleteDataArtist(),\n 4 - updateDataArtist(),\n 0 - exit");
             x = input.nextInt();
            switch (x) {
                case 1: {
                    readData();
                    break;
                }
                case 2: {
                    insertDataArtist();
                    break;
                }
                case 3: {
                    deleteDataArtist();
                    break;
                }
                case 4:{
                    updateDataArtist();
                    break;
                }
            }
            }


        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver is not loaded");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            //close connection ,statement and resultset
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            } // ignore
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    private static void readData() throws SQLException {

        rs = statement.executeQuery("SELECT * FROM Artists");

        // PERSON
        System.out.format("%16s", "Table Person\n");
        String[] table = {"ID", "Surname", "Name", "Position", "Id_organization"};
        for (String i : table) {
            System.out.format("%16s", i);
        }
        System.out.println();

        while (rs.next()) {
            int id = rs.getInt(table[0]);
            String surname = rs.getString(table[1]);
            String name = rs.getString(table[2]);
            String city = rs.getString(table[3]);
            String email = rs.getString(table[4]);
            // Simplmy Print the results
            System.out.format("%16s%16s%16s%16s%16s\n", id, surname, name, city, email);
        }
        // Organizations
        rs = statement.executeQuery("SELECT * FROM organizations");
        System.out.format("%16s", "Table Organizations\n");
        table = new String[]{"ID", "Name", "Address", "Phone_number"};
        for (String i : table) {
            System.out.format("%16s", i);
        }
        System.out.println();

        while (rs.next()) {
            int id = rs.getInt(table[0]);
            String name = rs.getString(table[1]);
            String address = rs.getString(table[2]);
            int phone_number = rs.getInt(table[3]);

            System.out.format("%16s%16s%16s%16s\n", id, name, address, phone_number);
        }

        // Projects
        rs = statement.executeQuery("SELECT * FROM Projects");
        System.out.format("%16s", "Table Projects\n");
        table = new String[]{"ID", "Name", "Date_start", "Date_end"};
        for (String i : table) {
            System.out.format("%16s", i);
        }
        System.out.println();

        while (rs.next()) {
            int id = rs.getInt(table[0]);
            String name = rs.getString(table[1]);
            Date date_start = rs.getDate(table[2]);
            Date date_end = rs.getDate(table[3]);

            System.out.format("%16s%16s%16s%16s\n", id, name, date_start, date_end);
        }

        // Projects
        rs = statement.executeQuery("SELECT * FROM artist_projects");
        System.out.format("%16s", "Table artists_projects\n");
        table = new String[]{"ID", "artist_id", "projects_id"};
        for (String i : table) {
            System.out.format("%16s", i);
        }
        System.out.println();

        while (rs.next()) {
            int id = rs.getInt(table[0]);
            int artist_id = rs.getInt(table[1]);
            int projects_id = rs.getInt(table[2]);

            System.out.format("%16s%16s%16s\n", id, artist_id, projects_id);
        }
    }

    private static void insertDataArtist() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new artist: ");
        String newArtist_name = input.next();
        String newArtist_surname = input.next();
        String newArtist_position = input.next();
        int id_organization = input.nextInt();


        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO artists(surname," +
                " name, position, id_organization) VALUES (?,?,?,?)");

        preparedStatement.setString(1, newArtist_name);
        preparedStatement.setString(2, newArtist_surname);
        preparedStatement.setString(3, newArtist_position);
        preparedStatement.setInt(4, id_organization);

        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: " + n);

    }

    private static void deleteDataArtist() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input id of artists for delete: ");
        Integer artist = input.nextInt();

        // 3. executing SELECT query
        //   PreparedStatements can use variables and are more efficient
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM artists WHERE id=?");
        preparedStatement.setInt(1, artist);
        int n = preparedStatement.executeUpdate();
        System.out.println("Count rows that deleted: " + n);
    }

    private static void updateDataArtist() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input id  of artist what you want to update: ");
        Integer id_artist = input.nextInt();
        System.out.println("Input new surname of artist for %s: "+ id_artist);
        String surname_new = input.next();


        statement.execute("UPDATE artists SET surname='"+surname_new+"' WHERE id='"+id_artist+"';");


    }

}





