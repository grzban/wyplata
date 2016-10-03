public class SkladkaZUS {

	private double spoleczne;
	private double zdrowotne;
	private double zdrowotneDoOdliczenia;
	private double funduszPracy;
	
	public SkladkaZUS(){
		
	}
	
	public SkladkaZUS(double spoleczene, double funduszPracy, double zdrowotne, double zdrowotneDoOdliczenia) {
		this.spoleczne = spoleczene;
		this.funduszPracy = funduszPracy;
		this.zdrowotne = zdrowotne;
		this.zdrowotneDoOdliczenia = zdrowotneDoOdliczenia;
	}
	
	public double sumaSkladekZus(){
		double result;
		
		result = getSpoleczne() + getFunduszPracy()+getZdrowotne();
	
		return result;
	}
	
	
	
	public double getSpoleczne() {
		return spoleczne;
	}

	public void setSpoleczne(double spoleczne) {
		this.spoleczne = spoleczne;
	}

	
	public double getFunduszPracy() {
		return funduszPracy;
	}

	public void setFunduszPracy(double funduszPracy) {
		this.funduszPracy = funduszPracy;
	}
		
	public double getZdrowotne() {
		return zdrowotne;
	}

	public void setZdrowotne(double zdrowotne) {
		this.zdrowotne = zdrowotne;
	}

	public double getZdrowotneDoOdliczenia() {
		return zdrowotneDoOdliczenia;
	}

	public void setZdrowotneDoOdliczenia(double zdrowotneDoOdliczenia) {
		this.zdrowotneDoOdliczenia = zdrowotneDoOdliczenia;
	}
	
}
