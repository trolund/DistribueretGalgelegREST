package brugerautorisation.transport.rmi;

import java.rmi.Naming;

public class Brugeradminklient {
	public static void main(String[] arg) throws Exception {

//		Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://localhost/brugeradmin");
	//	Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
/*
		Bruger off = ba.hentBrugerOffentligt("s123456");
		System.out.println("Fik offentlige data " + Diverse.toString(off));

    //ba.sendGlemtAdgangskodeEmail("s123456", "Dette er en test, husk at skifte kode");
		//ba.ændrAdgangskode("s123456", "kode1xyz", "kode1xyz");
		Bruger b = ba.hentBruger("s123456", "kode1xyz");
		System.out.println("Fik bruger " + b);
		System.out.println("med data " + Diverse.toString(b));
		// ba.sendEmail("jacno", "xxx", "Hurra det virker!", "Jeg er så glad");

		Object ekstraFelt = ba.getEkstraFelt("s123456", "kode1xyz", "hobby");
		System.out.println("Brugerens hobby er: " + ekstraFelt);

		ba.setEkstraFelt("s123456", "kode1xyz", "hobby", "Tennis og programmering"); // Skriv noget andet her

		String webside = (String) ba.getEkstraFelt("s123456", "kode1xyz", "webside");
		System.out.println("Brugerens webside er: " + webside);
*/

/*
		Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        Bruger off = ba.hentBrugerOffentligt("s161791");
        System.out.println(off);
		//ba.ændrAdgangskode("s161791", "kodeufudaa", "kode123");
		System.out.println(ba.hentBruger("s161791", "kode123"));
*/
	}
}
