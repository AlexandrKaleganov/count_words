package ru.callinsicght.countwords.container;

import org.apache.log4j.Logger;
import ru.callinsicght.countwords.reposiroty.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletIndex extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletIndex.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/vievs/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
//                PrintWriter writer = new PrintWriter(resp.getOutputStream());
//                writer.append(new ObjectMapper().writeValueAsString(Dispatch.getInstance().access(action,
//                        .getInstance().addAllObject(
//                                req.getParameter("an"), req.getParameter("car"),
//                                (ArrayList<Photo>) req.getSession().getAttribute("phList")))));
//                writer.flush();
    }
}
