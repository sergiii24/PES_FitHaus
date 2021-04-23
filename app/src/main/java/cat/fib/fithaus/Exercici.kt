package cat.fib.fithaus

/** Classe Exercici (data class)
 *
 *  Classe amb els elements d'un exercici a mostrar en el recyler view.
 *
 *  @author Oriol Prat.
 */
data class Exercici (val nom: String,
                     val duracio: Int,
                     val dificultat: String,
                     val imatge: String
                     )