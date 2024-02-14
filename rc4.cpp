#include <iostream>
#include <iomanip>
#include <vector>

/* HW#3 PROBLEM #7
 * RC4 algorithm with keys {0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F, 0x77}
 * output S after init, after 100 bytes of keystream, after 1000 bytes of keystream
*/

class RC4 {
private:
    std::vector<unsigned char> S;
    int i;
    int j;

    void swap(int x, int y) {
        unsigned char temp = S[x];
        S[x] = S[y];
        S[y] = temp;
    }

public:
    RC4(std::vector<unsigned char> key) {
        S.resize(256);
        for (int i = 0; i < 256; ++i)
            S[i] = i;

        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + S[i] + key[i % key.size()]) % 256;
            swap(i, j);
        }

        this->i = 0;
        this->j = 0;
    }

    unsigned char getNextByte() {
        i = (i + 1) % 256;
        j = (j + S[i]) % 256;
        swap(i, j);
        return S[(S[i] + S[j]) % 256];
    }

    std::vector<unsigned char> generateKeystream(int length) {
        std::vector<unsigned char> keystream;
        for (int k = 0; k < length; ++k)
            keystream.push_back(getNextByte());
        return keystream;
    }

    void printState() {
        std::cout << "Permutation S:\n";
        for (int row = 0; row < 16; ++row) {
            for (int col = 0; col < 16; ++col)
                std::cout << std::setw(2) << std::setfill('0') << std::hex << (int)S[row * 16 + col] << " ";
            std::cout << "\n";
        }
        std::cout << "i: " << i << ", j: " << j << "\n";
    }
};

int main() {
    std::vector<unsigned char> key = {0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F, 0x77};
    RC4 rc4(key);

    // Initial state
    std::cout << "After initialization:\n";
    rc4.printState();

    // Generate first 100 bytes of keystream
    std::cout << "\nAfter generating first 100 bytes of keystream:\n";
    rc4.generateKeystream(100);
    rc4.printState();

    // Generate first 1000 bytes of keystream
    std::cout << "\nAfter generating first 1000 bytes of keystream:\n";
    rc4.generateKeystream(1000);
    rc4.printState();

    return 0;
}