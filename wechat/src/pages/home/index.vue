
<template>
    <div>
        <keep-alive>
            <router-view />
        </keep-alive>
        <div style="height:3.125rem"></div>
        <van-tabbar v-model="active" @change="navChange">
            <van-tabbar-item v-for="item in tabNav" :key="item.name" :name="item.name">
                <span>{{item.text}}</span>
                <template #icon="props">
                    <img :src="props.active ? item.selIcon : item.icon" />
                </template>
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {Button, Tabbar, TabbarItem} from 'vant';
    Vue.use(Tabbar);
    Vue.use(TabbarItem);
    Vue.use(Button);

    @Component
    export default class Index extends Vue {
        active: string = '/outpatient';
        tabNav: IOBJ[] = [
            {
                text: '门诊',
                name: 'homeOutpatient',
                icon: require('@/assets/image/icon/icon_58.png'),
                selIcon: require('@/assets/image/icon/icon_60.png')
            },
            {
                text: '住院',
                name: 'homeInHospital',
                icon: require('@/assets/image/icon/icon_50.png'),
                selIcon: require('@/assets/image/icon/icon_54.png')
            },
            {
                text: '我的',
                name: 'homeMy',
                icon: require('@/assets/image/icon/icon_52.png'),
                selIcon: require('@/assets/image/icon/icon_56.png')
            }
        ];

        navChange (name: string) {
            // console.log(name);
            this.$router.replace({name});
        }

        initTab () {
            const {name} = this.$route;
            this.active = name as string;
        }

        created () {
            this.initTab();
        }
    }
</script>

