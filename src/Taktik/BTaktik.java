/**
*
* @author Melih Onbasi - melih.onbasi@ogr.sakarya.edu.tr
* @since 25 ‎Mayis ‎2023 ‎Persembe
* <p>
* Bu sinifta koloninin kullanacagi taktik tanimlanmistir.
* Taktik sinifindan kalitim alan BTaktik sinifi.
* </p>
*/

package Taktik;

import java.util.Random;

import Koloni.Koloni;

public class BTaktik extends Taktik 
{
	//Koloni populasyon ve yemek stok degerine gore koloniye ozgu savas degeri uretimi BTaktik icin.
	@Override 
	public int Savas(Koloni k)
	{
		Random rnd = new Random();
		int rastgeleDeger = rnd.nextInt(1001);
		int sonuc = ((k.getYemekStogu() + k.getYemekStogu()*k.getPopulasyon())*rastgeleDeger)%1001;
		return sonuc;
	}
}
