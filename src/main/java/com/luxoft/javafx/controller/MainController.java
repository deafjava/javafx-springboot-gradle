package com.luxoft.javafx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MainController {

//    @FXML
//    public ComboBox<Country> countriesComboBox;
//
//    @Autowired
//    private CountryService countryService;
//
//    @FXML
//    public void initialize() {
//        countriesComboBox.setConverter(new CountryNameStringConverter());
//        countriesComboBox.setItems(FXCollections.observableArrayList(countryService.getAllCountries()));
//        countriesComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            String old = Optional.ofNullable(oldValue).orElse(new Country("BR", "Brazil")).getName();
//            log.info("Old: " + old + ", New: " + newValue.getName());
//        });
//    }
}
