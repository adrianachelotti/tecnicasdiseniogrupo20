<jsp:include page="/WEB-INF/jspf/encabezado.jspf"></jsp:include>
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
								<td class="listado_par">Sucesos</td>	
								<td class="listado_par">Suceso Esperados</td>
								<td class="listado_par">Dispositivo</td>
								<td class="listado_par">Acciones</td>
																								
							</tr>	
							<tr>
								<td>Regla i</td>
								<td><select><option>Suceso 1</option><option>Suceso 2</option><option>Suceso 3</option></select><input type="submit" value="agregar"></input></td>							
								<td>Suceso Esperado 1 <br></br>Suceso Esperado 2 <br></br></td>
								<td><select><option>Dispistivo 1</option><option>Dispistivo 2</option><option>Dispistivo 3</option></select> </td>
								<td><select><option>Encender</option><option>Apagar</option></select></td>																		
							</tr>	
													
						</table>
						<input type="submit" value="Guardar" ></input>
					</form> 
				</fieldset>
				
			</td>
		</tr>
		</table>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/piedepagina.jspf"></jsp:include>