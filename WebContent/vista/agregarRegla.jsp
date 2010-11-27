<jsp:include page="encabezado.jsp"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<%@ page language="java" contentType="text/html" import="modelo.edificio.Dispositivo"%>
<jsp:useBean id="piso" scope="session" class="controladores.beans.EdificioBean"/>
<%
	List<String> listaMedicionesPosibles = piso.obtenerMedicionesPosibles();
	List<String> listaMedicionesEsperadas = piso.obtenerMedicionesEsperadas();
	List<Dispositivo> dispositivos = piso.obtenerListadoDeDispositivos();
%>

<div class="contenido">
	<div class="titulo"><h3>Reglas</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
				<form class="elegante" id="reglasConfiguracion" name="reglasConfiguracion" action="AgregarRegla!agregarSucesoEsperado">
					<fieldset> <legend>Configuraci&oacute;n de Reglas </legend>
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Sucesos</td>	
								<td class="listado_par">Suceso Esperados</td>
								<td class="listado_par">Dispositivo</td>
								<td class="listado_par">Acciones</td>
																								
							</tr>	
							<tr>
								<td>
									<select name="sucesoAgregar">										
										<%for (String sucesoAgregar: listaMedicionesPosibles){ %>
										<option value="<%=sucesoAgregar%>"><%=sucesoAgregar%></option>
										<%} %>
									</select>
									<input type="submit" value="agregar"></input>
								</td>							
								<td>
									<%for(String sucesoEsperado:listaMedicionesEsperadas){ %>
										<%=sucesoEsperado%><br>
									<%} %>									
								</td>
								<td>
									<select name="dispositivoElegido">
										<%int index =0 ; %>
										<%for(Dispositivo dispositivo:dispositivos){ %>
										<option value="<%=index%>"><%=dispositivo.obtenerDescripcion()%></option>
										<% index++;
										} %>
									</select> 
								</td>
								<td><select name="accion">
										<option value="encender">Encender</option>
										<option value="apagar">Apagar</option>
									</select>
								</td>																		
							</tr>	
													
						</table>
						</fieldset>
						<input type="hidden" name="nivel" value="<%=piso.obtenerNivel()%>">
						<input type="submit" value="Guardar" onclick="document.reglasConfiguracion.action='AgregarRegla!guardar?nivel=<%=piso.obtenerNivel()%>'" ></input>
						<a href="ListadorDeReglas?nivel=<%=piso.obtenerNivel()%>">Cancelar</a>
					</form> 
				
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="piedepagina.jsp"></jsp:include>