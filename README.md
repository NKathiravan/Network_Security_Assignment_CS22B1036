# Network_Security_Assignment_CS22B1036

# RSA Algorithm Implementation 🔐

This project implements the RSA public-key cryptosystem using Java. It's a part of the Network Security course assignment (CS1702) at the **National Institute of Technology Puducherry**, submitted by **N Kathiravan (CS22B1036)**.

## 📚 Introduction

**RSA** (Rivest–Shamir–Adleman) is one of the first and most widely-used public-key cryptographic systems. It enables secure communication by allowing public encryption and private decryption.

RSA is based on the computational difficulty of factoring large composite numbers, a challenge known as the **factoring problem**.

## 🔑 How RSA Works

1. Choose two large prime numbers `p` and `q`.
2. Compute:
   - `n = p * q`
   - `φ(n) = lcm(p−1, q−1)`
3. Select a public key `e` such that `1 < e < φ(n)` and `gcd(e, φ(n)) = 1`.
4. Compute the private key `d`, where `d ≡ e⁻¹ mod φ(n)`.
5. Encryption: `c ≡ m^e mod n`
6. Decryption: `m ≡ c^d mod n`

## 🧠 Features
- Enter the two large prime numbers to be used for key generation
- Enter a message to encrypt.
- The program outputs:
  - Public and private keys
  - Encrypted message
  - Decrypted message

## 📄 Output Example

![image](https://github.com/user-attachments/assets/5b2ec695-6329-435a-be06-9d918d3da0f6)

