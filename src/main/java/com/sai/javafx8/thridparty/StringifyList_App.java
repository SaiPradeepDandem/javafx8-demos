/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.javafx8.thridparty;

import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class presents a snippet of code to String-ify a List using JavaFX Binding.
 *
 * @ref https://courses.bekwam.net/public_tutorials/bkcourse_stringifylistapp.html
 * @author sai.dandem
 */
public class StringifyList_App extends Application {

    private int counter = 1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.add(new Person("Carl"));
        persons.add(new Person("Edvin"));
        persons.add(new Person("Thomas"));

        Label label = new Label("Empty");
        label.textProperty().bind(Bindings.createStringBinding(() -> persons.stream().map(Person::getName)
                .collect(Collectors.joining(", ")), persons));

        Button b = new Button("Add One");
        b.setOnAction(e -> persons.add(new Person("Matt " + counter++)));

        VBox vbox = new VBox();
        vbox.setSpacing(10.0d);
        vbox.setPadding(new Insets(40.0d));
        vbox.getChildren().addAll(label, b);

        Scene scene = new Scene(vbox, 480, 320);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
