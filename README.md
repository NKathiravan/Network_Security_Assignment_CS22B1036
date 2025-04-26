# Network_Security_Assignment_CS22B1036

# RSA Algorithm Implementation 🔐

This project implements the RSA public-key cryptosystem using Python. It's a part of the Network Security course assignment (CS1702) at the **National Institute of Technology Puducherry**, submitted by **Sandeep U (CS22B1050)**.

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

- **Miller-Rabin Primality Test** for generating large primes
- **Extended Euclidean Algorithm** to compute the modular inverse
- **Key Pair Generation** usypted original messageing large prime numbers
- **Text Encryption/Decryption** using public/private RSA keys

- Enter the desired bit length (e.g., 1024) for key generation.
- Enter a message to encrypt.
- The program outputs:
  - Public and private keys
  - Encrypted message
  - Decr

## 📄 Output Example

```
Enter key bit-length (e.g., 1024): 512
Generating RSA keys...
Public Key:  (e, n)
Private Key:  (d, n)

Enter message to encrypt: hello

Encrypted Message:
[2793, 8482, 13293, 13293, 15456]

Decrypted Message:
hello
```
