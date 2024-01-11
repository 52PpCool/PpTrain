package com.ppku.trains.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/ocr")
public class ocrController {
    private String secretId = "205a69df725a4613b5cf7e3908004d74";

    /**
     * 客户端密钥
     */
    private String secretKey = "c70a1da695ea433e9fc5802ca68eddfa";

    @PostMapping("/baodan")
    public Map certificateCardOcr(@RequestBody MultipartFile file) throws Exception {
        //转BASE64

        JSONObject reqData = new JSONObject();//请求数据
        String imageBaseStr = Base64Utils.encodeToString(file.getBytes());
        Map<String, String> obj = new HashMap<>();
        reqData.put("ImageBase64", imageBaseStr);
        obj.put("sign", getAuthorizationHeader(secretId, secretKey, reqData.toJSONString()));
        obj.put("imageBaseStr", imageBaseStr);
        return obj;
    }

    public static String getAuthorizationHeader(String secretId, String secretKey, String bodyParam) throws Exception {
        String timestamp = String.valueOf(System.currentTimeMillis());
        // 1.0 组装待加签字符串
        String toDeal = bodyParam + "&" + secretId + "&" + secretKey + "&" + timestamp;
        // 2.0 使用sha256算法进行加签
        return sha256Hex(toDeal);
    }

