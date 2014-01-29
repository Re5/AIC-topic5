<%-- 
    Document   : index
    Created on : 29.1.2014, 13:38:05
    Author     : Dominik
--%>
<%@ page import="java.util.ArrayList,java.util.List,mainPackage.HistoryType,java.util.*,java.lang.String" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QoS Aware Service Composition</title>
        <link rel="stylesheet" type="text/css" type="text/css" href="style.css" />
    </head>
    <body>
        <div class="container">
          <div class="sidebar1">                              
            <!-- end .sidebar1 --></div>
          <div class="content">
            <h1>QoS aware web service compositon</h1>
            <form action="Analyse" id="form1" method="get">
                <input class="textbox" type="text" name="searchedString">
                <button type="submit" name="" value="" class="css3button2">Analyse</button>
            </form>
            <div class="CSSTableGenerator" style="margin: 50px 0 50px 20px">
                <table>
                   <tr><td>Id</td><td>Terms</td><td>Processing time</td><td>Report</td></tr>
            <%            
                List<HistoryType> historyList = (List<HistoryType>)session.getAttribute("historyList");
                if(historyList != null){                    
                    for(int i=0;i < historyList.size();i++){
                        out.println("<tr><td>");
                        out.println(historyList.get(i).getId() + "</td><td>");
                        for(int j=0;j < historyList.get(i).getSearched().length;j++){
                            if(j!=0)
                                out.println(", ");
                            out.println(historyList.get(i).getSearched()[j]);
                            }
                        out.println("</td>");
                        out.println("<td>" + historyList.get(i).getTime()+"</td><td>"); //</tr>");
                        %>

                        <a target="_blank" href="ViewPdf?ID=<%= historyList.get(i).getId() %>">View</a></td></tr>

                        <%
                    }
                }
            %>
            </table>
            </div>
            <!-- end .content --></div>
          <!-- end .container --></div>
        </body>
</html>
