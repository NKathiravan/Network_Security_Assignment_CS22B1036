import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    public static BigInteger modInverse(BigInteger e, BigInteger phi) {
        return e.modInverse(phi);
    }

    public static boolean isPrime(BigInteger n) {
        return n.isProbablePrime(10);
    }

    public static BigInteger[] generateKeypair(BigInteger p, BigInteger q) {
        if (!isPrime(p) || !isPrime(q)) {
            throw new IllegalArgumentException("Both numbers must be prime.");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("p and q cannot be the same.");
        }

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.valueOf(2);
        while (e.compareTo(phi) < 0) {
            if (gcd(e, phi).equals(BigInteger.ONE)) {
                break;
            }
            e = e.add(BigInteger.ONE);
        }

        BigInteger d = modInverse(e, phi);
        return new BigInteger[] { e, n, d };
    }

    public static BigInteger[] encrypt(String message, BigInteger e, BigInteger n) {
        BigInteger[] cipher = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            cipher[i] = BigInteger.valueOf((int) message.charAt(i)).modPow(e, n);
        }
        return cipher;
    }

    public static String decrypt(BigInteger[] ciphertext, BigInteger d, BigInteger n) {
        StringBuilder plaintext = new StringBuilder();
        for (BigInteger cipher : ciphertext) {
            plaintext.append((char) cipher.modPow(d, n).intValue());
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first prime number (p): ");     
        String input1 = scanner.nextLine();
        System.out.print("Enter second prime number (q): ");
        String input2 = scanner.nextLine();
        BigInteger p = new BigInteger(input1);
        BigInteger q = new BigInteger(input2);
        BigInteger[] keys = generateKeypair(p, q);
        BigInteger e = keys[0];
        BigInteger n = keys[1];
        BigInteger d = keys[2];
        System.out.print("Enter a message to encrypt: ");
        String message = scanner.nextLine();
        BigInteger[] ciphertext = encrypt(message, e, n);
        String decryptedMessage = decrypt(ciphertext, d, n);
        scanner.close();
        System.out.println("Original Message: " + message);
        System.out.println("Public Key: (e: " + e + ", n: " + n + ")");
        System.out.println("Private Key: (d: " + d + ", n: " + n + ")");
        System.out.println("Encrypted Message: ");
        for (BigInteger c : ciphertext) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
