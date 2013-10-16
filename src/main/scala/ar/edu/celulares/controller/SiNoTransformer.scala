package ar.edu.celulares.controller

import com.uqbar.commons.collections.Transformer
import ar.edu.celulares.domain.Celular

class SiNoTransformer extends Transformer[Celular, String] {

	override def transform(celular: Celular) : String = 
	    if (celular.getRecibeResumenCuenta) "SI" else "NO"
	
}