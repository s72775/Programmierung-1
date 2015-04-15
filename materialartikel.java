import java.io.*;

class Materialartikel {
	private String Artikelname;
	private int Lagerbestand;
//bisher unbenutzt:
	private int Mengeneinheit;
	private int Packungsgroesse;
	private int Mindeststueckzahl;
	private int Bestellmenge;
	private String Hersteller;
	private String Haendler;

	public Materialartikel()
	{
	}
	public Materialartikel(String Artikelname)
	{
		this.Artikelname = Artikelname;
	}

	/*  [gs]etter  */

	//Artikelname
	public String getArtikelname()
	{
		return this.Artikelname;
	}
	public void setArtikelname(String Artikelname)
	{
		this.Artikelname = Artikelname;
	}
	//Hersteller
	public String getHersteller()
	{
		return this.Hersteller;
	}
	public void setHersteller(String Hersteller)
	{
		this.Hersteller = Hersteller;
	}
	//Haendler
	public String getHaendler()
	{
		return this.Haendler;
	}
	public void setHaendler(String Haendler)
	{
		this.Haendler = Haendler;
	}
	//Mengeneinheit
	public int getMengeneinheit()
	{
		return this.Mengeneinheit;
	}
	public void setMengeneinheit(int Anzahl)
	{
		this.Mengeneinheit = Anzahl;
	}
	//Packungsgroesse
	public int getPackungsgroesse()
	{
		return this.Packungsgroesse;
	}
	public void setPackungsgroesse(int Anzahl)
	{
		this.Packungsgroesse = Anzahl;
	}
	//Mindeststueckzahl
	public int getMindeststueckzahl()
	{
		return this.Mindeststueckzahl;
	}
	public void setMindeststueckzahl(int Anzahl)
	{
		this.Mindeststueckzahl = Anzahl;
	}
	//Bestellmenge
	public int getBestellmenge()
	{
		return this.Bestellmenge;
	}
	public void setBestellmenge(int Anzahl)
	{
		this.Bestellmenge = Anzahl;
	}
	//Lagerbestand
	public int getLagerbestand()
	{
		return this.Lagerbestand;
	}
	public void setLagerbestand(int Anzahl)
	{
		this.Lagerbestand = Anzahl;
	}

	/*  ...  */

	public void Entnahme(int Anzahl) throws Exception
	{
		if( getLagerbestand() >= Anzahl ){
			this.Lagerbestand -= Anzahl;
		}else{
			throw new LagerLeerException("Lager leer");
		}
	}

	public String toString()
	{
		return "Artikel: "+this.Artikelname+", Bestand: "+this.Lagerbestand;
	}

	/*  ...  */

	public static void main(String args[]) throws Exception
	{
		//falls man mal was nur so zum Testen ausgeben mag
		boolean debug=false;
		
		Materialartikel x =  new Materialartikel();
		
		if(args.length > 0){
			//umständliche Schleife weil ggf. mehr Eigenschaften hinzu kommen und so die Verzweigung über die Anzahl erledigt ist
			for(int i=0; i<args.length; i++) {

				switch(i) {
					case 0:
						x.setArtikelname(args[i]);
						break;
					case 1:
						x.setLagerbestand(Integer.parseInt(args[i]));
						break;
					case 2:	//hidden feature, debug mit Argument 2 aktivieren 
						debug=true;
						break;
					default:
				}

			}

		}else{

			System.out.println("Sie haben keine Argumente übergeben!\nBitte übergeben Sie als nulltes den Artikelnamen und als erstes die Anzahl.");

		}

		System.out.println("\n\n== Bestand == \n\n" + x/*.toString()*/);

		//hält die Eingabeschleife am Leben
		boolean run = true;

		while(run){

			System.out.print("\n== Funktionen ==\n\n1 - Materialentnahme\n2 - Nachbestellung\n9 - Beenden\n\n");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int auswahl=0;
			while(auswahl==0) {
				try {
					System.out.println("Eingabe: ");
					auswahl = Integer.parseInt(br.readLine());
				}
				catch(Exception NumberFormatException)
				{
					System.out.println("Ne, das will keiner wissen!");
				}
			}
			int delta=0;

			switch(auswahl){
				case 1:
					while(delta <= 0) {
						System.out.print("Wieviel? Anzahl in Ziffern: ");
						try {
							delta = Integer.parseInt(br.readLine());
						} catch(Exception NumberFormatException){
							System.out.println("Das war jetzt nicht nett, benimm dich!");
						}
					}
					try{
						try{
							x.Entnahme(delta);
						}
						catch(Exception LagerLeerException) {
							System.out.println("Fehler: Die Anzahl kann nicht bedient werden!");
						}
					}
					catch(Exception NumberFormatException)
					{
						System.out.println("Fehler: Die Zahl ist komisch!");
					}

					break;
				case 2:
					while(delta <= 0) {
						System.out.print("Wieviel? Anzahl in Ziffern: ");
						try {
							delta = Integer.parseInt(br.readLine());
						} catch(Exception NumberFormatException){
							System.out.println("Das war jetzt nicht nett, benimm dich!");
						}
					}
					x.setLagerbestand(x.getLagerbestand() + delta);
					break;
				case 9:
					run = false;
					break;
				default:
					System.out.println("Unerwartete Eingabe!");
			}

			System.out.println("\n"+x/*.toString()*/);
		}

	}

}
