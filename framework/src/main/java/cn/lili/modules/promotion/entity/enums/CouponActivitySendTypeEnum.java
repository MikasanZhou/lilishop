package cn.lili.modules.promotion.entity.enums;

/**
 * 优惠券活动发送类型枚举
 *
 * @author Bulbasaur
 * @date: 2021/5/20 5:47 下午
 */
public enum CouponActivitySendTypeEnum {

    ALL("全部会员"),
    DESIGNATED("指定会员");

    private final String description;

    CouponActivitySendTypeEnum(String str) {
        this.description = str;
    }

    public String description() {
        return description;
    }
}
