package com.sweetmart.modules.coupon.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券信息表
 * </p>
 *
 * @author wendy
 * @since 2019-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_coupon_info")
public class CouponInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 优惠券活动ID;定义生成规则 activity_type+activity_fig+yyyyMMddHHmmss+4位随机串
     */
    private String activityId;

    /**
     * 活动类型：P-卡系统支付券；M.营销券；
     */
    private String activityType;

    /**
     * 活动标记 D.档期营销（总部） C.采购营销 M.门店营销 S.供应商
     */
    private String activityFig;

    /**
     * 优惠券标题
     */
    private String couponTitle;

    /**
     * 优惠券类型：AMOUNT-固定面额(品类券)；COUPON_BUY-提货券；EXCHANGE-换购券；DISCOUNT-折扣券；GIFT-赠品券；
     */
    private String couponType;

    /**
     * 优惠券记帐方式：DISCOUNT:折扣 PAY:支付；BOTH:折扣+支付；
     */
    private String couponAccountType;

    /**
     * 优惠券售价,单位分
     */
    private Integer couponPrice;

    /**
     * 换购价格，单位分；当coupon_type=EXCHANGE时填写
     */
    private Integer exchangePrice;

    /**
     * 优惠券金额，单位分；当coupon_type=AMOUNT时填写
     */
    private Integer ticketMoney;

    /**
     * 折扣比例，例如8折填写0.8，95折填写0.95；当coupon_type=DISCOUNT时填写
     */
    private BigDecimal discountPercent;

    /**
     * 优惠券描述，例如：满50减10/10元优惠券/凭券购买5折优惠/凭券1元换购等/xx赠品
     */
    private String couponDesc;

    /**
     * 发券开始时间
     */
    private LocalDateTime startDate;

    /**
     * 发券结束时间
     */
    private LocalDateTime endDate;

    /**
     * 使用须知;可以是规则说明
     */
    private String useRules;

    /**
     * 券有效期类型：FIXED-固定日期时间范围有效；RELATIVE-相对领券时间有效期；
     */
    private String validateType;

    /**
     * 用券开始时间
     */
    private LocalDateTime useStartDate;

    /**
     * 用券结束时间
     */
    private LocalDateTime useEndDate;

    /**
     * 用券开始，领券后+X天开始生效
     */
    private Integer useStartDays;

    /**
     * 用券结束，领券后+Y天失效（Y>X）
     */
    private Integer useEndDays;

    /**
     * 状态：0.编辑；1.上线；-1.作废；
     */
    private Integer status;

    /**
     * 制单人
     */
    private String createBy;

    /**
     * 制单时间
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String remark;


}
