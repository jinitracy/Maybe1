import java.util.Scanner;
public class subnetting{
public static void main(String args[]){
Scanner scan = new Scanner(System.in);
System.out.println("enter ip address");
String ip = scan.nextLine();
String[] splitip = ip.split("\\.");
String bip="",lastaddress,firstaddress;
for(int i=0;i<4;i++)
bip += appendzeroes(Integer.toBinaryString(Integer.parseInt(splitip[i])));
System.out.println("the binary ip address is"+bip);
lastaddress=firstaddress=bip;

int mask,bits,over,power,start,end;
System.out.println("enter the subnet mask");
mask=scan.nextInt();
bits=32-mask;

if(mask>24){
over=mask-24;
power=over;
}
else if(mask>64){
over=mask-16;
power=over+8;
}
else{
over=mask-8;
power=mask+8;
}

System.out.println("the no of networks is"+Math.pow(2,power));
System.out.println("the network portion is "+ (Math.pow(2,(8-over))-2));

for(int i=31;i>31-bits;i--)
firstaddress=firstaddress.substring(0,i)+"0"+firstaddress.substring(i+1);
System.out.println("firstaddress ");
start =0;end=8;
for(int i=0;i<4;i++){
System.out.print(Integer.parseInt(firstaddress.substring(start,end),2));
if(i!=3)
System.out.print(".");
start =end;end+=8;
}


for(int i=31;i>31-bits;i--)
lastaddress=lastaddress.substring(0,i)+"1"+lastaddress.substring(i+1);
System.out.println("");
System.out.println("lastaddress ");
start =0;end=8;
for(int i=0;i<4;i++){
System.out.print(Integer.parseInt(lastaddress.substring(start,end),2));
if(i!=3)
System.out.print(".");
start =end;end+=8;
}
}

public static String appendzeroes(String s){
 String temp="00000000";
 return temp.substring(s.length())+s;

}
}