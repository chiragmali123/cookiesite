package com.cookies.demo.controller;

import com.cookies.demo.model.Viewer;
import com.cookies.demo.repo.ViewerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by akshay on 4/2/20
 *
 * @author akshay
 * ViewerController
 */

@Controller
public class ViewerController {

    @Autowired
    ViewerRepo viewerRepo;

    @GetMapping("/pixel")
    @ResponseBody
    public Viewer getPixel(@RequestParam(name="id") String id,
                           HttpServletResponse resp,
                           HttpServletRequest req) {
        Viewer byViewerId = viewerRepo.findByViewerId(id);

        Viewer v=null;
        if (byViewerId==null){
            //create
            String remoteAddr = "";

            if (req != null) {
                remoteAddr = req.getHeader("X-FORWARDED-FOR");
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = req.getRemoteAddr();
                }
            }

            v = new Viewer()
            .setViewerId(id)
            .setPublicIp(remoteAddr)
            .setUserAgent(req.getHeader("User-Agent"))
            .setImage("https://wootagtest.s3-ap-southeast-1.amazonaws.com/tj.png");

            viewerRepo.save(v);
        }
        return v;
    }

    @GetMapping("/getimage")
    @ResponseBody
    public Viewer getImage(@RequestParam(name="id") String id,
                           HttpServletResponse resp,
                           HttpServletRequest req) {
        Viewer byViewerId = viewerRepo.findByViewerId(id);
        if(byViewerId==null){
            String remoteAddr = "";

            if (req != null) {
                remoteAddr = req.getHeader("X-FORWARDED-FOR");
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = req.getRemoteAddr();
                }
            }

            Viewer v = new Viewer()
                    .setViewerId(id)
                    .setPublicIp(remoteAddr)
                    .setUserAgent(req.getHeader("User-Agent"))
                    .setImage("https://wootagtest.s3-ap-southeast-1.amazonaws.com/tj.png");

            viewerRepo.save(v);
            return v;
        }
        return byViewerId;
    }

}
