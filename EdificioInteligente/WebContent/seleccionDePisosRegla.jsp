<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.Piso"%>
<jsp:useBean id="edificio" scope="session" class="controladores.beans.EdificioBean"/>
<%
	List<Piso> listadoDePisos = edificio.obtenerListadoDePisos();
%>
<div class="contenido">
	<div class="titulo"><h3>Reglas</h3></div>
	<div class="cuerpo" align="center">
		<table width ="500" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="500" align="center">			
				<form class="elegante" id="pisosListado" name="pisosListado" action="ListadorDeReglas">
					<fieldset> <legend>Listado de Pisos </legend>
						<table width="400" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Nivel</td>
																						
							</tr>	
					
							<tr>
								<td>
									<select name="nivel">
									<%for (Piso piso:listadoDePisos) {	%>		
										<option value="<%=piso.obtenerNivel()%>"><%=piso.obtenerNivel()%></option>
									<%} %>
									</select>
								</td>																				
							</tr>	
															
						</table>
					</fieldset>
					<input type="submit" value="Ver Reglas"></input>	
				</form> 
				
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>