/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinifta Koloni yonetimi ile ilgili metotlar tanimlanmistir.
* Kolonilere sembolun atanmasi durumlarinin kontrolu ve guncellenmesi metotlarini icerir.
* </p>
*/

package Koloni;

public class KoloniYonetim 
{
	public static void koloniGuncelle(Koloni[] koloniler) //Tura ozgu koloni guncellemeleri
	{
		for(int i = 0; i < koloniler.length; i++)
		{
			if(koloniler[i].getCanlilik() == true)
			{
				//Koloni yemek stogunun uretim degeri katilarak guncellenmesi;
				int guncellenenYemek = koloniler[i].getYemekStogu() + koloniler[i].getUretim().Uret(koloniler[i]);
				koloniler[i].setYemekStogu(guncellenenYemek);
				
				//Koloni populasyonunun %20 oraninda arttirilmasi.
				int guncellenenPopulasyon = koloniler[i].getPopulasyon() + ((koloniler[i].getPopulasyon() * 20) / 100);
				koloniler[i].setPopulasyon(guncellenenPopulasyon);
				
				//Koloni yemek stogunun (guncel populasyon * 2) degerinde tuketilmesi ve guncellenmesi. 
				int tuketilenYemek = koloniler[i].getYemekStogu() - (koloniler[i].getPopulasyon() * 2);
				koloniler[i].setYemekStogu(tuketilenYemek);
			}
		}
	}
	public static boolean tekKoloniKontrol(Koloni[] koloniler) //Hayatta tek koloni kalip kalmadiginin kontrolu.
	{
		int sayi = 0;
		
		for(int i = 0; i < koloniler.length; i++)
		{
			if(koloniler[i].getCanlilik() == false) //Hayatta olmayan kolonilerin sayisinin alma.
			{
				sayi++;
			}
		}
		if(sayi == koloniler.length - 1) //Tek koloni kalmis ise true diger durumlarda false dondurur.
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void koloniKontrol(Koloni k1, Koloni k2) //Savas sonrasi kolonilerin canlilik durumu kontrolu.
	{	
		if(k1.getPopulasyon() <= 0 || k1.getYemekStogu() <= 0) { //Populasyon veya yemek stogu sıfırın altina dusen canliligini kaybeder.
			k1.setCanlilik(false);
		}
		if(k2.getPopulasyon() <= 0 || k2.getYemekStogu() <= 0){
			k2.setCanlilik(false);
		}			
	}
	
	public static void koloniSembolAta(Koloni[] koloni) //Kolonilere rastgele sembol atanmasi.
	{
		char[] karakterler = 
			{
				'¶', '§','1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F',
			    'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^',
			    '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','w', 'x', 'y', 'z', '{', '|', '}', '~', '⌂',
			    'Ç', 'ü', 'é', 'â', 'ä', 'à', 'å', 'ç', 'ê', 'ë', 'è', 'ï', 'î', 'ı', 'Ä', 'Å', 'É', 'æ', 'Æ', 'ô',
			    'ö', 'ò', 'û', 'ù', 'İ', 'Ö', 'Ü', 'ø', '£', 'Ø', 'Ş', 'ş', 'á', 'í', 'ó', 'ú', 'ñ', 'Ñ', 'Ğ', 'ğ', '¿',
			    '®', '¬', '½', '¼', '¡', '«', '»', '░', '▒', '▓', '│', '┤', 'Á', 'Â', 'À', '©', '╣', '║', '╗', '╝', '¢', '¥',
			    '┐', '└', '┴', '┬', '├', '─', '┼', 'ã', 'Ã', '╚', '╔', '╩', '╦', '╠', '═', '╬', '¤', 'º', 'ª', 'Ê', 'Ë', 'È',
			    'Í', 'Î', 'Ï', '┘', '┌', '█', '▄', '¦', 'Ì', '▀', 'Ó', 'ß', 'Ô', 'Ò', 'õ', 'Õ', 'µ', '×', 'Ú', 'Û', 'Ù',
			    'ì', 'ÿ', '¯', '´', '­', '±', '', '¾', '¶', '§', '÷', '¸', '°', '¨', '·', '¹', '³', '²', '■'
			};
		
		int[] kullanilmisSayilar = new int[karakterler.length]; //Verilmis sembolleri belirlemek icin int dizisi.
			
		for(int i = 0; i < koloni.length; i++)
		{
			int tekilSembol;
			do
	        {
	            tekilSembol = (int)(Math.random()*karakterler.length); //Rastgele uretime göre sembolun koloniye verilmesi.
	            koloni[i].setSembol(karakterler[tekilSembol]);                              
	        } while (kullanilmisSayilar[tekilSembol] == 1);
	        kullanilmisSayilar[tekilSembol] = 1;   //Kullanilan indeksi isaretleme.
		}
	}

}
