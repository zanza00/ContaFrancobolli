package some.packet;

import java.util.ArrayList;
import java.util.List;

public class ContaFrancobolli {

	private static List<Integer> change;
	private static int[] qualiFrancobolli;
	private static int[] valoreFrancobolli;
	
	/**
	 * @return the change
	 */
	public static List<Integer> getChange() {
		return change;
	}
	/**
	 * @param availableCoins
	 *            the availableCoins to set
	 */
	public static void setValoreFrancobolli(int[] ListaFrancobolli) {
		valoreFrancobolli = ListaFrancobolli;
		qualiFrancobolli = new int[valoreFrancobolli.length];
	}
	
	/**
	 * 
	 * @param francobollo il nuovo valore del francobollo da aggiungere all'array dei valori 
	 */
	public static void addNuovoFrancobollo(int francobollo) {
		int [] tempArray = new int[valoreFrancobolli.length+1];
		// copio i valori dall'array orginale a quello temporaneo 
		System.arraycopy(valoreFrancobolli, 0, tempArray, 0, valoreFrancobolli.length);
		//aggiungo i valori all'array temporaneo
		tempArray[valoreFrancobolli.length+1]= francobollo;
		// faccio puntare valoreFrancobolli all'array temporaneo
		valoreFrancobolli = tempArray;
		//riordino l'array per non rompere l'algoritmo 
		ordinaValoreFrancobolli();
	}
	
	/**
	 * riordina in ordine decrescente i valori dei francobolli nell'array valoriFrancobolli;
	 */
	private static void ordinaValoreFrancobolli(){
		//TODO fare l'algoritmo migliore per riordinare l'array, da considerare che l'unico valore non ordinato è quello all'ultimo posto
	}
	
	private int amount = 0;

	public ContaFrancobolli(int amount) {
		this.amount = amount;
	}
	public int getQualiFrancobolliLength(){
		return qualiFrancobolli.length;
	}
	
	/**
	 * @return valoreFrancobolli
	 */
	public int[] getValoreFrancobolli() {
		return valoreFrancobolli;
	}

	public List<Integer> makeChange() {
		List<Integer> current = new ArrayList<Integer>();
		return makeChange(amount, 0, current);
	}

	public List<Integer> makeChange(int amount, int currentStamp,
			List<Integer> current) {

		if (amount == 0) {  // if amount = zero, we are at the bottom of a
							// successful recursion
			                // return current solution
			return current;
		} else if (amount < 0) { // check to see if we went too far
 			// reject current solution, we went too far
			return null; 

		} else if (currentStamp >= valoreFrancobolli.length) { // no more coin to check
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

	/**
	 * @param change the change to set
	 */
	public void setChange(List<Integer> change) {
		ContaFrancobolli.change = change;
	}

	public void setValoreFrancobolli() {
		int[] listaFb = { 330, 300, 200, 140, 85, 75, 50, 10, 5 };
		setValoreFrancobolli(listaFb);
	}

	public int valoreFrancobollo(int i){
		return valoreFrancobolli[i];
	}
}
