
<template>
    <div>
        <ul class="z-list">
            <li class="flex-box justify-s-b align-center z-l-item" @click="areaClick({})">
                <div>123</div>
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


    @Component
    export default class List extends Vue {
        @Inject('igIndex') readonly igIndex!: IOBJ;

        areaClick (item: IOBJ) {
            // console.log(this.igIndex);
            this.igIndex.openFun(item);
        }

        created () {
        }
    }
</script>

<style lang="scss" scoped>
</style>

