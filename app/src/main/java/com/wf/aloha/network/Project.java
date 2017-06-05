package com.wf.aloha.network;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfei on 2017/5/23.
 */

public class Project {

        public static final String STATUS_UPCOMING = "UPCOMING";
        public static final String STATUS_INVESTING = "INVESTING";
        public static final String STATUS_FULLBID = "FULLBID";
        public static final String STATUS_PAYBACK = "PAYBACK";
        public static final String STATUS_INVESTED = "INVESTED";
        public static final String STATUS_BADBID = "BADBID";

        public static final String REPAYWAY_AVG_CAPITAL_INTEREST = "AVG_CAPITAL_INTEREST";
        public static final String REPAYWAY_FIRST_INTEREST = "FIRST_INTEREST";
        public static final String REPAYWAY_ONCE = "ONCE";

        public static final String INTEREST_MODEL_BY_USER = "INTEREST_MODEL_BY_USER";
        public static final String INTEREST_MODEL_UNITED = "INTEREST_MODEL_UNITED";

        public static final String DUE_TIME_MONTH = "MONTH";
        public static final String DUE_TIME_DAY = "DAY";

        public static final String TYPE_1 = "应收账款";// 对应getLoanTypeList接口回参的id=2
        public static final String TYPE_2 = "企业信用贷款";// 对应getLoanTypeList接口回参的id=3
        public static final String TYPE_3 = "票据";// 对应getLoanTypeList接口回参的id=4
        public static final String TYPE_4 = "应收账款按天";// 对应getLoanTypeList接口回参的id=5
        public static final String TYPE_5 = "订单贷";// 对应getLoanTypeList接口回参的id=6

        /** base--getProjectList--start */

        /** 项目编号 */
        private String projectNo;
        /** 项目名 */
        private String name;
        /** 类型，如应收贷 */
        private String type;
        /** 投资总额，BigDecimal */
        private String totalAmount;
        /** 可投余额，BigDecimal，小数点后只有两位 */
        private String balance;
        /** 递增金额，BigDecimal */
        private String increaseAmt;
        /** 最小起投金额，BigDecimal */
        private String minInvestAmt;
        /** 最大起投金额，BigDecimal */
        private String maxInvestAmt;
        /** 年化利率，BigDecimal */
        private String ratePerYear;
        /** 活动加息利率，BigDecimal */
        private String awardRatePerYear;
        /** 周期，int */
        private String dueTime;
        /** 周期单位, DAY表示天，MONTH表示月 */
        private String dueTimeUnit;
        /** 状态, UPCOMING表示即将发布，INVESTING表示投标种，FULLBID表示满标，PAYBACK表示还款中，INVESTED表示还款完成，BADBID表示流标 */
        private String status;
        /** 计息方式, INTEREST_MODEL_BY_USER表示投标当日计息，INTEREST_MODEL_UNITED表示放款次日计息 */
        private String interestModel;
        /** 收益方式, AVG_CAPITAL_INTEREST表示等额本息，FIRST_INTEREST表示按月付息, 到期还本，ONCE表示到期还本付息 */
        private String repayWay;
        /** 描述，可空 */
        private String description;

        /** 发标时间, 即投标开始时间 */
        private JSONObject pubTime;
        /** 满标时间，可空 */
        private JSONObject saleOutTime;

        /** 借款企业--债权出让方 */
        private String creditorName;
        /** 还款企业--债务还款方 */
        private String loanName;
        /** 承付企业--承付方可能是核心企业(该文本)或股东(右标签文本为股东)或空(整个条不显示了)，可空 */
        private String coreName;

        /** 项目类型id，long */
        private String loanTypeId;
        /** 项目借款人id，long */
        private String loanUserId;

        /** 总还款期数（只有在还款中的和还款完成状态下，值才是对的），int */
        private String totalReturnNum;
        /** 已还款期数，int */
        private String returnedNum;
        /** 成功投资人数 */
        private int successInvestCount;
        /** 项目标签 */
        private JSONArray projectLabelList;

        /** DKD_TYPE(0, "道口贷"),
         DKJH_TYPE(1, "道口计划"),
         DKBL_TYPE(2, "道口保理"),
         SHKX_TYPE(3, "上行快线"),//用来标记提供给上行快线的项目，项目是在上行快线平台上销售
         SHKX_VIP_TYPE(4, "上行快线专享");//用来标记只有上行快线的用户可以投资，在道口贷平台上销售的项目 */
        private String investProjectType;

