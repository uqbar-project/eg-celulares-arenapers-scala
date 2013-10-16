package ar.edu.celulares.home

import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.utils.Observable
import ar.edu.celulares.domain.Celular
import ar.edu.celulares.domain.Modelo
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import uqbar.arena.persistence.PersistentHome
import scala.collection.JavaConverters._

@Observable
object HomeModelos extends PersistentHome[Modelo] {

	def create(unaDescripcion: String, unCosto: Float, requiereResumenCuenta: Boolean) : Unit = {
		var modelo = new Modelo()
		modelo.setDescripcion(unaDescripcion)
		modelo.setRequiereResumenCuenta(requiereResumenCuenta)
		modelo.setCosto(unCosto)
		this.create(modelo)
	}

	def modelos: java.util.List[Modelo] = allInstances
	
	def get(descripcion: String) : Modelo =
		modelos.find(modelo => modelo.getDescripcion.equalsIgnoreCase(descripcion)).getOrElse(null) // Acá hay que pensar algo.

	override def getEntityType = classOf[Modelo]

	override def createExample = new Modelo

	def getCriterio(example: Modelo) = null

	def createIfNotExists(modeloCelular: Modelo) = {
		if (this.get(modeloCelular.getDescripcion) == null) {
			this.create(modeloCelular)
		}
	}

}