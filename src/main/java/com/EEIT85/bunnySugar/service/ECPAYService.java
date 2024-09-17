package com.EEIT85.bunnySugar.service;

import com.EEIT85.bunnySugar.dto.PostMerchantDto;
import com.EEIT85.bunnySugar.dto.QueryOrderDTO;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.QueryTradeInfoObj;
import ecpay.payment.integration.exception.EcpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class ECPAYService {
    @Autowired
    OrdersRepository ordersRepository;

    public String ecpayCheckout(PostMerchantDto postMerchantDto) throws UnsupportedEncodingException {
        String form = null;
        try {

            //時間格式化
            String formattedDate = getFormattedCurrentDate();
            // 設定支付訊息
            AllInOne all = new AllInOne("");
            AioCheckOutALL obj = new AioCheckOutALL();
            obj.setMerchantTradeNo(postMerchantDto.getMerchantNo());
            obj.setMerchantTradeDate(formattedDate);
            obj.setTotalAmount(postMerchantDto.getTotal().toString());
            obj.setTradeDesc("test Description");
            obj.setItemName("本次購買總金額");
            obj.setReturnURL("http://localhost:8081/test");
            obj.setNeedExtraPaidInfo("N");
            obj.setClientBackURL("http://127.0.0.1:5174/orderList");
            obj.setNeedExtraPaidInfo("Y");
            form = all.aioCheckOut(obj, null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return form;
    }

    public String queryOrder(QueryOrderDTO queryOrderDTO) {
        // 初始化 AllInOne 物件
        AllInOne all = new AllInOne("");  // 這裡應該填入適當的參數，可能是 HashKey

        // 帶入merchantID及merchantTradeNo即可查詢訂單
        QueryTradeInfoObj queryTradeInfoObj = new QueryTradeInfoObj();
        queryTradeInfoObj.setMerchantID(queryOrderDTO.getMerchantID());
        queryTradeInfoObj.setMerchantTradeNo(queryOrderDTO.getMerchantTradeNo());
//        queryTradeInfoObj.setTimeStamp(EcpayFunction.genUnixTimeStamp());

        try {
            // 調用 AllInOne 的 queryTradeInfo 方法
            String result = all.queryTradeInfo(queryTradeInfoObj);
            // 將字串依據 "&" 分割為多個鍵值對
            String[] pairs = result.split("&");
            // 創建一個 JSON 物件來存放結果
            // 使用 Stream API 簡化分割和處理邏輯
            JSONObject json = new JSONObject();
            //把傳回的物件格式轉為json
            Arrays.stream(result.split("&"))
                    .map(pair -> pair.split("=", 2)) // 分割每個鍵值對
                    .forEach(keyValue -> json.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : "")); // 添加到 JSON
            return result;
        } catch (EcpayException e) {
            // 異常處理
            System.err.println("Error querying order: " + e.getMessage());
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // 封装日期格式化方法
    private String getFormattedCurrentDate() {
        //取得當前時間
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 去除小數
        String dateStr = localDateTime.toString();
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return dateTime.format(outputFormatter);
    }

//    HandlingCharge=0&ItemName=&MerchantID=2000132&MerchantTradeNo=e7d6872b&PaymentDate=&PaymentType=
//    &PaymentTypeChargeFee=0&TradeAmt=0&TradeDate=&TradeNo=&
//    TradeStatus=10200047&CheckMacValue=8470E0E2FA75D92BEFC2BDE9BF6E7407421413115002A1FA8E3EE0D32A4E2CBB

//    AlipayID=&AlipayTradeNo=&amount=123123&ATMAccBank=&ATMAccNo=&auth_code=777777
//    &card4no=2222&card6no=431195&CustomField1=&CustomField2=&CustomField3=&CustomField4=&eci=0
//    &ExecTimes=&Frequency=&gwsr=13401070&HandlingCharge=2462&ItemName=本次購買總金額
//    &MerchantID=2000132&MerchantTradeNo=e9d51cd6&PayFrom=&PaymentDate=2024/09/29 17:19:17&PaymentNo=
//    &PaymentType=Credit_CreditCard&PaymentTypeChargeFee=2462&PeriodAmount=
//    &PeriodType=&process_date=2024/09/29 17:19:17&red_dan=0&red_de_amt=0&red_ok_amt=0
//    &red_yet=0&staed=0&stage=0&stast=0&StoreID=&TenpayTradeNo=&TotalSuccessAmount=&TotalSuccessTimes=
//    &TradeAmt=123123&TradeDate=2024/09/29 17:18:47&TradeNo=2409291718474485&TradeStatus=1&WebATMAccBank=
//    &WebATMAccNo=&WebATMBankName=
//    &CheckMacValue=5806397A0629797D13966453FDC6BD8F60721782ED65C009E2F57FC4CF3FFEF2
}