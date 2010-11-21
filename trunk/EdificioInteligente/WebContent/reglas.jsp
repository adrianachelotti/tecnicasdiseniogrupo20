<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.*"%>
<%@ page language="java" contentType="text/html" import="modelo.manejadorDeSucesos.*"%>
<jsp:useBean id="piso" scope="session" class="controladores.beans.EdificioBean"/>
<script type="text/javascript">
function cambiar(indice){
	var idRegla = document.getElementById("reglaElegida");
	var x = document.getElementById("regla"+indice).value;
	var accion = document.getElementById("habilitacionRegla");
	accion.value=x;	
	idRegla.value=indice;
	document.reglaListado.action ="ListadorDeReglas!configurarRegla";	
}
</script>
<% List<Implicacion> reglas = piso.obtenerListadoDeReglas(); %>
<div class="contenido">
	<div class="titulo"><h3>Reglas</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">			
				<form class="elegante" id="reglaListado" name="reglaListado" action="AgregarRegla">
					<fieldset> <legend>Listado de Reglas </legend>
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Reglas</td>
								<td class="listado_par">Sucesos Esperados</td>
								<td class="listado_par">Dispositivo</td>									
								<td class="listado_par">Accion</td>
								<td class="listado_par">Estado Actual</td>									
								<td class="listado_par">Cambiar Estado</td>									
							</tr>	
						<%	
							int index = 0 ;
							for (Implicacion reglaActual:reglas) {								
						%>		
							<tr>
								<td><%=reglaActual.getIdentificador() %></td>								
								<td><%for (Suceso suc: reglaActual.getSucesos()){%>
										<%=suc.getIdSuceso() %></br>
									<%} %>
								</td>
								<td>
									<%=reglaActual.getAccion().obtenerDispositivo().obtenerDescripcion() %>
								</td>
								<td> 
									<%=reglaActual.getAccion().obtenerNombre()%>								
								</td>
								<%String habilitado = reglaActual.estaHabilitada()?"Habilitada":"Deshabilitada"; %>
								<td><%=habilitado %></td>
								<td>
									<select id="regla<%=reglaActual.getIdentificador()%>">
										<option value="habilitar">Habilitar</option>
										<option value="deshabilitar">Deshabilitar</option>
									</select>
									<input type="submit" onclick="cambiar('<%=reglaActual.getIdentificador()%>')" value="Cambiar">
								</td>		
													
							</tr>	
						<%index++;
						} %>										
						</table>
						<input type="hidden" value="<%=piso.obtenerNivel()%>" name="nivel">
						<input type="hidden" name="reglaElegida" id="reglaElegida">
						<input type="hidden" name="habilitacionRegla" id="habilitacionRegla">
						
					</fieldset>
						<input type="submit" value="Agregar Regla">
						<a href="ListadorDeReglas!seleccionarPisos">Volver</a>		
				</form> 
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>