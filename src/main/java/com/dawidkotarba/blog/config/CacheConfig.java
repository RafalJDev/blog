package com.dawidkotarba.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableCaching
class CacheConfig {

    @Value("classpath:ehcache.xml")
    private Resource ehCacheConfiguration;

    @Bean
    CacheManager ehCacheManager() {
        return new EhCacheCacheManager(ehCacheFactoryBean().getObject());
    }

    @Bean
    EhCacheManagerFactoryBean ehCacheFactoryBean() {
        final EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(ehCacheConfiguration);
        factoryBean.setShared(true);
        return factoryBean;
    }
}
