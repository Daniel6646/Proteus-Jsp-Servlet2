
package register;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
            String email=request.getParameter("logemail");
            String pass=request.getParameter("logpass");
            PreparedStatement st=connection.prepareStatement("select email,pass from student_details where email=? and pass=?");
            st.setString(1, email);
            st.setString(2, pass);
            ResultSet rs=st.executeQuery();
            ShowDetail sh=new ShowDetail();
            Boolean b=email.matches("^(.+)@(.+)$");
            if(b && pass.length()>=8)
            {
                if(rs.next())
                {
                    out.println("<html><head><title>Student Page</title></head>");
                    String result=sh.select(email);
                    out.println("<body bgcolor=grey><h1>Welcome</h1>"
                            + "Your details are <br>"+result+
                            "<a href=result.html>Do you want to update details?</a></body></html>");
                }
                else
                {
                out.println("<html><head></head><body bgcolor=grey><h1>Wrong Password/Email</h1></body></html>");
                }
            }
            else
            {
                out.println("<html><head><title>Something went wrong</title></head>");
                out.println("<body bgcolor=grey><h1>Enter correct Information please</h1></body></html>");
            }
            rs.close();
            st.close();
            connection.close();
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
