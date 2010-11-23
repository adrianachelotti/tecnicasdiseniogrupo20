<jsp:include page="encabezado.jsp"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.*"%>
<jsp:useBean id="piso" scope="session" class="controladores.beans.EdificioBean"/>
<script type="text/javascript">
function cambiar(indice){
	var idSensor = document.getElementById("idSensor");
	var x = document.getElementById("accion"+indice).value;
	var accion =document.getElementById("medicionCambiar");
	accion.value=x;	
	idSensor.value=indice;	
}
function habilitar(indice){
	var idSensor = document.getElementById("idSensor");
	var x = document.getElementById("cambio"+indice).value;
	var accion =document.getElementById("estadoCambiar");
	accion.value=x;	
	idSensor.value=indice;
	document.sensorListado.action="ListadorDeSensores!cambiarEstado";	
}
</script>
<% List<Sensor> sensores = piso.obtenerListadoDeSensores(); %>
<div class="contenido">
	<div class="titulo"><h3>Sensores</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Listado de Sensores </legend>
				<form class="elegante" id="sensorListado" name="sensorListado" action="ListadorDeSensores!cambiarMedicion">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Sensor</td>
								<td class="listado_par">Medicion Actual</td>
								<td class="listado_par">Mediciones Posibles</td>
								<td class="listado_par">Estado Actual</td>
								<td class="listado_par">Cambiar Estado </td>																	
							</tr>	
						<%	
							int index = 0 ;
							for (Sensor sensorActual:sensores) {								
						%>		
							<tr>
								<td><%=sensorActual.obtenerDescripcion() %></td>								
								<td><%=sensorActual.obtenerMedicion() %></td>
								<td>
									<select id="accion<%=index%>">								
										<%for(String medicion:sensorActual.obtenerMedicionesPosibles()){ %>
											<option value="<%=medicion%>"><%=medicion%></option>
										<%} %>
									</select>
									<input type="submit" value="cambiar" id="1" onclick="cambiar('<%=index%>')"></input>
								</td>
								<%String habilitado = sensorActual.estaHabilitado()?"Habilitado":"Deshabilitado"; %>
								<td>
									<%=habilitado%>
								</td>
								<td>
									<select id="cambio<%=index%>">
										<option value="habilitar">Habilitar</option>
										<option value="deshabilitar">Deshabilitar</option>
									</select>
									<input type="submit" value="cambiar" id="2" onclick="habilitar('<%=index%>')"></input>
								</td>							
							</tr>	
						<%index++;
						} %>										
						</table>
						<input type="hidden" value="<%=piso.obtenerNivel()%>" name="nivel">
						<input type="hidden" name="idSensor" id="idSensor"></input>
						<input type="hidden" name="medicionCambiar" id="medicionCambiar"></input>
						<input type="hidden" name="estadoCambiar" id="estadoCambiar"></input>
					</form> 
				</fieldset>
				<a href="ListadorDeSensores!seleccionarPisos">Volver</a>
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="piedepagina.jsp"></jsp:include>