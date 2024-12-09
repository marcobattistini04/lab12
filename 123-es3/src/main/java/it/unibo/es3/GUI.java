package it.unibo.es3;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GUI extends JFrame {
    private final static String SPACE = " ";
	private final static String AST = "*";
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int width) {
        this.logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        JPanel canva = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(width,width));
        JButton button = new JButton(">"); 
        canva.add(panel, BorderLayout.CENTER);
        canva.add(button, BorderLayout.SOUTH);
        this.getContentPane().add(canva);
        
        button.addActionListener( e -> {
           logics.hit();
           this.updateView();
           if(logics.toQuit()) {
                System.exit(0);
           }
        });
            
        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
                final JButton jb = new JButton();
                this.buttons.put(jb, new Pair<Integer,Integer>(i, j));
                panel.add(jb);
            }
        }
        
        this.setVisible(true);
    }

    public void updateView(){
        List<Pair<Integer, Integer>> list = logics.getPositions();
        buttons.forEach((button, pair) -> {
            if(list.contains(pair)) {
                button.setText(AST);
            } else {
                button.setText(SPACE);
            }
        });
    }
    
}