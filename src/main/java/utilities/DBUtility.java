package utilities;
//import mysql library
import com.example.f21comp1011gctest1student.NetflixShow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.*;
import java.util.ArrayList;


public class DBUtility {

    //Database connection data. Student was not working. I had to drop and re-create the user
    private static String user = "student";
    private static String pw = "student";
    private static String connectURL = "jdbc:mysql://localhost:3306/javaTest";

    //set Observable List
    public static final ObservableList<NetflixShow> dataList = FXCollections.observableArrayList();

    //method to get information to populate the table
    public static ArrayList<NetflixShow> getNetflixCatalog(String showType, String showRating) {

        ArrayList<NetflixShow> netflixShows = new ArrayList<>();

        ResultSet resultSet = null;

        //All types and ratings

        String sql = "";

        //Filter by type, all ratings
        if (showType != null && showRating == null) {
            sql = "SELECT * FROM netflix WHERE type = ?";
        }

        //Filter by rating, all types
        if (showRating != null && showType == null) {
            sql = "SELECT * FROM netflix WHERE rating = ?";
        }

        //Filter by type and rating
        if (showType != null && showRating != null) {
            sql = "SELECT * FROM netflix WHERE type != ? AND rating != ?";
        }

        System.out.println(sql);

        try(
                Connection conn = DriverManager.getConnection(connectURL, user, pw);
                PreparedStatement statement = conn.prepareStatement(sql);

        )
        {
            statement.setString(1, showType);
            statement.setString(2, showRating);
            System.out.println(statement);
            resultSet = statement.executeQuery();


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
        finally{
            //close result set
            try{
                if(resultSet != null)
                    resultSet.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        dataList.addAll(netflixShows);
        return netflixShows;

    }

}