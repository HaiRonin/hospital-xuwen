package com.ruoyi.his.remote.response.healthcard;

/**
 * Created by ASUS on 2019/1/25.
 */
public class HealthCardGetResponse extends HealthCardBaseResponse {

    /**
     * card : {"qrCodeText":"C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1","name":"张三","gender":"男","nation":"汉族","birthday":"1998-09-08","idNumber":"101102199809089988","idType":"01","address":"地址","phone1":"18808808808","phone2":"18808808808","phid":"36078220180818113110003XXXX","patid":"院内ID","healthCardId":"A4E0374BCBEAB40E4D66AB5078B44XXX","adminExt":"{\"qr_code\":\"C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1\",\"ecardId\":\"C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX\",\"main_index\":\"36078220180818113110003XXXX\",\"id_type\":\"居民身份证\",\"id_number\":\"101102199809089988\",\"name\":\"张三\",\"sex\":\"男\",\"birthday\":\"1998-09-08\",\"telephone\":\"18808808808\",\"nation\":\"汉族\",\"unit\":\"null\",\"address\":\"地址\"}"}
     */

    private CardBean card;

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public static class CardBean {
        /**
         * qrCodeText : C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1
         * name : 张三
         * gender : 男
         * nation : 汉族
         * birthday : 1998-09-08
         * idNumber : 101102199809089988
         * idType : 01
         * address : 地址
         * phone1 : 18808808808
         * phone2 : 18808808808
         * phid : 36078220180818113110003XXXX
         * patid : 院内ID
         * healthCardId : A4E0374BCBEAB40E4D66AB5078B44XXX
         * adminExt : {"qr_code":"C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX:1","ecardId":"C7DA29345B6DF90A6F5BBEBD73EBE2EDA26F341A6CFEEEB121XXX","main_index":"36078220180818113110003XXXX","id_type":"居民身份证","id_number":"101102199809089988","name":"张三","sex":"男","birthday":"1998-09-08","telephone":"18808808808","nation":"汉族","unit":"null","address":"地址"}
         */

        private String qrCodeText;
        private String name;
        private String gender;
        private String nation;
        private String birthday;
        private String idNumber;
        private String idType;
        private String address;
        private String phone1;
        private String phone2;
        private String phid;
        private String patid;
        private String healthCardId;
        private String adminExt;

        public String getQrCodeText() {
            return qrCodeText;
        }

        public void setQrCodeText(String qrCodeText) {
            this.qrCodeText = qrCodeText;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone1() {
            return phone1;
        }

        public void setPhone1(String phone1) {
            this.phone1 = phone1;
        }

        public String getPhone2() {
            return phone2;
        }

        public void setPhone2(String phone2) {
            this.phone2 = phone2;
        }

        public String getPhid() {
            return phid;
        }

        public void setPhid(String phid) {
            this.phid = phid;
        }

        public String getPatid() {
            return patid;
        }

        public void setPatid(String patid) {
            this.patid = patid;
        }

        public String getHealthCardId() {
            return healthCardId;
        }

        public void setHealthCardId(String healthCardId) {
            this.healthCardId = healthCardId;
        }

        public String getAdminExt() {
            return adminExt;
        }

        public void setAdminExt(String adminExt) {
            this.adminExt = adminExt;
        }
    }
}
