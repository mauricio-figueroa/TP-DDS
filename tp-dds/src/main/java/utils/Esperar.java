package utils;


public class Esperar{
  public static void main(String[] args){
     System.out.println("Hola ahora esperamo asta que");
     try{
        Thread.sleep(7000);
     }catch(InterruptedException e){}
     System.out.println("que transcurran 7 segundos ok");
   }
}