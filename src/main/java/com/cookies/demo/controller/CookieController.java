package com.cookies.demo.controller;

import com.cookies.demo.common.PhpRoute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by akshay on 4/2/20
 *
 * @author akshay
 * CookieController
 */
@PhpRoute
@Controller
public class CookieController {
    @GetMapping("/evercookie_cache.php")
    public void cacheMethod(
            @RequestParam("name") String name,
            @RequestParam("cookie") String cookiee,
            HttpServletResponse resp,
            HttpServletRequest req) throws IOException {

        boolean cookieExists = false;
        String cookieValue = null;
        Cookie[] cookies = req.getCookies();

        if (null != cookies) {
            // Iterate over cookies until we find one named evercookie_cache
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("evercookie_cache")) {
                    cookieExists = true;
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        // If the cookie doesn't exist, send 304 Not Modified and exit.
        if (!cookieExists) {
            resp.setStatus(304);
            return;
        }

        // The cookie was present; set up the response headers.
        resp.setContentType("text/html");
        resp.addHeader("Last-Modified", "Wed, 30 Jun 2010 21:36:48 GMT");
        resp.addHeader("Expires", "Tue, 31 Dec 2030 23:30:45 GMT");
        resp.addHeader("Cache-Control", "private, max-age=630720000");

        // Print the contents of the cookie as the response body.
        ServletOutputStream body = resp.getOutputStream();

        try {
            body.print(cookieValue);
        } finally {
            body.close();
        }

        // And we're done.
        resp.setStatus(200);
        resp.flushBuffer();

    }

    @GetMapping("/evercookie_etag.php")
    public void etag(
            @RequestParam("name") String name,
            @RequestParam("cookie") String cookiee,
            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean cookieExists = false;
        String cookieValue = null;
        Cookie[] cookies = req.getCookies();

        if (null != cookies) {
            // Iterate over cookies until we find one named evercookie_etag
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("evercookie_etag")) {
                    cookieExists = true;
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        ServletOutputStream body = resp.getOutputStream();

        try {

            if (cookieExists) {
                // Cookie set; send cookie value as Etag header/response body.
                resp.addHeader("Etag", cookieValue);
                body.print(cookieValue);
            } else {
                // No cookie; set the body to the request's If-None-Match value.
                body.print(req.getHeader("If-None-Match"));
            }

        } finally {
            // close the output stream.
            body.close();
        }

        resp.setStatus(200);
    }

    @GetMapping("/evercookie_png.php")
    public void getPng(
            @RequestParam("name") String name,
            @RequestParam("cookie") String cookiee,
            HttpServletResponse resp,
            HttpServletRequest req) throws IOException {
        boolean cookieExists = false;
        String cookieValue = null;
        Cookie[] cookies = req.getCookies();

        if (null != cookies) {
            // Iterate over cookies until we find one named evercookie_png
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("evercookie_png")) {
                    cookieExists = true;
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        // If the cookie doesn't exist, send 304 Not Modified and exit.
        if (!cookieExists) {
            resp.setStatus(304);
            return;
        }

        // Generate a PNG image from the cookie value.
        BufferedImage image = new BufferedImage(200, 1, BufferedImage.TYPE_INT_ARGB);
        image.createGraphics().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        int x = 0;

        for (int i = 0; i < cookieValue.length(); i += 3) {
            // Treat every 3 chars of the cookie value as an {R,G,B} triplet.
            Color c = new Color(cookieValue.charAt(i), cookieValue.charAt(i + 1), cookieValue.charAt(i + 2));
            image.setRGB(x++, 0, c.getRGB());
        }

        // The cookie was present; set up the response headers.
        resp.setContentType("image/png");
        resp.addHeader("Last-Modified", "Wed, 30 Jun 2010 21:36:48 GMT");
        resp.addHeader("Expires", "Tue, 31 Dec 2033 23:30:45 GMT");
        resp.addHeader("Cache-Control", "private, max-age=630720000");

        // Send the generate image data as the response body.
        OutputStream body = resp.getOutputStream();

        try {
            ImageIO.write(image, "png", body);
        } finally {
            body.close();
        }

        // And we're done.
        resp.setStatus(200);
        resp.flushBuffer();
    }
}
