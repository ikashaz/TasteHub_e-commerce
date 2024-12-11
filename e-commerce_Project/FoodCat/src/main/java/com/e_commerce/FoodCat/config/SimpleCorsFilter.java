////package com.e_commerce.FoodCat.config;
////
////import jakarta.servlet.*;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.core.Ordered;
////import org.springframework.core.annotation.Order;
////import org.springframework.stereotype.Component;
////
////import java.io.IOException;
////import java.util.HashMap;
////import java.util.Map;
////
////@Component
////@Order(Ordered.HIGHEST_PRECEDENCE)
////public class SimpleCorsFilter implements Filter {
////
////    @Value("${app.client.url}")
////    private String clientAppUrl = "";
////
////    public SimpleCorsFilter() {
////    }
////
////    @Override
////    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
////            throws IOException, ServletException {
////        HttpServletResponse response = (HttpServletResponse) res;
////        HttpServletRequest request = (HttpServletRequest) req;
////        Map<String, String> map = new HashMap<>();
////        String originHeader = request.getHeader("origin");
////        response.setHeader("Access-Control-Allow-Origin", originHeader);
////        //allow the API called from front end
////        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
////        response.setHeader("Access-Control-Max-Age", "3600");
////        response.setHeader("Access-Control-Allow-Headers", "*");
////
////        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
////            response.setStatus(HttpServletResponse.SC_OK);
////        } else {
////            chain.doFilter(req, res);
////        }
////    }
////
////    @Override
////    public void init(FilterConfig filterConfig) {
////    }
////
////    @Override
////    public void destroy() {
////    }
////}
////
//////import jakarta.servlet.FilterChain;
//////import jakarta.servlet.FilterConfig;
//////import jakarta.servlet.ServletRequest;
//////import jakarta.servlet.ServletResponse;
//////import jakarta.servlet.http.HttpServletRequest;
//////import jakarta.servlet.http.HttpServletResponse;
//////import lombok.Value;
//////import org.springframework.core.Ordered;
//////import org.springframework.core.annotation.Order;
//////import org.springframework.stereotype.Component;
//////
//////import java.util.HashMap;
//////import java.util.Map;
//////import java.util.logging.Filter;
//////
//////@Component
//////@Order(Ordered.HIGHEST_PRECEDENCE)
//////public class SimpleCorsFilter implements Filter {
//////
//////    @Value("${app.client.url}")
//////    private String clientAppUrl= "";
//////
//////    public SimpleCorsFilter(){
//////
//////    }
//////
//////    @Override
//////    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain){
//////        HttpServletResponse response=(HttpServletResponse) res;
//////        HttpServletRequest request=(HttpServletRequest) req;
//////        Map<String, String> map = new HashMap<>();
//////        String originHeader=request.getHeader("origin");
//////        response.setHeader("Access-Control-Allow-Origin",originHeader);
//////        //allow the API called from front end
//////        response.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,OPTIONS,DELETE");
//////        response.setHeader("Access-Control-Max-Age","3600");
//////        response.setHeader("Access-Control-Allow-Headers","*");
//////
//////        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
//////            response.setStatus(HttpServletResponse.SC_OK);
//////        } else{
//////            chain.doFilter(req,res);
//////        }
//////    }
//////
//////}
//////
//////@Override
//////public void init(FilterConfig filterConfig){
//////
//////}
//////
//////@Override
//////public void destroy(){
//////
//////}
//
//package com.e_commerce.FoodCat.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCorsFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpServletRequest request = (HttpServletRequest) req;
//
//        String token = getTokenFromRequest(request);
//        //System.out.println("Token: " + token);
//
//        response.setHeader("Access-Control-Allow-Origin", "*"); // Set this to your client URL in production
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
//
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(req, res);
//        }
//
//
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            String token= bearerToken.substring(7);
//            System.out.println("Retrieved Token: " + token); // Log the token
//            return token;
//        }
//        return null;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {}
//
//    @Override
//    public void destroy() {}
//}
