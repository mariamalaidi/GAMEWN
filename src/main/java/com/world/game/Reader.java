package com.world.game;

import com.world.game.entity.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reader  {
    private JTextField textField = new JTextField();             // textField إسمه Text Field هنا أنشأنا
    private JLabel label = new JLabel("Enter your name");        // label إسمه Label هنا أنشأنا
    private JLabel labelResult = new JLabel();                   // فارغ labelResult إسمه Label هنا أنشأنا
    private JButton button = new JButton("Get text");
    JFrame jFrame = new JFrame("Read name");
    public Reader(){
        jFrame.add(label);
        jFrame.add(textField);
        jFrame.add(button);
        jFrame.add(labelResult);
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        button.addActionListener(new ActionListener() {         // button هنا نضع الأوامر التي نريد تنفيذها عند النقر على الزر
            @Override
            public void actionPerformed(ActionEvent e) {
                labelResult.setText(textField.getText());       // button عند النقر على الـ label و وضعه كنص للكائن textField سيتم جلب النص الموجود في الكائن
                Player.name = textField.getName();
            }
        });
    }
}
