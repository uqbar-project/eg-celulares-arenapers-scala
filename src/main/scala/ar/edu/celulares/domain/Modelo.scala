package ar.edu.celulares.domain

import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable
import org.uqbar.commons.utils.TransactionalAndObservable
import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.PersistentField

@TransactionalAndObservable
@PersistentClass
class Modelo extends Entity {

    private var _descripcion : String = _
    private var _costo : Float = _
    private var _requiereResumenCuenta : Boolean = _
  	  
  	@PersistentField def getDescripcion = _descripcion
  	def setDescripcion(value: String) = _descripcion = value 
	@PersistentField def getCosto() = _costo
	def setCosto(value: Float) = _costo = value
	@PersistentField def getRequiereResumenCuenta() : Boolean = _requiereResumenCuenta
	def setRequiereResumenCuenta(value: Boolean) = _requiereResumenCuenta = value
	
	def this(unaDescripcion: String, unCosto: Float, siRequiereResumenCuenta: Boolean) = {
  		this()
  		_descripcion = unaDescripcion
  		_costo = unCosto
  		_requiereResumenCuenta = siRequiereResumenCuenta 
  	}
  	
	def getDescripcionEntera() : String = {
		getDescripcion + " ($ " + getCosto + ")"
	}

	override def toString() : String = {
		getDescripcionEntera
	}

}