package cn.et.yitao.home.dao;

import cn.et.yitao.home.bean.Advertising;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @描述: 广告类的dao层
 * @Author: pyj
 * @DateTime: 2018/10/14 0014--下午 2:53
 */
@Repository
public interface ADsDao {

    /**
     * 功能描述 查询所有未删除的广告
     * @author pyj
     * @date 2018/10/14 0014
     * @param
     * @return java.util.List<cn.et.yitao.content.bean.ADs>
     */
    List<Advertising> findAllADs();

    /**
     * author:chenqi
     * desc:添加广告
     * @param advertising
     */
    void addAds(Advertising advertising);

    /**
     * author:chenqi
     * desc:通用更新广告
     * @param advertising
     * @return
     */
    Integer updateAds(Advertising advertising);

    /**
     * author:chenqi
     * desc:通用查询广告
     * @param advertising
     * @return
     */
    List<Advertising> getListAds(Advertising advertising);

    /**
     * author:chenqi
     * desc:模糊查询
     * @param advertising
     * @return
     */
    List<Advertising> getListDimAds(Advertising advertising);

}
