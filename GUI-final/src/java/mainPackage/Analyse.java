package mainPackage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
//import org.netbeans.asynchronouos.*;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.http.HttpSession;
import org.netbeans.enterprise.bpel.asynchronoussampleclient.*;
import org.netbeans.enterprise.bpel.asynchronoussampleschemanamespace.*;

/**
 *
 * @author Dominik
 */
public class Analyse extends HttpServlet {
    List<HistoryType> historyList = new ArrayList<HistoryType>();
    int counter = 0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try{
                long startTime = System.currentTimeMillis();
                AsynchronousClientService service = new org.netbeans.enterprise.bpel.asynchronoussampleclient.AsynchronousClientService();
                MyPortTypeClient port = service.getAsynchronousClientPortName();                
                TypeA inputType = new org.netbeans.enterprise.bpel.asynchronoussampleschemanamespace.TypeA();

                inputType.setId(new BigInteger(128, new Random()).toString(16)); //QuickNDirty Hex
                List<String> keywords = inputType.getKeyword();
                String[] strings = request.getParameter("searchedString").split(",");
                for (int i = 0; i < strings.length; i++) {
                    keywords.add(strings[i]);
                }

                TypeB result = port.operationA(inputType);
                String fault = result.getFaultInfo();

                if (fault != null) {
                    out.println("Fault: " + fault);
                } else {
                    FileOutputStream outStr = new FileOutputStream("report.pdf");
                    outStr.write(result.getPdfData());
                    Long endTime   = System.currentTimeMillis();
                    Long totalTime = endTime - startTime;
                    double totalTimeSeconds = totalTime.doubleValue();
                    totalTimeSeconds = totalTimeSeconds/1000;
                    HttpSession session = request.getSession();
                    historyList.add(new HistoryType(++counter, strings, Double.toString(totalTimeSeconds), result.getPdfData()));
                    session.setAttribute("historyList", historyList);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
             } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
