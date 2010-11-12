<jsp:include page="/WEB-INF/jspf/header.jspf"></jsp:include>
<%@ page language="java" contentType="text/html" import="java.util.List"%>
<div class="contenido">
	<div class="titulo"><h3>Sensores</h3></div>
	<div class="cuerpo" align="center">
		<table height ="300" cellpadding="0" cellspacing="0"  >
		<tr>
			<td width="800" align="center">
			<fieldset> <legend>Listado de Sensores </legend>
				<form class="elegante" id="sensoresListado" name="sensoresListado" action="">
						<table width="600" border="1" class="listado" cellpadding="0" cellspacing="0" >
							<tr>
								<td class="listado_par">Sensor</td>
								<td class="listado_par">Medicion</td>							
								<td class="listado_par">Estados</td>																
							</tr>	
						<%	
							for (int i=0;i<20;i++) {						
						%>		
							<tr>
								<td>Sensor&nbsp; <%=i %></td>
								<td>Estado Actual</td>								
								<td><select><option>Estado 1</option><option>Estado 2</option></select></td>																		
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