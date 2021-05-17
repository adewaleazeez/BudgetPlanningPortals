/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpp.servlet;

import com.bpp.hibernate.ProjectDetailHibernateHelper;
import com.bpp.hibernate.ProjectDetail;
import com.bpp.hibernate.UsersHibernateHelper;
import com.bpp.utility.Utility;
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
public class ProjectDetailServlet extends HttpServlet {

    /**
     * Processes requests fo+r both HTTP <code>GET</code> and <code>POST</code>
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

            ProjectDetailHibernateHelper helper = new ProjectDetailHibernateHelper();
            Utility utility = new Utility();

            String option;
            String id;
            String name;
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
            String programme;
            String objective;
            String project_code;
            
            String resp = null;

            option = request.getParameter("option");
            if (option == null) {
                option = "";
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

            mda = request.getParameter("mda");
            if (mda == null) {
                mda = "";
            }
            
//            String _mda;
//            int imda=0;
//            _mda = request.getParameter("MDA");
//            if (_mda != null && !_mda.equals("")) {
//                imda = Integer.parseInt(_mda);
//            }
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

            programme = request.getParameter("programme");
            if (programme == null) {
                programme = "";
            }

            objective = request.getParameter("objective");
            if (objective == null) {
                objective = "";
            }

            project_code = request.getParameter("project_code");
            if (project_code == null) {
                project_code = "";
            }

            if (option.equals(Utility.OPTION_UPDATE)) {
                synchronized (this) {
                    ProjectDetail projectDetail = helper.fetchOne(id);
                    if (projectDetail != null) {
                        projectDetail.setName(name);
                        projectDetail.setCode(code);
                        projectDetail.setMda(Integer.parseInt(mda));
                        projectDetail.setDepartment(Integer.parseInt(department));
                        projectDetail.setSectorGoal(sector_goal);
                        projectDetail.setSectorProgramme(sector_programme);
                        projectDetail.setYearId(Integer.parseInt(year_id));
                        projectDetail.setProjectStatus(Integer.parseInt(project_status));
                        projectDetail.setScore(Float.parseFloat(score));
                        projectDetail.setRank(Integer.parseInt(rank));
                        projectDetail.setPolicy(policy);
                        projectDetail.setProgramme(programme);
                        projectDetail.setObjective(objective);
                        projectDetail.setProjectCode(project_code);
                        resp = helper.update(projectDetail);
                        if(resp.equals(Utility.ActionResponse.UPDATED.toString())){
                            helper.rankProjects();
                        }
                    } else {
                        //resp = Utility.ActionResponse.NO_RECORD.toString();
                        option = Utility.OPTION_INSERT;
                    }
                }
            }

            if (option.equals(Utility.OPTION_INSERT)) {
                synchronized (this) {
                    String proj_code = (Integer.parseInt(helper.fetchProjectCode(Integer.parseInt(mda), policy, programme)) + 1) + "";
                    project_code = policy + programme + repeatNTimes("0", 6 - proj_code.length()) + proj_code + objective;
                    code = repeatNTimes("0", 6 - proj_code.length()) + proj_code;
                    ProjectDetail projectDetail = new ProjectDetail();
                    projectDetail.setId(Integer.parseInt(helper.getMaxSerialNo("Project_Detail")) + 1);
                    projectDetail.setDateCreated(utility.dbDateNow());
                    projectDetail.setName(name);
                    projectDetail.setOrgId(1);
                    projectDetail.setCode(code);
                    projectDetail.setMda(Integer.parseInt(mda));
                    projectDetail.setDepartment(Integer.parseInt(department));
                    projectDetail.setSectorGoal(sector_goal);
                    projectDetail.setSectorProgramme(sector_programme);
                    projectDetail.setYearId(Integer.parseInt(year_id));
                    projectDetail.setProjectStatus(Integer.parseInt(project_status));
                    projectDetail.setScore(Float.parseFloat(score));
                    projectDetail.setRank(Integer.parseInt(rank));
                    projectDetail.setPolicy(policy);
                    projectDetail.setProgramme(programme);
                    projectDetail.setObjective(objective);
                    projectDetail.setProjectCode(project_code);
                    resp = helper.insert(projectDetail);
                    if(resp.equals(Utility.ActionResponse.INSERTED.toString())){
                        resp += project_code;
                        helper.rankProjects();
                    }
                }
            }

            if (option.equals(Utility.OPTION_DELETE)) {
                synchronized (this) {
                    ProjectDetail projectDetail = new ProjectDetail();
                    projectDetail = helper.fetchOne(id);
                    if (projectDetail != null) {
                        resp = helper.delete(projectDetail);
                    } else {
                        resp = Utility.ActionResponse.NO_RECORD.toString();
                    }
                }
            }
            if (option.equals(Utility.OPTION_SELECT_ALL)) {
                //helper.updateScoreRank();
                resp = helper.fetchAll();
            }

            if (option.equals(Utility.OPTION_SELECT_A_RECORD)) {
                resp = helper.fetchOneJSON(id);
            }

            if (option.equals(Utility.OPTION_GET_NEW_CODE)) {
                resp = helper.fetchCode(parent, 8);
            }

            out.println(resp);
        } finally {
            out.close();
        }
    }

    String repeatNTimes(String s, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(s);
        }
        return builder.toString();
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
