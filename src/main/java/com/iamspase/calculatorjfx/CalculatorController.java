package com.iamspase.calculatorjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {
    private String operator = "";
    private double result = 0.0;
    private boolean isNegative = false;
    private boolean didFirstCalculation = false;

    @FXML
    private Label currentLbl, prevLbl, operatorLbl;

    public void clearAll(ActionEvent actionEvent) {
        result = 0;
        isNegative = false;
        operator = "";

        currentLbl.setText("");
        prevLbl.setText("");
        operatorLbl.setText(operator);
    }

    public void clear(ActionEvent actionEvent) {
        if(currentLbl.getText().isEmpty()) return;

        String oldString = currentLbl.getText();
        String newString = currentLbl.getText().substring(0, oldString.length() - 1);

        currentLbl.setText(newString);
    }

    public void changeArithmeticSign(ActionEvent actionEvent) {
        if (isNegative) {
            currentLbl.setText(currentLbl.getText().replace("-", ""));
        }
        else {
            currentLbl.setText("-" + currentLbl.getText());
        }

        isNegative = !isNegative;
    }

    public void operatorClicked(ActionEvent actionEvent) {
        Button buttonClicked = (Button) actionEvent.getSource();
        operator = buttonClicked.getText();
        operatorLbl.setText(operator);

        if(currentLbl.getText().isEmpty()) return;

        prevLbl.setText(currentLbl.getText());

        currentLbl.setText("");
    }

    public void numberClicked(ActionEvent actionEvent) {
        if(!didFirstCalculation) {
            currentLbl.setText("");
            didFirstCalculation = true;
        }
        Button buttonClicked = (Button) actionEvent.getSource();

        currentLbl.setText(currentLbl.getText() + buttonClicked.getText());
    }

    public void equalsClicked(ActionEvent actionEvent) {
        if(operator.equalsIgnoreCase("")) {
            return;
        }

        switch(operator) {
            case "+": result = Double.parseDouble(prevLbl.getText()) + Double.parseDouble(currentLbl.getText());
                break;
            case "-": result = Double.parseDouble(prevLbl.getText()) - Double.parseDouble(currentLbl.getText());
                break;
            case "X": result = Double.parseDouble(prevLbl.getText()) * Double.parseDouble(currentLbl.getText());
                break;
            case "/": result = Double.parseDouble(prevLbl.getText()) / Double.parseDouble(currentLbl.getText());
                break;
            default: result = 0.0;
        }

        result = Math.floor(result);

        prevLbl.setText(prevLbl.getText() + " " + operator + " " + currentLbl.getText() + " =");
        currentLbl.setText(String.valueOf(result));

        operator = "";
        operatorLbl.setText(operator);
    }


    public void addPoint(ActionEvent actionEvent) {
        if(currentLbl.getText().contains(".")) {
            return;
        }

        currentLbl.setText(currentLbl.getText() + ".");
    }

    public void onSquareClicked(ActionEvent actionEvent) {
        if(currentLbl.getText().isEmpty()) return;
        result = Double.parseDouble(currentLbl.getText()) * Double.parseDouble(currentLbl.getText());

        currentLbl.setText(String.valueOf(result));
    }
}