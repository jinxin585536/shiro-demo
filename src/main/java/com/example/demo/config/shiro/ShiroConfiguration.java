package com.example.demo.config.shiro;

import com.example.demo.config.SessionFilter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huangyy@zjiec.com
 * @date 2020/10/26
 */
@Configuration
public class ShiroConfiguration {

   /*@Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/auth/login", "anon");
        chainDefinition.addPathDefinition("/image/**","anon");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }*/

    /**
     * 将{@link CustomRealm}注册到shiro
     */
    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }


    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setUnauthorizedUrl("/401");

        Map<String, String> filterMap = new LinkedHashMap<>();
        //anon 不拦截
        filterMap.put("/auth/login", "anon");

        //自定义过滤器
        Map<String, Filter> filters = shiroFilter.getFilters();
        filters.put("erp", new SessionFilter());

        shiroFilter.setFilters(filters);
        filterMap.put("/**", "erp");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

}
