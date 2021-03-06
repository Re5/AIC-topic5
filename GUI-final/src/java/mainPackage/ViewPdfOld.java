/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.netbeans.enterprise.bpel.asynchronoussampleschemanamespace.TypeB;

/**
 *
 * @author Install
 */
public class ViewPdfOld extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("ID");

        if (id != null) {
            HttpSession session = request.getSession();
            List<HistoryType> historyList = (List<HistoryType>)session.getAttribute("historyList");

            byte[] data = historyList.get(Integer.parseInt(id)-1).getPdf();

//            TypeB data = HumanService.getItem(id);
            if (data != null) {
                response.setContentType("application/pdf");
                ServletOutputStream outStream = response.getOutputStream();
                response.setContentLength(data.length);
                response.setHeader("Content-Disposition", "attachement; filename=report" + id + ".pdf");
                outStream.write(data);
            }
            return;
        }
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
