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

	@PersistentField var id : Integer = _
	@PersistentField var numero : Integer = _
	@PersistentField var nombre : String = _
	@Relation var modeloCelular : Modelo = _
	@PersistentField var recibeResumenCuenta : Boolean = false

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
		modeloCelular = unModeloCelular
		// fin comentario
		recibeResumenCuenta = unModeloCelular.requiereResumenCuenta
	}

	def setRecibeResumenCuenta(siRecibeResumenCuenta: Boolean) = {
		// idem modeloCelular
		recibeResumenCuenta = siRecibeResumenCuenta
		// fin comentario _ sobre variable
	}

	// ********************************************************
	// ** Validacion
	// ********************************************************
	/**
	 * Valida que el celular esté correctamente cargado
	 */
	def validar() : Unit = {
		if (numero == null) {
			throw new UserException("Debe ingresar número")
		}
		if (numero.intValue() <= this.MAX_NUMERO) {
			throw new UserException("El número debe ser mayor a " + this.MAX_NUMERO)
		}
		if (!this.ingresoNombre()) {
			throw new UserException("Debe ingresar nombre")
		}
		if (modeloCelular == null) {
			throw new UserException("Debe ingresar un modelo de celular")
		}
	}

	def ingresoNombre() : Boolean = (nombre != null) && (!nombre.trim().equals(""))

	// ********************************************************
	// ** Getters y setters
	// ********************************************************
	def getHabilitaResumenCuenta() : Boolean = !modeloCelular.requiereResumenCuenta

	// ********************************************************
	// ** Misceláneos
	// ********************************************************
	override def toString() : String = {
		var result = new StringBuffer()
		if (nombre != null) {
			result.append(nombre)  
		} else {
			result.append("Celular sin nombre")
		}
		if (modeloCelular != null) {
			result.append(" - " + modeloCelular)
		}
		if (numero != null) {
			result.append(" - " + numero)
		}
		if (recibeResumenCuenta) {
			result.append(" - recibe resumen")
		} else {
			result.append(" - no recibe resumen")
		}
		return result.toString()
	}

}