package practice.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import practice.core.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //provider과 똑같이 동작함

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        System.out.println(" = " + myLogger.getClass());

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        //Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
