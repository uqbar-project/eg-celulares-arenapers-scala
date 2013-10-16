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

	def create(descripcion: String, costo: Float, requiereResumenCuenta: Boolean) : Unit = {
		var modelo = new Modelo()
		modelo.descripcion = descripcion
		modelo.costo = costo
		modelo.requiereResumenCuenta = requiereResumenCuenta
		this.create(modelo)
	}

	def modelos: java.util.List[Modelo] = allInstances
	
	def get(descripcion: String) : Modelo =
		modelos.find(modelo => modelo.descripcion.equalsIgnoreCase(descripcion)).getOrElse(null) // Acá hay que pensar algo.

	def find(descripcion: String) : List[Modelo] = {
		var example = new Modelo()
		example.descripcion = descripcion
		this.searchByExample(example).asScala.toList		
	}

	override def getEntityType = classOf[Modelo]

	override def createExample = new Modelo

	def getCriterio(example: Modelo) = null

	def createIfNotExists(modeloCelular: Modelo) = {
		println("***********Modelos: " + modelos)
		if (this.find(modeloCelular.descripcion).isEmpty) {
			this.create(modeloCelular)
		}
	}

}