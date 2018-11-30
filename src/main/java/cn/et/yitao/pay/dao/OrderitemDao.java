package cn.et.yitao.pay.dao;

import cn.et.yitao.pay.bean.Orderitem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情de Dao层
 * Author:zhoupengfei
 * Datetime:2018年10月11日 23:09
 */
@Repository
public interface OrderitemDao {
    /**
     * 根据订单id查询订单详情
     * @param oid
     * @return
     */
    List<Orderitem> getListOrderitemByoid(Integer oid);


    /**
     * 添加订单详情
     * @param orderitem
     * @return
     */
    Integer orderitemAdd(Orderitem orderitem);
}
