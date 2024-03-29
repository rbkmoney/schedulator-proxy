package com.rbkmoney.schedulatorproxy.service;

import com.rbkmoney.damsel.schedule.ScheduledJobExecutorSrv;
import com.rbkmoney.woody.thrift.impl.http.THServiceBuilder;
import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet("/v1/schedulator/proxy")
public class SchedulatorProxyServlet extends GenericServlet {

    private Servlet thriftServlet;

    @Autowired
    private ScheduledJobExecutorSrv.Iface requestHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        thriftServlet =
              new THServiceBuilder().build(ScheduledJobExecutorSrv.Iface.class, requestHandler);
    }

    @Override
    public void service(ServletRequest request, ServletResponse response)
          throws ServletException, IOException {
        thriftServlet.service(request, response);
    }
}
