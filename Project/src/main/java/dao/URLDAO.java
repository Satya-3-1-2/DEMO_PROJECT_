package dao;

import model.ShortenedURL;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class URLDAO {

    private static final String INSERT_URL_SQL = "INSERT INTO shortened_urls (long_url, short_url) VALUES (?, ?)";
    private static final String SELECT_LONG_URL_BY_SHORT_URL_SQL = "SELECT long_url FROM shortened_urls WHERE short_url = ?";

    public boolean addURL(ShortenedURL shortenedURL) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_URL_SQL)) {
            preparedStatement.setString(1, shortenedURL.getLongURL());
            preparedStatement.setString(2, shortenedURL.getShortURL());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getLongURLByShortURL(String shortURL) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LONG_URL_BY_SHORT_URL_SQL)) {
            preparedStatement.setString(1, shortURL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("long_url");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
