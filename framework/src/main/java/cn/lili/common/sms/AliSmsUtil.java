package cn.lili.common.sms;

import cn.lili.modules.message.entity.dos.SmsSign;
import cn.lili.modules.message.entity.dos.SmsTemplate;

import java.util.Map;

/**
 * @author Chopper
 * @version v4.1
 * @Description:
 * @since 2021/2/1 6:05 下午
 */
public interface AliSmsUtil {
    /**
     * 申请短信签名
     *
     * @param smsSign 短信签名
     */
    void addSmsSign(SmsSign smsSign) throws Exception;


    /**
     * 删除短信签名
     *
     * @param signName 签名名称
     */
    void deleteSmsSign(String signName) throws Exception;

    /**
     * 查询短信签名申请状态
     *
     * @param signName 签名名称
     */
    Map<String, Object> querySmsSign(String signName) throws Exception;

    /**
     * 修改未审核通过的短信签名，并重新提交审核。
     *
     * @param smsSign 短信签名
     */
    void modifySmsSign(SmsSign smsSign) throws Exception;

    /**
     * 修改未审核通过的短信模板，并重新提交审核。
     *
     * @param smsTemplate 短信模板
     * @throws Exception
     */
    void modifySmsTemplate(SmsTemplate smsTemplate) throws Exception;

    /**
     * 查看短信模板
     *
     * @param templateCode 短信模板CODE
     * @throws Exception
     */
    Map<String, Object> querySmsTemplate(String templateCode) throws Exception;

    /**
     * 申请短信模板
     *
     * @param smsTemplate 短信模板
     * @return
     * @throws Exception
     */
    String addSmsTemplate(SmsTemplate smsTemplate) throws Exception;

    /**
     * 删除短信模板
     *
     * @param templateCode 短信模板CODE
     * @throws Exception
     */
    void deleteSmsTemplate(String templateCode) throws Exception;
}
