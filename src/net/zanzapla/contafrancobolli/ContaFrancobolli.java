package net.zanzapla.contafrancobolli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public class ContaFrancobolli {

	private static List<Integer> change;
	private static int[] qualiFrancobolli; //array per il numero di francobolli di quel taglio necessari per arrivare all'importo
	private static int[] valoreFrancobolli; // il valore nominale dei francobolli disponibili
	private int importo; //l'importo richiesto 

	/*
	 * costruttore della classe
	 */
	public ContaFrancobolli(int amount) {
		this.importo = amount;
	}

	/*
	 * Di seguito i metodi per gestire l'array valoreFrancobolli
	 */
	/**
	 * @return valoreFrancobolli
	 */
	public int[] getValoreFrancobolli() {
		return valoreFrancobolli;
	}

	/**
	 * @param availableCoins
	 *            the availableCoins to set
	 */
	public void setValoreFrancobolli(int[] ListaFrancobolli) {
		valoreFrancobolli = ListaFrancobolli;
		qualiFrancobolli = new int[valoreFrancobolli.length];
	}

	public void setValoreFrancobolli() {
		int[] listaFb = { 330, 300, 200, 140, 85, 75, 50, 10, 5 };
		setValoreFrancobolli(listaFb);
	}

	/**
	 * 
	 * @param d
	 *            il nuovo valore del francobollo da aggiungere all'array dei
	 *            valori
	 *            <p>
	 *            può essere sia int che double, se è int deve essere già
	 *            moltiplicato per 100
	 *            <p>
	 *            ad esempio 3.42 deve essere inserito come 342
	 */
	public void addNuovoFrancobollo(int valore) {
		// aggiungo il valore alla fine dell'array
		valoreFrancobolli = ArrayUtils.add(valoreFrancobolli, valore);
		// riordino l'array per non rompere l'algoritmo
		ordinaValoreFrancobolli();
	}

	public void addNuovoFrancobollo(double d) {
		int val = (int) (d * 100);
		addNuovoFrancobollo(val);
	}

	/**
	 * riordina in ordine decrescente i valori dei francobolli nell'array
	 * valoriFrancobolli;
	 */
	private static void ordinaValoreFrancobolli() {
		// uso sort() per riordinare in ordine ascendente
		Arrays.sort(valoreFrancobolli);
		// metto l'array in ordine decrescente
		ArrayUtils.reverse(valoreFrancobolli);
	}

	/**
	 * 
	 * @param valore
	 *            il valore del francobollo da eliminare moltiplicato per 100
	 *            <p>
	 *            ad esempio 3.30€ diventa 330
	 * 
	 */
	public void removeUnFrancobollo(int valore) {
		valoreFrancobolli = ArrayUtils.removeElement(valoreFrancobolli, valore);
	}

	/**
	 * 
	 * @param valore
	 *            il valore del francobollo da eliminare
	 */
	public void removeUnFrancobollo(double valore) {
		int tmp = (int) (valore * 100);
		removeUnFrancobollo(tmp);
	}

	public int getQualiFrancobolliLength() {
		return qualiFrancobolli.length;
	}

	/*
	 * L'algoritmo che trova il minor numero di francobolli necessari
	 */
	/**
	 * @return the change
	 */
	public static List<Integer> getChange() {
		return change;
	}

	/**
	 * @param change
	 *            the change to set
	 */
	public void setChange(List<Integer> change) {
		ContaFrancobolli.change = change;
	}

	public List<Integer> makeChange() {
		List<Integer> current = new ArrayList<Integer>();
		return makeChange(importo, 0, current);
	}

	public List<Integer> makeChange(int amount, int currentStamp,
			List<Integer> current) {

		if (amount == 0) { // if amount = zero, we are at the bottom of a
							// successful recursion
							// return current solution
			return current;
		} else if (amount < 0) { // check to see if we went too far
			// reject current solution, we went too far
			return null;

		} else if (currentStamp >= valoreFrancobolli.length) { // no more coin
																// to check
			// reject current solution, no more coin to check
			return null;
		} else {
			List<Integer> pathA = new ArrayList<Integer>(current);
			// go on skipping the currentStamp
			pathA = makeChange(amount, currentStamp + 1, pathA);

			List<Integer> pathB = new ArrayList<Integer>(current);
			// go on adding the currentStamp to the solution
			pathB.add(Integer.valueOf(valoreFrancobolli[currentStamp]));
			pathB = makeChange(amount - valoreFrancobolli[currentStamp],
					currentStamp, pathB);

			if (pathA == null && pathB == null) {
				return null;
			}
			if (pathA == null) {
				return pathB;
			}
			if (pathB == null) {
				return pathA;
			}

			return pathA.size() < pathB.size() ? pathA : pathB;
		}
	}

	/*
	 * 
	 */
	/**
	 * 
	 * @param i
	 *            la posizione nell'array
	 * @return il valore per quell'indice
	 */
	public int qualeFrancobollo(int i) {
		return qualiFrancobolli[i];
	}

	/**
	 * 
	 * @param change
	 *            una lista di valori di francobolli
	 */
	public void quantiFrancobolli() {
		if (getChange() != null) {
			int i = 0, j = 0;
			while (j < getChange().size()) {
				if (getChange().get(j) == valoreFrancobolli[i]) {
					qualiFrancobolli[i]++;
					j++;
				} else {
					i++;
				}
			}
		}
	}

	public int valoreFrancobollo(int i) {
		return valoreFrancobolli[i];
	}
}
