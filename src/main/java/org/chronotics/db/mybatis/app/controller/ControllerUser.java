package org.chronotics.db.mybatis.app.controller;

import javax.annotation.Resource;

import org.chronotics.db.mybatis.app.service.ServiceUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerUser {
	@Resource(name = "serviceUser")
    private ServiceUser serviceUser;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
          
        return "select done";
    }
}
