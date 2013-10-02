import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Разработчик - Геннадий Матющенко
 * Использована IDE :IntelliJ IDEA:.
 */
public class Controller {
    public TextField fcField;
    public TextArea messageLabel;
    public TextField maxField;
    public TextField numField;
    public CheckBox numCheck;
    public Label timeLabel;

    public void fcButton(ActionEvent actionEvent) throws InterruptedException {
        if(fcField.getText().isEmpty()) messageLabel.setText("Введите число, факториал которого необходимо рассчитать");

        String num = fcField.getText();
        long start = System.currentTimeMillis();
        messageLabel.setText("Факториал " + num + ": " + Util.getFactorial(Long.parseLong(num)).toString());
        long end = System.currentTimeMillis();
        long time = end - start;
        if (time < 1000) timeLabel.setText("На расчет факториала потребовалось " + time + " миллисекунд (а)");
        if (time > 1000) timeLabel.setText("На расчет факториала потребовалось " + time / 1000 + " секунд (а)");
        if (time > 60000) timeLabel.setText("На расчет факториала потребовалось " + time / 60000 + " минут (а)");
    }

    public void maxButton(ActionEvent actionEvent) {
        if(maxField.getText().isEmpty()) messageLabel.setText("Введите ряд чисел, максимальное число из которого нужно\nнайти." +
                " Числа разделяйте пробелом.");
        ArrayList<Long> list = new ArrayList<Long>();
        for(String s : maxField.getText().split(" ")) list.add(Long.decode(s));
        messageLabel.setText("Максимальное число: " + Util.getMaxNum(list));
    }

    public void numButton(ActionEvent actionEvent) {
        long start = System.currentTimeMillis();

        String input = numField.getText();
        int inputLength = input.length();
        char[] num = new char[inputLength];
        for(int i = 0; i < inputLength; i++) num[i] = input.charAt(i);

        if(numCheck.isSelected()){
            Util.numVarRepeat(num, new char[inputLength], 0);
            messageLabel.setText("Возможные варианты:\n" +
                    Util.numVarsRepeat.toString());
            Util.numVarsRepeat.clear();
        } else {
            Util.numVar(num, new char[inputLength], 0);
            messageLabel.setText("Возможные варианты:\n" +
                    Util.numVars.toString());
            Util.numVars.clear();
        }
        long end = System.currentTimeMillis();
        long time = end - start;

        if (time < 1000) timeLabel.setText("На расчет перестановок потребовалось " + time + " миллисекунд (а)");
        if (time > 1000) timeLabel.setText("На расчет перестановок потребовалось " + time / 1000 + " секунд (а)");
        if (time > 60000) timeLabel.setText("На расчет перестановок потребовалось " + time / 60000 + " минут (а)");
    }
}
