package com.example.webserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("time filter start");
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        logger.info("time filter consume {} ms", endTime - startTime);
        logger.info("time filter end");
    }

    @Override
    public void destroy() {
        logger.info("time filter destroy");
    }
}
