/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinifta okunan string ifadenin integer dizisine ayristirilmasi yapilmaktadir.
* </p>
*/

package Paket;

public class SayiOkuma { //SayiOkuma sinifi.
	
	public static int[] sayilariOku(String girdi) //Alinan string ifadenin icindeki sayilari ayiklayan metot.
	{
        String[] ayrikSayilar = girdi.split(" "); //Kullanicinin girdiği ifadenin string olarak alinip split yardimi ile sayilara ayrilmasi.
        int[] sayilar = new int[ayrikSayilar.length]; //Sayi adedince dizi olusturulmasi.

        for (int i = 0; i < ayrikSayilar.length; i++) 
        {
            sayilar[i] = Integer.parseInt(ayrikSayilar[i]); //Stringden cekilmis olan deglerin integer olarak diziye aktarilmasi.
        }

        return sayilar; //Dizinin return edilmesi.
	}
}
