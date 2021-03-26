package cat.fib.fithaus


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrearPerfilActivityTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<CrearPerfilActivity> = ActivityTestRule(CrearPerfilActivity::class.java)

    @Test
    fun insert_nom(){
        onView(withId(R.id.Nom)).perform(typeText("Paco"))
       //onView(withId(R.id.CrearPerfilButton)).perform(click())
    }

    @Test
    fun insert_primer_cognom() {
        onView(withId(R.id.PrimerCognom)).perform(typeText("Perez"))
    }

    @Test
    fun insert_segon_cognom() {
        onView(withId(R.id.SegonCognom)).perform(typeText("Fernandez"))
    }

    @Test
    fun insert_nom_usuari() {
        onView(withId(R.id.NomUsuari)).perform(typeText("paco2"))
    }

    @Test
    fun insert_correu_electronic() {
        onView(withId(R.id.CorreuElectronic)).perform(typeText("fit@fithaus.com"))
    }

    @Test
    fun insert_contrasenya() {
        onView(withId(R.id.Contrasenya)).perform(typeText("Hola1234?"))
    }

    @Test
    fun insert_sexe() {
        onView(withId(R.id.Sexe_Dona))
                .perform(click());

        onView(withId(R.id.Sexe_Dona))
                .check(matches(isChecked()));

        onView(withId(R.id.Sexe_Home))
                .check(matches((isNotChecked())));

        onView(withId(R.id.Sexe_Altre))
                .check(matches((isNotChecked())));
    }

    @Test
    fun insert_data_naixement() {
        onView(withId(R.id.DataNaixement)).perform(typeText("23/05/1992"))
    }

}