<jsp:include page="/WEB-INF/jspf/header.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<div class="contenido">
	<div class="titulo"><h3>Dispositivo</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Listado de Dispositivos </legend>
				<form class="elegante" id="dispositivoListado" name="dispositivoListado" action="">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Dospisitivo</td>
								<td class="listado_par">Estado</td>								
							</tr>	
						<%	
							for (int i=0;i<20;i++) {						
						%>		
							<tr>
								<td>Dispositivo&nbsp; <%=i %></td>
								<td><select><option>Encendido</option><option>Apagado</option></select></td>																		
							</tr>	
						<%} %>										
						</table>
					</form> 
				</fieldset>
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jspf"></jsp:include>