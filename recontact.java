/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class recontact extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
            String mobile=request.getParameter("snum");
            String city=request.getParameter("scity");
            String state=request.getParameter("sstate");
            int id=Integer.parseInt(request.getParameter("stnum"));
            Boolean b1=mobile.matches("(0/91)?[7-9][0-9]{9}");
            if(b1)
            {
                PreparedStatement st=connection.prepareStatement("update student_details set mobile=?,city=?,state=? where id=?");
                st.setString(1, mobile);
                st.setString(2, city);
                st.setString(3, state);
                st.setInt(4, id);
                st.executeUpdate();
                out.println("<html><head><title>Update Status</title></head>");
                out.println("<body bgcolor=grey><h1>Details Updated</h1></body></html>");
                st.close();
                connection.close();
            }
            else
            {
                out.println("<html><head><title>Something went wrong</title></head>");
                out.println("<body bgcolor=grey><h1>Enter correct Information please</h1></body></html>");
            }
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace(System.err);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
