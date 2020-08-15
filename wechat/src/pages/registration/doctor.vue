
<template>
    <div class="box">
        <div class="common-block common-block-1">
            <div class="flex-box info-box">
                <img
                    class="info-img"
                    :src="info.photoUrl || queryInfo.photoUrl || require('@/assets/image/d-male.png')"
                    alt
                />
                <div class="flex-1">
                    <div class="info-text-1">{{info.name || queryInfo.name}}</div>
                    <div class="info-text-2">{{info.job || queryInfo.job}}</div>
                </div>
            </div>
            <div class="info-text-3" v-show="info.ddesc">医生简介：{{info.ddesc}}</div>
        </div>

        <div class="common-block flex-box align-center tiem-box" v-if="reserveTimeList.length">
            <div
                v-for="(item, index) in reserveTimeList"
                :key="index"
                class="flex-1 tb-item"
                :class="{cur: dateText === item.date}"
                @click="getReserveTime(item.date)"
            >
                <div class="tb-text-1">{{item.week}}</div>
                <div class="tb-text-2">{{item.day}}</div>
            </div>
        </div>

        <div
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box align-center rel"
            v-show="item.show"
        >
            <div class="flex-1 item-text-1">{{item.text}}</div>
            <div class="flex-1">
                剩余
                <span class="num-text">{{item.num}}</span>个号
            </div>
            <div class="item-text-3">
                <span class="price-text">{{item.price}}</span>元
            </div>
            <div class="flex-1 flex-box align-center justify-end">
                {{item.disabled ? '不可挂号' : '可挂号'}}
                <van-icon name="arrow" class="item-icon" />
            </div>
            <div class="abs item-disabled" v-show="item.disabled"></div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Prop} from 'vue-property-decorator';


    @Component
    export default class Doctor extends Vue {
        @Prop() readonly departmentorganId!: string;

        dateText: string = '';
        toDay: boolean = true;
        info: IOBJ = {};
        queryInfo: IOBJ = {};
        reserveTimeList: IOBJ[] = [];
        list: IOBJ[] = [
            {show: true, num: 0, price: 0, disabled: true, key: 'morningNum', text: '上午'},
            {show: true, num: 0, price: 0, disabled: true, key: 'noonNum', text: '中午'},
            {show: true, num: 0, price: 0, disabled: true, key: 'afternoonNum', text: '下午'},
            {show: true, num: 0, price: 0, disabled: true, key: 'eveningNum', text: '晚上'},
            {show: true, num: 0, price: 0, disabled: true, key: 'earlymorningNum', text: '凌晨'},
        ];

        // 创建七天时间
        createSevenDay () {
            const date = new Date();
            // date.setDate();
            const arr = [];
            for (let i = 0; i < 7; i++) {
                date.setDate(date.getDate() + 1);
                const {y, m, d, week} = utils.dateData(date);
                arr.push({
                    date: `${y}-${m}-${d}`,
                    week,
                    day: d,
                });
            }
            // console.log(arr);
            this.reserveTimeList = arr;
            // this.dateText = arr[0].date;
            this.getReserveTime(arr[0].date);
        }

        // 获取医生信息
        getDoctorInfo () {
            // queryDoctorById
            // organdoctorId
            const res = {
                Doctor: [
                    {
                        'account': '248',
                        'name': '梁子兴',
                        'departmentorganId': '1002',
                        'ddesc': '儿科医疗组长，从事儿科工作十多年,曾先后到广州市儿童医院小儿外科专科进修，有丰富的临床实践经验熟练掌握小儿外科及儿科常见病、多发病、疑难病的诊治。',
                        'specialty': '',
                        'job': '副主任医师',
                        'specialtyName': '',
                        'treatdiseases': '',
                        'achievement': '',
                        'socialResponsibility': '',
                        'papersPublished': '',
                        'peopleHomepage': '',
                        'organdoctorId': '209',
                        'photoUrl': ''
                    }
                ]
            };

            this.info = (res.Doctor && res.Doctor[0]) || {};
        }

        // 根据医生查询号源
        getReserveTime (date?: string) {
            // querytoregdoctorListbydoctorid
            if (date) {
                this.dateText = date;
            } else {
                const dObj = new Date();
                const {y, m, d, week} = utils.dateData(dObj);
                date = `${y}-${m}-${d}`;
            }
            const res = {
                regDoctor: [
                    {
                        'organdoctorId': '209',
                        'departmentorganId': '1002',
                        'sourceDate': '2020-8-09',
                        'consultationFee': '15',
                        'morningNum': '18',
                        'afternoonNum': '11',
                        'eveningNum': '0',
                        'doctorName': '梁子兴',
                        'locationName': '儿科'
                    }
                ]
            };
            const data: IOBJ = res.regDoctor[0];
            this.list.forEach((item) => {
                const num: string | undefined = data[item.key];
                // debugger;
                if (!num) {
                    item.show = false;
                    return;
                }

                item.num = Math.max(+num, 0);
                item.disabled = item.num <= 0;
                item.price = data.consultationFee;
            });
        }

        created () {
            const {photoUrl, name, job, type} = this.$route.query;
            const toDay = this.toDay = (type || '') + '' === '0';
            // console.log(this.departmentorganId);

            this.$store.commit('upDataTitle', toDay ? '挂号列表' : '预约列表');
            this.queryInfo = {photoUrl, name, job};
            this.getDoctorInfo();
            toDay ? this.getReserveTime() : this.createSevenDay();
        }
    }
</script>

<style lang="scss" scoped>
    // .common-block-1 {
    //     margin-top: 1rem;
    // }
    .box {
        padding-top: 1rem;
    }
    .info-img {
        width: 4rem;
        height: 5rem;
        object-fit: cover;
        object-position: top;
        margin-right: 1rem;
    }
    .info-text-1 {
        font-size: 1.1rem;
    }
    .info-text-2 {
        color: $color-grey;
        margin-top: 0.4rem;
    }
    .info-text-3 {
        margin-top: 0.8rem;
        padding-top: 0.8rem;
        border-top: $border-line;
        color: $color-grey;
        font-size: 0.9rem;
        white-space: 1.5;
        line-height: 1.5;
    }

    // .item-text-1{width: 40%;}
    .item-text-3 {
        min-width: 3rem;
    }
    .num-text {
        // color: #07c160;
        color: $main-color;
    }
    .price-text {
        color: #ff976a;
    }
    .item-icon {
    }

    // .item-disabled{background: #999;}
    .item-disabled {
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        background: rgb(255 255 255 / 50%);
    }

    .tiem-box {
        text-align: center;
    }
    .tb-text-2 {
        width: 1.5rem;
        height: 1.5rem;
        line-height: 1.5rem;
        // background: #000;
        margin: 0.5rem auto 0;
        border-radius: 50%;
        color: $main-color;
    }
    .tb-item.cur .tb-text-2 {
        background: $main-cur-color;
        color: #fff;
        font-size: 0.8rem;
    }
</style>

