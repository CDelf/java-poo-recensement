package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.exceptions.RecensementException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws NumberFormatException, RecensementException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		int min, max = 0;
		// Si l'entrée utilisateur n'est bien constitué que de chiffres, convertir
		if(NumberUtils.isCreatable(saisieMin) && NumberUtils.isCreatable(saisieMax)) {
			min = Integer.parseInt(saisieMin) * 1000;
			max = Integer.parseInt(saisieMax) * 1000;
		} else {
			// sinon jète l'exception
			throw new NumberFormatException("Les populations minimum et maximum doivent être des chiffres, réessayez.");
		}

		// Si min > max, jète une exception
		if(min <0 || max <0 || min > max) {
			throw new RecensementException("La population minimum ne peut être supérieure à la population maximum, recommencez.");
		}

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			} else {
				throw new RecensementException("Le département n'a pas été trouvé, vérifiez votre code et réessayez.");
			}
		}
	}

}
