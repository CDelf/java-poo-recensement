package fr.diginamic.recensement.services.exceptions;

/**
 * Exception créée pour gérer les erreurs d'entrées utilisateur dans la recherche de recensement
 */
public class RecensementException extends Exception {
    /**
     * Constructeur
     * @param message message expliquant la cause du déclenchement de l'exception
     */
    public RecensementException(String message) {
        super(message);
    }
}
