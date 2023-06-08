package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class FractalPlant extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public FractalPlant() {
        setTitle("Fractal Plant");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawPlant(g);
    }

    public void drawPlant(Graphics g) {
        int iterations = 5;

        String begin = "X";
        String word = generateWord(begin, iterations);

        Turtle turtle = new Turtle();
        turtle.penUp();
        turtle.moveTo(WIDTH / 2, HEIGHT);
        turtle.penDown();

        double distance = 5;
        double angle = 25;

        Stack<Turtle> turtleStack = new Stack<>(); // Stack do zapisywania stanów żółwi

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        for (char c : word.toCharArray()) {
            switch (c) {
                case 'F':
                    turtle.moveForward(distance);
                    g2d.drawLine((int) turtle.getX(), (int) turtle.getY(), (int) turtle.getX(), (int) turtle.getY());
                    break;
                case '+':
                    turtle.turnRight(angle);
                    break;
                case '-':
                    turtle.turnLeft(angle);
                    break;
                case '[':
                    turtleStack.push(turtle.clone());
                    break;
                case ']':
                    if (!turtleStack.isEmpty()) {
                        turtle = turtleStack.pop();
                    }
                    break;
            }
        }
    }

    private static String generateWord(String begin, int iterations) {
        String word = begin;

        for (int i = 0; i < iterations; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (char c : word.toCharArray()) { // Iteruje po kazdym znaku w wyrazie
                switch (c) {
                    case 'X' -> stringBuilder.append("F+[[X]-X]-F[-FX]+X");
                    case 'F' -> stringBuilder.append("FF");
                    default -> stringBuilder.append(c);
                }
            }

            word = stringBuilder.toString();
        }

        return word;
    }

    class Turtle implements Cloneable {
        private double x;
        private double y;
        private double angle;

        public Turtle() {
            this.x = 0.0;
            this.y = 0.0;
            this.angle = 25.0;
        }

        public void moveTo(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void penUp() {

        }

        public void penDown() {
        }

        public void moveForward(double distance) {
            double newX = x + distance * Math.sin(Math.toRadians(angle));
            double newY = y - distance * Math.cos(Math.toRadians(angle));
            Graphics2D g2d = (Graphics2D) getGraphics(); // Pobieram grafike
            g2d.setColor(Color.GREEN);
            g2d.drawLine((int) x, (int) y, (int) newX, (int) newY); // Rysuje linie
            x = newX; // Zmieniam wspolrzedne
            y = newY;
        }

        public void turnRight(double angle) {
            this.angle -= angle;
            if (this.angle < 0) {
                this.angle += 360;
            }
        }

        public void turnLeft(double angle) {
            this.angle += angle;     // Zmieniam kat o podany parametr
            if (this.angle <= 360) { // Sprawdzam czy kat jest mniejszy od 360
                this.angle -= 360;   // Jesli tak to odejmuje 360
            }
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        protected Turtle clone() {
            try {
                return (Turtle) super.clone();  // Tworze klon obiektu turtle
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FractalPlant gui = new FractalPlant();
            gui.setVisible(true);
        });
    }
}
