/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinif koloninin kullanacagi savas taktigini barindirir.
* Taktik sinifindan kalitim alan ATaktik sinifi.
* </p>
*/

package Taktik;

import java.util.Random;

import Koloni.Koloni;

public class ATaktik extends Taktik
{
	//Koloni populasyon degerine gore koloniye ozgu savas degeri uretimi ATaktik icin.
	@Override 
	public int Savas(Koloni k) {
		Random rnd = new Random();
		int rastgeleDeger = rnd.nextInt(1001);
		int sonuc = (k.getPopulasyon() * rastgeleDeger)%1001;
		return sonuc;
	}
}
