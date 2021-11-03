package utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    public Map<String, String> selectNewToken(){
        Map<String, String> mapResult = new HashMap<>();
        try {
            String uri = "jdbc:postgresql://ec2-54-147-207-184.compute-1.amazonaws.com:5432/d1fq4qgun3gep";
            String user = "cmkckjpktdsjjl";
            String pass = "377164185c9bbcf2c5cb90227a7973fac52b9dfa7f27707187e8ad1cec5f741f";

            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection(uri, user, pass);

            PreparedStatement pstm = conexao.prepareStatement("SELECT * FROM token;");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.wasNull()) {
                while (resultSet.next()){
                    mapResult.put("grant_type", resultSet.getString("grant_type"));
                    mapResult.put("refresh_token", resultSet.getString("refresh_token"));
                    mapResult.put("client_id", resultSet.getString("client_id"));
                    mapResult.put("client_secret", resultSet.getString("client_secret"));
                }
            }
            pstm.close();
            conexao.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return mapResult;
    }

    public void insertNewToken(String refresh_token, String grant_type, String client_id, String client_secret) {
        try {
            String uri = "jdbc:postgresql://ec2-54-147-207-184.compute-1.amazonaws.com:5432/d1fq4qgun3gep";
            String user = "cmkckjpktdsjjl";
            String pass = "377164185c9bbcf2c5cb90227a7973fac52b9dfa7f27707187e8ad1cec5f741f";

            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection(uri, user, pass);

            PreparedStatement pstm = conexao.prepareStatement("SELECT * FROM token;");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.wasNull()) {
                PreparedStatement preparedStatement = conexao.prepareStatement("DELETE FROM token");
                preparedStatement.execute();
                preparedStatement.close();
            }
            PreparedStatement pst = conexao.prepareStatement("INSERT INTO token (refresh_token, grant_type, client_id, client_secret)\n" +
                    "VALUES('"+refresh_token+"', '"+grant_type+"', '"+client_id+"', '"+client_secret+"');");

            pst.execute();
            pstm.close();
            pst.close();
            conexao.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
