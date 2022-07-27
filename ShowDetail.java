/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;
import java.sql.*;
import java.io.*;
/**
 *
 * @author ling4
 */
public class ShowDetail {
    
    public String select(String emailsel) throws IOException
       {
           String finalstudent=null;
             try
              {
                    Class.forName("com.mysql.cj.jdbc.Driver"); 
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
                    PreparedStatement stm=connection.prepareStatement("select id,fname,lname,email,mobile,gender,DOB,city,state from student_details where email=?");
                    stm.setString(1, emailsel);
                    ResultSet rs=stm.executeQuery();
                    while(rs.next())
                    {
                        int id=rs.getInt("id");
                        String fname=rs.getString("fname");
                        String lname=rs.getString("lname");
                        String email=rs.getString("email");
                        long mobile=rs.getLong("mobile");
                        String gen=rs.getString("gender");
                        Date db=rs.getDate("DOB");
                        String city=rs.getString("city");
                        String state=rs.getString("state");
                        finalstudent="Student Number:"+id+"<br>"+"Firstname:"+fname+"<br>"+"Lastname:"+lname+"<br>"+"Email:"+email+"<br>"+"Mobile Number:"+mobile+"<br>"+"Gender:"+gen+"<br>"+"Date of Birth:"+db+"<br>"+"City:"+city+"<br>"+"State:"+state+"<br>";
                    }
                    rs.close();
                    stm.close();
                    connection.close();
              }
            catch(SQLException | ClassNotFoundException e)
              {
                    e.printStackTrace(System.err);
              }
             return finalstudent;
       }
    
}
