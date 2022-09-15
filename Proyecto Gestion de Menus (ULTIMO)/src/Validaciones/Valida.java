package Validaciones;

public class Valida {
	
	public static void ValidaFechas(int a, int m, int d) throws Exception {
		switch (m) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				if (d<1 || d>31)
					throw new Exception("La fecha ingresada no es correcta");
				break;
			case 2:
				if (d<1 || d>28)
					throw new Exception("La fecha ingresada no es correcta");
				break;
			default:
				if (d<1 || d>30)
					throw new Exception("La fecha ingresada no es correcta");
				break;
		}
	}
/**
 * Valida que la fecha ingresada sea correcta
 * @param a Año
 * @param m Mes
 * @param d Dia
 * @throws Exception Si la fecha ingresada no es correcta
 */
	public static void ValidaString(String s) throws Exception {
		int len = s.length();
		if (len==0)
			throw new Exception("Por favor, complete todos los campos");
		else {
			s = s.toUpperCase();
			char c;
			for (len = s.length()-1; len>=0; len--) {
				c = s.charAt(len);
				if (!((c>='A' && c<='Z') || c==' ' || c=='Ñ' || c=='Á' || c=='É' || c=='Í' || c=='Ó' || c=='Ú'))
					throw new Exception("Los campos solo pueden contener letras");
			}
		}
	}
/**
 * Valida que un string no este vacio y contenga un entero positivo
 * @param s String a validar
 * @throws Exception Si esta vacio o tiene un caracter que no es numero
 */
	public static void ValidaNumeros(String s) throws Exception {
		int len = s.length();
		if (len==0)
			throw new Exception("Por favor, complete todos los campos");
		else
			for (len = s.length()-1; len>=0; len--)
				if (s.charAt(len)<'0' || s.charAt(len)>'9')
					throw new Exception("Los precios solo pueden contener números enteros, "+"y no negativos.");
	}

/**
 * Valida que un Entero sea positivo
 * @param numero Numero a validar
 * @throws Exception Si el numero es negativo
 */
	public static void ValidaPositivo (int numero) throws Exception{
		if (numero<0)
			throw new Exception ("Por favor, ingrese un numero positivo");
	}
/**
 * Valida que un Double sea positivo
 * @param numero Numero a validar
 * @throws Exception Si el numero es negativo
 */
	public static void ValidaPositivo (double numero) throws Exception{
		if (numero<0)
			throw new Exception ("Por favor, ingrese un numero positivo");
	}
}