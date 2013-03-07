package some.packet;

import java.util.ArrayList;
import java.util.List;

public class ContaFrancobolli {

	private static int[] valoreFrancobolli;
	private static int[] qualiFrancobolli;

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
	public static void setValoreFrancobolli(int[] ListaFrancobolli) {
		valoreFrancobolli = ListaFrancobolli;
		qualiFrancobolli = new int[valoreFrancobolli.length];
	}

	public static void setValoreFrancobolli() {
		valoreFrancobolli = new int[] { 330, 300, 200, 140, 85, 75, 50, 10, 5 };
		qualiFrancobolli = new int[valoreFrancobolli.length];
	}

	private int amount = 0;

	public ContaFrancobolli(int amount) {
		this.amount = amount;
	}

	public List<Integer> makeChange() {
		List<Integer> current = new ArrayList<Integer>();
		return makeChange(amount, 0, current);
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

	/**
	 * 
	 * @param change
	 *            una lista di valori di francobolli
	 */
	public static void quantiFrancobolli(List<Integer> change) {
		if (change != null) {
			int i = 0, j = 0;
			while (j < change.size()) {
				if (change.get(j) == valoreFrancobolli[i]) {
					qualiFrancobolli[i]++;
					j++;
				} else {
					i++;
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Inseririsci l'importo da suddividere");
		double importoIniziale = TextIO.getDouble();

		int amount = (int) (importoIniziale * 100);

		setValoreFrancobolli();

		ContaFrancobolli f = new ContaFrancobolli(amount);
		List<Integer> change = f.makeChange();


		System.out.println("la cifra inserita è: " + importoIniziale + "€");


		if (change == null) {
			System.out.println("Nessuna soluzione trovata, controlla che la cifra inserita sia corretta");
		} else {
			quantiFrancobolli(change);			
			System.out.println("di seguito sono elencati i tagli necessari");
			for (int i = 0; i < qualiFrancobolli.length; i++) {
				if (qualiFrancobolli[i] > 0) {
					System.out.println("Numero francobolli da "	+ (double) valoreFrancobolli[i]/100 + " €: " + qualiFrancobolli[i]);
				}
			}
		}
	}
}
