package rojinaReview.autenticazione.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import rojinaReview.model.utilities.WrapperDaoAdminPanel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanel extends HttpServlet {
    private String homePage = "./home";
    private String classView = "/WEB-INF/results/adminPanel/";
    private String mainView = "/WEB-INF/results/adminPanel/AdminPanel.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") == null || request.getParameter("className") == null)
                response.sendRedirect(homePage);
            else{
                String className = request.getParameter("className");
                request.setAttribute("className",className);

                ArrayList<Object> list = WrapperDaoAdminPanel.doRetriveAll(className);
                request.setAttribute("list",list);

                /*Il parametro "classView" ha una path composta da "classView" + il parametro
                "Class" Presente nella request, questo permette di generare una view dinamica in base
                alla table richiesta dall' utente
                (Il parametro di cui si parla servirà alla "mainView")*/
                String classViewTmp = this.classView + className + "Table.jsp";
                request.setAttribute("classView",classViewTmp);

                RequestDispatcher dispatcher = request.getRequestDispatcher(mainView);
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
