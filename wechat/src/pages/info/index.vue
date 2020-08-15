
<template>
    <div class="z-box">
        <div
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box align-center rel"
            @click="$router.push({name: item.name})"
        >
            <div class="flex-1 item-text-1">{{item.text}}</div>
            <van-icon name="arrow" class="item-icon" />
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';


    @Component
    export default class Index extends Vue {

        list: IOBJ[] = [
            {text: '医院简介', name: 'infoHospitalntroduction'},
            {text: '专家介绍', name: 'infoExpertIntroduction'},
            {text: '药品/非药品查询', name: 'infoDrugSearch'},
        ];

        created () {
        }
    }
</script>

<style lang="scss" scoped>
</style>

