/**
 * Run this file to run the program
 */

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements KeyListener {
    private Graph graph = new Graph();

    public Main() {
        // Creates a canvas (the graph) and resizes it
        graph.setSize(Graph.LENGTH, Graph.HEIGHT);

        // set up the window frame
        JFrame frame = new JFrame("3D Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(graph);
        frame.pack();
        frame.setVisible(true);

        // Add key listener to the graph canvas
        graph.addKeyListener(this);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // Rotates, stretches or shrinks the plane
        // Note that stretching will lead to a bad resolution
        // and should only be used to undo a shrink
        if (e.getKeyChar() == 'e') {
            graph.rotate_z(true);
        } else if (e.getKeyChar() == 'q') {
            graph.rotate_z(false);
        } else if (e.getKeyChar() == 'd') {
            graph.rotate_y(true);
        } else if (e.getKeyChar() == 'a') {
            graph.rotate_y(false);
        } else if (e.getKeyChar() == 'w') {
            graph.rotate_x(true);
        } else if (e.getKeyChar() == 's') {
            graph.rotate_x(false);
        } else if (e.getKeyChar() == '+') {
            graph.stretch();
        } else if (e.getKeyChar() == '-') {
            graph.shrink();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}