package Calcula.com.br.calculadora;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

public class CalculaImposto extends JFrame implements ActionListener {
    private Sinais sinais;
    private JTextField valorUm;
    private JTextField valorDois;

    private JComboBox escolhaDeCalculo;
    private JButton botaoCalcular;
    private JTextField resultadoTF;


    private DecimalFormat df = new DecimalFormat("0.00");

    public CalculaImposto() {
        valorUm = new JTextField(10);
        valorDois = new JTextField(15);

        escolhaDeCalculo = new JComboBox();
        escolhaDeCalculo.addItem(Sinais.DIVISAO);
        escolhaDeCalculo.addItem(Sinais.MAIS);
        escolhaDeCalculo.addItem(Sinais.MENOS);
        escolhaDeCalculo.addItem(Sinais.MULTIPLICACAO);


        botaoCalcular = new JButton("Calcular");

        resultadoTF = new JTextField();
        resultadoTF.setEditable(true);//se pode ser escrito nesse quadro ou nao

        JPanel calculadoraPainel = new JPanel();//Criando janela com Leayout dos botoes criados
        calculadoraPainel.setLayout(new FlowLayout(FlowLayout.LEFT));
        calculadoraPainel.add(valorUm);
        calculadoraPainel.add(escolhaDeCalculo);
        calculadoraPainel.add(valorDois);
        calculadoraPainel.add(botaoCalcular);

        JPanel conteudoPainel = new JPanel();
        conteudoPainel.setBorder(new TitledBorder("Calculadora"));
        conteudoPainel.setLayout(new BorderLayout());
        conteudoPainel.add(BorderLayout.CENTER, resultadoTF);
        conteudoPainel.add(BorderLayout.SOUTH, calculadoraPainel);

        super.setTitle("Calculadora");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setContentPane(conteudoPainel);
        super.pack();
        super.setLocationRelativeTo(this);

        botaoCalcular.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        boolean num10k = true;
        boolean num20k = true;

        //df é decimal formati, formato decimal
        double num2 = 0;
        double num1 = 0;
        try {
            num2 = df.parse(valorDois.getText()).doubleValue();
            num1 = df.parse(valorUm.getText()).doubleValue();
        } catch (ParseException ex) {
            num10k = false;
            num20k = false;
        }

        if (num10k && num20k) {
            boolean divZero = false;
            double result = 0;

            Object selItem = escolhaDeCalculo.getSelectedItem();
            if (selItem.equals(Sinais.MAIS)) {
                result = num1 + num2;
            } else if (selItem.equals(Sinais.MENOS)) {
                result = num1 - num2;
            } else if (selItem.equals(Sinais.MULTIPLICACAO)) {
                result = num1 * num2;
            } else if (selItem.equals(Sinais.DIVISAO)) {
                if (num2 == 0) {
                    divZero = true;
                } else {
                    result = num1 / num2;
                }
            }
            if ( divZero ) {
                String msg = "Operação inválida - divisão por zero!";
                JOptionPane.showMessageDialog( null, msg, "Calculadora", JOptionPane.WARNING_MESSAGE );
            } else {
                resultadoTF.setText( df.format( result ) );
            }
        } else {
            if ( num10k == false ) {
                String msg = "O valor \"" + valorUm.getText() + "\" não é um numero válido.";
                JOptionPane.showMessageDialog( this, msg, "Calculadora", JOptionPane.WARNING_MESSAGE );
            } else if ( num20k == false ) {
                String msg = "O valor \"" + valorDois.getText() + "\" não é um numero válido.";
                JOptionPane.showMessageDialog( this, msg, "Calculadora", JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    public static void main(String[] args) {
        CalculaImposto calculaImposto = new CalculaImposto();
        calculaImposto.setVisible(true);
    }
}

