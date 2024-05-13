/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinifta koloninin kullanacagi uretim islemi tanimlanmistir.
* Uretim sinifindan kalitim alan AUretim sinifi.
* </p>
*/

package Uretim;

import java.util.Random;
import Koloni.Koloni;

public class AUretim extends Uretim 
{
	//Koloni populasyon ve yemek stok degerine gore koloniye ozgu uretim degeri AUretim icin.
	@Override 
	public int Uret(Koloni k) {
		Random rnd = new Random();
		int rastgeleDeger = rnd.nextInt(11);
		int sonuc = (k.getPopulasyon() * k.getYemekStogu() * rastgeleDeger)%11;
		return sonuc;
	}
}
