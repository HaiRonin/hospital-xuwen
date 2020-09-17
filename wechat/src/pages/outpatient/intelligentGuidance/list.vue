
<template>
    <div>
        <ul class="z-list">
            <li
                class="flex-box justify-s-b align-center z-l-item"
                v-for="(item, index) in list"
                :key="index"
                @click="areaClick({bodyPart: item.bodyPart})"
            >
                <div>{{item.bodyPart}}</div>
                <div></div>
            </li>
            <!-- <li class="flex-box justify-s-b align-center z-l-item">
                <div>123</div>
                <div></div>
            </li> -->
        </ul>
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Inject} from 'vue-property-decorator';
    import {getBodyListPart} from '@/apis';

    @Component
    export default class List extends Vue {
        @Inject('igIndex') readonly igIndex!: IOBJ;

        list: IOBJ[] = [];

        areaClick (item: IOBJ) {
            // console.log(this.igIndex);
            this.igIndex.openFun(item);
        }

        async getList () {
            // 调用父iframe
            const ilgObj = (window as any).parent.intelligentGuidance;
            if (ilgObj && ilgObj.getBodyListPart) {
                ilgObj.getBodyListPart((res: IOBJ) => {
                    this.list = res.data;
                });
                return;
            }



            const res = await getBodyListPart();
            this.list = res.data;
        }

        created () {
            this.getList();
        }
    }
</script>

<style lang="scss" scoped>
</style>

