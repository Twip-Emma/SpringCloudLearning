package cn.itcast.order.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 七画一只妖
 * @Date: 2022/2/9 11:10
 */
@Component
public class HeaderOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        //获取请求头
        String origin = request.getHeader("origin");
        //非空判断
        if(StringUtil.isEmpty(origin)){
            origin = "blank";
        }
        return origin;
    }
}
