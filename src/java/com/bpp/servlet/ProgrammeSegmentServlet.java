/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.ProgrammeSegmentHeader1HibernateHelper;
import com.bpp.hibernate.ProgrammeSegmentHeader1;
import com.bpp.hibernate.ProgrammeSegmentHeader3HibernateHelper;
import com.bpp.hibernate.ProgrammeSegmentHeader3;
import com.bpp.hibernate.ProgrammeSegmentHeader2HibernateHelper;
import com.bpp.hibernate.ProgrammeSegmentHeader2;
import com.bpp.hibernate.ProgrammeSegmentHibernateHelper;
import com.bpp.hibernate.ProgrammeSegment;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ProgrammeSegmentServlet extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        try {
            UsersHibernateHelper userhelper = new UsersHibernateHelper();
            //userhelper.saveUserIdSession(Integer.parseInt(session.getAttribute("userid").toString()));

            ProgrammeSegmentHeader1HibernateHelper header1Helper = new ProgrammeSegmentHeader1HibernateHelper();
            ProgrammeSegmentHeader2HibernateHelper header2Helper = new ProgrammeSegmentHeader2HibernateHelper();
            ProgrammeSegmentHeader3HibernateHelper header3Helper = new ProgrammeSegmentHeader3HibernateHelper();
            ProgrammeSegmentHibernateHelper helper = new ProgrammeSegmentHibernateHelper();
            ProgrammeSegment ProgrammeSegment = new ProgrammeSegment();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
            String dateCreated;
            String orgId;
            String entity;
            String code;
            String parent;
            String mda;
            String department;
            String sector_goal;
            String sector_programme;
            String year_id;
            String project_status;
            String score;
            String rank;
            String policy;
            String budgetyear;
            String NewOld;

            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
            }

            entity = request.getParameter("entity");
            if (entity == null) {
                entity = "";
            }

            id = request.getParameter("id");
            if (id == null) {
                id = "";
            }

            name = request.getParameter("name");
            if (name == null) {
                name = "";
            }

            code = request.getParameter("code");
            if (code == null) {
                code = "";
            }

            parent = request.getParameter("parent");
            if (parent == null) {
                parent = "";
            }

            dateCreated = request.getParameter("dateCreated");
            if (dateCreated == null) {
                dateCreated = "";
            }

            orgId = request.getParameter("orgId");
            if (orgId == null) {
                orgId = "";
            }
            mda = request.getParameter("mda");
            if (mda == null) {
                mda = "";
            }
            String _mda;
            int imda=0;
            _mda = request.getParameter("MDA");
            if (_mda != null && _mda != "") {
                imda = Integer.parseInt(_mda);
            }
            department = request.getParameter("department");
            if (department == null) {
                department = "";
            }
            sector_goal = request.getParameter("sector_goal");
            if (sector_goal == null) {
                sector_goal = "";
            }
            sector_programme = request.getParameter("sector_programme");
            if (sector_programme == null) {
                sector_programme = "";
            }
            year_id = request.getParameter("year_id");
            if (year_id == null) {
                year_id = "";
            }
            project_status = request.getParameter("project_status");
            if (project_status == null) {
                project_status = "0";
            }
            score = request.getParameter("score");
            if (score == null) {
                score = "-1";
            }
            rank = request.getParameter("rank");
            if (rank == null) {
                rank = "-1";
            }
            policy = request.getParameter("policy");
            if (policy == null) {
                policy = "";
            }

            budgetyear = request.getParameter("budgetyear");
            if (budgetyear == null) {
                budgetyear = "";
            }

            NewOld = request.getParameter("NewOld");
            if (NewOld == null) {
                NewOld = "";
            }
            if (option.equals(Utility.OPTION_INSERT) && entity.equals("ProgrammeSegment")) {
                synchronized (this) {
                    ProgrammeSegment programmeSegment = new ProgrammeSegment();
                    ProgrammeSegmentHeader3 segmentParent = header3Helper.fetchOne(parent);
                    programmeSegment.setId(Integer.parseInt(helper.getMaxSerialNo("Programme_Segment")) + 1);
                    programmeSegment.setDateCreated(utility.dbDateNow());
                    programmeSegment.setName(name);
                    programmeSegment.setCode(code);
                    programmeSegment.setOrgId(1);
                    programmeSegment.setParent(segmentParent.getId());
                    resp = helper.insert(programmeSegment);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("ProgrammeSegmentHeader1")) {
                synchronized (this) {
                    ProgrammeSegmentHeader1 programmeSegmentHeader1 = new ProgrammeSegmentHeader1();
                    programmeSegmentHeader1.setId(Integer.parseInt(header1Helper.getMaxSerialNo("Programme_Segment_Header1")) + 1);
                    programmeSegmentHeader1.setDateCreated(utility.dbDateNow());
                    programmeSegmentHeader1.setName(name);
                    programmeSegmentHeader1.setOrgId(1);
                    programmeSegmentHeader1.setCode(code);
                    resp = header1Helper.insert(programmeSegmentHeader1);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("ProgrammeSegmentHeader2")) {
                synchronized (this) {
                    ProgrammeSegmentHeader1 header2Parent = header1Helper.fetchOne(parent);
                    ProgrammeSegmentHeader2 programmeSegmentHeader2 = new ProgrammeSegmentHeader2();
                    programmeSegmentHeader2.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Programme_Segment_Header1")) + 1);
                    programmeSegmentHeader2.setDateCreated(utility.dbDateNow());
                    programmeSegmentHeader2.setName(name);
                    programmeSegmentHeader2.setOrgId(1);
                    programmeSegmentHeader2.setCode(code);
                    programmeSegmentHeader2.setParent(header2Parent.getId());
                    resp = header2Helper.insert(programmeSegmentHeader2);
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_INSERT) && entity.equals("ProgrammeSegmentHeader3")) {
                synchronized (this) {
                    ProgrammeSegmentHeader2 header3Parent = header2Helper.fetchOne(parent);
                    ProgrammeSegmentHeader3 programmeSegmentHeader3 = new ProgrammeSegmentHeader3();
                    //programmeSegmentHeader3.setId(Integer.parseInt(header2Helper.getMaxSerialNo("Programme_Segment_Header1")) + 1);
                    programmeSegmentHeader3.setId(Integer.parseInt(header3Helper.getMaxSerialNo("Programme_Segment_Header3")) + 1);
                    programmeSegmentHeader3.setDateCreated(utility.dbDateNow());
                    programmeSegmentHeader3.setName(name);
                    programmeSegmentHeader3.setOrgId(1);
                    programmeSegmentHeader3.setCode(code);
                    programmeSegmentHeader3.setMda(Integer.parseInt(mda));
                    programmeSegmentHeader3.setDepartment(Integer.parseInt(department));
                    programmeSegmentHeader3.setSectorGoal(Integer.parseInt(sector_goal));
                    programmeSegmentHeader3.setSectorProgramme(Integer.parseInt(sector_programme));
                    programmeSegmentHeader3.setYearId(Integer.parseInt(year_id));
                    programmeSegmentHeader3.setProjectStatus(Boolean.parseBoolean(project_status));
                    programmeSegmentHeader3.setScore(Double.parseDouble(score));
                    programmeSegmentHeader3.setRank(Integer.parseInt(rank));

                    programmeSegmentHeader3.setParent(header3Parent.getId());
                    resp = header3Helper.insert(programmeSegmentHeader3);

                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("ProgrammeSegment")) {
                synchronized (this) {
                    ProgrammeSegment programmeSegment = new ProgrammeSegment();
                    programmeSegment = helper.fetchOne(id);
                    if (programmeSegment != null) {
                        programmeSegment.setName(name);
                        programmeSegment.setCode(code);
                        resp = helper.update(programmeSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("ProgrammeSegmentHeader1")) {
                synchronized (this) {
                    ProgrammeSegmentHeader1 programmeSegmentHeader1 = new ProgrammeSegmentHeader1();
                    programmeSegmentHeader1 = header1Helper.fetchOne(id);
                    if (programmeSegmentHeader1 != null) {
                        programmeSegmentHeader1.setName(name);
                        programmeSegmentHeader1.setCode(code);
                        resp = header1Helper.update(programmeSegmentHeader1);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("ProgrammeSegmentHeader2")) {
                synchronized (this) {
                    ProgrammeSegmentHeader2 programmeSegmentHeader2 = new ProgrammeSegmentHeader2();
                    programmeSegmentHeader2 = header2Helper.fetchOne(id);
                    if (programmeSegmentHeader2 != null) {
                        programmeSegmentHeader2.setName(name);
                        programmeSegmentHeader2.setCode(code);
                        resp = header2Helper.update(programmeSegmentHeader2);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_UPDATE) && entity.equals("ProgrammeSegmentHeader3")) {
                synchronized (this) {
                    ProgrammeSegmentHeader3 programmeSegmentHeader3 = new ProgrammeSegmentHeader3();
                    programmeSegmentHeader3 = header3Helper.fetchOne(id);

                    if (programmeSegmentHeader3 != null) {
                        programmeSegmentHeader3.setName(name);
                        programmeSegmentHeader3.setCode(code);
                        programmeSegmentHeader3.setMda(Integer.parseInt(mda));

                        if (department != "") {
                            programmeSegmentHeader3.setDepartment(Integer.parseInt(department));
                        }
                        if (sector_goal != "") {
                            programmeSegmentHeader3.setSectorGoal(Integer.parseInt(sector_goal));
                        }
                        if (sector_programme != "") {
                            programmeSegmentHeader3.setSectorProgramme(Integer.parseInt(sector_programme));
                        }
                        if (year_id == null || year_id.length() == 0) {
                            // Escape here
                        } else {
                            programmeSegmentHeader3.setYearId(Integer.parseInt(year_id));
                        }
                        if (project_status != "") {
                            programmeSegmentHeader3.setProjectStatus(Boolean.parseBoolean(project_status));
                        }
                        programmeSegmentHeader3.setScore(Double.parseDouble(score));
                        programmeSegmentHeader3.setRank(Integer.parseInt(rank));

                        resp = header3Helper.update(programmeSegmentHeader3);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("ProgrammeSegment")) {
                synchronized (this) {
                    ProgrammeSegment programmeSegment = new ProgrammeSegment();
                    programmeSegment = helper.fetchOne(id);
                    if (programmeSegment != null) {
                        resp = helper.delete(programmeSegment);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("ProgrammeSegmentHeader1")) {
                synchronized (this) {
                    ProgrammeSegmentHeader1 programmeSegmentHeader1 = new ProgrammeSegmentHeader1();
                    programmeSegmentHeader1 = header1Helper.fetchOne(id);
                    if (programmeSegmentHeader1 != null) {
                        resp = header1Helper.delete(programmeSegmentHeader1);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("ProgrammeSegmentHeader2")) {
                synchronized (this) {
                    ProgrammeSegmentHeader2 programmeSegmentHeader2 = new ProgrammeSegmentHeader2();
                    programmeSegmentHeader2 = header2Helper.fetchOne(id);
                    if (programmeSegmentHeader2 != null) {
                        resp = header2Helper.delete(programmeSegmentHeader2);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);

                }
            }

            if (option.equals(Utility.OPTION_DELETE) && entity.equals("ProgrammeSegmentHeader3")) {
                synchronized (this) {
                    System.out.println("Deleting Header 3 with id " + id);
                    ProgrammeSegmentHeader3 programmeSegmentHeader3 = new ProgrammeSegmentHeader3();
                    programmeSegmentHeader3 = header3Helper.fetchOne(id);
                    if (programmeSegmentHeader3 != null) {
                        resp = header3Helper.delete(programmeSegmentHeader3);
                    } else {
                        System.out.println("No programmeSegment 3 in servlet");

                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                    Gson gson = new Gson();
                    resp = gson.toJson(resp);
                }
            }
            if (option.equals(Utility.OPTION_SELECT_POLICIES) && entity.equals("ProgrammeSegmentHeader3")) {

                resp = header3Helper.fetchAllPolicies(policy, NewOld, budgetyear);

            }
            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("ProgrammeSegment")) {
                resp = helper.fetchAll(Integer.parseInt(parent));
            }

            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("ProgrammeSegmentHeader1")) {
                System.out.println("Selecting all header1");
                resp = header1Helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("ProgrammeSegmentHeader2")) {
                if (_mda == null) {
                    resp = header2Helper.fetchAll(Integer.parseInt(parent));
                } else {
                    resp = header2Helper.fetchByMDA(imda);
                }

            }

            if (option.equals(Utility.OPTION_SELECT_ALL) && entity.equals("ProgrammeSegmentHeader3")) {
                resp = header3Helper.fetchAll(Integer.parseInt(parent));

            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("ProgrammeSegment")) {
                synchronized (this) {
                    resp = helper.fetchOneJSON(id);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("ProgrammeSegmentHeader1")) {
                synchronized (this) {
                    resp = header1Helper.fetchOneJSON(id);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("ProgrammeSegmentHeader2")) {
                synchronized (this) {
                    resp = header2Helper.fetchOneJSON(id);
                }
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD) && entity.equals("ProgrammeSegmentHeader3")) {
                synchronized (this) {
                    resp = header3Helper.fetchOneJSON(id);

                }
            }

            if (option.equals(Utility.OPTION_GET_NEW_CODE) && entity.equals("ProgrammeSegmentHeader2")) {
                synchronized (this) {
                    resp = header2Helper.fetchCode(parent, 2);
                }
            }
            if (option.equals(Utility.OPTION_GET_NEW_CODE) && entity.equals("ProgrammeSegmentHeader3")) {
                synchronized (this) {
                    resp = header3Helper.fetchCode(parent, 8);
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ProgrammeSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ProgrammeSegmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
