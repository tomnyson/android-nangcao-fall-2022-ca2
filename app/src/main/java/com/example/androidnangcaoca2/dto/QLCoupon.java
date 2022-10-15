package com.example.androidnangcaoca2.dto;

import java.util.ArrayList;
import java.util.List;

public class QLCoupon {
    List<Coupon> coupons = new ArrayList<>();

    public QLCoupon() {
        coupons.add(new Coupon("MEM53", 10));
        coupons.add(new Coupon("MEM5371", 20));
        coupons.add(new Coupon("VIP41", 30));
    }

    public boolean add(Coupon coupon) {
        this.coupons.add(coupon);
        return  true;
    }
    public Coupon getDetail(String code) {
     Coupon coupon = null;
     for(Coupon coupon1:coupons) {
         if (coupon1.getCode().equals(code)) {
             return  coupon1;
         }
     }
     return  null;
    }
    public List<Coupon> getList() {
        return  this.coupons;
    }
}
