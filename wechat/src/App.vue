<template>
    <div id="app">
        <router-view />
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Prop} from 'vue-property-decorator';

    @Component({})
    export default class Layout extends Vue {
    }
</script>

<style lang="scss" scoped>
</style>
