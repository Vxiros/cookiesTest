import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class kafedaresServlet extends HttpServlet {
    private String cities[] = { "A8ina", "Kalamata", "Livadeia", "Patra", "Thesalonikh" };
    private String stores[][] = { { "Joy Coffee", "Coffe Lab" },
                                  { "Coffe Shop", "CoffeRO" },
                                  { "Kafe Livadaki", "40 kafedares" },
                                  { "Coffe Carnival", "Coffe Island" },
                                  { "O Lefkos Kafes", "Metro Coffee" } };
    private double prices[][] = { {1.5, 1.8}, {2.1, 1.6}, {1.2, 2.3}, {1.7, 2.9}, {1.5, 1.2} };
    private int time[][] = { {7, 18}, {21, 16}, {12, 23}, {17, 29}, {15, 12} };

    public void doPost( HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException
    {
        PrintWriter output;
        response.setContentType( "text/html" );
        output = response.getWriter();
        output.println( "<head> <title> Kafedares </title> </head>" );
        output.println( "<body>" );
        output.println( "<h1> Kafedares </h1>" );
        output.println( "" );

        String form = request.getParameter("form_ID");
        if (form.equals( "perioxh" )) {
            String firstName = request.getParameter( "firstName" );
            String lastName = request.getParameter( "lastName" );
            String fullName = firstName + " " + lastName;
            Cookie fname = new Cookie( "fullName", fullName );
            fname.setMaxAge(60*60*24); //1 mera
            response.addCookie( fname );


            String city = request.getParameter( "city" );
            Cookie ct = new Cookie( "city" , city );
            ct.setMaxAge(60*60*24); //1 mera
            response.addCookie( ct );

            String address = request.getParameter( "address" );
            Cookie addr = new Cookie( "address" , address );
            addr.setMaxAge(60*60*24); //1 mera
            response.addCookie( addr );

            output.println( "<p> Ta stoixeia sas kataxwrhthikan." );
            output.println( "Pathste to koumpi Next gia na proxwrhsete. </p>" );
            output.println( "<button onclick=window.location.href='http://localhost:8080/kafedares/paraggeleia.html'>Next</button>" );
        }
        else if (form.equals( "paraggeleia" )) {
            String eidos = request.getParameter( "eidos" );
            String zaxarh = request.getParameter( "zaxarh" );
            String eidosZaxarhs = request.getParameter( "eidosZaxarhs" );
            String posothta = request.getParameter( "posothta" );
            String kafes = posothta + " " + eidos + " " + zaxarh + " " + eidosZaxarhs;
            Cookie kaf = new Cookie( "kafes", kafes );
            kaf.setMaxAge(60*30); //30 lepta
            response.addCookie( kaf );

            output.println( kafes );
            output.println( "<p> H paraggeleia sas proste8hke sto kala8i." );
            output.println( "Pathste to koumpi Allagh gia na alla3ete thn paraggelia kai to koumpi Next gia na proxwrhsete. </p>" );
            output.println( "<button onclick=window.location.href='http://localhost:8080/kafedares/paraggeleia.html'>Allagh</button>" );
            output.println( "<button onclick=window.location.href='http://localhost:8080/kafedares/epiloges.html'>Next</button>" );
        }

        output.println( "</body>" );
        output.println( "</HTML>" );
        output.close ();
        
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        PrintWriter output;
        response.setContentType( "text/html" );
        output = response.getWriter();
        output.println( "<head> <title> Kafedares </title> </head>" );
        output.println( "<body>" );
        output.println( "<h1> Kafedares </h1>" );
        Cookie cookies[];
        cookies = request.getCookies();
        String fullName = " ", addr = " ", city = " ";
        if ( cookies.length != 0 ) {
            for ( int i = 0; i < cookies.length; i++ ) {
                if(cookies[i].getName().equals("fullName")) {
                    fullName = cookies[i].getValue();
                }
                if(cookies[i].getName().equals("city")) {
                    city = cookies[i].getValue();
                }
                if(cookies[i].getName().equals("address")) {
                    addr = cookies[i].getValue();
                }
            }
            output.println( "<h3>" + fullName + "</h3>");
            output.println( "<h3>" + city + "</h3>");
            output.println( "<h3>" + addr + "</h3>");

            for ( int i = 0; i < cookies.length; i++ ) {
                if(cookies[i].getName().equals("kafes")) {
                    output.println( "<h4>" + cookies[i].getValue() + "</h4>");
                }
            }

            String form = request.getParameter("form_ID");
            if (form.equals( "epiloges" )) {
                String epiloges = request.getParameter( "epiloges" );
                output.println( " <p><h3> Anazhthsh me vash: </h3>" );
                output.println( epiloges ); output.println( "</p>" );

                for (int i=0; i<=4; i++) {
                    if (city.equals(cities[i])) {
                        if (epiloges.equals("Timh")) {
                            output.println( "<h4>Epilogh1: " + stores[i][0] + " " + prices[i][0] + "euro o ka8e kafes</h4>");
                            output.println( "<h4>Epilogh2: " + stores[i][1] + " " + prices[i][1] + "euro o ka8e kafes</h4>");
                        }
                        else if (epiloges.equals("Xronos E3uphrethshs")) {
                            output.println( "<h4>Epilogh1: " + stores[i][0] + " " + time[i][0] + "lepta gia na ftasei spiti sas</h4>");
                            output.println( "<h4>Epilogh2: " + stores[i][1] + " " + time[i][1] + "lepta gia na ftasei spiti sas</h4>");
                        }

                    }
                }
                output.println("<tr>");
                output.println("<td><input type=radio name=st value=Epilogh1>Epilogh1</td>");
                output.println("<td><input type=radio name=st value=Epilogh1>Epilogh2</td>");
                output.println( "<button onclick=window.location.href='http://localhost:8080/kafedares/telos.html'>Ypovolh Paraggeleias</button>" );
            }

        
        }
        output.println( "</body>" );
        output.println( "</HTML>" );
        output.close ();
    }
}