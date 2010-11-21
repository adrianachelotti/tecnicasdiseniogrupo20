<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
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
								<td class="listado_par">Estado</td>
								<td class="listado_par">Estado Posibles</td>									
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
								<input type="submit" value="cambiar" onclick="cambiar('<%=index%>')"></input>
								</td>							
							</tr>	
						<%index++;
						} %>										
						</table>
						<input type="hidden" value="<%=piso.obtenerNivel()%>" name="nivel">
						<input type="hidden" name="idSensor" id="idSensor"></input>
						<input type="hidden" name="medicionCambiar" id="medicionCambiar"></input>
					</form> 
				</fieldset>
				<a href="ListadorDeSensores!seleccionarPisos">Volver</a>
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>