        /** base--getProjectList--end */


        /** extra--getProjectDetail--start */

        /** 资金用途，可空 */
        private String fundsUse;
        /** 还款来源，可空 */
        private String repayment;
        /** 风险控制，可空 */
        private String riskControl;
        /** 商业承兑汇票号，可空 */
        private String tradeAcceptNum;
        /** 采购合同的url，可空 */
        private String purchaseContract;
        /** 票面金额，BigDecimal，可空 */
        private String faceAmount;
        /** 相关图片 过时 */
        private String relatedMaterialPic;
        /** 承诺书图片 */
        private List<Material> promisePicList;
        /** 相关图片 */
        private List<Material> relatedMaterialPicList;

        /** 出票日期，可空 */
        private JSONObject dateOfIssue;

        /** 借款企业id，long */
        private String creditorEnterpriseId;
        /** 还款企业id，long */
        private String loanEnterpriseId;
        /** 核心企业id，long，可空 */
        private String coreEnterpriseId;


        /** extra--getProjectDetail--end */

        public static Project fromJsonForProjectStatic(JSONObject jsonObj) {
            Project project = new Project();
            if (jsonObj == null) {
                return project;
            }
            try {
                project.projectNo = jsonObj.optString("projectNo");
                project.dueTime = jsonObj.getString("dueTime");
                project.dueTimeUnit = jsonObj.getString("dueTimeUnit");
                project.totalAmount = jsonObj.getString("totalAmount");
                project.name = jsonObj.getString("name");
                project.creditorName = jsonObj.getString("creditorName");
                project.loanName = jsonObj.getString("loanName");
                project.coreName = jsonObj.optString("coreName");
                project.repayWay = jsonObj.optString("repayWay");
                project.interestModel = jsonObj.getString("interestModel");
                project.description = jsonObj.optString("description");
                project.type = jsonObj.getString("type");
                project.ratePerYear = jsonObj.optString("ratePerYear");
                project.increaseAmt = jsonObj.optString("increaseAmt", "100");
                project.minInvestAmt = jsonObj.optString("minInvestAmt");
                project.maxInvestAmt = jsonObj.optString("maxInvestAmt");
                project.investProjectType = jsonObj.optString("investProjectType");
            } catch (JSONException e) {
                e.printStackTrace();
                project = null;
            } finally {
                return project;
            }
        }

        public static Project fromJsonForProjectDynamic(JSONObject jsonObj) {
            Project project = new Project();
            if (jsonObj == null) {
                return project;
            }
            try {
                project.pubTime = jsonObj.getJSONObject("pubTime");
                project.balance = jsonObj.getString("balance");
                project.status = jsonObj.getString("status");
            } catch (JSONException e) {
                e.printStackTrace();
                project = null;
            } finally {
                return project;
            }
        }

