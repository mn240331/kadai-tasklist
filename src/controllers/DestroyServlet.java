package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks_kanri;
import utils.DBUtil;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DestroyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // セッションスコープからタスクのIDを取得して
            // 該当のIDのタスク管理1件のみをデータベースから取得
            Tasks_kanri m = em.find(Tasks_kanri.class, (Integer) (request.getSession().getAttribute("Tasks_kanri_id")));

            em.getTransaction().begin();
            em.remove(m); // データ削除
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "削除が完了しました。"); // ここを追記
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("Tasks_kanri_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
