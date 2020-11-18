package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		if (this.estDansUnGarage()){
                    throw new Exception("La voiture doit sortir de son garage "
                            + "actuel avant de pouvoir rentrer dans un autre");
                }
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
                if (!this.estDansUnGarage()){
                    throw new Exception("La voiture doit être dans un garage"
                            + "pour pouvoir en sortir");
                }
		myStationnements.get(myStationnements.size()-1).terminer();
		// TODO: Implémenter cette méthode
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> s = new HashSet<>();
                for (Stationnement g : myStationnements){
                    s.add(g.getGarage());
                }
                return s;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// TODO: Implémenter cette méthode
                int x = myStationnements.size();
                return (x != 0 && myStationnements.get(x-1).estEnCours());
		// Vrai si le dernier stationnement est en cours
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
                String res = "";
                for (Stationnement g : myStationnements){
                    String a = "";
                    if (!res.trim().contains(g.getGarage().toString())){
                        a = g.getGarage().toString()+": \n";
                        for (Stationnement f : myStationnements){
                            if (f.getGarage()==g.getGarage()){
                                a += f.toString();
                            }
                        }
                        res += a+"\n";
                    }
                }
                out.println(res);
	}

}
