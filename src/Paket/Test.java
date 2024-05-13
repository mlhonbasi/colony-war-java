/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinif main olarak gorev yapmaktadir.
* Programin baslatilmasi ve istenilen islemleri gerceklestirilmesi olur.
* </p>
*/

package Paket;

import java.util.Scanner;

public class Test //Programin baslatilmasi.
{

	public static void main(String[] args) 
	{
		clearConsole(); //Konsolun ilk basta temizlenmesi.
		
		Scanner scanner = new Scanner(System.in); //Scanner nesnesi olusturulup girilen ifadenin okunmasi.
		System.out.print("Sayilari girin: ");
		String girdi = scanner.nextLine();
		scanner.close();
		
		int[] sayilar = SayiOkuma.sayilariOku(girdi); //Girilen ifadenin sayilara parcalanmis halini alma.
		
		Oyun oyun = new Oyun(sayilar); //Oyun nesnesi olusturulmasi.
		oyun.Baslat(); //Oyunun baslatilmasi.
	}
	public final static void clearConsole() //Konsolu temizleme.
	{
		 try {
	            if (System.getProperty("os.name").contains("Windows")) {
	                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	            } else {
	                System.out.print("\033[H\033[2J");
	                System.out.flush();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}

}
