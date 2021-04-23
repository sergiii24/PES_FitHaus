package cat.fib.fithaus

/** Classe Classe (data class)
 *
 *  Classe amb els elements d'una classe a mostrar en el recyler view.
 *
 *  @author Oriol Prat.
 */
data class Classe (val nom: String,
                   val duracio: Int,
                   val dificultat: String,
                   val imatge: String
                   )