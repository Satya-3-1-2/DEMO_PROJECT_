package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.LinkAnalytics;

public class AnalyticsDAO {
    private static Statement DatabaseConnector;

	public static boolean saveAnalyticsData(String shortURL, LinkAnalytics analyticsData) {
        String sql = "INSERT INTO analytics (short_url, clicks) VALUES (?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, shortURL);
            preparedStatement.setInt(2, analyticsData.getClicks());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static LinkAnalytics getAnalyticsData(String shortURL) {
        String sql = "SELECT clicks FROM analytics WHERE short_url = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, shortURL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int clicks = resultSet.getInt("clicks");
                return new LinkAnalytics(clicks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}