package controller.operation;

import controller.Controller;
import controller.HttpUtil;
import persistence.dao.BicycleDAO;
import persistence.dao.StationDAO;
import persistence.entity.Bicycle;
import persistence.entity.Station;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BicycleRegistController implements Controller {
    private final BicycleDAO bicycleDAO = BicycleDAO.getInstance();
    private final StationDAO stationDAO = StationDAO.getInstance();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            HttpUtil.forward(req, res, "/WEB-INF/view/operation/bicycle/bicycleRegist.jsp");
        }
        else if (req.getMethod().equals("POST")) {
            registOK(req, res);
        }
    }

    private void registOK(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long stationId = Long.parseLong(req.getParameter("stationId"));
       // Station station = stationDAO.findById(stationId); // 기존의 Station을 가져오는 로직

        Bicycle bicycle = Bicycle.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .state(req.getParameter("state"))
               // .station(station)
                .build();
        bicycleDAO.create(bicycle);

        HttpUtil.forward(req, res, "/WEB-INF/view/operation/bicycle/bicycleMgmt.jsp");
    }
}