        /**
         * 将一个项目基础信息的json字符串转化为一个Project对象。
         *
         * @param jsonObj
         */
        public static Project fromJSONForBase(JSONObject jsonObj) {
            Project project = new Project();
            try {
                if (jsonObj != null) {
                    project.projectNo = jsonObj.optString("projectNo");
                    project.name = jsonObj.getString("name");
                    project.totalAmount = jsonObj.getString("totalAmount");
                    project.balance = jsonObj.getString("balance");
                    project.ratePerYear = jsonObj.getString("ratePerYear");
                    project.awardRatePerYear = jsonObj.optString("awardRatePerYear");
                    project.dueTime = jsonObj.getString("dueTime");
                    project.dueTimeUnit = jsonObj.getString("dueTimeUnit");
                    project.increaseAmt = jsonObj.optString("increaseAmt", "100");
                    project.minInvestAmt = jsonObj.getString("minInvestAmt");
                    project.maxInvestAmt = jsonObj.getString("maxInvestAmt");
                    project.status = jsonObj.getString("status");
                    project.interestModel = jsonObj.getString("interestModel");
                    project.repayWay = jsonObj.optString("repayWay");
                    project.totalReturnNum = jsonObj.getString("totalReturnNum");
                    project.returnedNum = jsonObj.getString("returnedNum");
                    project.description = jsonObj.optString("description");
                    project.pubTime = jsonObj.getJSONObject("pubTime");
                    project.saleOutTime = jsonObj.optJSONObject("saleOutTime");
                    project.type = jsonObj.getString("type");
                    project.creditorName = jsonObj.getString("creditorName");
                    project.loanName = jsonObj.getString("loanName");
                    project.coreName = jsonObj.optString("coreName");
                    project.loanTypeId = jsonObj.getString("loanTypeId");
                    project.loanUserId = jsonObj.getString("loanUserId");

                    project.successInvestCount = jsonObj.optInt("successInvestCount");
                    project.projectLabelList = jsonObj.optJSONArray("projectLabelList");
                } else {
                    project = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                project = null;
            } finally {
                return project;
            }
        }

        /**
         * 将一个项目额外信息的json字符串转化为一个Project对象。
         *
         * @param jsonObj
         */
        public static Project fromJSONForExtra(JSONObject jsonObj) {
            Project project = new Project();
            try {
                if (jsonObj != null) {
                    project.projectNo = jsonObj.getString("projectNo");
                    project.fundsUse = jsonObj.optString("fundsUse");
                    project.riskControl = jsonObj.optString("riskControl");
                    project.repayment = jsonObj.optString("repayment");
                    project.tradeAcceptNum = jsonObj.optString("tradeAcceptNum");
                    project.purchaseContract = jsonObj.optString("purchaseContract");
                    project.faceAmount = jsonObj.optString("faceAmount");
                    project.creditorEnterpriseId = jsonObj.getString("creditorEnterpriseId");
                    project.loanEnterpriseId = jsonObj.getString("loanEnterpriseId");
                    project.coreEnterpriseId = jsonObj.optString("coreEnterpriseId");

                    project.dateOfIssue = jsonObj.optJSONObject("dateOfIssue");

                    project.promisePicList = new ArrayList<Material>();
                    JSONArray picArr = jsonObj.optJSONArray("promisePicList");
                    if (picArr != null) {
                        for (int i = 0; i < picArr.length(); i++) {
                            Material m = Material.parseJSON(picArr.getJSONObject(i));
                            if (m != null) {
                                project.promisePicList.add(m);
                            }
                        }
                    }
                    project.relatedMaterialPicList = new ArrayList<Material>();
                    JSONArray materialArr = jsonObj.optJSONArray("relatedMaterialPicList");
                    if (materialArr != null) {
                        for (int i = 0; i < materialArr.length(); i++) {
                            Material m = Material.parseJSON(materialArr.getJSONObject(i));
                            if (m != null) {
                                project.relatedMaterialPicList.add(m);
                            }
                        }
                    }
                } else {
                    project = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                project = null;
            } finally {
                return project;
            }
        }

        /**
         * 将一个项目额外信息的json字符串转化为一个Project对象。
         *
         * @param jsonObj
         */
        public static Project fromJsonForRisk(JSONObject jsonObj) {
            Project project = new Project();
            try {
                if (jsonObj != null) {
                    project.repayment = jsonObj.optString("repayment");
                    project.riskControl = jsonObj.optString("riskControl");

                    project.promisePicList = Material.fromJson(jsonObj.optJSONArray("promisePicList"));

                    project.relatedMaterialPicList = Material.fromJson(jsonObj.optJSONArray("relatedMaterialPicList"));
                } else {
                    project = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                project = null;
            } finally {
                return project;
            }
        }



        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getTotalAmount() {
            return totalAmount;
        }


        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }


        public String getBalance() {
            return balance;
        }


        public void setBalance(String balance) {
            this.balance = balance;
        }


        public String getRatePerYear() {
            return ratePerYear;
        }


        public void setRatePerYear(String ratePerYear) {
            this.ratePerYear = ratePerYear;
        }


        public String getDueTime() {
            return dueTime;
        }


        public void setDueTime(String dueTime) {
            this.dueTime = dueTime;
        }


        public String getDueTimeUnit() {
            return dueTimeUnit;
        }


        public void setDueTimeUnit(String dueTimeUnit) {
            this.dueTimeUnit = dueTimeUnit;
        }

        public String getIncreaseAmt() {
            return increaseAmt;
        }

        public void setIncreaseAmt(String increaseAmt) {
            this.increaseAmt = increaseAmt;
        }

        public String getMinInvestAmt() {
            return minInvestAmt;
        }


        public void setMinInvestAmt(String minInvestAmt) {
            this.minInvestAmt = minInvestAmt;
        }


        public String getMaxInvestAmt() {
            return maxInvestAmt;
        }


        public void setMaxInvestAmt(String maxInvestAmt) {
            this.maxInvestAmt = maxInvestAmt;
        }


        public JSONObject getPubTime() {
            return pubTime;
        }



        public String getStatus() {
            return status;
        }


        public void setStatus(String status) {
            this.status = status;
        }


        public String getProjectNo() {
            return projectNo;
        }


        public String getInterestModel() {
            return interestModel;
        }

        public String getRepayWay() {
            return repayWay;
        }


        public String getDescription() {
            return description;
        }


        public String getType() {
            return type;
        }


        public JSONObject getSaleOutTime() {
            return saleOutTime;
        }

        public String getCreditorName() {
            return creditorName;
        }

        public String getLoanName() {
            return loanName;
        }

        public String getCoreName() {
            return coreName;
        }

        public String getLoanTypeId() {
            return loanTypeId;
        }

        public String getLoanUserId() {
            return loanUserId;
        }

        public String getTotalReturnNum() {
            return totalReturnNum;
        }

        public String getReturnedNum() {
            return returnedNum;
        }

        public String getFundsUse() {
            return fundsUse;
        }


        public String getTradeAcceptNum() {
            return tradeAcceptNum;
        }


        public String getRepayment() {
            return repayment;
        }


        public String getRiskControl() {
            return riskControl;
        }


        public String getRelatedMaterialPic() {
            return relatedMaterialPic;
        }

        public String getPurchaseContract() {
            return purchaseContract;
        }


        public String getFaceAmount() {
            return faceAmount;
        }


        public List<Material> getPromisePicList() {
            return promisePicList;
        }

        public List<Material> getRelatedMaterialPicList() {
            return relatedMaterialPicList;
        }

        public JSONObject getDateOfIssue() {
            return dateOfIssue;
        }

        public String getCreditorEnterpriseId() {
            return creditorEnterpriseId;
        }

        public String getLoanEnterpriseId() {
            return loanEnterpriseId;
        }

        public String getCoreEnterpriseId() {
            return coreEnterpriseId;
        }

        public int getSuccessInvestCount() {
            return successInvestCount;
        }

        public void setSuccessInvestCount(int successInvestCount) {
            this.successInvestCount = successInvestCount;
        }

        public JSONArray getProjectLabelList() {
            return projectLabelList;
        }

        public void setProjectLabelList(JSONArray projectLabelList) {
            this.projectLabelList = projectLabelList;
        }

        public String getAwardRatePerYear() {
            return awardRatePerYear;
        }

        public void setAwardRatePerYear(String awardRatePerYear) {
            this.awardRatePerYear = awardRatePerYear;
        }

        public String getInvestProjectType() {
            return investProjectType;
        }

        public String getRepayWayCode(){
            if("AVG_CAPITAL_INTEREST".equals(repayWay)) return "0";
            if("FIRST_INTEREST".equals(repayWay)) return "1";
            if("ONCE".equals(repayWay)) return "2";
            return repayWay;
        }

        public String getRepayWayStr() {
            if (TextUtils.equals(repayWay, "AVG_CAPITAL_INTEREST")) {
                return "等额本息";
            } else if (TextUtils.equals(repayWay, "FIRST_INTEREST")) {
                return "按月付息, 到期还本";
            } else {
                return "到期还本付息";
            }
        }

        public String getInterestModelStr() {
            if (TextUtils.equals(interestModel, "INTEREST_MODEL_BY_USER")) {
                return "投标当日计息";
            } else if (TextUtils.equals(interestModel, "INTEREST_MODEL_UNITED")) {
                return "放款次日计息";
            } else {
                return "投标当日计息";
            }
        }

        public boolean canInvest(){
            double balanceD = 0;
            try {
                balanceD = Double.parseDouble(balance);
            } catch (Exception e) { }
            return "INVESTING".equals(status) &&  balanceD> 0;
        }
}
