package cn.et.yitao.home.service.impl;

import cn.et.yitao.home.bean.Advertising;
import cn.et.yitao.home.dao.ADsDao;
import cn.et.yitao.home.service.ADsService;
import cn.et.yitao.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @描述: 广告的service实现层
 * @Author: pyj
 * @DateTime: 2018/10/14 0014--下午 3:14
 */
@Service
public class ADsServiceImpl implements ADsService {
    private static final Logger LOGGER = Logger.getLogger(ADsServiceImpl.class);

    @Autowired
    private ADsDao aDsDao;

    @Autowired
    private  RedisTemplate redisTemplate;

    @Override
    public List<Advertising> selectAllADs() {
        List<Advertising> advertisingList = new ArrayList<>();
        ListOperations listOperations = redisTemplate.opsForList();
        if (redisTemplate.hasKey("lunbotu")) {
            List<Advertising> lunbotu = listOperations.range("lunbotu", 0, -1);
            System.out.println("从redis中查数据" + lunbotu.size());
            return lunbotu;
        }
        List<Advertising> allADs = aDsDao.findAllADs();
        Long lunbotu = listOperations.leftPushAll("lunbotu", allADs);
        System.out.println("插入redis中"+lunbotu);
        return allADs;
    }

    @Override
    public List<Map> getListDimAds(Advertising advertising) {
        advertising.setIsdelete(0);
        List<Advertising> listDimAds = aDsDao.getListDimAds(advertising);
        List<Map> mapList = new ArrayList<>();
        for (Advertising advertising1 : listDimAds) {
            Map map = new HashMap();
            map.put("id", advertising1.getId());
            map.put("headline", advertising1.getHeadline());
            map.put("subhead", advertising1.getSubhead());
            map.put("url", advertising1.getUrl());
            map.put("link", advertising1.getLink());
            map.put("createDate", DateUtils.toDateDay(advertising1.getCreateDate()));
            mapList.add(map);
            LOGGER.debug(advertising1);
        }
        return mapList;
    }

    @Override
    public Integer updateAds(Advertising advertising) {
        return aDsDao.updateAds(advertising);
    }

    @Override
    public void addAds(Advertising advertising) {
        advertising.setCreateDate(new Date());
        aDsDao.addAds(advertising);
    }

    @Override
    public Integer deleteAds(Integer id) {
        Advertising advertising = new Advertising();
        advertising.setIsdelete(1);
        advertising.setId(id);
        return aDsDao.updateAds(advertising);
    }

    @Override
    public Advertising getAdsById(Integer id) {
        Advertising advertising = new Advertising();
        advertising.setId(id);
        List<Advertising> listAds = aDsDao.getListAds(advertising);
        if (listAds != null && !listAds.isEmpty()) return listAds.get(0);
        return null;
    }


}
