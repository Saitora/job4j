package ru.job4j.junior.multithreading.threads;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private boolean directionX = false;
    private boolean directionY = false;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    private void moveRect() {
        if ((this.rect.getX() + this.rect.getWidth()) >= limitX) {
            directionX = true;
        } else if (this.rect.getX() <= 0) {
            directionX = false;
        }
        if ((this.rect.getY() + this.rect.getHeight()) >= limitY) {
            directionY = true;
        } else if (this.rect.getY() <= 0) {
            directionY = false;
        }
        if (directionX) {
            this.rect.setX(this.rect.getX() - 1);
        } else {
            this.rect.setX(this.rect.getX() + 1);
        }
        if (directionY) {
            this.rect.setY(this.rect.getY() - 1);
        } else {
            this.rect.setY(this.rect.getY() + 1);
        }
    }

    @Override
    public void run() {
        while (true) {
            moveRect();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
