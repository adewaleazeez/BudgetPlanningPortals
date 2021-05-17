/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.DepartmentHod;
import com.bpp.hibernate.DepartmentHODHibernateHelper;
import com.bpp.hibernate.DepartmentHibernateHelper;
import com.bpp.hibernate.Departments;
import com.bpp.hibernate.MdaHibernateHelper;
import com.bpp.hibernate.Mdas;
import com.bpp.hibernate.Users;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADEWALE
 */
//@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class DepartmentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            DepartmentHibernateHelper helper = new DepartmentHibernateHelper();
            MdaHibernateHelper mdaHelper = new MdaHibernateHelper();
            DepartmentHODHibernateHelper departmentHODHelper = new DepartmentHODHibernateHelper();
            Departments departments = new Departments();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String mda_id;
            String administrative_segment;
            String hod_id;
            String dateCreated;
            String orgId;
            String _mda;
            int mda=0;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            _mda = request.getParameter("MDA");
            if (_mda == null) { 
                _mda = "0";
            }else{
                mda = Integer.parseInt(_mda);
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            name = request.getParameter("name");
            if (name == null) {
                name = "";
            }

            mda_id = request.getParameter("mda_id");
            if (mda_id == null) {
                mda_id = "";
            }

            administrative_segment = request.getParameter("administrative_segment");
            if (administrative_segment == null) {
                administrative_segment = "";
            }

            hod_id = request.getParameter("hod_id");
            if (hod_id == null) {
                hod_id = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }

            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                if (_mda.equals("0")) {
                    resp = helper.fetchAll();
                } else {
                    resp = helper.fetchByMDA(mda);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                synchronized (this) {
                    resp = helper.fetchOne(id);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_BY_ID)) {
                resp = helper.fetchById(mda_id);
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                departments = helper.checkName(name, mda_id);
                if (departments == null) {
                    Mdas mdas = mdaHelper.exists(Integer.parseInt(mda_id));
                    Departments department = new Departments();
                    department.setDateCreated(utility.dbDateNow());
                    department.setId(Integer.parseInt(helper.getMaxSerialNo("Departments")) + 1);
                    department.setMdaId(mdas.getId());
                    department.setName(name);
                    resp = helper.insert(department);
                } else {
                    resp = Utility.ActionResponse.RECORD_EXISTS.toString();
                }
                //System.out.println(resp);
                //Gson gson = new Gson();
                //resp = gson.toJson(resp);
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    Departments department = new Departments();
                    department = helper.exists(id);
                    if (department != null) {
                        resp = helper.delete(department);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    //Gson gson = new Gson();
                    //resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    Departments department = new Departments();
                    department = helper.exists(id);
                    if (department != null) {
                        department.setName(name);
                        resp = helper.updateDepartment(department);
                    }
//
//                    Gson gson = new Gson();
//                    resp = gson.toJson(resp);
                }
            }

            if (option.equals("FETCH_ALL_USERS_IN_MDA")) {
                synchronized (this) {
                    resp = mdaHelper.fetchUsers(id);
                }
            }

            if (option.equals("OPTION_UPDATE_HOD")) {
                synchronized (this) {
                    Departments department = new Departments();
                    department = helper.exists(id);
                    if(!hod_id.equals("0")){
                        Users user = new Users();
                        user = userhelper.exists_id(hod_id);

                        DepartmentHod departmentHOD = new DepartmentHod();
                        //departmentHODHelper.deleteByHod(hod_id);
                        departmentHOD = departmentHODHelper.dept_exists(id);

                        if (departmentHOD != null) {
                            departmentHOD.setDepartments(department);
                            departmentHOD.setUsers(user);
                            resp = departmentHODHelper.update(departmentHOD);
                        } else {
                            if(user != null){
                                departmentHOD = new DepartmentHod();
                                departmentHOD.setDepartments(department);
                                departmentHOD.setUsers(user);
                                departmentHOD.setId(Integer.parseInt(helper.getMaxSerialNo("Department_HOD")) + 1);
                                departmentHOD.setDateCreated(utility.dbDateNow());
                                departmentHOD.setOrgId(1);
                                resp = departmentHODHelper.insert(departmentHOD);
                            }
                        }
                    }else{
                        DepartmentHod departmentHOD = departmentHODHelper.dept_exists(id);
                        departmentHODHelper.deleteByHod(departmentHOD.getDepartments().getId()+"");
                    }
                }
            }
            out.println(resp);

        } finally {
            out.close();
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
