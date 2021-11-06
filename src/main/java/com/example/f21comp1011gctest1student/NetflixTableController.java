package com.example.f21comp1011gctest1student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.DBUtility;

import java.util.Arrays;
import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class NetflixTableController implements Initializable {

    @FXML
    private TableView<NetflixShow> tableView;

    @FXML
    private TableColumn<NetflixShow, String> showIdCol;

    @FXML
    private TableColumn<NetflixShow, String> typeCol;

    @FXML
    private TableColumn<NetflixShow, String> titleCol;

    @FXML
    private TableColumn<NetflixShow, String> ratingCol;

    @FXML
    private TableColumn<NetflixShow, String> directorCol;

    @FXML
    private TableColumn<NetflixShow, String> castCol;

    @FXML
    private CheckBox movieCheckBox;

    @FXML
    private CheckBox tvCheckBox;

    @FXML
    private ComboBox<String> selectRatingComboBox;

    @FXML
    private Label numOfShowsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectRatingComboBox.getItems().add("All ratings");
        selectRatingComboBox.getItems().addAll(getRatingsFromTable());


        //populate the combobox with the ratingsList. Hardcoded for now.
//        List<String> ratingsList = Arrays.asList("PG-13","R","TV-14","TV-G","TV-MA","TV-Y","TV-Y7");
//        selectRatingComboBox.getItems().addAll(ratingsList);

        showIdCol.setCellValueFactory(new PropertyValueFactory<>("showId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));
        castCol.setCellValueFactory(new PropertyValueFactory<>("cast"));

        tableView.getItems().addAll(DBUtility.getNetflixCatalog("All", "All ratings"));

        //show the number of Shows/Movies on the list. Getting from the tableview
        numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());

        //set checkboxes selected
        movieCheckBox.setSelected(true);
        tvCheckBox.setSelected(true);

        //set ComboBox to AllRating value by default
        selectRatingComboBox.getSelectionModel().select(0);

        selectRatingComboBox.getItems().addAll(getRatingsFromTable());
    }

    //private method called getRatingsFromTable that returns a TreeSet of all the ratings in the table
    //then it adds the ratings to the selectRatingComboBox
    private TreeSet<String> getRatingsFromTable() {
        TreeSet<String> ratings = new TreeSet<>();
        for (NetflixShow show : tableView.getItems())

            ratings.add(show.getRating());

        System.out.println(ratings);
        return ratings;

    }


    @FXML
    void applyFilter(ActionEvent event)  {

        tableView.getItems().clear();

        String type = "All";
        String rating = selectRatingComboBox.getSelectionModel().getSelectedItem();

        if (rating == null) {
            rating = "All ratings";
        }

        if (movieCheckBox.isSelected() && !tvCheckBox.isSelected()) {
            type = "Movie";
        }
        else if (!movieCheckBox.isSelected() && tvCheckBox.isSelected()) {
            type = "TV Show";
        }
        else if (!movieCheckBox.isSelected() && !tvCheckBox.isSelected()) {
            type = "none";
        }

        tableView.getItems().addAll(DBUtility.getNetflixCatalog(type, rating));


        numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());
    }

}
