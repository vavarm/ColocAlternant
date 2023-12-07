package fr.umontpellier.polytech.ig.colocalternant.dao.utils;

import java.sql.*;

public class DbUtils {
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
    }
}