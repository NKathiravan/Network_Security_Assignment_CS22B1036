import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class RSA_GUI extends JFrame {

    private JTextField pField, qField, messageField;
    private JTextArea outputArea;
    private JButton generateKeysButton, encryptButton, decryptButton;

    private BigInteger e, d, n;
    private BigInteger[] ciphertext;

    public RSA_GUI() {
        setTitle("RSA Encryption/Decryption");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        pField = new JTextField();
        qField = new JTextField();
        messageField = new JTextField();
        generateKeysButton = new JButton("Generate Keys");
        encryptButton = new JButton("Encrypt Message");
        decryptButton = new JButton("Decrypt Message");

        inputPanel.add(new JLabel("Prime Number p:"));
        inputPanel.add(pField);
        inputPanel.add(new JLabel("Prime Number q:"));
        inputPanel.add(qField);
        inputPanel.add(new JLabel("Message:"));
        inputPanel.add(messageField);
        inputPanel.add(generateKeysButton);
        inputPanel.add(encryptButton);
        inputPanel.add(decryptButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button Listeners
        generateKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateKeys();
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptMessage();
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptMessage();
            }
        });

        setVisible(true);
    }

    private void generateKeys() {
        try {
            BigInteger p = new BigInteger(pField.getText());
            BigInteger q = new BigInteger(qField.getText());

            BigInteger[] keys = RSA.generateKeypair(p, q);
            e = keys[0];
            n = keys[1];
            d = keys[2];

            outputArea.setText("");
            outputArea.append("Keys generated successfully!\n");
            outputArea.append("Public Key: (e: " + e + ", n: " + n + ")\n");
            outputArea.append("Private Key: (d: " + d + ", n: " + n + ")\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Key Generation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void encryptMessage() {
        try {
            if (e == null || n == null) {
                JOptionPane.showMessageDialog(this, "Please generate keys first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String message = messageField.getText();
            ciphertext = RSA.encrypt(message, e, n);

            outputArea.append("\nEncrypted Message:\n");
            for (BigInteger c : ciphertext) {
                outputArea.append(c.toString() + " ");
            }
            outputArea.append("\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Encryption Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decryptMessage() {
        try {
            if (ciphertext == null || d == null || n == null) {
                JOptionPane.showMessageDialog(this, "Please encrypt a message first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String decrypted = RSA.decrypt(ciphertext, d, n);
            outputArea.append("\nDecrypted Message:\n" + decrypted + "\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Decryption Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RSA_GUI());
    }
}
