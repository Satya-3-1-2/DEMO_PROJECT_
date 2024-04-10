package dao;

import model.AnalyticsData;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyticsDAO {

    private static final String INSERT_ANALYTICS_DATA_SQL = "INSERT INTO analytics_data (url_id, clicks) VALUES (?, ?)";
    private static final String UPDATE_ANALYTICS_DATA_SQL = "UPDATE analytics_data SET clicks = ? WHERE url_id = ?";
    private static final String SELECT_ANALYTICS_DATA_BY_URL_ID_SQL = "SELECT * FROM analytics_data WHERE url_id = ?";

    public boolean addAnalyticsData(AnalyticsData analyticsData) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ANALYTICS_DATA_SQL)) {
            preparedStatement.setInt(1, analyticsData.getUrlId());
            preparedStatement.setInt(2, analyticsData.getClicks());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAnalyticsData(AnalyticsData analyticsData) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ANALYTICS_DATA_SQL)) {
            preparedStatement.setInt(1, analyticsData.getClicks());
            preparedStatement.setInt(2, analyticsData.getUrlId());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AnalyticsData getAnalyticsDataByUrlId(int urlId) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ANALYTICS_DATA_BY_URL_ID_SQL)) {
            preparedStatement.setInt(1, urlId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                AnalyticsData analyticsData = new AnalyticsData();
                analyticsData.setId(resultSet.getInt("id"));
                analyticsData.setUrlId(resultSet.getInt("url_id"));
                analyticsData.setClicks(resultSet.getInt("clicks"));
                return analyticsData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
