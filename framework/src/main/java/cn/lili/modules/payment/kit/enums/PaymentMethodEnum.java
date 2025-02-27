package cn.lili.modules.payment.kit.enums;

/**
 * 支付方式枚举
 *
 * @author Chopper
 * @date 2020/12/18 18:08
 */
public enum PaymentMethodEnum {

    WECHAT("wechatPlugin", "微信"),
    ALIPAY("aliPayPlugin", "支付宝"),
    WALLET("walletPlugin", "余额支付"),
    BANK_TRANSFER("bankTransferPlugin", "线下转账");

    /**
     * 插件id 调用对象，需要实现payment接口
     */
    private final String plugin;
    /**
     * 支付名称
     */
    private final String paymentName;

    public String getPlugin() {
        return plugin;
    }

    public String paymentName() {
        return paymentName;
    }

    /**
     * 根据支付方式名称返回对象
     *
     * @param name
     * @return
     */
    public static PaymentMethodEnum paymentNameOf(String name) {
        for (PaymentMethodEnum value : PaymentMethodEnum.values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }

    PaymentMethodEnum(String plugin, String paymentName) {
        this.plugin = plugin;
        this.paymentName = paymentName;
    }

}
