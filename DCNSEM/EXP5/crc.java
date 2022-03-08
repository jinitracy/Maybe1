import java.util.*;

import javax.swing.Renderer;

public class crc{
    private static String Data_word="";
    private static String Generator="";
    private static String k="";
    private static String code="";
    private static String data="";
    private static String remainder="";

    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the binary word:");
        Data_word= scan.nextLine();
        System.out.println("Enter the generator polynomial");
        Generator= scan.nextLine();
        for(int i=0;i<Generator.length()-1;i++){
            k+='0';
        }
        data = Data_word + k ;
        code= div(data, Generator);
        data = Data_word + code;
        System.out.println("The transmitted word is: " + data);
        System.out.println("Enter the received word:");
        data = scan.nextLine();
        code=div(data,Generator);

        if(Integer.parseInt(code)==0){
            System.out.println("No error");
        }
        else{
            System.out.println("Contains error");
        }
    }

        public static String div(String data, String Generator){
          int length= Generator.length();
          code = data.substring(0,Generator.length());
          while(length<data.length()){
              if(code.charAt(0)=='0'){
                  code= code.substring(1,code.length());
                  code += data.charAt(length++);
              }
              process_xor();
          }
          if(code.charAt(0)=='1'){
          process_xor();
          }
          return (code =code.substring(1, code.length()));

        }

        public static void process_xor() {

        for(int i=0;i<Generator.length();i++){
            if(code.charAt(i)==Generator.charAt(i))
               remainder += '0';
            else
               remainder += '1';  
        }
            code = remainder;
            remainder="";
        }
    }