package register;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
        private static final String insert = "INSERT INTO student_details" +
                "  (id,fname,lname,email,mobile,gender,DOB,city,state,pass) VALUES " +
                    " (? ,?, ?, ?, ?, ?, ?, ?, ?, ?);";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                ShowDetail sh=new ShowDetail();
                Class.forName("com.mysql.cj.jdbc.Driver"); 
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                String email=request.getParameter("semail");
                String mobile=request.getParameter("snum");
                String gen=request.getParameter("gen");
                String date=request.getParameter("date1");
                String city=request.getParameter("scity");
                String state=request.getParameter("sstate");
                String pass=request.getParameter("spass");
                Boolean b=email.matches("^(.+)@(.+)$");
                Boolean b1=mobile.matches("(0/91)?[7-9][0-9]{9}");
                if(b && b1 && pass.length()>=8)
                {
                    PreparedStatement preparedStatement = connection.prepareStatement(insert);
                    preparedStatement.setString(1, null);
                    preparedStatement.setString(2, fname);
                    preparedStatement.setString(3, lname);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, mobile);
                    preparedStatement.setString(6, gen);
                    preparedStatement.setString(7, date);
                    preparedStatement.setString(8, city);
                    preparedStatement.setString(9, state);
                    preparedStatement.setString(10,pass);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                    String result=sh.select(email);
                    out.println("<html><head><title>Student Page</title></head>");
                    out.println("<body bgcolor=grey><h1>Data Entered, Hello "+fname+"</h1>"
                        + "Your details are <br>"+result+"<a href=result.html>Do you want to update details?</a></body>");
                    out.println("</html>");
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
