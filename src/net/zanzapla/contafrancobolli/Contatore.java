package net.zanzapla.contafrancobolli;

import net.zanzapla.contafrancobolli.ContaFrancobolli;

public class Contatore {
	
	public static void main(String[] args) {
		System.out.println("Inseririsci l'importo da suddividere");
		double importoIniziale = TextIO.getDouble();

		int importo = (int) (importoIniziale * 100);
		

		ContaFrancobolli contaFb = new ContaFrancobolli(importo);
		contaFb.setValoreFrancobolli();
		contaFb.setChange(contaFb.makeChange());


		System.out.println("la cifra inserita è: " + importoIniziale + "€");


		if (ContaFrancobolli.getChange() == null) {
			System.out.println("Nessuna soluzione trovata, controlla che la cifra inserita sia corretta");
		} else {
			contaFb.quantiFrancobolli();			
			System.out.println("di seguito sono elencati i tagli necessari");
			for (int i = 0; i < contaFb.getQualiFrancobolliLength(); i++) {
				if (contaFb.qualeFrancobollo(i) > 0) {
					System.out.println("Numero francobolli da "	+ (double) contaFb.valoreFrancobollo(i)/100 + " €: " + contaFb.qualeFrancobollo(i));
				}
			}
		}
	}

}
