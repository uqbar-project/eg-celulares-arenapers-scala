package ar.edu.celulares.domain

import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable
import org.uqbar.commons.utils.TransactionalAndObservable
import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.PersistentField

@TransactionalAndObservable
@PersistentClass
class Modelo extends Entity {

	@PersistentField var id : Integer = _
  	@PersistentField var descripcion : String = _
	@PersistentField var costo : BigDecimal = _
	@PersistentField var requiereResumenCuenta : Boolean = _

	def this(_descripcion: String, _costo: BigDecimal, _requiereResumenCuenta: Boolean) = {
  		this()
  		descripcion = _descripcion
  		costo = _costo
  		requiereResumenCuenta = _requiereResumenCuenta 
  	}
  	
	def getDescripcionEntera() : String = {
		descripcion + " ($ " + costo + ")"
	}

	override def toString() : String = {
		getDescripcionEntera
	}

}