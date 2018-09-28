package com.chen.web.springboot.interceptor;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=======初始化过滤器=========");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        long time = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("耗时："+(System.currentTimeMillis() - time));
    }

    @Override
    public void destroy() {
        System.out.println("===========销毁过滤器============");
    }
}
