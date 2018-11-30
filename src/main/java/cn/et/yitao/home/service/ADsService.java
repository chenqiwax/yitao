package cn.et.yitao.home.service;

import cn.et.yitao.home.bean.Advertising;

import java.util.List;
import java.util.Map;

/**
 * @描述: 广告的service层
 * @Author: pyj
 * @DateTime: 2018/10/14 0014--下午 3:11
 */
public interface ADsService {

    /**
     * 功能描述 查询广告
     *
     * @param
     * @return java.util.List<cn.et.yitao.content.bean.ADs>
     * @author pyj
     * @date 2018/10/14 0014
     */
    List<Advertising> selectAllADs();

    /**
     * author:chenqi
     * desc:模糊查询
     *
     * @param advertising
     * @return
     */
    List<Map> getListDimAds(Advertising advertising);

    /**
     * author:chenqi
     * desc:更新广告
     *
     * @param advertising
     * @return
     */
    Integer updateAds(Advertising advertising);

    /**
     * author:chenqi
     * desc:添加广告
     *
     * @param advertising
     */
    void addAds(Advertising advertising);

    /**
     * author:chenqi
     * desc:删除广告
     *
     * @param id
     * @return
     */
    Integer deleteAds(Integer id);

    /**
     * author:chenqi
     * desc:通过id查询广告
     *
     * @param id
     * @return
     */
    Advertising getAdsById(Integer id);
}
