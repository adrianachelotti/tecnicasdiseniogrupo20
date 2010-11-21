<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.driver.*"%>
<jsp:useBean id="edificio" scope="session" class="controladores.beans.EdificioBean"/>
<%List<DriverSensor> drivers = edificio.obtenerCatalogoDriversDeSensores(); %>
<div class="contenido">
	<div class="titulo"><h3>Pisos</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">		
				<form class="elegante" id="sensorAgregar" name="sensorAgregar" action="AgregarSensor!agregar">
					<fieldset> <legend>Agregar Sensor </legend>
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >						
						<tr>
							<td class="listado_impar">Elija Driver:</td>
							<td>
								<select name="driverElegido">
									<%for (DriverSensor driver: drivers){ %>
									<option><%=driver.obtenerNombre()%></option>
									<%} %>		
								</select>					
							</td>
						</tr>	
						<tr>
							<td class="listado_impar">Descripci&oacute;n:</td>
							<td><input type="text" name="descripcionSensor"> </td>							
						</tr>	
										
						</table>
					</fieldset>		
					<input type="hidden" name="nivel" value="<%=edificio.obtenerNivel()%>">
					<input type="submit" value="Guardar" ></input>&nbsp;<a href="ListadorDePisos">Volver</a>
				</form> 
				
					
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>