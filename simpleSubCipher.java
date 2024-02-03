import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/* HW#2 PROBLEM #11
 * input ciphertext -> letter frequency
 * input key -> decryption result
*/
public class simpleSubCipher {

  //letter frequency method with hashmap
  static Map<Character, Integer> freqAnalysis(String ciphertxt){
    Map<Character, Integer> freqMap = new HashMap<>();
    for (int i = 0; i < ciphertxt.length(); i++){
      freqMap.merge(ciphertxt.charAt(i), 1, Integer::sum);
    }
    return freqMap;
  } 

  //cipher method
  static StringBuilder simpleSub(int uKey, String ciphertxt){
    StringBuilder decryptedtxt = new StringBuilder();
    char shiftedChar = 'a';
    for (int i = 0; i < ciphertxt.length(); i++){
      shiftedChar = (char) (((ciphertxt.charAt(i) - 'A' + uKey) % 26) + 'A');
      decryptedtxt.append(shiftedChar);
    }
    return decryptedtxt;
  }

  //main method
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);  // Create a Scanner object
    System.out.println("What is the ciphertext?"); //prompt user for ciphertext

    String ciphertxt = scnr.nextLine();  // Read user input
    ciphertxt = ciphertxt.toUpperCase();
    System.out.println(freqAnalysis(ciphertxt));  // print letter frequency
    System.out.println("Would you like to test a key? (y/n)"); //ask if user wants to try key
    String rString = scnr.nextLine();  // Read user input
    int uKey = 0;
    while (rString.equals("y")){
      System.out.println("Enter a key to test (must be between 1-25)."); //ask user for key to test
      uKey = scnr.nextInt();
      scnr.nextLine();
      System.out.println(simpleSub(uKey, ciphertxt)); //test key & return result
      System.out.println("Would you like to test another key? (y/n)"); //ask if user wants to try key
      rString = scnr.nextLine();  // Read user input
    }
    System.out.println("Okay, see you next time!");
    scnr.close();
  }
}
