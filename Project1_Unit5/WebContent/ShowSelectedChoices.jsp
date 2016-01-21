<!-- JSP that shows the OptionSets and prints the selected choices with total vehicle cost. -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Here is what you selected</h1>
		<%
			Automobile automobile = (Automobile) request.getSession()
					.getAttribute("automobile");
		%>
		<table border="1">
			<tr>
				<td><b>Name </b></td>
				<td>Value</td>
				<td>Price</td>
			</tr>
			<tr>
				<td><b> Make/Model </b></td>
				<td><%=automobile.getMake() + automobile.getModel()%></td>
				<td><%=automobile.getBaseprice()%></td>
			</tr>

			<%
				for (String opsetName : automobile.getOptionsetNames()) {
			%>
			<tr>
				<td><b><%=opsetName%></b></td>
				<td><%=request.getParameter(opsetName)%></td>
				<td><%=automobile.getOptionPrice(opsetName,
						request.getParameter(opsetName))%></td>
			</tr>
			<%
				automobile.setOptionChoice(opsetName,
							request.getParameter(opsetName));
				}
			%>
			<tr>
				<td><b>Total Cost</b></td>
				<td></td>
				<td><%=automobile.getTotalPrice()%></td>
			</tr>
		</table>
	</center>
</body>
</html>