package ru.job4j.junior.multithreading.threads;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        RectangleMove recMoveRunner = new RectangleMove(rect, limitX, limitY);
        Thread pong = new Thread(recMoveRunner);
        pong.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        rect.toFront();
        stage.setOnCloseRequest(e -> {
            recMoveRunner.stop();
        });
        stage.show();
    }
}