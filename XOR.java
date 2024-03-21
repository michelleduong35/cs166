public class XOR {

  static int calculate(String a, String b){
    int result = 0;

    for(int i=0;i<a.length();i++){
      if(a.charAt(i)!=b.charAt(i)) result+=1;
    }

    return result;
  }  
 
  

  public static void main(String[] args) {
    System.out.println(calculate("1001110010001011011110100001010000100101001101101001010110000100","1000100001010101001000100011001101100110100110011100110010111011"));
  }
}