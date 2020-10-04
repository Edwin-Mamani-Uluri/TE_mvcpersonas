/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kr1pt0n
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

   

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
        int id;
        int pos;
       int opcion=Integer.parseInt(request.getParameter("op"));
       HttpSession ses=request.getSession();
        ArrayList<Persona> lista=(ArrayList<Persona>) ses.getAttribute("lista_estudiantes");
       if(opcion==1){
           //nuevo
           Persona p = new Persona();
           request.setAttribute("miPersona", p);
           request.getRequestDispatcher("editar.jsp").forward(request, response);
       }
       if(opcion==2){
           //editar
           id=Integer.parseInt(request.getParameter("id"));
           pos=buscar_indice(request,id);
           Persona p1=lista.get(pos);
           request.setAttribute("miPersona", p1);
           request.getRequestDispatcher("editar.jsp").forward(request, response);
       }
       
       if(opcion==3){
           id=Integer.parseInt(request.getParameter("id"));
           pos=buscar_indice(request,id);
           lista.remove(pos);
           ses.setAttribute("lista_estudiantes", lista);
           response.sendRedirect("index.jsp");
       }
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
        int pos;
        int id=Integer.parseInt(request.getParameter("id"));
        int edad=Integer.parseInt(request.getParameter("edad"));
        String nombres=request.getParameter("nombres");
        String apellidos=request.getParameter("apellidos");
        String nuevo=request.getParameter("nuevo");
       // boolean nuevo=Boolean.parseBoolean(request.getParameter("nuevo"));
        Persona per = new Persona();
        per.setId(id);
        per.setNombres(nombres);
        per.setApellidos(apellidos);
        per.setEdad(edad);
        HttpSession ses=request.getSession();
        ArrayList<Persona> lista = (ArrayList<Persona>)ses.getAttribute("lista_estudiantes");
        if(nuevo.equals("true")){
        lista.add(per);
        }else{
            //editar
            //buscar elemento en coleccion
            pos=buscar_indice(request,id);
            lista.set(pos, per);
            //remplazar
            
        }response.sendRedirect("index.jsp");
        
    }
    
    private int buscar_indice(HttpServletRequest request,int id){
        HttpSession ses=request.getSession();
        ArrayList<Persona> lista=(ArrayList<Persona>) ses.getAttribute("lista_estudiantes");
        int i=0;
        if(lista.size()>0){
            while(i<lista.size()){
            if(lista.get(i).getId()==id){
                break;
            }else{
                i++;
            }
            }
        }
        return i;
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
