package com.thirdshop.filter;

import com.thirdshop.utils.SystemContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SystemContextFilter implements Filter {
    private Integer pageSize;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try{
            pageSize=Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        } catch (NumberFormatException e){
            pageSize=15;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Integer offset=0;
        try{
            offset=Integer.parseInt(req.getParameter("pager.offset"));
        } catch (NumberFormatException e){

        }
        try{
            SystemContext.setOrder(req.getParameter("order"));
            SystemContext.setSort(req.getParameter("sort"));
            SystemContext.setPageOffset(offset);
            SystemContext.setPageSize(pageSize);
            SystemContext.setRealPath(((HttpServletRequest)req).getSession().getServletContext().getRealPath("/"));
            chain.doFilter(req,resp);
        } finally {
            SystemContext.removeOrder();
            SystemContext.removeSort();
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
            SystemContext.removeRealPath();
        }
    }

    @Override
    public void destroy() {

    }
}
