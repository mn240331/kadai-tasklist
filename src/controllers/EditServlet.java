package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks_kanri;
import utils.DBUtil;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EditServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 該当のIDのタスク管理1件のみをデータベースから取得
        Tasks_kanri m = em.find(Tasks_kanri.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        // タスク管理情報とセッションIDをリクエストスコープに登録
        request.setAttribute("Tasks_kanri", m);
        request.setAttribute("_token", request.getSession().getId());

        // タスク管理データが存在しているときのみ
        // メッセージIDをセッションスコープに登録
        if(m != null) {
            request.getSession().setAttribute("Tasks_kanri_id", m.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
    }
}
