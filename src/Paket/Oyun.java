/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinifta oyun tanimlanmistir.
* Oyunun baslatilmasi, tur islemleri ve kolonilerle alakali islemlerin yapilmasi bulunmaktadir.
* </p>
*/

package Paket;

import java.util.Random;
import Koloni.*;

public class Oyun 
{
	private Koloni[] koloniler;
	
	public Oyun(int[] sayilar) //Oyun sinifi kurucusu.
	{
		koloniler = new Koloni[sayilar.length]; //Kullanicinin girmis oldugu sayi adedine gore Koloni dizisi olusturma
		
		for(int i = 0; i < sayilar.length; i++)
		{
			koloniler[i] = new Koloni(sayilar[i]); //Koloni nesnelerinin olusturulmasi
		}
		KoloniYonetim.koloniSembolAta(koloniler); //Olusturulan kolonilere sembollerinin verilmesi
	}
	
	public void Baslat() //Oyun dongusunun baslatilmasi ve tur guncellemeleri
	{
		int tur = 0;
		boolean sonKoloni = false;
		
		while(!sonKoloni) //Tek koloni kalana kadar calisan while dongusu.
		{
			koloniDurumYazdir(tur);	//Kolonilerin sembol, populasyon, yemekstok, zafer ve yenilgilerinin ekrana yazdirilmasi.					
			koloniSavaslari();		//Kolonilerin savasi icin cagirilan metot.						
			sonKoloni = KoloniYonetim.tekKoloniKontrol(koloniler); //Tek koloni kalip kalmadiginin kontrolu.
			
			if(sonKoloni){ //Son koloni kaldiysa onu da yazdirmak icin.
				koloniDurumYazdir(tur+1);
			}
			KoloniYonetim.koloniGuncelle(koloniler); //Kolonilerin tura ozgu guncellemelerinin yapilmasi.
			tur++;
		}
	}
	
	private void koloniSavaslari() //Kolonileri kendi aralarında kombinasyonlayan ve savastiran metot.
	{
		for(int i = 0; i < koloniler.length - 1; i++) //Gerekli kombinasyonlari saglayan ic-ice for donguleri
		{
			for(int j = i + 1; j < koloniler.length; j++)
			{
				if(koloniler[i].getCanlilik() == true && koloniler[j].getCanlilik() == true)
				{
					koloniSavas(koloniler[i], koloniler[j]); //Secili kombinasyonun savastirilmasi icin cagrilan metot.
				}
			}
		}
	}
	
	private void koloniSavas(Koloni koloni1, Koloni koloni2) //Secili kombinasyonlari savastiran metot.
	{
		int koloni1guc = koloni1.getTaktik().Savas(koloni1); //Kolonilerin
		int koloni2guc = koloni2.getTaktik().Savas(koloni2); //Savas guclerinin alinmasi
		int gucFarki = Math.abs(koloni1guc - koloni2guc);
		int gucOran = (gucFarki / 1000) * 100; //Guc farkina gore guc oraninin belirlenmesi.
		
		//Kolonilerin savas guclerine gore kazanma ve kaybetme durumlari
		if(koloni1guc > koloni2guc) {
			savasSonuclandir(koloni1, koloni2, gucOran); //Savasin sonuclandirilmasi icin cagrilan metot. Koloni1 kazanmasi.
		}
		else if(koloni2guc > koloni1guc) { //Koloni2 kazanmasi
			savasSonuclandir(koloni2, koloni1, gucOran);
		}
		else //Esit oldugu taktirde populasyon cogunluguna gore kazanilmasi.
		{
			if(koloni1.getPopulasyon() > koloni2.getPopulasyon())
			{
				savasSonuclandir(koloni1, koloni2, gucOran);
			}
			else if(koloni2.getPopulasyon() > koloni1.getPopulasyon())
			{
				savasSonuclandir(koloni2, koloni1, gucOran);
			}
			else //Populasyonlarinda belirleyemedigi durumlarda rastgele kazanma.
			{
				Random random = new Random();
		        int kazanan = random.nextInt(2);
		        
		        if(kazanan == 0)
		        {
		        	savasSonuclandir(koloni1, koloni2, gucOran);
		        }
		        else
		        {
		        	savasSonuclandir(koloni2, koloni1, gucOran);
		        }
			}
		}
		KoloniYonetim.koloniKontrol(koloni1, koloni2); //Savas sonrasi kolonilerin canlilik durumlarinin kontrolu.
	}
	
	//Kazanan ve kaybedenen gerekli degerlerinin guncellenmesi.
	private void savasSonuclandir(Koloni kazanan, Koloni kaybeden, int gucOran) 
	{
		//Kaybedenin populasyonun ilgili oranda azaltilmasi.
		int kayipPopulasyon = (kaybeden.getPopulasyon() - ((kaybeden.getPopulasyon()*gucOran) / 100));
		kaybeden.setPopulasyon(kayipPopulasyon);
		
		//Kazanan koloninin, kaybedenden ilgili oranda yemek almasi.
		int kazanilanYemek = (kazanan.getYemekStogu() + ((kaybeden.getYemekStogu()*gucOran) / 100));
		kazanan.setYemekStogu(kazanilanYemek);
		
		//Kaybedenin yemek stogunun azaltilmasi.
		int kaybedilenYemek = (kaybeden.getYemekStogu() - ((kaybeden.getYemekStogu()*gucOran) / 100));
		kaybeden.setYemekStogu(kaybedilenYemek);
		
		kazanan.setZafer(kazanan.getZafer() + 1); //Kazananin zafer sayisi arttirilmasi.
		kaybeden.setYenilgi(kaybeden.getYenilgi() + 1); //Kaybedenin yenilgi sayisi arttirilmasi.
	}
	
	private void koloniDurumYazdir(int tur) //Her turda yazdirilacak olan koloni degerleri.
	{
		System.out.println("\nTur Sayisi: " + tur); //Kacinci tur oldugu yazdirilmasi.
		System.out.printf("%-10s %-15s %-20s %-15s %-15s\n", "Koloni", "Populasyon", "Yemek Stogu", "Kazanma", "Kaybetme");
		for (int a = 0; a < koloniler.length; a++) {//Kolonilerin sembol, populasyon, yemekstok, zafer ve yenilgilerinin yazdirilmasi.
		    System.out.println(koloniler[a]);
		}
		System.out.println("\n------------------------------------------------------------------------");
	}
		
}
	
	
	
	
		

