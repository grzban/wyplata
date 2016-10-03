public class DaneDoWyliczen {

	private double kwotaZaGodz;
	private int iloscDyzurow12;
	private int iloscDyzurow24;
	private int iloscGodzin;
	
	private SkladkaZUS zus;
	
	public DaneDoWyliczen(SkladkaZUS zus) {
		this.zus = zus;
	}

	public int sumaGodzWMiesiacu(){
		int result = 24 * getIloscDyzurow24() + 12 * getIloscDyzurow12();
		
		return result;
	}
		
	public double obliczKwotaNetto(){
		double result = sumaGodzWMiesiacu() * kwotaZaGodz;
		return result;
	}
			
	public double obliczKwotaNetto1(){
		double result = getIloscGodzin() * kwotaZaGodz;
		return result;
	}
	public double obliczKwoteBrutto1(){
		double result = obliczKwotaNetto1() * 1.23;
		return result;
	}
	
	public double obliczVAT1(){
		double result = obliczKwotaNetto1() * 0.23;
		return result;
	}
	public double obliczPit1(){
		double result = (obliczKwotaNetto1()  - zus.getSpoleczne())* 0.18 - zus.getZdrowotneDoOdliczenia();
		return result;
	}
	public double obliczKwoteNaReke1(){
		double result = obliczKwotaNetto1() - zus.sumaSkladekZus() - obliczPit1();
		return result;
	}
	public double obliczKwoteBrutto(){
		double result = obliczKwotaNetto() * 1.23;
		return result;
	}
	
	public double obliczVAT(){
		double result = obliczKwotaNetto() * 0.23;
		return result;
	}
	
	public double obliczPit(){
		double result = (obliczKwotaNetto()  - zus.getSpoleczne())* 0.18 - zus.getZdrowotneDoOdliczenia();
		return result;
	}

	public double obliczKwoteNaReke(){
		double result = obliczKwotaNetto() - zus.sumaSkladekZus() - obliczPit();
		return result;
	}
	
	public int getIloscGodzin() {
		return iloscGodzin;
	}

	public void setIloscGodzin(int iloscGodzin) {
		this.iloscGodzin = iloscGodzin;
	}

	public double getKwotaZaGodz() {
		return kwotaZaGodz;
	}

	public void setKwotaZaGodz(double kwotaZaGodz) {
		this.kwotaZaGodz = kwotaZaGodz;
	}

	public int getIloscDyzurow12() {
		return iloscDyzurow12;
	}

	public void setIloscDyzurow12(int iloscDyzurow12) {
		this.iloscDyzurow12 = iloscDyzurow12;
	}

	public int getIloscDyzurow24() {
		return iloscDyzurow24;
	}

	public void setIloscDyzurow24(int iloscDyzurow24) {
		this.iloscDyzurow24 = iloscDyzurow24;
	}

	
	
	
}
