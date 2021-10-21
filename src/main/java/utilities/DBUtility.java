package utilities;
//import mysql library
import com.example.f21comp1011gctest1student.NetflixShow;

import java.sql.*;
import java.util.ArrayList;


public class DBUtility {
    private static String user = "student";
    private static String pw = "student";
    private static String connectURL = "jdbc:mysql://localhost:3306/javaTest";


    //method to get information to populate the table
    public static ArrayList<NetflixShow> getNetflixCatalog() {

        ArrayList<NetflixShow> netflixShows = new ArrayList<>();

        String sql = "SELECT showId, type, title, rating, director, cast \n" +
                    "FROM netflix ";

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                String showId = resultSet.getString("showId");
                String type = resultSet.getString("type");
                String title = resultSet.getString("title");
                String rating = resultSet.getString("rating");
                String director = resultSet.getString("director");
                String cast = resultSet.getString("cast");

                NetflixShow newShow = new NetflixShow(showId, type, title, rating, director, cast);
                newShow.setShowId(showId);
                newShow.setType(type);
                newShow.setTitle(title);
                newShow.setRating(rating);
                newShow.setDirector(director);
                newShow.setCast(cast);

                netflixShows.add(newShow);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return netflixShows;

    }
}