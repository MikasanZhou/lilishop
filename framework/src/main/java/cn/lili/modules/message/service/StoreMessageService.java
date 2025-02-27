package cn.lili.modules.message.service;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.message.entity.dos.StoreMessage;
import cn.lili.modules.message.entity.vos.StoreMessageQueryVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 店铺接收消息业务层
 *
 * @author Chopper
 * @date 2020/11/17 3:44 下午
 */
public interface StoreMessageService extends IService<StoreMessage> {


    /**
     * 通过消息id删除
     *
     * @param messageId
     */
    boolean deleteByMessageId(String messageId);

    /**
     * 多条件分页获取
     *
     * @param storeMessageQueryVO
     * @param pageVO
     * @return
     */
    IPage<StoreMessage> getPage(StoreMessageQueryVO storeMessageQueryVO, PageVO pageVO);

    /**
     * 保存店铺消息信息
     *
     * @param messages 消息
     * @return
     */
    boolean save(List<StoreMessage> messages);


    /**
     * 修改店铺消息状态
     *
     * @param status 状态
     * @param id     id
     * @return
     */
    boolean editStatus(String status, String id);

}