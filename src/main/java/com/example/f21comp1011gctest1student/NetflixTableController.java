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

        if (movieCheckBox.isSelected() == true && tvCheckBox.isSelected()==false) {
            FilteredList<NetflixShow> filteredList = new FilteredList<>(DBUtility.dataList, b -> true);
            filteredList.setPredicate(netflixShow -> {
                if (netflixShow.getType().contains("Movie") && netflixShow.getRating().contains(selectRatingComboBox.getSelectionModel().getSelectedItem())) {
                    return true;
                }
                else if (netflixShow.getType().contains("Movie") && selectRatingComboBox.getSelectionModel().getSelectedItem() == "All ratings"){
                    return true;
                }


                else return false;
            });

            SortedList<NetflixShow> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);
            numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());

            System.out.println("Filtering Movies Only");
        }
        else if (movieCheckBox.isSelected() == true && tvCheckBox.isSelected()==true){
            FilteredList<NetflixShow> filteredList = new FilteredList<>(DBUtility.dataList, b -> true);
            filteredList.setPredicate(netflixShow -> {
                if (netflixShow.getType().contains("Movie") || netflixShow.getType().contains("TV Show") && netflixShow.getRating().contains(selectRatingComboBox.getSelectionModel().getSelectedItem())) {
                    return true;
                }
                else if (netflixShow.getType().contains("Movie") || netflixShow.getType().contains("TV Show") && selectRatingComboBox.getSelectionModel().getSelectedItem() == "All ratings"){
                    return true;
                }
                else return false;
            });

            SortedList<NetflixShow> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);
            numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());

            System.out.println("Showing Movies and TV Shows");
        }
        else if (movieCheckBox.isSelected() == false && tvCheckBox.isSelected()==true){
            FilteredList<NetflixShow> filteredList = new FilteredList<>(DBUtility.dataList, b -> true);
            filteredList.setPredicate(netflixShow -> {
                if (netflixShow.getType().contains("TV Show") && netflixShow.getRating().contains(selectRatingComboBox.getSelectionModel().getSelectedItem())) {
                    return true;
                }
                else if (netflixShow.getType().contains("TV Show") && selectRatingComboBox.getSelectionModel().getSelectedItem() == "All ratings"){
                    return true;
                }else return false;
            });

            SortedList<NetflixShow> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);
            numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());

            System.out.println("Filtering TV Shows Only");
        }
        else if (movieCheckBox.isSelected() == false && tvCheckBox.isSelected()==false) {
            try {
                FilteredList<NetflixShow> filteredList = new FilteredList<>(DBUtility.dataList, b -> true);
                filteredList.setPredicate(netflixShow -> {
                    return false;
                });

                SortedList<NetflixShow> sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(tableView.comparatorProperty());
                tableView.setItems(sortedData);
                numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());

                System.out.println("No filters selected. Not showing Movies nor TV Shows");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        //check if any rating is selected and filter
//        if (selectRatingComboBox.getSelectionModel().getSelectedItem() != "All ratings"){
//            FilteredList<NetflixShow> filteredList = new FilteredList<>(DBUtility.dataList, b -> true);
//            filteredList.setPredicate(netflixShow -> {
//                if (netflixShow.getRating().contains(selectRatingComboBox.getSelectionModel().getSelectedItem())) {
//
//                    return true;
//                } else {
//
//                    return false;
//                }
//            });
//
//            SortedList<NetflixShow> sortedData = new SortedList<>(filteredList);
//            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
//            tableView.setItems(sortedData);
//            numOfShowsLabel.setText("Number of movies/shows: "+ tableView.getItems().size());
//            System.out.println("Filtering by");
//            System.out.println(selectRatingComboBox.getSelectionModel().getSelectedItem());
//            System.out.println();
//        }

    }
}
