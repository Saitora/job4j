package ru.job4j.junior.multithreading.threads;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int deltaX = 5;
    private int deltaY = 5;
    private final int limitX;
    private final int limitY;

    private volatile boolean isRunning = false;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    private void moveRect() {
        if (((this.rect.getX() + this.rect.getWidth()) >= limitX) || (this.rect.getX() <= 0)) {
            deltaX *= -1;
        }
        if (((this.rect.getY() + this.rect.getHeight()) >= limitY) || (this.rect.getY() <= 0)) {
            deltaY *= -1;
        }
        this.rect.setX(this.rect.getX() + deltaX);
        this.rect.setY(this.rect.getY() + deltaY);
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            moveRect();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
