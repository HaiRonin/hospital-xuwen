<template>
    <view class="box">
        <u-form :model="params" ref="uForm" label-width="200rpx" class="form common-block">
            <u-form-item label="姓名">
                <u-input v-model="params.name" maxlength="8" placeholder="请输入就诊人姓名" />
            </u-form-item>
            <u-form-item label="性别">
                <u-radio-group v-model="params.gender">
                    <u-radio v-for="(item, index) in genderItems" :key="index" :name="item.label" >
                        {{item.label}}
                    </u-radio>
                </u-radio-group>
            </u-form-item>
            <u-form-item label="民族">
                <!-- <u-input :value="params.nation" @tap="nationShow = true" type="input" :disabled="true" placeholder="请选择" /> -->
                <picker mode="selector" :range="nationSort" range-key="label" class="picker-input" @change="bindDateChange($event, 'nation')">
                    <view class="picker-input" v-if="params.nation">{{params.nation}}</view>
                    <view class="picker-input p-i-color" v-else>请选择</view>
                </picker>
            </u-form-item>
            <u-form-item label="出生日期">
                <!-- <u-input v-model="params.birthday" placeholder="请输入就诊人姓名" /> -->
                <picker mode="date" class="picker-input" @change="bindDateChange($event, 'birthday')">
                    <view class="picker-input" v-if="params.birthday">{{params.birthday}}</view>
                    <view class="picker-input p-i-color" v-else>请选择</view>
                </picker>
            </u-form-item>
            <!-- <u-form-item label="证件类型">
                <u-input :value="params.idType" @tap="idTypeShow = true" type="input" :disabled="true" placeholder="请选择" />
            </u-form-item> -->
            <u-form-item label="身份证号码">
                <u-input v-model="params.idNumber" maxlength="20" placeholder="请输入就诊人身份证号码" />
            </u-form-item>
        </u-form>

        <!-- <u-select v-model="nationShow" :list="nationSort" @confirm="selectChange($event, 'nation')"></u-select> -->
        <!-- <u-select v-model="idTypeShow" :list="cardTypeItems" @confirm="selectChange($event, 'idType')"></u-select> -->
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Prop, Watch} from 'vue-property-decorator';
    import {nationItems, genderItems, cardTypeItems} from './otherSort';

    // 获取身份证上的出身日期
    const getBirthdayFromIdCard = (idCard: string) => {
        var birthday = '';
        if (idCard.length === 15) {
            birthday = '19' + idCard.substr(6, 6);
        } else if (idCard.length === 18) {
            birthday = idCard.substr(6, 8);
        }

        birthday = birthday.replace(/(.{4})(.{2})/, '$1-$2-');

        return birthday;
    };

    @Component
    export default class InView extends Vue {
        @Prop({type: Object}) readonly patientData!: IOBJ;

        nationShow = false;
        idTypeShow = false;
        nationSort = nationItems;
        genderItems = genderItems;
        cardTypeItems = cardTypeItems;
        date = [];
        params: IOBJ = {
            gender: '男',
            birthday: '',
            idType: '01',
            nation: '',
            idNumber: '',
            name: ''
        };

        @Watch('patientData')
        handler (item: IOBJ | null) {
            if (!item) return;
            const params = this.params;
            params.gender = ['男', '女'].includes(item.Sex) ? item.Sex : '男';
            params.idNumber = item.IDCardno;
            params.name = item.Name;
            params.birthday = getBirthdayFromIdCard(item.IDCardno);
            console.log(item);
        }

        selectChange (e: IOBJ, key: string) {
            console.log(e, key);
            const params = this.params;
            params[key] = e[0].label;
            // console.log(params[key]);
        }

        bindDateChange (e: IOBJ, key: string) {
            console.log(e, key);
            const nationSort = this.nationSort;
            const val = e.detail.value;
            switch (key) {
                case 'nation':
                    this.params.nation = nationSort[val].label;
                    break;
                case 'birthday':
                    this.params.birthday = val;
                    break;
            }
        }

        // getParams () {
        //     return this.params;
        // }

        created () {
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box{
        font-size: 28rpx;
    }
    .form {
        // background: #fff;
        // margin: 20rpx;
        padding: 0 30rpx;
    }

    .picker-input{
        width: 100%;
    }

    .p-i-color{
        color: #c0c4cc;
    }

</style>
