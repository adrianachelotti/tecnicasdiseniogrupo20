<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.driver.*"%>
<jsp:useBean id="edificio" scope="session" class="controladores.beans.EdificioBean"/>
<%List<DriverDispositivo> drivers = edificio.obtenerCatalogoDriversDeDisposititvos(); %>
<div class="contenido">
	<div class="titulo"><h3>Dispositivos</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Agregar Dispositivos </legend>
				<form class="elegante" id="dispositivosAgregar" name="dispositivoAgregar" action="AgregarDispositivo!agregar">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >						
						<tr>
							<td>Elija Driver:</td>
							<td>
								<select name="driverElegido">
									<%for (DriverDispositivo driver: drivers){ %>
									<option><%=driver.obtenerNombre()%></option>
									<%} %>		
								</select>					
							</td>
						</tr>	
						<tr>
							<td>Descripci&oacute;n:</td>
							<td><input type="text" name="descripcionDispositivo"> </td>							
						</tr>	
										
						</table>
						
						<input type="hidden" name="nivel" value="<%=edificio.obtenerNivel()%>">
						<input type="submit" value="Guardar" ></input>&nbsp;<a href="ListadorDePisos">Volver</a>
						
					</form> 
				</fieldset>
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>