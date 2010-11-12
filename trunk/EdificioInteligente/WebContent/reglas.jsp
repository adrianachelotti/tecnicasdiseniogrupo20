<jsp:include page="/WEB-INF/jspf/header.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<div class="contenido">
	<div class="titulo"><h3>Reglas</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Configuraci&oacute;n de Reglas </legend>
				<form class="elegante" id="reglasConfiguracion" name="reglasConfiguracion" action="">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Regla</td>													
								<td class="listado_par">Suceso Esperados</td>
								<td class="listado_par">Dispositivo</td>
								<td class="listado_par">Acciones</td>
																								
							</tr>	
						<%	
							for (int i=0;i<20;i++) {						
						%>		
							<tr>
								<td>Regla i</td>
															
								<td>Suceso Esperado 1 <br></br>Suceso Esperado 2 <br></br></td>
								<td>Dispositivo seleccionado</td>
								<td>Encendido</td>																		
							</tr>	
						<%} %>										
						</table>
						<a href="agregarRegla.jsp">Agregar Regla</a>
					</form> 
				</fieldset>
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jspf"></jsp:include>