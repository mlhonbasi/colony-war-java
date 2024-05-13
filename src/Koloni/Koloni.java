/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinifta koloni genel yapisiyla tanimlanmistir.
* Sinifin icerdigi uyeler ve metot tanimlamalari yapilmistir.
* </p>
*/

package Koloni;

import Taktik.*;
import Uretim.*;

public class Koloni //Koloni sınıfı
{
	//Koloni sinif uyeleri.
	private int yemekStogu;
	private int populasyon;
	private int zafer;
	private int yenilgi;
	private char sembol;
	private boolean canlilik;
	private Taktik taktik;
	private Uretim uretim;
	
	public Koloni(int populasyon) //Koloni sinifi kurucu metodu.
	{
		this.populasyon = populasyon;
		this.yemekStogu = populasyon * populasyon;
		this.zafer = 0;
		this.yenilgi = 0;
		this.canlilik = true;
		this.taktik = rastgeleTaktik();
		this.uretim = rastgeleUretim();
		this.sembol = ' ';
	}
	
	private Taktik rastgeleTaktik() //Koloni icin rastgele taktik secimi.
	{
		int sayi = (int)(Math.random()*2);
		
		if(sayi == 0) {
			return new ATaktik();
		}
		else {
			return new BTaktik();
		}
	}
	
	private Uretim rastgeleUretim() //Koloni icin rastgele uretim secimi.
	{
		int sayi = (int)(Math.random()*2);
		
		if(sayi == 0) {
			return new AUretim();
		}
		else {
			return new BUretim();
		}
	}
	
	@Override
	public String toString()
	{
		if(canlilik == true)
			return String.format("%3c %13d %18d %16d %15d", sembol, populasyon, yemekStogu, zafer, yenilgi);
		else
			return String.format("%3c %13s %18s %16s %15s", sembol, "  ---", "  ---", "  ---", "  ---");
	}
	
	//Getter ve setter metotlari...
	public int getPopulasyon() {
        return populasyon;
    }

    public void setPopulasyon(int populasyon) {
        this.populasyon = populasyon;
    }

    public int getYemekStogu() {
        return yemekStogu;
    }

    public void setYemekStogu(int yemekStogu) {
        this.yemekStogu = yemekStogu;
    }
    
    public int getZafer() {
    	return zafer;
    }
    
    public void setZafer(int zafer) {
    	this.zafer = zafer;
    }
    
    public int getYenilgi() {
    	return yenilgi;
    }
    
    public void setYenilgi(int yenilgi) {
    	this.yenilgi = yenilgi;
    }
    	
    public char getSembol() {
    	return sembol;
    }
    
    public void setSembol(char sembol) {
    	this.sembol = sembol;
    }
    
    public boolean getCanlilik() {
    	return canlilik;
    }
    
    public void setCanlilik(boolean canlilik) {
    	this.canlilik = canlilik;
    }
    
    public Taktik getTaktik() {
        return taktik;
    }

    public void setTaktik(Taktik taktik) {
        this.taktik = taktik;
    }

    public Uretim getUretim() {
        return uretim;
    }

    public void setUretim(Uretim uretim) {
        this.uretim = uretim;
    }
	
	
}
