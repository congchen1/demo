package com.redis.demo.config;

import com.redis.demo.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {
    /*
    最大空闲数
     */
    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    /*
    控制一个pool可分配多少个jedis实例,用来替换上面的redis.maxActive,如果是jedis 2.4以后用该属性
     */
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    /*
    最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
     */
    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;
    /*
    连接的最小空闲时间 默认1800000毫秒(30分钟)
     */
    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    /*
    每次释放连接的最大数目,默认3
     */
    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;
    /*
    逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
     */
    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.cluster.max-redirects}")
    private Integer mmaxRedirectsac;

    /**
     * JedisPool连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(maxIdle);
        //最大连接数
        poolConfig.setMaxTotal(maxTotal);
        //连接等待时间
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestWhileIdle(testWhileIdle);
        return poolConfig;
    }

    /**
     * 单机版配置
     * @Title: JedisConnectionFactory
     * @param @param jedisPoolConfig
     * @param @return
     * @return JedisConnectionFactory
     * @autor lpl
     * @date 2018年2月24日
     * @throws
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //连接池
        JedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //IP地址
        JedisConnectionFactory.setHostName("192.168.177.128");
        //端口号
        JedisConnectionFactory.setPort(6379);
        //如果Redis设置有密码
        //JedisConnectionFactory.setPassword(password);
        //客户端超时时间单位是毫秒
        JedisConnectionFactory.setTimeout(5000);
        return JedisConnectionFactory;
    }

    /**
     * Redis集群配置
     * @return
     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = clusterNodes.split(",");
//        Set<RedisNode> nodes = new HashSet<>();
//        for(String host : serverArray){
//            String[] ipAndPort = host.split(":");
//            nodes.add(new RedisNode(ipAndPort[0],Integer.valueOf(ipAndPort[2])));
//        }
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(mmaxRedirectsac);
//        return redisClusterConfiguration;
//    }

    /**
     * 配置redis的哨兵
     * @return RedisSentinelConfiguration
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
//    @Bean
//    public RedisSentinelConfiguration sentinelConfiguration(){
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
//        //配置matser的名称
//        RedisNode redisNode = new RedisNode(hostName, port);
//        redisNode.setName("mymaster");
//        redisSentinelConfiguration.master(redisNode);
//        //配置redis的哨兵sentinel
//        RedisNode senRedisNode = new RedisNode(senHost1,senPort1);
//        Set<RedisNode> redisNodeSet = new HashSet<>();
//        redisNodeSet.add(senRedisNode);
//        redisSentinelConfiguration.setSentinels(redisNodeSet);
//        return redisSentinelConfiguration;
//    }

    /**
     * 配置工厂
     * @return
     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        //集群模式
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration(),jedisPoolConfig());
//        //哨兵模式
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig,jedisPoolConfig);
//        return  jedisConnectionFactory;
//    }

    /**
     * 实例化Template对象
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        initDomainRedisTemplate(redisTemplate,redisConnectionFactory);

        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     * @param template
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String,Object> template,
                                         RedisConnectionFactory factory){
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //开启事物
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(factory);
    }
    /**
     * 注入封装RedisTemplate
     * @Title: redisUtil
     * @return RedisUtil
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
    @Bean(name = "redisUtil")
    public RedisUtils redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils redisUtil = new RedisUtils();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

}
