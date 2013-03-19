package net.zanzapla.contafrancobolli;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		//System.out.println("Inseririsci l'importo da suddividere");
		//double importoIniziale = TextIO.getDouble();

		int importo = 400;//(int) (importoIniziale * 100);
		

		ContaFrancobolli contaFb = new ContaFrancobolli(importo);
		contaFb.setValoreFrancobolli();
		contaFb.addNuovoFrancobollo(3.50);
		contaFb.addNuovoFrancobollo(342);
		contaFb.removeUnFrancobollo(140);
		contaFb.removeUnFrancobollo(0.75);
		int[] test = contaFb.getValoreFrancobolli();
		System.out.println(Arrays.toString(test));;
	}

}
