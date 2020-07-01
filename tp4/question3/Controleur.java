package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel{

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        
        ButtonsActionListener actionListener = new ButtonsActionListener();
        donnee.addActionListener(actionListener);
        
        boutons.add(push);  push.addActionListener(actionListener);
        boutons.add(add);   add.addActionListener(actionListener);
        boutons.add(sub);   sub.addActionListener(actionListener);
        boutons.add(mul);   mul.addActionListener(actionListener);
        boutons.add(div);   div.addActionListener(actionListener);
        boutons.add(clear); clear.addActionListener(actionListener);
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        if(pile.estPleine()){
            push.setEnabled(false);
        }   
        else{
            push.setEnabled(true);
        }   
        
        if(pile.taille() <= 1){
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        } 
        else {
            add.setEnabled(true);
            sub.setEnabled(true);
            mul.setEnabled(true);
            div.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }
    public class ButtonsActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String actionCommand = event.getActionCommand();
            int operande1 = 0;
            int operande2 = 0;
            int resultat = 0;
            boolean divisionParZero = false;
             
            if(actionCommand.equals("push")){
                try{
                    pile.empiler(operande());
                } 
                catch(NumberFormatException nfe){
                }
                catch(PilePleineException ppe) {
                    ppe.printStackTrace();
                }
            } 
            else if(actionCommand.equals("[]")){
                while(!pile.estVide()){
                    try{
                        pile.depiler();
                    } 
                    catch(PileVideException pve){
                        pve.printStackTrace();
                    }
                }
            } 
            else if(actionCommand.equals("+")||actionCommand.equals("-")||actionCommand.equals("*")||actionCommand.equals("/")){
                try{
                    operande1 = pile.depiler();
                    operande2 = pile.depiler();
                } 
                catch(PileVideException pve){
                    pve.printStackTrace();
                }
            
                if(actionCommand.equals("+")){
                    resultat = operande2 + operande1;
                }
                else if(actionCommand.equals("-")){
                    resultat = operande2 - operande1;
                }
                else if(actionCommand.equals("*")) 
                    resultat = operande2 * operande1;
                }
                else if(actionCommand.equals("/")) {
                    if(operande1 == 0){
                        divisionParZero = true;
                    }
                    else{
                        resultat = operande2 / operande1;
                    }   
                }
                try{
                    if(divisionParZero){
                        pile.empiler(operande2);
                        pile.empiler(operande1);
                    }
                    else pile.empiler(resultat);
                }
                catch(PilePleineException ppe){
                    ppe.printStackTrace();
                }
            }
        }
    }