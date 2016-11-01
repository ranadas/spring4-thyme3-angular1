package com.rdas.controller;

import eu.bitwalker.useragentutils.UserAgent;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rdas on 16/10/2016.
 */
@RestController
@RequestMapping("/ua/")
public class UserAgentDetectorController {

    @RequestMapping(value = "/lite", method = RequestMethod.GET)
    public ResponseEntity showUserAgentLite(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Map<String, String> ua = new HashMap<>();
        ua.put("os", userAgent.getOperatingSystem().getName());
        ua.put("browser", userAgent.getBrowser().getName());
        ua.put("browserVer", userAgent.getBrowserVersion().toString());
        return new ResponseEntity(ua.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/dtl", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity showUA1(HttpServletRequest request) {
        // Get an UserAgentStringParser and analyze the requesting client
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));

        Map<String, String> ua = new HashMap<>();
        ua.put("os", agent.getOperatingSystem().getName());
        ua.put("device", agent.getDeviceCategory().getName());
        ua.put("userAgentType", agent.getTypeName());
        ua.put("browser", agent.getName());
        ua.put("browserVer", agent.getVersionNumber().toVersionString());
        return new ResponseEntity(ua.toString(), HttpStatus.OK);
    }
}
