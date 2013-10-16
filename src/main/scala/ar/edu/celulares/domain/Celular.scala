package ar.edu.celulares.domain

import org.uqbar.commons.model.ObservableUtils
import org.uqbar.commons.model.UserException
import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable
import org.uqbar.commons.utils.Transactional
import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.PersistentField
import uqbar.arena.persistence.annotations.Relation

@Transactional
@Observable
@PersistentClass
class Celular extends Entity {

  	final var MAX_NUMERO = 100000

	var _numero : Integer = _
	@PersistentField def getNumero = _numero
	def setNumero(value: Integer) = _numero = value
  	var _nombre : String = _
	@PersistentField def getNombre = _nombre
  	def setNombre(value: String) = _nombre = value
  	var _modeloCelular : Modelo = _
  	@Relation def getModeloCelular = _modeloCelular
	var _recibeResumenCuenta : Boolean = false
	@PersistentField def getRecibeResumenCuenta = _recibeResumenCuenta

	// ********************************************************
	// ** Getters y setters
	// Los getters y setters por default no se deben codificar
	// peeeeeero...
	// en nuestro ejemplo tenemos que modificar la propiedad
	// recibeResumenCuenta en base al modelo de celular seleccionado
	// ********************************************************

	def setModeloCelular(unModeloCelular: Modelo) = {
		// para no entrar en loop infinito, en el setter debemos
		// utilizar _ para indicar que nos referimos a la variable
		// que genera xtend para compilar en Java
		_modeloCelular = unModeloCelular
		// fin comentario
		_recibeResumenCuenta = unModeloCelular.getRequiereResumenCuenta
	}

	def setRecibeResumenCuenta(siRecibeResumenCuenta: Boolean) = {
		// idem modeloCelular
		_recibeResumenCuenta = siRecibeResumenCuenta
		// fin comentario _ sobre variable
	}

	// ********************************************************
	// ** Validacion
	// ********************************************************
	/**
	 * Valida que el celular esté correctamente cargado
	 */
	def validar() : Unit = {
		if (_numero == null) {
			throw new UserException("Debe ingresar número")
		}
		if (_numero.intValue() <= this.MAX_NUMERO) {
			throw new UserException("El número debe ser mayor a " + this.MAX_NUMERO)
		}
		if (!this.ingresoNombre()) {
			throw new UserException("Debe ingresar nombre")
		}
		if (_modeloCelular == null) {
			throw new UserException("Debe ingresar un modelo de celular")
		}
	}

	def ingresoNombre() : Boolean = (_nombre != null) && (!_nombre.trim().equals(""))

	// ********************************************************
	// ** Getters y setters
	// ********************************************************
	def getHabilitaResumenCuenta() : Boolean = !_modeloCelular.getRequiereResumenCuenta

	// ********************************************************
	// ** Misceláneos
	// ********************************************************
	override def toString() : String = {
		var result = new StringBuffer()
		if (_nombre != null) {
			result.append(_nombre)  
		} else {
			result.append("Celular sin nombre")
		}
		if (_modeloCelular != null) {
			result.append(" - " + _modeloCelular)
		}
		if (_numero != null) {
			result.append(" - " + _numero)
		}
		if (_recibeResumenCuenta) {
			result.append(" - recibe resumen")
		} else {
			result.append(" - no recibe resumen")
		}
		return result.toString()
	}

}