    private static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return bytesToHexFun(d);
    }

    private static String bytesToHexFun(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            buf.append(String.format("%02x", b & 0xff));
        }
        return buf.toString();
    }

    public static void main(String[] args) throws IOException {
        z01();

    }

    // 腾讯保单 人保
    public static void t01() {

        List<ApplyGuarantee> applyGuaranteeList = new ArrayList<>();

        JSONArray structuralList = null;
        ApplyGuarantee applyGuarantee = new ApplyGuarantee();
        String res
            = "{\"Response\":{\"Angle\":-0.007890728302299976,\"RequestId\":\"58b38db1-e75c-4463-89f7-d45c7b509916\",\"StructuralList\":[{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"承保险种\"},\"Value\":{\"AutoContent\":\"新能源车损失险\",\"Coord\":{\"LeftBottom\":{\"X\":58,\"Y\":763},\"LeftTop\":{\"X\":58,\"Y\":718},\"RightBottom\":{\"X\":189,\"Y\":763},\"RightTop\":{\"X\":189,\"Y\":718}}}},{\"Key\":{\"AutoName\":\"承保险种\"},\"Value\":{\"AutoContent\":\"第三者责任保险\",\"Coord\":{\"LeftBottom\":{\"X\":595,\"Y\":741},\"LeftTop\":{\"X\":595,\"Y\":718},\"RightBottom\":{\"X\":726,\"Y\":741},\"RightTop\":{\"X\":726,\"Y\":718}}}},{\"Key\":{\"AutoName\":\"保险金额/责任限额(元)\"},\"Value\":{\"AutoContent\":\"112800元\",\"Coord\":{\"LeftBottom\":{\"X\":321,\"Y\":741},\"LeftTop\":{\"X\":321,\"Y\":720},\"RightBottom\":{\"X\":395,\"Y\":741},\"RightTop\":{\"X\":395,\"Y\":720}}}},{\"Key\":{\"AutoName\":\"保险金额/责任限额(元)\"},\"Value\":{\"AutoContent\":\"2000000元\",\"Coord\":{\"LeftBottom\":{\"X\":852,\"Y\":741},\"LeftTop\":{\"X\":852,\"Y\":720},\"RightBottom\":{\"X\":939,\"Y\":741},\"RightTop\":{\"X\":939,\"Y\":720}}}},{\"Key\":{\"AutoName\":\"保险费(元)\"},\"Value\":{\"AutoContent\":\"3056.89\",\"Coord\":{\"LeftBottom\":{\"X\":525,\"Y\":740},\"LeftTop\":{\"X\":525,\"Y\":720},\"RightBottom\":{\"X\":592,\"Y\":740},\"RightTop\":{\"X\":592,\"Y\":720}}}},{\"Key\":{\"AutoName\":\"保险费(元)\"},\"Value\":{\"AutoContent\":\"1487.48\",\"Coord\":{\"LeftBottom\":{\"X\":1062,\"Y\":738},\"LeftTop\":{\"X\":1062,\"Y\":720},\"RightBottom\":{\"X\":1129,\"Y\":738},\"RightTop\":{\"X\":1129,\"Y\":720}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"承保险种\"},\"Value\":{\"AutoContent\":\"车上司机责任险\",\"Coord\":{\"LeftBottom\":{\"X\":58,\"Y\":767},\"LeftTop\":{\"X\":58,\"Y\":744},\"RightBottom\":{\"X\":188,\"Y\":767},\"RightTop\":{\"X\":188,\"Y\":744}}}},{\"Key\":{\"AutoName\":\"承保险种\"},\"Value\":{\"AutoContent\":\"车上乘客责任险\",\"Coord\":{\"LeftBottom\":{\"X\":595,\"Y\":768},\"LeftTop\":{\"X\":595,\"Y\":745},\"RightBottom\":{\"X\":724,\"Y\":768},\"RightTop\":{\"X\":724,\"Y\":745}}}},{\"Key\":{\"AutoName\":\"保险金额/责任限额(元)\"},\"Value\":{\"AutoContent\":\"10000元\",\"Coord\":{\"LeftBottom\":{\"X\":324,\"Y\":769},\"LeftTop\":{\"X\":324,\"Y\":745},\"RightBottom\":{\"X\":392,\"Y\":769},\"RightTop\":{\"X\":392,\"Y\":745}}}},{\"Key\":{\"AutoName\":\"保险金额/责任限额(元)\"},\"Value\":{\"AutoContent\":\"10000元X4座\",\"Coord\":{\"LeftBottom\":{\"X\":838,\"Y\":767},\"LeftTop\":{\"X\":838,\"Y\":745},\"RightBottom\":{\"X\":957,\"Y\":767},\"RightTop\":{\"X\":957,\"Y\":745}}}},{\"Key\":{\"AutoName\":\"保险费(元)\"},\"Value\":{\"AutoContent\":\"17.27\",\"Coord\":{\"LeftBottom\":{\"X\":543,\"Y\":767},\"LeftTop\":{\"X\":543,\"Y\":747},\"RightBottom\":{\"X\":592,\"Y\":767},\"RightTop\":{\"X\":592,\"Y\":747}}}},{\"Key\":{\"AutoName\":\"保险费(元)\"},\"Value\":{\"AutoContent\":\"32.91\",\"Coord\":{\"LeftBottom\":{\"X\":1079,\"Y\":767},\"LeftTop\":{\"X\":1079,\"Y\":747},\"RightBottom\":{\"X\":1128,\"Y\":767},\"RightTop\":{\"X\":1128,\"Y\":747}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"承保险种\"},\"Value\":{\"AutoContent\":\"道路救援增值服务特约条款\",\"Coord\":{\"LeftBottom\":{\"X\":58,\"Y\":794},\"LeftTop\":{\"X\":58,\"Y\":771},\"RightBottom\":{\"X\":280,\"Y\":794},\"RightTop\":{\"X\":280,\"Y\":771}}}},{\"Key\":{\"AutoName\":\"保险金额/责任限额(元)\"},\"Value\":{\"AutoContent\":\"7次\",\"Coord\":{\"LeftBottom\":{\"X\":343,\"Y\":794},\"LeftTop\":{\"X\":343,\"Y\":772},\"RightBottom\":{\"X\":377,\"Y\":794},\"RightTop\":{\"X\":377,\"Y\":772}}}},{\"Key\":{\"AutoName\":\"保险费(元)\"},\"Value\":{\"AutoContent\":\"0.00\",\"Coord\":{\"LeftBottom\":{\"X\":551,\"Y\":794},\"LeftTop\":{\"X\":551,\"Y\":774},\"RightBottom\":{\"X\":592,\"Y\":794},\"RightTop\":{\"X\":592,\"Y\":774}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"机构\"},\"Value\":{\"AutoContent\":\"中国银行保险监督管理委员会\",\"Coord\":{\"LeftBottom\":{\"X\":58,\"Y\":35},\"LeftTop\":{\"X\":58,\"Y\":8},\"RightBottom\":{\"X\":372,\"Y\":35},\"RightTop\":{\"X\":372,\"Y\":8}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"标题\"},\"Value\":{\"AutoContent\":\"神行车保新能源汽车保险单(电子保单)\",\"Coord\":{\"LeftBottom\":{\"X\":351,\"Y\":106},\"LeftTop\":{\"X\":351,\"Y\":71},\"RightBottom\":{\"X\":832,\"Y\":106},\"RightTop\":{\"X\":832,\"Y\":71}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"机构\"},\"Value\":{\"AutoContent\":\"太平洋保险\",\"Coord\":{\"LeftBottom\":{\"X\":151,\"Y\":201},\"LeftTop\":{\"X\":151,\"Y\":164},\"RightBottom\":{\"X\":319,\"Y\":201},\"RightTop\":{\"X\":319,\"Y\":164}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"company\"},\"Value\":{\"AutoContent\":\"CPIC\",\"Coord\":{\"LeftBottom\":{\"X\":152,\"Y\":228},\"LeftTop\":{\"X\":152,\"Y\":200},\"RightBottom\":{\"X\":220,\"Y\":228},\"RightTop\":{\"X\":220,\"Y\":200}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"号码\"},\"Value\":{\"AutoContent\":\"DZCB22480000040868\",\"Coord\":{\"LeftBottom\":{\"X\":793,\"Y\":246},\"LeftTop\":{\"X\":793,\"Y\":221},\"RightBottom\":{\"X\":1013,\"Y\":246},\"RightTop\":{\"X\":1013,\"Y\":221}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险单号\"},\"Value\":{\"AutoContent\":\"ASHZ663E2122B004271N\",\"Coord\":{\"LeftBottom\":{\"X\":863,\"Y\":290},\"LeftTop\":{\"X\":863,\"Y\":269},\"RightBottom\":{\"X\":1087,\"Y\":290},\"RightTop\":{\"X\":1087,\"Y\":269}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"被保险人\"},\"Value\":{\"AutoContent\":\"凡迎春\",\"Coord\":{\"LeftBottom\":{\"X\":150,\"Y\":349},\"LeftTop\":{\"X\":150,\"Y\":328},\"RightBottom\":{\"X\":206,\"Y\":349},\"RightTop\":{\"X\":206,\"Y\":328}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"手机号\"},\"Value\":{\"AutoContent\":\"135****5580\",\"Coord\":{\"LeftBottom\":{\"X\":839,\"Y\":349},\"LeftTop\":{\"X\":839,\"Y\":328},\"RightBottom\":{\"X\":941,\"Y\":349},\"RightTop\":{\"X\":941,\"Y\":328}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"证件类型\"},\"Value\":{\"AutoContent\":\"身份证\",\"Coord\":{\"LeftBottom\":{\"X\":150,\"Y\":379},\"LeftTop\":{\"X\":150,\"Y\":357},\"RightBottom\":{\"X\":206,\"Y\":379},\"RightTop\":{\"X\":206,\"Y\":357}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"证件号\"},\"Value\":{\"AutoContent\":\"41270219790603361X\",\"Coord\":{\"LeftBottom\":{\"X\":483,\"Y\":378},\"LeftTop\":{\"X\":483,\"Y\":359},\"RightBottom\":{\"X\":650,\"Y\":378},\"RightTop\":{\"X\":650,\"Y\":359}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"地址\"},\"Value\":{\"AutoContent\":\"广东省深圳\",\"Coord\":{\"LeftBottom\":{\"X\":820,\"Y\":379},\"LeftTop\":{\"X\":820,\"Y\":357},\"RightBottom\":{\"X\":914,\"Y\":379},\"RightTop\":{\"X\":914,\"Y\":357}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"车主\"},\"Value\":{\"AutoContent\":\"凡迎春\",\"Coord\":{\"LeftBottom\":{\"X\":112,\"Y\":410},\"LeftTop\":{\"X\":112,\"Y\":388},\"RightBottom\":{\"X\":172,\"Y\":410},\"RightTop\":{\"X\":172,\"Y\":388}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"投保人\"},\"Value\":{\"AutoContent\":\"凡迎春\",\"Coord\":{\"LeftBottom\":{\"X\":754,\"Y\":410},\"LeftTop\":{\"X\":754,\"Y\":388},\"RightBottom\":{\"X\":811,\"Y\":410},\"RightTop\":{\"X\":811,\"Y\":388}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况厂牌型号\"},\"Value\":{\"AutoContent\":\"比亚迪BYD7004BEV1纯电动轿车\",\"Coord\":{\"LeftBottom\":{\"X\":555,\"Y\":462},\"LeftTop\":{\"X\":555,\"Y\":418},\"RightBottom\":{\"X\":732,\"Y\":462},\"RightTop\":{\"X\":732,\"Y\":418}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况初次登记日期\"},\"Value\":{\"AutoContent\":\"2022年07月05日\",\"Coord\":{\"LeftBottom\":{\"X\":861,\"Y\":451},\"LeftTop\":{\"X\":861,\"Y\":431},\"RightBottom\":{\"X\":954,\"Y\":451},\"RightTop\":{\"X\":954,\"Y\":431}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况能源(燃料)种类\"},\"Value\":{\"AutoContent\":\"纯电动\",\"Coord\":{\"LeftBottom\":{\"X\":271,\"Y\":494},\"LeftTop\":{\"X\":271,\"Y\":470},\"RightBottom\":{\"X\":331,\"Y\":494},\"RightTop\":{\"X\":331,\"Y\":470}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况发动机号\"},\"Value\":{\"AutoContent\":\"1W2002238\",\"Coord\":{\"LeftBottom\":{\"X\":556,\"Y\":492},\"LeftTop\":{\"X\":556,\"Y\":472},\"RightBottom\":{\"X\":642,\"Y\":492},\"RightTop\":{\"X\":642,\"Y\":472}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况VIN码/车架号\"},\"Value\":{\"AutoContent\":\"LC0CE4CC3N0138981\",\"Coord\":{\"LeftBottom\":{\"X\":862,\"Y\":492},\"LeftTop\":{\"X\":862,\"Y\":472},\"RightBottom\":{\"X\":1017,\"Y\":492},\"RightTop\":{\"X\":1017,\"Y\":472}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况机动车种类\"},\"Value\":{\"AutoContent\":\"6座以下客车\",\"Coord\":{\"LeftBottom\":{\"X\":272,\"Y\":524},\"LeftTop\":{\"X\":272,\"Y\":501},\"RightBottom\":{\"X\":376,\"Y\":524},\"RightTop\":{\"X\":376,\"Y\":501}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况使用性质\"},\"Value\":{\"AutoContent\":\"家庭自用车\",\"Coord\":{\"LeftBottom\":{\"X\":553,\"Y\":524},\"LeftTop\":{\"X\":553,\"Y\":501},\"RightBottom\":{\"X\":646,\"Y\":524},\"RightTop\":{\"X\":646,\"Y\":501}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况核定载质量\"},\"Value\":{\"AutoContent\":\"0.00千克\",\"Coord\":{\"LeftBottom\":{\"X\":855,\"Y\":522},\"LeftTop\":{\"X\":855,\"Y\":501},\"RightBottom\":{\"X\":931,\"Y\":522},\"RightTop\":{\"X\":931,\"Y\":501}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"保险车辆情况核定载客\"},\"Value\":{\"AutoContent\":\"5人\",\"Coord\":{\"LeftBottom\":{\"X\":1057,\"Y\":522},\"LeftTop\":{\"X\":1057,\"Y\":501},\"RightBottom\":{\"X\":1091,\"Y\":522},\"RightTop\":{\"X\":1091,\"Y\":501}}}}]}]},{\"Groups\":[{\"Lines\":[{\"Key\":{\"AutoName\":\"绝对免赔额(元)\"},\"Value\":{\"AutoContent\":\"/\",\"Coord\":{\"LeftBottom\":{\"X\":288,\"Y\":1030},\"LeftTop\":{\"X\":288,\"Y\":1004},\"RightBottom\":{\"X\":309,\"Y\":1030},\"RightTop\":{\"X\":309,\"Y\":1004}}}}]}]}],\"WordList\":[]}}";

        JSONObject respBody = JSONObject.parseObject(res);
        JSONObject response = Optional.ofNullable(respBody)
            .map(json -> json.getJSONObject("Response"))
            .orElse(new JSONObject());

        // 获取保单信息
        structuralList = response.getJSONArray("StructuralList");
        if (ObjectUtil.isNotEmpty(structuralList)) {
            for (Object structural : structuralList) {
                JSONObject jsonData = JSONObject.parseObject(structural.toString());
                JSONArray Groups = jsonData.getJSONArray("Groups");
                if (ObjectUtil.isNotEmpty(Groups)) {
                    for (Object group : Groups) {
                        JSONObject jsonDataGroup = JSONObject.parseObject(group.toString());
                        JSONArray Lines = jsonDataGroup.getJSONArray("Lines");
                        if (ObjectUtil.isNotEmpty(Lines)) {
                            String InsuranceType = "";
                            BigDecimal InsuranceCoverage = BigDecimal.ZERO;
                            for (Object line : Lines) {
                                JSONObject jsonDataLine = JSONObject.parseObject(line.toString());
                                JSONObject guaranteeKey = jsonDataLine.getJSONObject("Key");
                                if (StrUtil.isNotEmpty(chnKey)) {
                                    JSONObject value = jsonDataLine.getJSONObject("Value");
                                    if ("被保险人".equals(chnKey) || "姓名".equals(
                                        chnKey) || "名称".equals(
                                        chnKey) || chnKey
                                        .contains("被保险人名称") || "被保险人：".equals(
                                        chnKey) || "姓名/名称：".equals(
                                        chnKey) || "被保险人信息姓名".equals(
                                        chnKey)) {
                                        applyGuarantee.setAssured(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("车主")) {
                                        applyGuarantee.setDrivingLicenseOwner(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("标题")) {
                                        applyGuarantee.setTitle(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("投保人")) {
                                        applyGuarantee.setApplicant(value.getString("AutoContent"));
                                    }
                                    if ((chnKey.contains("车架号"))
                                        || (chnKey.contains("VIN码"))
                                        || (chnKey.contains("车辆识别代码"))) {
                                        if (value.getString("AutoContent").contains("/")) {
                                            applyGuarantee.setVin(value.getString("AutoContent")
                                                .substring(0, value.getString("AutoContent").lastIndexOf("/")));
                                        } else {
                                            applyGuarantee.setVin(value.getString("AutoContent"));
                                        }
                                    }
                                    if (chnKey.contains("发动机号")) {
                                        applyGuarantee.setEngineNo(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("使用性质")) {
                                        applyGuarantee.setUseCharacter(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("承保险种")
                                        || chnKey.contains("投保险别")) {
                                        InsuranceType = value.getString("AutoContent");
                                    }
                                    if (chnKey.contains("保险金额/责任限额")
                                        || "务次数上限".equals(chnKey)) {
                                        Pattern pattern = Pattern.compile(
                                            "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])|([1-9]\\d*\\.?\\d*[\\u4e00-\\u9fa5])|(0\\.\\d*[1-9][\\u4e00-\\u9fa5])");
                                        Matcher isNum = pattern.matcher(value.getString("AutoContent"));
                                        if (isNum.matches()) {
                                            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                                            Matcher m = p.matcher(value.getString("AutoContent"));
                                            if (m.find()) {
                                                InsuranceCoverage = (new BigDecimal(value.getString("AutoContent")
                                                    .substring(0, value.getString("AutoContent").length() - 1)));
                                            } else {
                                                InsuranceCoverage = (new BigDecimal(value.getString("AutoContent")));
                                            }
                                        }
                                    }
                                    if ("新能源汽车损失保险".equals(InsuranceType) || "新能源车损失险".equals(
                                        InsuranceType) || "车损险".equals(InsuranceType) || "车辆损失险".equals(
                                        InsuranceType) || "机动车损失保险".equals(InsuranceType)
                                        || "新能源汽车损失保险条款(A)".equals(InsuranceType)
                                        || "新能源汽车损失保险可选绝对免赔额0元".equals(InsuranceType)) {
                                        applyGuarantee.setDamageCoverage((InsuranceCoverage));
                                    }
                                    if ("新能源汽车第三者责任保险".equals(InsuranceType) || "第三者责任保险".equals(
                                        InsuranceType) || "三者险".equals(InsuranceType) || "商业第三者责任保险".equals(
                                        InsuranceType) || "机动车第三者责任保险".equals(InsuranceType)
                                        || "新能源汽车第三者责任保险(B)".equals(InsuranceType)) {
                                        applyGuarantee.setThreeLiabilityInsurance((InsuranceCoverage));
                                    }
                                    if (chnKey.contains("保险期间")) {
                                        Matcher matcher = Pattern.compile("(\\d{4})年(\\d{1,2})月(\\d{1,2})日")
                                            .matcher(value.getString("AutoContent"));
                                        List<String> stringList = new ArrayList<>();
                                        while (matcher.find()) {
                                            stringList.add(matcher.group(0));
                                        }
                                        applyGuarantee.setPolicyDate(stringList.get(0));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (ObjectUtil.isNotEmpty(structuralList)) {
            applyGuarantee.setJsonData(structuralList.toJSONString());
        }
        applyGuarantee.setApplyNo("123456");
        if (StrUtil.isNotEmpty(applyGuarantee.getTitle()) && (applyGuarantee.getTitle().contains("商业保险")
            || applyGuarantee.getTitle().contains("神行车保新能源汽车保险单"))) {
            applyGuaranteeList.add(applyGuarantee);
        }

        System.out.println(applyGuaranteeList.size());

    }


    // 平安 中科软
    public static void z01() throws IOException {
        // D:\Rcar\BaiduSyncdisk\Rcar\验证\保单
        Path path = Paths.get("D:/Rcar/BaiduSyncdisk/Rcar/验证/保单/平安保险 中.txt");
        byte[] data = Files.readAllBytes(path);
        String res = new String(data, "utf-8");

        List<ApplyGuarantee> applyGuaranteeList = new ArrayList<>();

        JSONArray structuralList = null;
        ApplyGuarantee applyGuarantee = new ApplyGuarantee();
        JSONObject respBody = JSONObject.parseObject(res);
        JSONObject response = Optional.ofNullable(respBody)
            .map(json -> json.getJSONObject("Response"))
            .orElse(new JSONObject());

        // 获取保单信息
        JSONArray pageInfo = response.getJSONArray("PageInfo");
        JSONObject pages = pageInfo.getJSONObject(0);
        JSONArray result = pages.getJSONArray("Result");
        JSONObject results = result.getJSONObject(0);
        JSONArray resultList = results.getJSONArray("ResultList");
        JSONObject resultLists = resultList.getJSONObject(0);
        // DetailList   FieldList
        // 保险信息
        JSONArray detailLists = resultLists.getJSONArray("DetailList");
        JSONObject detailList = detailLists.getJSONObject(0);
        JSONArray detailData = detailList.getJSONArray("DetailData");
        // 键值信息
        JSONArray FieldList = resultLists.getJSONArray("FieldList");
        List<String> policyDate = new ArrayList<>();
        for (Object line : FieldList) {
            JSONObject jsonDataLine = JSONObject.parseObject(line.toString());
            String chnKey = String.valueOf(jsonDataLine.get("chn_key"));
            if (StrUtil.isNotEmpty(chnKey)) {
                if ("被保险人".equals(chnKey) || "姓名".equals(chnKey) || "名称".equals(chnKey) || chnKey.contains(
                    "被保险人名称") || "被保险人：".equals(chnKey) || "姓名/名称：".equals(chnKey)
                    || "被保险人信息姓名".equals(chnKey)) {
                    applyGuarantee.setAssured(String.valueOf(jsonDataLine.get("value")));
                }
                if (chnKey.contains("车主")) {
                    applyGuarantee.setDrivingLicenseOwner(String.valueOf(jsonDataLine.get("value")));
                }
                if (chnKey.contains("标题")) {
                    applyGuarantee.setTitle(String.valueOf(jsonDataLine.get("value")));
                }
                if (chnKey.contains("投保人")) {
                    applyGuarantee.setApplicant(String.valueOf(jsonDataLine.get("value")));
                }
                if (chnKey.contains("车架号") || chnKey.contains("VIN码") || chnKey.contains("车辆识别代码")) {
                    applyGuarantee.setVin(String.valueOf(jsonDataLine.get("value")));
                }
                if (chnKey.contains("发动机号")) {
                    applyGuarantee.setEngineNo(String.valueOf(jsonDataLine.get("value")));
                }
                if (chnKey.contains("使用性质")) {
                    applyGuarantee.setUseCharacter(String.valueOf(jsonDataLine.get("value")));
                }
            }
        }
        // 保险期间
        if (CollUtil.isNotEmpty(policyDate)) {
            StringBuilder policy = new StringBuilder();
            for (String a : policyDate) {
                policy.append(a);
            }
            applyGuarantee.setPolicyDate(String.valueOf(policy));
        }

        for (Object o : detailData) {
            String InsuranceType;
            JSONObject insurance = JSONObject.parseObject(o.toString());
            JSONArray insuranceInfo = insurance.getJSONArray("FieldList");
            for (Object insuranceInfoItem : insuranceInfo) {
                JSONObject item = JSONObject.parseObject(insuranceInfoItem.toString());
                String itemChnKey = item.get("chn_key").toString();
                if (itemChnKey.contains("承保险种") || itemChnKey.contains("投保险别")) {
                    InsuranceType = item.get("value").toString();
                }

                if (itemChnKey.contains("责任限额")
                    || "务次数上限".equals(chnKey)) {
                    Pattern pattern = Pattern.compile(
                        "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])|([1-9]\\d*\\.?\\d*[\\u4e00-\\u9fa5])|(0\\.\\d*[1-9][\\u4e00-\\u9fa5])");
                    Matcher isNum = pattern.matcher(value.getString("AutoContent"));
                    if (isNum.matches()) {
                        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                        Matcher m = p.matcher(value.getString("AutoContent"));
                        if (m.find()) {
                            InsuranceCoverage = (new BigDecimal(value.getString("AutoContent")
                                .substring(0, value.getString("AutoContent").length() - 1)));
                        } else {
                            InsuranceCoverage = (new BigDecimal(value.getString("AutoContent")));
                        }
                    }
                }
            }


            if ("新能源汽车损失保险".equals(InsuranceType) || "新能源车损失险".equals(
                InsuranceType) || "车损险".equals(InsuranceType) || "车辆损失险".equals(
                InsuranceType) || "机动车损失保险".equals(InsuranceType)
                || "新能源汽车损失保险条款(A)".equals(InsuranceType)
                || "新能源汽车损失保险可选绝对免赔额0元".equals(InsuranceType)) {
                applyGuarantee.setDamageCoverage((InsuranceCoverage));
            }
            if ("新能源汽车第三者责任保险".equals(InsuranceType) || "第三者责任保险".equals(
                InsuranceType) || "三者险".equals(InsuranceType) || "商业第三者责任保险".equals(
                InsuranceType) || "机动车第三者责任保险".equals(InsuranceType)
                || "新能源汽车第三者责任保险(B)".equals(InsuranceType)) {
                applyGuarantee.setThreeLiabilityInsurance((InsuranceCoverage));
            }
            if (chnKey.contains("保险期间")) {
                Matcher matcher = Pattern.compile("(\\d{4})年(\\d{1,2})月(\\d{1,2})日")
                    .matcher(value.getString("AutoContent"));
                List<String> stringList = new ArrayList<>();
                while (matcher.find()) {
                    stringList.add(matcher.group(0));
                }
                applyGuarantee.setPolicyDate(stringList.get(0));
            }
        }


        structuralList = response.getJSONArray("StructuralList");
        if (ObjectUtil.isNotEmpty(structuralList)) {
            for (Object structural : structuralList) {
                JSONObject jsonData = JSONObject.parseObject(structural.toString());
                JSONArray Groups = jsonData.getJSONArray("Groups");
                if (ObjectUtil.isNotEmpty(Groups)) {
                    for (Object group : Groups) {
                        JSONObject jsonDataGroup = JSONObject.parseObject(group.toString());
                        JSONArray Lines = jsonDataGroup.getJSONArray("Lines");
                        if (ObjectUtil.isNotEmpty(Lines)) {
                            String InsuranceType = "";
                            BigDecimal InsuranceCoverage = BigDecimal.ZERO;
                            for (Object line : Lines) {
                                JSONObject jsonDataLine = JSONObject.parseObject(line.toString());
                                    JSONObject guaranteeKey = jsonDataLine.getJSONObject("Key");
                                    if (StrUtil.isNotEmpty(chnKey)) {
                                        JSONObject value = jsonDataLine.getJSONObject("Value");
                                    if ("被保险人".equals(chnKey) || "姓名".equals(
                                        chnKey) || "名称".equals(
                                        chnKey) || chnKey
                                        .contains("被保险人名称") || "被保险人：".equals(
                                        chnKey) || "姓名/名称：".equals(
                                        chnKey) || "被保险人信息姓名".equals(
                                        chnKey)) {
                                        applyGuarantee.setAssured(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("车主")) {
                                        applyGuarantee.setDrivingLicenseOwner(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("标题")) {
                                        applyGuarantee.setTitle(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("投保人")) {
                                        applyGuarantee.setApplicant(value.getString("AutoContent"));
                                    }
                                    if ((chnKey.contains("车架号"))
                                        || (chnKey.contains("VIN码"))
                                        || (chnKey.contains("车辆识别代码"))) {
                                        if (value.getString("AutoContent").contains("/")) {
                                            applyGuarantee.setVin(value.getString("AutoContent")
                                                .substring(0, value.getString("AutoContent").lastIndexOf("/")));
                                        } else {
                                            applyGuarantee.setVin(value.getString("AutoContent"));
                                        }
                                    }
                                    if (chnKey.contains("发动机号")) {
                                        applyGuarantee.setEngineNo(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("使用性质")) {
                                        applyGuarantee.setUseCharacter(value.getString("AutoContent"));
                                    }
                                    if (chnKey.contains("承保险种")
                                        || chnKey.contains("投保险别")) {
                                        InsuranceType = value.getString("AutoContent");
                                    }
                                    if (chnKey.contains("保险金额/责任限额")
                                        || "务次数上限".equals(chnKey)) {
                                        Pattern pattern = Pattern.compile(
                                            "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])|([1-9]\\d*\\.?\\d*[\\u4e00-\\u9fa5])|(0\\.\\d*[1-9][\\u4e00-\\u9fa5])");
                                        Matcher isNum = pattern.matcher(value.getString("AutoContent"));
                                        if (isNum.matches()) {
                                            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                                            Matcher m = p.matcher(value.getString("AutoContent"));
                                            if (m.find()) {
                                                InsuranceCoverage = (new BigDecimal(value.getString("AutoContent")
                                                    .substring(0, value.getString("AutoContent").length() - 1)));
                                            } else {
                                                InsuranceCoverage = (new BigDecimal(value.getString("AutoContent")));
                                            }
                                        }
                                    }
                                    if ("新能源汽车损失保险".equals(InsuranceType) || "新能源车损失险".equals(
                                        InsuranceType) || "车损险".equals(InsuranceType) || "车辆损失险".equals(
                                        InsuranceType) || "机动车损失保险".equals(InsuranceType)
                                        || "新能源汽车损失保险条款(A)".equals(InsuranceType)
                                        || "新能源汽车损失保险可选绝对免赔额0元".equals(InsuranceType)) {
                                        applyGuarantee.setDamageCoverage((InsuranceCoverage));
                                    }
                                    if ("新能源汽车第三者责任保险".equals(InsuranceType) || "第三者责任保险".equals(
                                        InsuranceType) || "三者险".equals(InsuranceType) || "商业第三者责任保险".equals(
                                        InsuranceType) || "机动车第三者责任保险".equals(InsuranceType)
                                        || "新能源汽车第三者责任保险(B)".equals(InsuranceType)) {
                                        applyGuarantee.setThreeLiabilityInsurance((InsuranceCoverage));
                                    }
                                    if (chnKey.contains("保险期间")) {
                                        Matcher matcher = Pattern.compile("(\\d{4})年(\\d{1,2})月(\\d{1,2})日")
                                            .matcher(value.getString("AutoContent"));
                                        List<String> stringList = new ArrayList<>();
                                        while (matcher.find()) {
                                            stringList.add(matcher.group(0));
                                        }
                                        applyGuarantee.setPolicyDate(stringList.get(0));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (ObjectUtil.isNotEmpty(structuralList)) {
            applyGuarantee.setJsonData(structuralList.toJSONString());
        }
        applyGuarantee.setApplyNo("123456");
        if (StrUtil.isNotEmpty(applyGuarantee.getTitle()) && (applyGuarantee.getTitle().contains("商业保险")
            || applyGuarantee.getTitle().contains("神行车保新能源汽车保险单"))) {
            applyGuaranteeList.add(applyGuarantee);
        }

        System.out.println(applyGuaranteeList.size());

    }
}