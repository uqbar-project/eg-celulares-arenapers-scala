package ar.edu.celulares.runnable

import ar.edu.celulares.ui.BuscarCelularesWindow
import org.uqbar.arena.Application
import uqbar.arena.persistence.Configuration
import ar.edu.celulares.home.HomeModelos
import ar.edu.celulares.domain.Modelo

object CelularApplication extends Application with App {
	
	override def createMainWindow() = new BuscarCelularesWindow(this)
	
	// Arena-persistence levanta los objetos persistidos
	Configuration.configure()
	// Fin Arena-persistence
	// Creamos modelos de la base la primera vez
	HomeModelos.createIfNotExists(new Modelo("ARENA PERSISTENCE 72", 100f, false))
	HomeModelos.createIfNotExists(new Modelo("NOKIA ASHA 501", 700f, true))
	HomeModelos.createIfNotExists(new Modelo("LG OPTIMUS L5 II", 920f, false))
	HomeModelos.createIfNotExists(new Modelo("LG OPTIMUS L3 II", 450f, true))
	HomeModelos.createIfNotExists(new Modelo("NOKIA LUMIA 625", 350f, true))
	HomeModelos.createIfNotExists(new Modelo("MOTOROLA RAZR V3", 350f, false))
	// Fin Creamos modelos de la base
	start()
	
}